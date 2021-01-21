package com.biomans.fbt.notice.dao;

import java.sql.SQLException;
import java.util.List;

import com.biomans.fbt.domain.Notice;

public interface NoticeDAO {
	//Me01-2
	public void addNotice(Notice notice) throws SQLException;
	//N002
	public List<Notice> showNoticesByUser(String email) throws SQLException;
}
