-- api_category
INSERT INTO api_category(api_type, api_name) VALUES(0, "email");
INSERT INTO api_category(api_type, api_name) VALUES(1, "Kakao");
INSERT INTO api_category(api_type, api_name) VALUES(2, "naver");

-- user
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, api_key, height, weight, was_pro, gender, born_date, area, api_type, image) 
VALUES('bioman1@gmail.com','1234','bioman1','010-1234-5678',sysdate(),sysdate(),'왼발','FW',null,180,80,1,1,'1995-06-01', '서울 성동구', 0 ,"bioman1@gmail.com.jpg");
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, api_key, height, weight, was_pro, gender, born_date, area, api_type) VALUES('bioman2@gmail.com','1234','bioman2','010-1234-5678',sysdate(),sysdate(),'오른발','MF',null,174,70,1,0,'1995-06-01', '서울 성동구', 0);
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, api_key, height, weight, was_pro, gender, born_date, area, api_type) VALUES('bioman3@gmail.com','1234','bioman3','010-1234-5678',sysdate(),sysdate(),'왼발','DF',null,179,77,0,0,'1995-06-01', '서울 성동구', 0);
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, api_key, height, weight, was_pro, gender, born_date, area, api_type) VALUES('bioman4@gmail.com','1234','bioman4','010-1234-5678',sysdate(),sysdate(),'오른발','FW',null,175,75,0,0,'1995-06-01', '서울 성동구', 0);
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, api_key, height, weight, was_pro, gender, born_date, area, api_type) VALUES('bioman5@gmail.com','1234','bioman5','010-1234-5678',sysdate(),sysdate(),'왼발','MF',null,185,73,0,1,'1995-06-01', '서울 성동구', 0);
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, api_key, height, weight, was_pro, gender, born_date, area, api_type) VALUES('bioman6@gmail.com','1234','bioman6','010-1234-5678',sysdate(),sysdate(),'오른발','DF',null,184,80,0,0,'1995-06-01', '서울 성동구', 0);
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, api_key, height, weight, was_pro, gender, born_date, area, api_type) VALUES('bioman7@gmail.com','1234','bioman7','010-1234-5678',sysdate(),sysdate(),'왼발','FW',null,183,79,0,1,'1995-06-01', '서울 성동구', 0);
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, api_key, height, weight, was_pro, gender, born_date, area, api_type) VALUES('bioman8@gmail.com','1234','bioman8','010-1234-5678',sysdate(),sysdate(),'오른발','MF',null,183,79,1,0,'1995-06-01', '서울 성동구', 0);
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, api_key, height, weight, was_pro, gender, born_date, area, api_type) VALUES('bioman9@gmail.com','1234','bioman9','010-1234-5678',sysdate(),sysdate(),'왼발','DF',null,181,78,0,1,'1995-06-01', '서울 성동구', 0);
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, api_key, height, weight, was_pro, gender, born_date, area, api_type) VALUES('bioman10@gmail.com','1234','bioman10','010-1234-5678',sysdate(),sysdate(),'오른발','FW',null,171,75,0,1,'1995-06-01', '서울 성동구', 0);
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, api_key, height, weight, was_pro, gender, born_date, area, api_type) VALUES('bioman11@gmail.com','1234','bioman11','010-1234-5678',sysdate(),sysdate(),'왼발','MF',null,174,78,0,1,'1995-06-01', '서울 성동구', 0);
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, api_key, height, weight, was_pro, gender, born_date, area, api_type) VALUES('bioman12@gmail.com','1234','bioman12','010-1234-5678',sysdate(),sysdate(),'오른발','DF',null,176,78,1,0,'1995-06-01', '서울 성동구', 0);
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, api_key, height, weight, was_pro, gender, born_date, area, api_type) VALUES('bioman13@gmail.com','1234','bioman13','010-1234-5678',sysdate(),sysdate(),'왼발','FW',null,172,76,0,0,'1995-06-01', '대구 달서구', 0);
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, api_key, height, weight, was_pro, gender, born_date, area, api_type) VALUES('bioman14@gmail.com','1234','bioman14','010-1234-5678',sysdate(),sysdate(),'오른발','MF',null,170,72,1,1,'1995-06-01' , '대구 달서구', 0);
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, api_key, height, weight, was_pro, gender, born_date, area, api_type) VALUES('bioman15@gmail.com','1234','bioman15','010-1234-5678',sysdate(),sysdate(),'왼발','DF',null,178,80,0,1,'1995-06-01' , '대구 달서구', 0);
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, api_key, height, weight, was_pro, gender, born_date, area, api_type) VALUES('bioman16@gmail.com','1234','bioman16','010-1234-5678',sysdate(),sysdate(),'오른발','FW',null,171,79,1,0,'1995-06-01', '대구 달서구', 0);
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, api_key, height, weight, was_pro, gender, born_date, area, api_type) VALUES('bioman17@gmail.com','1234','bioman17','010-1234-5678',sysdate(),sysdate(),'왼발','MF',null,177,78,1,1,'1995-06-01', '대구 달서구', 0);
INSERT INTO user(email, pass, name, phone_num, user_reg_date, recent_login, main_foot, position, height, weight, was_pro, gender, born_date, area, image, api_key, api_type) VALUES('wpdud94@naver.com', '12345678', '강제영', '010-5043-5765', '2020-12-05 16:08:55', '2020-12-05 16:08:55', '오른발', 'DF', '171', '70', '0', '0', '1995-06-01', NULL, NULL, '1549194656', '0');

-- team
-- team1 
INSERT INTO team(team_name, emblem, area, stadium_info, uniform_color, uniform_type, founding_date, team_reg_date, account, bank) VALUES('왕십리FC','왕십리FC.png','서울 성동구','성동구 구장','빨강',1,'2016-01-01',sysdate(),'1234','기업');
INSERT INTO team(team_name, emblem, area, stadium_info, uniform_color, uniform_type, founding_date, team_reg_date, account, bank) VALUES('답십리FC','답십리FC201018.png','서울 성동구','응봉동 구장','파랑',0,'2017-01-01',sysdate(),'5678','하나');
INSERT INTO team(team_name, emblem, area, stadium_info, uniform_color, uniform_type, founding_date, team_reg_date, account, bank) VALUES('대구FC','대구FC201018.jpg','대구 달서구','달서구 구장','초록',0,'2018-01-01',sysdate(),'2345','기업');

-- team_member
INSERT INTO team_member(team_member_id, member_reg_date, member_level, reg_status, team_id, email, nick_name)VALUES('1-bioman1@gmail.com',sysdate(),4,1,1,'bioman1@gmail.com','nick1');
INSERT INTO team_member(team_member_id, member_reg_date, member_level, reg_status, team_id, email, nick_name)VALUES('1-bioman2@gmail.com',sysdate(),3,1,1,'bioman2@gmail.com','nick2');
INSERT INTO team_member(team_member_id, member_reg_date, member_level, reg_status, team_id, email, nick_name)VALUES('1-bioman3@gmail.com',sysdate(),2,1,1,'bioman3@gmail.com','nick3');
INSERT INTO team_member(team_member_id, member_reg_date, member_level, reg_status, team_id, email, nick_name)VALUES('1-bioman4@gmail.com',sysdate(),1,1,1,'bioman4@gmail.com','nick4');
INSERT INTO team_member(team_member_id, member_reg_date, member_level, reg_status, team_id, email, nick_name)VALUES('1-bioman5@gmail.com',sysdate(),0,1,1,'bioman5@gmail.com','nick5');
INSERT INTO team_member(team_member_id, member_reg_date, member_level, reg_status, team_id, email, nick_name)VALUES('2-bioman6@gmail.com',sysdate(),4,1,2,'bioman6@gmail.com','nick6');
INSERT INTO team_member(team_member_id, member_reg_date, member_level, reg_status, team_id, email, nick_name)VALUES('2-bioman7@gmail.com',sysdate(),3,1,2,'bioman7@gmail.com','nick7');
INSERT INTO team_member(team_member_id, member_reg_date, member_level, reg_status, team_id, email, nick_name)VALUES('2-bioman8@gmail.com', sysdate(),2,1,2,'bioman8@gmail.com','nick8');
INSERT INTO team_member(team_member_id, member_reg_date, member_level, reg_status, team_id, email, nick_name)VALUES('2-bioman9@gmail.com',sysdate(),1,1,2,'bioman9@gmail.com','nick9');
INSERT INTO team_member(team_member_id, member_reg_date, member_level, reg_status, team_id, email, nick_name)VALUES('2-bioman10@gmail.com',sysdate(),0,1,2,'bioman10@gmail.com','nick10');
INSERT INTO team_member(team_member_id, member_reg_date, member_level, reg_status, team_id, email, nick_name)VALUES('3-bioman11@gmail.com', sysdate(),4,1,3,'bioman11@gmail.com','nick11');
INSERT INTO team_member(team_member_id, member_reg_date, member_level, reg_status, team_id, email, nick_name)VALUES('3-bioman12@gmail.com', sysdate(),3,1,3,'bioman12@gmail.com','nick12');
INSERT INTO team_member(team_member_id, member_reg_date, member_level, reg_status, team_id, email, nick_name)VALUES('3-bioman13@gmail.com', sysdate(),2,1,3,'bioman13@gmail.com','nick13');
INSERT INTO team_member(team_member_id, member_reg_date, member_level, reg_status, team_id, email, nick_name)VALUES('3-bioman14@gmail.com', sysdate(),1,1,3,'bioman14@gmail.com','nick14');
INSERT INTO team_member(team_member_id, member_reg_date, member_level, reg_status, team_id, email, nick_name)VALUES('3-bioman15@gmail.com', sysdate(),0,1,3,'bioman15@gmail.com','nick15');
INSERT INTO team_member(team_member_id, member_reg_date, member_level, reg_status, team_id, email, nick_name)VALUES('2-bioman1@gmail.com',sysdate(),2,1,2,'bioman1@gmail.com','nick1');
INSERT INTO team_member(team_member_id, member_reg_date, member_level, reg_status, team_id, email, nick_name)VALUES('3-bioman1@gmail.com',sysdate(),2,1,3,'bioman1@gmail.com','nick1');

-- match_schedule / vote_match / vote_match_setting / vote_match_result
-- case 1
INSERT INTO match_schedule(match_schedule_id, start_time, duration, cost, writer, reg_date, home_content, away_content, stadium_name, stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, home_team_id, away_team_id, is_confirmed)
VALUES('1', '2021-03-01 22:14:00', '2', '2', 'nick1', '2021-01-21 22:18:12', 'ㅇㅇ', NULL, '뚝섬한강공원 축구장1', '흙', '서울 광진구 자양동 410-1', '127.076336885325', '37.5280242726795', '1', '1', '11:11', '1', NULL, NULL);
INSERT INTO vote_match(vote_match_id, team_id, match_schedule_id, memo, due_date, vote_status, vote_reg_date, writer)
VALUES('1', '1', '1', 'ㅋㅋ', '2021-02-28 22:14:00', '0', '2021-01-21 22:18:12', 'nick1');
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES('1', '0', '-1', '1', '1', '1', '-1', '-1', NULL, '-1', '-1', NULL, '-1', '-1', '-1');
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('1', '1', '1', '1-bioman1@gmail.com', NULL);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('2', '1', '1', '1-bioman2@gmail.com', NULL);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('3', '1', '0', '1-bioman3@gmail.com', NULL);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('4', '1', '1', NULL, 'bioman12@gmail.com');
-- case 2
INSERT INTO match_schedule(match_schedule_id, start_time, duration, cost, writer, reg_date, home_content, away_content, stadium_name, stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, home_team_id, away_team_id, is_confirmed)
VALUES('2', '2021-02-28 03:01:00', '2', '1', 'nick1', '2021-01-22 03:02:04', 'ㅇㅇ', NULL, '중랑 물재생센터축구장', '인조잔디', '서울 성동구 용답동 245', '127.061798169733', '37.5566485196523', '1', '0', '5:5', '1', 1, 1
);
INSERT INTO vote_match(vote_match_id, team_id, match_schedule_id, memo, due_date, vote_status, vote_reg_date, writer)
VALUES('2', '1', '2', 'ㅋㅋ', '2021-02-27 03:01:00', '0', '2021-01-22 03:02:04', 'nick1'
);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES('2', '0', '-1', '1', '1', '1', '-1', '-1', NULL, '-1', '-1', NULL, '-1', '-1', '-1'
);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('5', '2', '1', '1-bioman1@gmail.com', NULL);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('6', '2', '1', '1-bioman2@gmail.com', NULL);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('7', '2', '1', '1-bioman3@gmail.com', NULL);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('8', '2', '1', '1-bioman4@gmail.com', NULL);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('9', '2', '1', '1-bioman5@gmail.com', NULL);

-- case 3
INSERT INTO match_schedule(match_schedule_id, start_time, duration, cost, writer, reg_date, home_content, away_content, stadium_name, stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, home_team_id, away_team_id, is_confirmed)
VALUES('3', '2021-02-15 04:40:00', '1', '1', 'nick1', '2021-01-22 04:41:08', 'dd', NULL, '뚝섬한강공원 축구장1', '흙', '서울 광진구 자양동 410-1', '127.076336885325', '37.5280242726795', '1', '0', '5:5', '1', NULL, NULL
);
INSERT INTO vote_match(vote_match_id, team_id, match_schedule_id, memo, due_date, vote_status, vote_reg_date, writer)
VALUES('3', '1', '3', 'zz', '2021-02-14 04:41:00', '1', '2021-01-22 04:41:08', 'nick1'
);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES('3', '0', '-1', '0', '0', '0', '-1', '-1', NULL, '-1', '-1', NULL, '-1', '-1', '-1'
);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('10', '3', '1', '1-bioman1@gmail.com', NULL);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('11', '3', '0', '1-bioman2@gmail.com', NULL);

-- case 4
INSERT INTO match_schedule(match_schedule_id, start_time, duration, cost, writer, reg_date, home_content, away_content, stadium_name, stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, home_team_id, away_team_id, is_confirmed)
VALUES('4', '2021-01-02 04:40:00', '1', '1', 'nick1', sysdate(), 'dd', NULL, '뚝섬한강공원 축구장1', '흙', '서울 광진구 자양동 410-1', '127.076336885325', '37.5280242726795', '1', '0', '5:5', '1', NULL, NULL
);
INSERT INTO vote_match(vote_match_id, team_id, match_schedule_id, memo, due_date, vote_status, vote_reg_date, writer)
VALUES('4', '1', '4', 'zz', '2021-01-02 04:41:00', '1', sysdate(), 'nick1'
);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES('4', '0', '-1', '0', '0', '0', '-1', '-1', NULL, '-1', '-1', NULL, '-1', '-1', '-1'
);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('12', '4', '1', '1-bioman1@gmail.com', NULL);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('13', '4', '0', '1-bioman2@gmail.com', NULL);

-- case 5
INSERT INTO match_schedule(match_schedule_id, start_time, duration, cost, writer, reg_date, home_content, away_content, stadium_name, stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, home_team_id, away_team_id, is_confirmed)
VALUES('5', '2021-02-20 02:08:00', '2', '1', 'nick1', '2021-01-23 02:08:46', 'dasd', NULL, '뚝섬한강공원 축구장1', '잔디', '서울 광진구 자양동 410-1', '127.076336885325', '37.5280242726795', '1', '0', '7:7', '1', NULL, NULL
);
INSERT INTO vote_match(vote_match_id, team_id, match_schedule_id, memo, due_date, vote_status, vote_reg_date, writer)
VALUES('5', '1', '5', 'cxczc', '2021-02-19 02:08:00', '1', '2021-01-23 02:08:46', 'nick1'
);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES('5', '0', '-1', '0', '0', '0', '-1', '-1', NULL, '-1', '-1', NULL, '-1', '-1', '-1'
);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('14', '5', '1', '1-bioman1@gmail.com', NULL);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('15', '5', '0', '1-bioman2@gmail.com', NULL);

-- case 6
INSERT INTO match_schedule(match_schedule_id, start_time, duration, cost, writer, reg_date, home_content, away_content, stadium_name, stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, home_team_id, away_team_id, is_confirmed)
VALUES('6', '2021-02-21 02:08:00', '2', '2', 'nick1', '2021-01-23 02:09:26', 'dsacxcxc', NULL, '중랑물재생센터', '인조잔디', '서울 성동구 용답동 246', '127.06281794528302', '37.55807696307835', '1', '1', '6:6', '1', NULL, NULL
);
INSERT INTO vote_match(vote_match_id, team_id, match_schedule_id, memo, due_date, vote_status, vote_reg_date, writer)
VALUES('6', '1', '6', 'fhghgf', '2021-02-20 02:09:00', '1', '2021-01-23 02:09:26', 'nick1'
);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES('6', '0', '-1', '0', '0', '0', '-1', '-1', NULL, '-1', '-1', NULL, '-1', '-1', '-1'
);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('16', '6', '1', '1-bioman1@gmail.com', NULL);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('17', '6', '0', '1-bioman2@gmail.com', NULL);

-- case 7
INSERT INTO match_schedule(match_schedule_id, start_time, duration, cost, writer, reg_date, home_content, away_content, stadium_name, stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, home_team_id, away_team_id, is_confirmed)
VALUES('7', '2021-02-28 03:01:00', '2', '1', 'nick1', '2021-01-22 03:02:04', 'ㅇㅇ', NULL, '중랑 물재생센터축구장', '인조잔디', '서울 성동구 용답동 245', '127.061798169733', '37.5566485196523', '1', '0', '5:5', '2', NULL, NULL
);
INSERT INTO vote_match(vote_match_id, team_id, match_schedule_id, memo, due_date, vote_status, vote_reg_date, writer)
VALUES('7', '2', '7', 'ㅋㅋ', '2021-02-27 03:01:00', '1', '2021-01-22 03:02:04', 'nick1'
);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES('7', '0', '-1', '1', '1', '1', '-1', '-1', NULL, '-1', '-1', NULL, '-1', '-1', '-1'
);
INSERT INTO search(search_id, team_id_giver, match_schedule_id, team_member_id, search_reg_date, waiting_time, min_number, due_date, content)
VALUES('2', '2', '7', '2-bioman6@gmail.com', '2021-01-23 04:59:12', '1', '1', '2021-01-20 04:59:00', 'czcasd'
);
INSERT INTO assignment(assignment_id, team_id_giver, match_schedule_id, team_member_id, reg_date, due_date, content)
VALUES('2', '2', '7', '2-bioman1@gmail.com', '2021-01-24 01:51:38', '2021-01-20 01:51:00', 'czxcasd'
);
INSERT INTO employ(employ_id, team_id_giver, match_schedule_id, team_member_id, cost, req_number, content, reg_date, due_date)
VALUES(2, 2, 7, '2-bioman1@gmail.com', 1, 1 , 'dsd', '2021-01-20 01:51:00', '2021-01-20 : 01:51:00'
);

-- case 8
INSERT INTO match_schedule(match_schedule_id, start_time, duration, cost, writer, reg_date, home_content, away_content, stadium_name, stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, home_team_id, away_team_id, is_confirmed)
VALUES('8', '2021-02-27 03:01:00', '2', '1', 'nick1', '2021-01-22 03:02:04', 'ㅇㅇ', NULL, '중랑 물재생센터축구장', '인조잔디', '서울 성동구 용답동 245', '127.061798169733', '37.5566485196523', '1', '0', '5:5', '2', NULL, NULL
);
INSERT INTO vote_match(vote_match_id, team_id, match_schedule_id, memo, due_date, vote_status, vote_reg_date, writer)
VALUES('8', '2', '8', 'ㅋㅋ', '2021-02-26 03:01:00', '1', '2021-01-22 03:02:04', 'nick1'
);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES('8', '0', '-1', '1', '1', '1', '-1', '-1', NULL, '-1', '-1', NULL, '-1', '-1', '-1'
);
INSERT INTO search(search_id, team_id_giver, match_schedule_id, team_member_id, search_reg_date, waiting_time, min_number, due_date, content)
VALUES('3', '2', '8', '2-bioman6@gmail.com', '2021-01-23 04:59:12', '1', '1', '2021-02-26 04:59:00', 'czcasd'
);
INSERT INTO search_reservation(search_res_id, search_id, team_id_taker, team_member_id, reservation_status)
VALUES(1, 3, 1, '1-bioman1@gmail.com', 0
);
INSERT INTO assignment(assignment_id, team_id_giver, match_schedule_id, team_member_id, reg_date, due_date, content)
VALUES('3', '2', '8', '2-bioman1@gmail.com', '2021-01-24 01:51:38', '2021-02-26 01:51:00', 'czxcasd'
);
INSERT INTO assignment_reservation(assignment_res_id, assignment_id, team_member_id, team_id_taker, reservation_status)
VALUES(1, 3, '1-bioman1@gmail.com', 1, 0
);
INSERT INTO employ(employ_id, team_id_giver, match_schedule_id, team_member_id, cost, req_number, content, reg_date, due_date)
VALUES(3, 2, 8, '2-bioman1@gmail.com', 1, 1 , 'dsd', '2021-01-20 01:51:00', '2021-02-26 : 01:51:00'
);
INSERT INTO employ_result(employ_result_id, employ_id, email, emp_result_status)
VALUES(1, 3, 'bioman1@gmail.com', 0);

-- case9
INSERT INTO match_schedule(match_schedule_id, start_time, duration, cost, writer, reg_date, home_content, away_content, stadium_name, stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, home_team_id, away_team_id, is_confirmed)
VALUES('9', '2021-02-26 03:01:00', '2', '1', 'nick1', '2021-01-22 03:02:04', 'ㅇㅇ', NULL, '중랑 물재생센터축구장', '인조잔디', '서울 성동구 용답동 245', '127.061798169733', '37.5566485196523', '1', '0', '5:5', '2', NULL, NULL
);
INSERT INTO vote_match(vote_match_id, team_id, match_schedule_id, memo, due_date, vote_status, vote_reg_date, writer)
VALUES('9', '2', '9', 'ㅋㅋ', '2021-02-25 03:01:00', '1', '2021-01-22 03:02:04', 'nick1'
);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES('9', '0', '-1', '1', '1', '1', '-1', '-1', NULL, '-1', '-1', NULL, '-1', '-1', '-1'
);
INSERT INTO search(search_id, team_id_giver, match_schedule_id, team_member_id, search_reg_date, waiting_time, min_number, due_date, content)
VALUES('4', '2', '9', '2-bioman6@gmail.com', '2021-01-23 04:59:12', '1', '1', '2021-02-25 04:59:00', 'czcasd'
);
INSERT INTO search_reservation(search_res_id, search_id, team_id_taker, team_member_id, reservation_status)
VALUES(2, 4, 1, '1-bioman1@gmail.com', 3
);
INSERT INTO assignment(assignment_id, team_id_giver, match_schedule_id, team_member_id, reg_date, due_date, content)
VALUES('4', '2', '9', '2-bioman1@gmail.com', '2021-01-24 01:51:38', '2021-02-25 01:51:00', 'czxcasd'
);
INSERT INTO assignment_reservation(assignment_res_id, assignment_id, team_member_id, team_id_taker, reservation_status)
VALUES(2, 4, '3-bioman1@gmail.com', 3, 2
);
INSERT INTO employ(employ_id, team_id_giver, match_schedule_id, team_member_id, cost, req_number, content, reg_date, due_date)
VALUES(4, 2, 9, '2-bioman1@gmail.com', 1, 1 , 'dsd', '2021-01-20 01:51:00', '2021-02-25 : 01:51:00'
);
INSERT INTO employ_result(employ_result_id, employ_id, email, emp_result_status)
VALUES(2, 4, 'bioman2@gmail.com', 2);

-- case10
INSERT INTO match_schedule(match_schedule_id, start_time, duration, cost, writer, reg_date, home_content, away_content, stadium_name, stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, home_team_id, away_team_id, is_confirmed)
VALUES('10', '2021-02-24 03:01:00', '2', '1', 'nick1', '2021-01-22 03:02:04', 'ㅇㅇ', 'ㅋㅋ', '중랑 물재생센터축구장', '인조잔디', '서울 성동구 용답동 245', '127.061798169733', '37.5566485196523', '1', '0', '5:5', '2', 1, 1
);
INSERT INTO vote_match(vote_match_id, team_id, match_schedule_id, memo, due_date, vote_status, vote_reg_date, writer)
VALUES('10', '2', '10', 'ㅋㅋ', '2021-02-23 03:01:00', '1', '2021-01-22 03:02:04', 'nick1'
);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES('10', '0', '-1', '1', '1', '1', '-1', '-1', NULL, '-1', '-1', NULL, '-1', '-1', '-1'
);
INSERT INTO vote_match(vote_match_id, team_id, match_schedule_id, memo, due_date, vote_status, vote_reg_date, writer)
VALUES('11', '1', '10', 'ㅋㅋ', '2021-02-23 03:01:00', '1', '2021-01-22 03:02:04', 'nick1'
);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES('11', '0', '-1', '1', '1', '1', '-1', '-1', NULL, '-1', '-1', NULL, '-1', '-1', '-1'
);
INSERT INTO employ(employ_id, team_id_giver, match_schedule_id, team_member_id, cost, req_number, content, reg_date, due_date)
VALUES(5, 2, 10, '2-bioman1@gmail.com', 1, 1 , 'dsd', '2021-01-20 01:51:00', '2021-02-23 : 01:51:00'
);
INSERT INTO employ_result(employ_result_id, employ_id, email, emp_result_status)
VALUES(3, 5, 'bioman2@gmail.com', 2);

-- case 11
INSERT INTO match_schedule(match_schedule_id, start_time, duration, cost, writer, reg_date, home_content, away_content, stadium_name, stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, home_team_id, away_team_id, is_confirmed)
VALUES('11', '2021-01-02 02:08:00', '2', '2', 'nick1', '2020-12-20 02:09:26', 'dsacxcxc', NULL, '중랑물재생센터', '인조잔디', '서울 성동구 용답동 246', '127.06281794528302', '37.55807696307835', '1', '1', '5:5', '1', 1, 1
);
INSERT INTO vote_match(vote_match_id, team_id, match_schedule_id, memo, due_date, vote_status, vote_reg_date, writer)
VALUES('12', '1', '11', 'fhghgf', '2021-01-01 02:09:00', '1', '2020-12-20 02:09:26', 'nick1'
);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES('12', '0', '-1', '0', '0', '0', '-1', '-1', NULL, '-1', '-1', NULL, '-1', '-1', '-1'
);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('18', '12', '1', '1-bioman1@gmail.com', NULL);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('19', '12', '1', '1-bioman2@gmail.com', NULL);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('20', '12', '1', '1-bioman3@gmail.com', NULL);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('21', '12', '1', '1-bioman4@gmail.com', NULL);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('22', '12', '1', '1-bioman5@gmail.com', NULL);

-- case 12
INSERT INTO match_schedule(match_schedule_id, start_time, duration, cost, writer, reg_date, home_content, away_content, stadium_name, stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, home_team_id, away_team_id, is_confirmed)
VALUES('12', '2021-01-09 02:08:00', '2', '2', 'nick1', '2020-12-20 02:09:26', 'dsacxcxc', NULL, '중랑물재생센터', '인조잔디', '서울 성동구 용답동 246', '127.06281794528302', '37.55807696307835', '1', '1', '5:5', '1', 2, 1
);
INSERT INTO vote_match(vote_match_id, team_id, match_schedule_id, memo, due_date, vote_status, vote_reg_date, writer)
VALUES('13', '1', '12', 'fhghgf', '2021-01-08 02:09:00', '1', '2020-12-20 02:09:26', 'nick1'
);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES('13', '0', '-1', '0', '0', '0', '-1', '-1', NULL, '-1', '-1', NULL, '-1', '-1', '-1'
);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('23', '13', '1', '1-bioman1@gmail.com', NULL);
INSERT INTO employ(employ_id, team_id_giver, match_schedule_id, team_member_id, cost, req_number, content, reg_date, due_date)
VALUES(6, 1, 12, '2-bioman1@gmail.com', 1, 1 , 'dsd', '2021-01-02 01:51:00', '2021-01-01 : 01:51:00'
);
INSERT INTO employ_result(employ_result_id, employ_id, email, emp_result_status)
VALUES(7, 6, 'bioman16@gmail.com', 2);
INSERT INTO vote_match(vote_match_id, team_id, match_schedule_id, memo, due_date, vote_status, vote_reg_date, writer)
VALUES('15', '2', '12', 'fhghgf', '2021-01-08 02:09:00', '1', '2020-12-20 02:09:26', 'nick1'
);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES('15', '0', '-1', '0', '0', '0', '-1', '-1', NULL, '-1', '-1', NULL, '-1', '-1', '-1'
);
insert into vote_match_result(vote_match_result_id, vote_match_id, attendance, team_member_id, email) 
values('29', '15', '1', '2-bioman6@gmail.com', NULL);

INSERT INTO match_schedule(match_schedule_id, home_team_id, away_team_id, start_time, duration, cost, writer, reg_date, content, stadium_name,
 stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, is_confirmed)
VALUES(2, '1', 2,'2020-12-22 09:00:00', '2', '10', 'nick1', '2020-10-18 14:18:06', '늦지 마시오', '뚝섬한강공원 축구장1', 
'흙', '서울 광진구 자양동 410-1', 127.076336885325, 37.5280242726795, '1', '0', '11:11', 0
);
INSERT INTO match_schedule(match_schedule_id, home_team_id, start_time, duration, cost, writer, reg_date, content, stadium_name, 
stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, is_confirmed)
VALUES(3, 2, '2020-12-22 15:00:00', '2', '10', 'nick6', '2020-10-18 14:18:06', '늦지 마시오', '응봉체육공원 축구장',
 '흙', '서울 성동구 응봉동 234-3', 127.037395212449, 37.5518618581322, '1', '0', '11:11', 0
);
INSERT INTO match_schedule(match_schedule_id, home_team_id, start_time, duration, cost, writer, reg_date, content, stadium_name, 
stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, is_confirmed)
VALUES(4, '1', '2020-12-29 09:00:00', '2', '10', 'nick1', '2020-11-21 14:18:06', '늦지 마시오', '뚝섬한강공원 축구장1', 
'흙', '서울 광진구 자양동 410-1', 127.076336885325, 37.5280242726795, '1', '0', '6:6', 0
);
INSERT INTO match_schedule(match_schedule_id, start_time, duration, cost, writer, reg_date, content, stadium_name, 
stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, home_team_id, away_team_id, is_confirmed)
VALUES('5','2020-12-16 00:27:00', '2', '5', 'nick11', '2020-11-24 00:27:38', 'dd', '뚝섬한강공원 축구장1', 
'흙', '서울 광진구 자양동 410-1', '127.076336885325', '37.5280242726795', '1', '0', '11:11', '1', NULL, 0
);
INSERT INTO match_schedule(match_schedule_id, start_time, duration, cost, writer, reg_date, content, stadium_name,
 stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, home_team_id, away_team_id, is_confirmed)
values('6', '2020-12-12 07:51:00', '0', '0', 'nick1', '2020-11-27 07:52:20', 'ㅇㅇ', '중랑 물재생센터축구장', 
'인조잔디', '서울 성동구 용답동 245', '127.061798169733', '37.5566485196523', '1', '0', '6:6', '1', 2, 1);
INSERT INTO match_schedule(match_schedule_id, home_team_id, away_team_id, start_time, duration, cost, writer, reg_date, content, stadium_name, 
stadium_type, stadium_address, stadium_x, stadium_y, stadium_parking, stadium_shower, match_type, is_confirmed)
VALUES(7, '1', 2, '2020-10-22 09:00:00', '2', '10', 'nick1', '2020-10-21 14:18:06', '늦지 마시오', '뚝섬한강공원 축구장1', 
'흙', '서울 광진구 자양동 410-1', 127.076336885325, 37.5280242726795, '1', '0', '11:11', 1
);

-- vote_match
INSERT INTO vote_match(vote_match_id, due_date, memo, vote_status, vote_reg_date, writer, team_id, match_schedule_id)
VALUES(1, '2020-10-14 21:00','늦지 마시오', 1, sysdate(), 'nick1', 1, 1);
INSERT INTO vote_match(vote_match_id, due_date, memo, vote_status, vote_reg_date, writer, team_id, match_schedule_id)
VALUES(2, '2020-12-21 21:00','늦지 마시오', 0, sysdate(), 'nick1', 1, 2);
INSERT INTO vote_match(vote_match_id, due_date, memo, vote_status, vote_reg_date, writer, team_id, match_schedule_id)
VALUES(3, '2020-12-21 21:00','늦지 마시오', 0, sysdate(), 'nick1', 2, 3);
INSERT INTO vote_match(vote_match_id, due_date, memo, vote_status, vote_reg_date, writer, team_id, match_schedule_id)
VALUES(4, '2020-12-27 21:00','늦지 마시오', 0, sysdate(), 'nick1', 1, 4);
INSERT INTO vote_match(vote_match_id, due_date, memo, vote_status, vote_reg_date, writer, team_id, match_schedule_id)
VALUES(5, '2020-12-15 21:00','늦지 마시오', 0, sysdate(), 'nick1', 1, 5);
INSERT INTO vote_match(vote_match_id, due_date, memo, vote_status, vote_reg_date, writer, team_id, match_schedule_id)
VALUES(6, '2020-12-11 21:00','늦지 마시오', 0, sysdate(), 'nick1', 1, 6);
INSERT INTO vote_match(vote_match_id, due_date, memo, vote_status, vote_reg_date, writer, team_id, match_schedule_id)
VALUES(7, '2020-10-21 21:00','늦지 마시오', 0, sysdate(), 'nick1', 1, 7);
INSERT INTO vote_match(vote_match_id, due_date, memo, vote_status, vote_reg_date, writer, team_id, match_schedule_id)
VALUES(8, '2020-12-21 21:00','늦지 마시오', 1, sysdate(), 'nick1', 2, 2);

-- vote_match_setting
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES(1, 0, 11, 1, 1, 1, -1, -1, null, -1, -1, null, 10, -1, -1);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES(2, 0, 11, 1, 1, 0, -1, -1, null, -1, -1, null, 10, -1, -1);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES(3, 0, 11, 1, 1, 1, -1, -1, null, -1, -1, null, 10, -1, -1);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES(4, 0, 1, 1, 1, 1, -1, -1, null, -1, -1, null, 10, -1, -1);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES(5, 0, 11, 1, 1, 1, -1, -1, null, -1, -1, null, 10, -1, -1);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES(6, 0, 11, 1, 1, 1, -1, -1, null, -1, -1, null, 10, -1, -1);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES(7, 0, 11, 1, 1, 0, -1, -1, null, -1, -1, null, 10, -1, -1);
INSERT INTO vote_match_setting(vote_match_id, type, cancel_number, is_first, waiting, friend_emp, self_min_number, self_max_number, emp_due_date, emp_min_number, away_min_number, away_due_date, assign_cost, emp_cost, search_cost)
VALUES(8, 0, 11, 1, 1, 0, -1, -1, null, -1, -1, null, 10, -1, -1);


-- vote_match_result
insert into vote_match_result(vote_match_result_id, attendance, vote_match_id, team_member_id) values(1, 1,1,'1-bioman1@gmail.com');
insert into vote_match_result(vote_match_result_id,attendance, vote_match_id, team_member_id) values(2, 0,1,'1-bioman2@gmail.com');
insert into vote_match_result(vote_match_result_id,attendance, email, vote_match_id) values(3, 1,'bioman17@gmail.com',1);
insert into vote_match_result(vote_match_result_id,attendance, email, vote_match_id) values(4, 1,'bioman16@gmail.com',1);
insert into vote_match_result(vote_match_result_id,attendance, vote_match_id, team_member_id) values(5, 1,3,'2-bioman6@gmail.com');
insert into vote_match_result(vote_match_result_id, attendance, vote_match_id, team_member_id) values(6, 2,4,'1-bioman3@gmail.com');
insert into vote_match_result(vote_match_result_id, attendance, vote_match_id, team_member_id) values(7, 1,6,'1-bioman3@gmail.com');
insert into vote_match_result(vote_match_result_id, attendance, vote_match_id, email) values(8, 1,6,'bioman7@gmail.com');
insert into vote_match_result(vote_match_result_id, attendance, vote_match_id, email) values(11, 1,8,'bioman7@gmail.com');


-- notice


-- search
INSERT INTO
search(search_id, team_id_giver, match_schedule_id, team_member_id, search_reg_date, waiting_time, min_number, due_date, content)
VALUES(1, 1, 1, '1-bioman1@gmail.com', sysdate(), 3, 11, '2020-10-13 21:00', "빠른 파악 부탁드립니다.");
INSERT INTO
search(search_id, team_id_giver, match_schedule_id, team_member_id, search_reg_date, waiting_time, min_number, due_date, content)
VALUES(3, 2, 3, '2-bioman6@gmail.com', sysdate(), 2, 11, '2020-10-21 21:00', "빠른 파악 부탁드립니다.");
INSERT INTO
search(search_id, team_id_giver, match_schedule_id, team_member_id, search_reg_date, waiting_time, min_number, due_date , content)
VALUES(4, 1, 4, '1-bioman1@gmail.com', sysdate(), 1, 1, '2020-12-27 21:00', "빠른 인원파악 부탁드립니다.");
INSERT INTO
search(search_id, team_id_giver, match_schedule_id, team_member_id, search_reg_date, waiting_time, min_number, due_date , content)
VALUES(5, 1, 5, '1-bioman1@gmail.com', sysdate(), 1, 1, '2020-12-15 21:00', "빠른 인원파악 부탁드립니다.");

-- search_reservation
INSERT INTO
search_reservation(search_id, team_id_taker, team_member_id, reservation_status)
VALUES(1, 2, '2-bioman7@gmail.com', -1);
INSERT INTO
search_reservation(search_id, team_id_taker, team_member_id, reservation_status)
VALUES(1, 3, '3-bioman11@gmail.com', -1);
INSERT INTO
search_reservation(search_id, team_id_taker, team_member_id, reservation_status)
VALUES(4, 2, '2-bioman7@gmail.com', 0);
insert into
search_reservation(search_id, team_id_taker, team_member_id, reservation_status)
VALUES(4, 3, '3-bioman11@gmail.com', 0);

-- assginment
INSERT INTO
assignment(assignment_id, team_id_giver, match_schedule_id, team_member_id, reg_date, due_date, content)
VALUES('1', '1', '4', '1-bioman1@gmail.com', '2020-11-24 00:25:21', '2020-12-28 00:21:00', 'zz');
INSERT INTO
assignment(assignment_id, team_id_giver, match_schedule_id, team_member_id, reg_date, due_date, content)
VALUES('2', '1', '6', '1-bioman1@gmail.com', '2020-11-24 00:26:36', '2020-12-10 00:26:00', 'zz');
INSERT INTO
assignment(assignment_id, team_id_giver, match_schedule_id, team_member_id, reg_date, due_date, content)
VALUES('3', '1', '5', '1-bioman1@gmail.com', '2020-11-24 00:27:38', '2020-12-15 00:27:00', 'zz');
INSERT INTO
assignment(assignment_id, team_id_giver, match_schedule_id, team_member_id, reg_date, due_date, content)
VALUES('6', '2', '3', '2-bioman1@gmail.com', '2020-12-12 01:58:13', '2020-12-15 01:58:00', 'dd');

-- assignment_reservation
INSERT INTO
assignment_reservation(assignment_id, team_id_taker, team_member_id, reservation_status)
VALUES('3', '3','3-bioman11@gmail.com','0');

INSERT INTO
assignment_reservation(assignment_id, team_id_taker, team_member_id, reservation_status)
VALUES('3', '2', '2-bioman7@gmail.com','0');


-- employ
INSERT INTO
employ(employ_id, team_id_giver, match_schedule_id, team_member_id, cost, req_number, content, reg_date, due_date)
values('4', '1', '1', '1-bioman1@gmail.com', '1', '3', '매너있는 분은 언제나 환영입니다.', '2020-10-13 07:46:21', '2020-10-10 15:52:00');
INSERT INTO
employ(employ_id, team_id_giver, match_schedule_id, team_member_id, cost, req_number, content, reg_date, due_date)
values('1', '1', '2', '1-bioman1@gmail.com', '1', '3', '매너있는 분은 언제나 환영입니다.', '2020-11-27 07:46:21', '2020-12-31 15:52:00');

INSERT INTO
employ(employ_id, team_id_giver, match_schedule_id, team_member_id, cost, req_number, content, reg_date, due_date)
values('2', '1', '4', '1-bioman1@gmail.com', '2', '3', '매너 좋아용', '2020-11-27 07:46:56', '2020-12-28 21:00:00');
INSERT INTO
employ(employ_id, team_id_giver, match_schedule_id, team_member_id, cost, req_number, content, reg_date, due_date)
values('3', '1', '6', '1-bioman1@gmail.com', '0', '2', 'ㅋㅋ', '2020-11-27 07:52:20', '2020-11-27 12:52:00');

-- employ_result
INSERT INTO
employ_result(employ_result_id, employ_id, email, emp_result_status)
values('5', '1', 'bioman8@gmail.com', '0');
INSERT INTO
employ_result(employ_result_id, employ_id, email, emp_result_status)
values('7', '1', 'bioman12@gmail.com', '-1');
INSERT INTO
employ_result(employ_result_id, employ_id, email, emp_result_status)
values('3', '2', 'bioman7@gmail.com', '0');
INSERT INTO
employ_result(employ_result_id, employ_id, email, emp_result_status)
values('8', '2', 'bioman12@gmail.com', '0');
INSERT INTO
employ_result(employ_result_id, employ_id, email, emp_result_status)
values('9', 3, 'bioman12@gmail.com', 1);

-- emp_score
INSERT INTO
emp_score(manner_promise, manner_contact, manner_rule, manner_body_fight, manner_slang, manner_smoking, manner_uniform, manner_payment, manner_arrangement, manner_referee, manner_tackle, forward, middle, defence, match_schedule_id, team_giver_id, emp_email)
values('7', '7', '9', '7', '8', '9', '7', '10', '8', '9', '9', '7', '9', '7', '1', '1', 'bioman16@gmail.com');
INSERT INTO
emp_score(manner_promise, manner_contact, manner_rule, manner_body_fight, manner_slang, manner_smoking, manner_uniform, manner_payment, manner_arrangement, manner_referee, manner_tackle, forward, middle, defence, match_schedule_id, team_giver_id, emp_email)
values('10', '8', '9', '8', '7', '8', '9', '10', '8', '7', '9', '7', '5', '9', '1', '1', 'bioman17@gmail.com');
INSERT INTO
emp_score(manner_promise, manner_contact, manner_rule, manner_body_fight, manner_slang, manner_smoking, manner_uniform, manner_payment, manner_arrangement, manner_referee, manner_tackle, forward, middle, defence, match_schedule_id, team_giver_id, emp_email)
values('10', '8', '9', '9', '10', '8', '9', '8', '9', '7', '7', '9', '5', '5', '1', '1', 'bioman7@gmail.com');

update user set emp_ability = 7.6666, emp_manner = 8.1818 where email = 'bioman16@gmail.com';
update user set emp_ability = 7, emp_manner = 8.3636 where email = 'bioman17@gmail.com';
update user set emp_ability = 6.3333, emp_manner = 8.5454 where email = 'bioman7@gmail.com';

-- team_score
-- team1
INSERT INTO
team_score(manner_promise, manner_contact, manner_rule, manner_body_fight, manner_slang, manner_smoking, manner_uniform, manner_payment, manner_arrangement, manner_referee, manner_tackle, forward, middle, defence, team_taker_id, match_schedule_id, team_giver_id, emp_email)
values('10', '4', '2', '3', '2', '3', '3', '4', '2', '3', '2', '9', '9', '9', 1, '1', 2, NULL);

update team set team_manner = 3.4545, team_ability = 9 where team_id = 1;
-- team2
INSERT INTO
team_score(manner_promise, manner_contact, manner_rule, manner_body_fight, manner_slang, manner_smoking, manner_uniform, manner_payment, manner_arrangement, manner_referee, manner_tackle, forward, middle, defence, team_taker_id, match_schedule_id, team_giver_id, emp_email)
values('10', '10', '9', '9', '8', '9', '9', '9', '8', '7', '8', '9', '7', '9', '2', '1', '1', NULL);
INSERT INTO
team_score(manner_promise, manner_contact, manner_rule, manner_body_fight, manner_slang, manner_smoking, manner_uniform, manner_payment, manner_arrangement, manner_referee, manner_tackle, forward, middle, defence, team_taker_id, match_schedule_id, team_giver_id, emp_email)
values('8', '10', '9', '9', '5', '9', '9', '9', '8', '7', '8', '9', '7', '9', '2', '1', null, 'bioman7@gmail.com');

update team set team_manner = 8.5, team_ability = 8.33334 where team_id = 2;


-- entry
INSERT INTO
entry(entry_id, team_member_id, match_schedule_id, email, team_id, attendance, type, match_score, goal, assist)
values('73', '1-bioman1@gmail.com', '1', NULL, 1, '2', '0', '9', '2', '0');
INSERT INTO
entry(entry_id, team_member_id, match_schedule_id, email, team_id,attendance, type, match_score, goal, assist)
values('74', NULL, '1', 'bioman16@gmail.com', 1,'2', '1', '7', '0', '1');
INSERT INTO
entry(entry_id, team_member_id, match_schedule_id, email, team_id,attendance, type, match_score, goal, assist)
values('75', NULL, '1', 'bioman17@gmail.com', 1,'2', '1', '5', '0', '0');
INSERT INTO
entry(entry_id, team_member_id, match_schedule_id, email, team_id,attendance, type, match_score, goal, assist)
values('76', NULL, '1', 'bioman7@gmail.com', 1,'2', '2', '7', '0', '1');
INSERT INTO
entry(entry_id, team_member_id, match_schedule_id, email, team_id,attendance, type, match_score, goal, assist)
values('77', '2-bioman6@gmail.com', '1',  null, 2, '2', '1', '7', '0', '1');

-- match_result
INSERT INTO
match_result(match_schedule_id, is_set, home_result, away_result, home_score, away_score)
values('1', '0', '1', '-1', '2', '1');
INSERT INTO
match_result(match_schedule_id, is_set, home_result, away_result, home_score, away_score)
values('7', '0', '-1', '1', '2', '3');
