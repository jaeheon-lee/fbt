update vote_match set vote_status = 1 where vote_match_id = '1-2';

select * from vote_match_result where vote_match_id = '1-2';
update vote_match_result set attendance = 1 where vote_match_result_id = '1-2-1-bioman2@gmail.com';

select * from vote_match;
update vote_match set vote_status = 0 where vote_match_id = '1-2';
update vote_match_setting set waiting = 0 where vote_match_id = '1-2';

select * from match_schedule;

select * from team;

select
m.match_schedule_id, m.start_time, m.match_type,
date_add(m.start_time, interval m.duration hour) end_time,
v.due_date, v.vote_status
from match_schedule m
join vote_match v
on m.match_schedule_id = v.match_schedule_id
;

select * from vote_match_setting;
        
select * from search;