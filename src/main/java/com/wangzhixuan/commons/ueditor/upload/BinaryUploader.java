package com.wangzhixuan.commons.ueditor.upload;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wangzhixuan.commons.ueditor.PathFormat;
import com.wangzhixuan.commons.ueditor.define.AppInfo;
import com.wangzhixuan.commons.ueditor.define.BaseState;
import com.wangzhixuan.commons.ueditor.define.FileType;
import com.wangzhixuan.commons.ueditor.define.State;
import com.wangzhixuan.commons.ueditor.manager.IUeditorFileManager;
import com.wangzhixuan.commons.utils.IOUtils;

public class BinaryUploader {

	@SuppressWarnings("unchecked")
	public static final State save(IUeditorFileManager fileManager, HttpServletRequest request,
			Map<String, Object> conf) {
		
		if (!(request instanceof MultipartHttpServletRequest)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		InputStream is = null;
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile("upfile");

			String savePath = (String) conf.get("savePath");
			String originFileName = file.getOriginalFilename();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (List<String>) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}

			savePath = PathFormat.parse(savePath, originFileName);

			String rootPath = (String) conf.get("rootPath");

			is = file.getInputStream();
			State storageState = fileManager.saveFile(is, rootPath, savePath, maxSize);

			if (storageState.isSuccess()) {
				storageState.putInfo("url", PathFormat.format(savePath));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}

			return storageState;
		} catch (IOException e) {
			return new BaseState(false, AppInfo.IO_ERROR);
		} finally {
			IOUtils.closeQuietly(is);
		}

	}

	private static boolean validType(String type, List<String> allowTypes) {
		return allowTypes.contains(type);
	}
}
