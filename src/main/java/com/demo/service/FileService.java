package com.demo.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.demo.model.UploadFile;

public interface FileService {
	 public UploadFile saveFile(HttpServletRequest request) throws IOException;
}
