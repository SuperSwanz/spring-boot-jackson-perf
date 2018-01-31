package com.sample.app.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.app.model.UserDetail;
import com.sample.app.util.JsonUtil;

public class UserDetailDTO {
	private Long id;

	private String userName;

	private String firstName;

	private String lastName;

	private Boolean status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String toJsonWithNewObjectMapper() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String toJsonWithStaticObjectMapper() {
		try {
			return JsonUtil.getObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static UserDetailDTO fromUserDetail(UserDetail userDetail) {
		UserDetailDTO dto = new UserDetailDTO();
		dto.setFirstName(userDetail.getFirstName());
		dto.setId(userDetail.getId());
		dto.setLastName(userDetail.getLastName());
		dto.setStatus(userDetail.getStatus());
		dto.setUserName(userDetail.getUserName());
		return dto;
	}
}