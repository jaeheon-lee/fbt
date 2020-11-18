update vote_match set vote_status = 1 where vote_match_id = '1-2';

select * from vote_match_result where vote_match_id = '1-2';
update vote_match_result set attendance = 1 where vote_match_result_id = '1-2-1-bioman2@gmail.com';

update vote_match set vote_status = 0 where vote_match_id = '1-1';