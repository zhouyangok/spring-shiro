package com.wangzhixuan.commons.ueditor.upload;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wangzhixuan.commons.ueditor.define.State;
import com.wangzhixuan.commons.ueditor.manager.IUeditorFileManager;

public class Uploader {
	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;

	public Uploader(HttpServletRequest request, Map<String, Object> conf) {
		this.request = request;
		this.conf = conf;
	}

	public final State doExec(IUeditorFileManager fileManager) {
		String filedName = (String) this.conf.get("fieldName");
		State state = null;
		if ("true".equals(this.conf.get("isBase64"))) {
			state = Base64Uploader.save(fileManager, this.request.getParameter(filedName), this.conf);
		} else {
			state = BinaryUploader.save(fileManager, this.request, this.conf);
		}
		return state;
	}
}
