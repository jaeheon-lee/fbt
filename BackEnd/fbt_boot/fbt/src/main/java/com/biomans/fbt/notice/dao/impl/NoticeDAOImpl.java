package com.biomans.fbt.notice.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biomans.fbt.domain.Notice;
import com.biomans.fbt.notice.dao.NoticeDAO;

@Repository
public class NoticeDAOImpl implements NoticeDAO{
	final String ns = "NoticeMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	//Me01-2
	@Override
	public void addNotice(Notice notice) throws SQLException {
		sqlSession.insert(ns+"addNotice", notice);
	}
	
	//N002
	@Override
	public List<Notice> showNoticesByUser(String email) throws SQLException {
		return sqlSession.selectList(ns+"showNoticesByUser", email);
	}
}
