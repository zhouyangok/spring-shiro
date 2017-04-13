package com.wangzhixuan.commons.ueditor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.wangzhixuan.commons.ueditor.define.ActionMap;
import com.wangzhixuan.commons.ueditor.define.AppInfo;
import com.wangzhixuan.commons.ueditor.define.BaseState;
import com.wangzhixuan.commons.ueditor.define.State;
import com.wangzhixuan.commons.ueditor.hunter.ImageHunter;
import com.wangzhixuan.commons.ueditor.manager.IUeditorFileManager;
import com.wangzhixuan.commons.ueditor.upload.Uploader;
import com.wangzhixuan.commons.utils.JsonUtils;

public class UeditorService {
	@Autowired 
	private UeditorManager ueditorManager;
	
	public String exec(HttpServletRequest request) {
		String callbackName = request.getParameter("callback");
		if (callbackName != null) {
			if (!validCallbackName(callbackName)) {
				return new BaseState(false, AppInfo.ILLEGAL).toJSONString();
			}
			return callbackName + "(" + invoke(request) + ");";
		} else {
			return invoke(request);
		}
	}

	private String invoke(HttpServletRequest request) {
		String actionType = request.getParameter("action");
		@SuppressWarnings("deprecation")
		String rootPath = request.getRealPath("/");
		String ctxPath  = request.getContextPath();
		
		if (actionType == null || !ActionMap.mapping.containsKey(actionType)) {
			return new BaseState(false, AppInfo.INVALID_ACTION).toJSONString();
		}
		if (ueditorManager == null || !ueditorManager.valid()) {
			return new BaseState(false, AppInfo.CONFIG_ERROR).toJSONString();
		}

		IUeditorFileManager fileManager = ueditorManager.getFileManager();
		
		State state = null;
		int actionCode = ActionMap.getType(actionType);
		Map<String, Object> conf = null;

		switch (actionCode) {

		case ActionMap.CONFIG:
			UeditorConfig allConfig = ueditorManager.getConfig();
			String imageUrlPrefix = allConfig.getImageUrlPrefix();
			String scrawlUrlPrefix = allConfig.getScrawlUrlPrefix();
			String snapscreenUrlPrefix = allConfig.getSnapscreenUrlPrefix();
			String catcherUrlPrefix = allConfig.getCatcherUrlPrefix();
			String videoUrlPrefix = allConfig.getVideoUrlPrefix();
			String fileUrlPrefix = allConfig.getFileUrlPrefix();
			String imageManagerUrlPrefix = allConfig.getImageManagerUrlPrefix();
			String fileManagerUrlPrefix = allConfig.getFileManagerUrlPrefix();

			if (null == imageUrlPrefix || "".equals(imageUrlPrefix.trim())) {
				allConfig.setImageUrlPrefix(ctxPath);
			}
			if (null == scrawlUrlPrefix || "".equals(scrawlUrlPrefix.trim())) {
				allConfig.setScrawlUrlPrefix(ctxPath);
			}
			if (null == snapscreenUrlPrefix || "".equals(snapscreenUrlPrefix.trim())) {
				allConfig.setSnapscreenUrlPrefix(ctxPath);
			}
			if (null == catcherUrlPrefix || "".equals(catcherUrlPrefix.trim())) {
				allConfig.setCatcherUrlPrefix(ctxPath);
			}
			if (null == videoUrlPrefix || "".equals(videoUrlPrefix.trim())) {
				allConfig.setVideoUrlPrefix(ctxPath);
			}
			if (null == fileUrlPrefix || "".equals(fileUrlPrefix.trim())) {
				allConfig.setFileUrlPrefix(ctxPath);
			}
			if (null == imageManagerUrlPrefix || "".equals(imageManagerUrlPrefix.trim())) {
				allConfig.setImageManagerUrlPrefix(ctxPath);
			}
			if (null == fileManagerUrlPrefix || "".equals(fileManagerUrlPrefix.trim())) {
				allConfig.setFileManagerUrlPrefix(ctxPath);
			}

			return JsonUtils.toJson(allConfig);

		case ActionMap.UPLOAD_IMAGE:
		case ActionMap.UPLOAD_SCRAWL:
		case ActionMap.UPLOAD_VIDEO:
		case ActionMap.UPLOAD_FILE:
			conf = ueditorManager.getConfig(actionCode, rootPath);
			state = new Uploader(request, conf).doExec(fileManager);
			break;

		case ActionMap.CATCH_IMAGE:
			conf = ueditorManager.getConfig(actionCode, rootPath);
			String[] list = request.getParameterValues((String) conf.get("fieldName"));
			state = new ImageHunter(fileManager, conf).capture(list);
			break;

		case ActionMap.LIST_IMAGE:
		case ActionMap.LIST_FILE:
			conf = ueditorManager.getConfig(actionCode, rootPath);
			int start = getStartIndex(request);
			state = fileManager.list(conf, start);
			break;

		}
		return state.toJSONString();
	}

	public int getStartIndex(HttpServletRequest request) {
		String start = request.getParameter("start");
		try {
			return Integer.parseInt(start);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * callback参数验证
	 * @param name 参数名
	 * @return boolean 是否校验通过
	 */
	public boolean validCallbackName(String name) {
		return name.matches("^[a-zA-Z_]+[\\w0-9_]*$");
	}
}
