package com.sample.app.service;

import java.util.stream.Stream;

import com.sample.app.model.UserDetail;

public interface UserDetailService {
	public Stream<UserDetail> getUserDetailsByStream();

	public void getUserDetailsByStreamAndConvertToDTOWithNewObjectMapper();
	
	public void getUserDetailsByStreamAndConvertToDTOWithStaticObjectMapper();
	}
