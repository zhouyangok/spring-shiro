package com.wangzhixuan.commons.ueditor;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.wangzhixuan.commons.ueditor.define.ActionMap;
import com.wangzhixuan.commons.ueditor.manager.DefaultFileManager;
import com.wangzhixuan.commons.ueditor.manager.IUeditorFileManager;
import com.wangzhixuan.commons.utils.Charsets;
import com.wangzhixuan.commons.utils.IOUtils;
import com.wangzhixuan.commons.utils.JsonUtils;

/**
 * Ueditor配置
 * 
 * @author L.cm
 *
 */
public class UeditorManager implements InitializingBean {
	private static final String CONFIG_FILE_JSON = "/ueditor.config.json";
	private UeditorConfig jsonConfig = null;

	private IUeditorFileManager fileManager;
	
	public UeditorManager() {
		fileManager = new DefaultFileManager();
	}

	public IUeditorFileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(IUeditorFileManager fileManager) {
		this.fileManager = fileManager;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		InputStream input = UeditorManager.class.getResourceAsStream(CONFIG_FILE_JSON);
		Assert.notNull(input, "can't find ueditor.config.json");
		try {
			String configContent = IOUtils.copyToString(input, Charsets.UTF_8);
			jsonConfig = JsonUtils.parse(filter(configContent), UeditorConfig.class);
		} finally {
			IOUtils.closeQuietly(input);
		}
	}
	
	// 验证配置文件加载是否正确
	public boolean valid() {
		return jsonConfig != null;
	}

	public UeditorConfig getConfig() {
		return jsonConfig;
	}

	public Map<String, Object> getConfig(int type, String rootPath) {
		Map<String, Object> conf = new HashMap<String, Object>();
		String savePath = null;
		switch (type) {
		case ActionMap.UPLOAD_FILE:
			conf.put("isBase64", "false");
			conf.put("maxSize", jsonConfig.getFileMaxSize());
			conf.put("allowFiles", jsonConfig.getFileAllowFiles());
			conf.put("fieldName", jsonConfig.getFileFieldName());
			savePath = jsonConfig.getFilePathFormat();
			break;

		case ActionMap.UPLOAD_IMAGE:
			conf.put("isBase64", "false");
			conf.put("maxSize", jsonConfig.getImageMaxSize());
			conf.put("allowFiles", jsonConfig.getImageAllowFiles());
			conf.put("fieldName", jsonConfig.getImageFieldName());
			savePath = jsonConfig.getImagePathFormat();
			break;

		case ActionMap.UPLOAD_VIDEO:
			conf.put("maxSize", jsonConfig.getVideoMaxSize());
			conf.put("allowFiles", jsonConfig.getVideoAllowFiles());
			conf.put("fieldName", jsonConfig.getVideoFieldName());
			savePath = jsonConfig.getVideoPathFormat();
			break;

		case ActionMap.UPLOAD_SCRAWL:
			conf.put("filename", "scrawl");
			conf.put("maxSize", jsonConfig.getScrawlMaxSize());
			conf.put("fieldName", jsonConfig.getScrawlFieldName());
			conf.put("isBase64", "true");
			savePath = jsonConfig.getScrawlPathFormat();
			break;

		case ActionMap.CATCH_IMAGE:
			conf.put("filename", "remote");
			conf.put("filter", jsonConfig.getCatcherLocalDomain());
			conf.put("maxSize", jsonConfig.getCatcherMaxSize());
			conf.put("allowFiles", jsonConfig.getCatcherAllowFiles());
			conf.put("fieldName", jsonConfig.getCatcherFieldName() + "[]");
			savePath = jsonConfig.getCatcherPathFormat();
			break;

		case ActionMap.LIST_IMAGE:
			conf.put("allowFiles", jsonConfig.getImageManagerAllowFiles());
			conf.put("dir", jsonConfig.getImageManagerListPath());
			conf.put("count", jsonConfig.getImageManagerListSize());
			break;

		case ActionMap.LIST_FILE:
			conf.put("allowFiles", jsonConfig.getFileManagerAllowFiles());
			conf.put("dir", jsonConfig.getFileManagerListPath());
			conf.put("count", jsonConfig.getFileManagerListSize());
			break;
		}
		conf.put("savePath", savePath);
		conf.put("rootPath", rootPath);
		return conf;
	}
	
	/**
	 * 过滤json, 剔除多行注释以及替换掉反斜杠
	 */
	private static String filter(String input) {
		return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");
	}
}
