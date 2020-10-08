package com.honeycom.fbt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honeycom.fbt.dao.FootBallTogetherDAO_LJH;
import com.honeycom.fbt.dto.User;

@Service
public class FootBallTogetherServiceImpl_MHK implements FootBallTogetherService_MHK{

	@Autowired
	private FootBallTogetherDAO_LJH footBallTogetherDAO_LJH; 
	
	@Override
	public User selectUser(User user) {
		return footBallTogetherDAO_LJH.selectUser(user);
	}
	

}
