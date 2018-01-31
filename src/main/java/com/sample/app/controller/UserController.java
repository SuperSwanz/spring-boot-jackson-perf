package com.sample.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.service.UserDetailService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserDetailService userDetailService;

	@RequestMapping(value = "/stream/new", method = RequestMethod.GET)
	public ResponseEntity<?> writeDataAsJsonUsingNewObjectMapper() {
		userDetailService.getUserDetailsByStreamAndConvertToDTOWithNewObjectMapper();
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/stream/static", method = RequestMethod.GET)
	public ResponseEntity<?> writeDataAsJsonUsingStaticObjectMapper() {
		userDetailService.getUserDetailsByStreamAndConvertToDTOWithStaticObjectMapper();
		return new ResponseEntity(HttpStatus.OK);
	}
}