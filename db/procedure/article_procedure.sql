CREATE DEFINER=`root`@`localhost` PROCEDURE `article_procedure`(
	in _switch varchar(50)
	,in _id int
    ,in _commentId int
    ,out result_set varchar(10)
)
BEGIN
declare checkData int;

	CASE _switch
    /* 기사 감정 데이터
	   작성자 : 이종현
    */
    when 'article_emotion' then
		SELECT count(*) into checkData 
		FROM article_emotion 
		WHERE article_id =_id;
		
		IF checkData > 0 THEN
		select emotion,count(*) as count
		from article_emotion 
		
		inner join emotion
		on article_emotion.emotion_id = emotion.id
		
		where article_id= _id
		group by emotion;
		set result_set = '200';
		END IF;
        
    /* 기사 대댓글 데이터
	   작성자 : 이종현
    */
    when 'article_comment_reply' then
		select
		c.id
		,u.id as userId
		,u.email
		,ud.nick_name as nickName
		,ud.local
		, c.content
		,date_format(c.created_at, '%Y-%m-%d %H:%i:%S') as createdAt
		,c.del_flag as delFlag 
		,(select count(reputation) from comm_reputation where reputation = 'recommend' and comment_id = c.id ) as recommend
		,(select count(reputation) from comm_reputation where reputation = 'decommend'and comment_id = c.id ) as decommend
		from comment as c
		join users as u on c.user_id = u.id
		join user_detail as ud on u.id = ud.user_id
	    where c.article_id = _id AND c.parent_id = _commentId
		;
    
    else set result_set = '500';
	end case;
END