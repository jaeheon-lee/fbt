package com.biomans.fbt;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biomans.fbt.domain.User;

@SpringBootTest
class UserTest {
	
	final String ns = "UserMapper.";
	
	@Autowired
	private SqlSession sqlsession;
	
	@Test
	void contextLoads() {
		User user = new User();
		user.setEmail("biomans3@gmail.com");
		user.setPass("1234");
		user.setPhoneNum("010-6234-5678");
		user.setMainFoot("오른발");
		user.setPosition("FW");
		user.setHeight(189);
		user.setWeight(78);
		user.setWasPro(1);
		user.setGender(1);
		user.setBorn_date("1990-06-15");
		user.setName("바이오3");
		
		sqlsession.insert(ns+"addUser", user);
	}

}
