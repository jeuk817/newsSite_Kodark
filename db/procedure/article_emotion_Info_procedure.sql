CREATE DEFINER=`jack`@`localhost` PROCEDURE `article_emotion_Info_procedure`(
	in _articleId int
)
BEGIN
	declare checkData int;
    SELECT count(*) into checkData 
    FROM article_emotion 
    WHERE article_id =_articleId
    ;
	
    IF checkData > 0 THEN
	select emotion,count(*) as count
    from article_emotion 
    
    inner join emotion
    on article_emotion.emotion_id = emotion.id
    
    where article_id= _articleId
    group by emotion
    ;
    
    END IF;

END