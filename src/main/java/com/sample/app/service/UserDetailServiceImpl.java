package com.sample.app.service;

import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.app.dto.UserDetailDTO;
import com.sample.app.model.UserDetail;
import com.sample.app.repository.UserDetailRepository;

@Service
public class UserDetailServiceImpl implements UserDetailService {
	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	private UserDetailRepository userDetailRepository;

	@Override
	@Transactional(readOnly = true)
	public Stream<UserDetail> getUserDetailsByStream() {
		try (Stream<UserDetail> userDetailsStream = userDetailRepository.getAllAsStream()) {
			return userDetailsStream;
		} catch (Exception ix) {
			ix.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public void getUserDetailsByStreamAndConvertToDTOWithNewObjectMapper() {
		try (Stream<UserDetail> userDetailsStream = userDetailRepository.getAllAsStream()) {
			userDetailsStream.forEach(userDetail -> {
				UserDetailDTO.fromUserDetail(userDetail).toJsonWithNewObjectMapper();
			});
			userDetailsStream.close();
		} catch (Exception ix) {
			ix.printStackTrace();
		} finally {
			//System.gc();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public void getUserDetailsByStreamAndConvertToDTOWithStaticObjectMapper() {
		try (Stream<UserDetail> userDetailsStream = userDetailRepository.getAllAsStream()) {
			userDetailsStream.forEach(userDetail -> {
				UserDetailDTO.fromUserDetail(userDetail).toJsonWithStaticObjectMapper();
			});
			userDetailsStream.close();
		} catch (Exception ix) {
			ix.printStackTrace();
		} finally {
			//System.gc();
		}
	}
}