select * from search;
select * from search_reservation;
select * from match_schedule;
select * from vote_match;
select * from vote_match_setting;
select * from vote_match_result;
select * from assignment;
select * from assignment_reservation;
select * from employ;
select * from employ_result order by employ_id;
select * from emp_score;
select * from user;
select * from team;
select * from user_stat;
select * from team_score;
select * from team_member order by team_id ASC;
select * from entry;
select * from match_result;
select * from notice;

delete from notice;
ALTER TABLE `notice` AUTO_INCREMENT = 0;
update user set image = 'bioman1@gmail.com.jpg' where email = 'bioman1@gmail.com';
UPDATE assignment_reservation
SET reservation_status = -1
WHERE assignment_id = 1
AND team_id_taker != 2;