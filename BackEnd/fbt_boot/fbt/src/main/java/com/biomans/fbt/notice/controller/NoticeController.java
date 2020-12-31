package com.biomans.fbt.notice.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.biomans.fbt.domain.Notice;
import com.biomans.fbt.notice.service.NoticeService;

@RestController
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	//FV09
	@PostMapping("/notice/1")
	public ResponseEntity inviteFriend(@RequestBody Notice notice) throws SQLException {
		try {
			noticeService.addNotice(notice);
			return new ResponseEntity(HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	//FN01
	@GetMapping("/notice/1/{email}")
	public ResponseEntity showNoticesByUser(@PathVariable String email) throws SQLException {
		try {
			List<Notice> notices = noticeService.showNoticesByUser(email);
			return new ResponseEntity(notices, HttpStatus.OK);
		}catch(RuntimeException e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
}
