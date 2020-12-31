package com.biomans.fbt.notice.service;

import java.sql.SQLException;
import java.util.List;

import com.biomans.fbt.domain.Notice;
import com.biomans.fbt.util.NoticeFactor;

public interface NoticeService {
	//FV09
	public void addNotice(Notice notice) throws SQLException;
	//FN01
	public List<Notice> showNoticesByUser(String email) throws SQLException;
	// 
	public void addNoticeByCase(NoticeFactor noticeFactor) throws SQLException;
}
