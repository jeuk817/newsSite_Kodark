CREATE DEFINER=`root`@`localhost` PROCEDURE `users_write_comment_procedure`(
    in _commentId int,
    in _email varchar(50),
    in _content varchar(500)
)
BEGIN
	declare userId int;
    declare articleId int;
    
    select id into userId from users where email = _email;
	select article_id into articleId from comment where id = _commentId;
    
	insert into comment(parent_id,user_id,article_id,content)
    values(_commentId,userId,articleId,_content);
END