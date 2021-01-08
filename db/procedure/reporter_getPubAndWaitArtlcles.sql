CREATE DEFINER=`jack`@`localhost` PROCEDURE `reporter_getPubAndWaitArtlcles`(
   in _status varchar(10)
)
BEGIN
	select id, category_id as category, title, status
		, DATE_FORMAT(created_at, '%Y-%m-%d') as createdAt
        , DATE_FORMAT(edited_at, '%Y-%m-%d') as editedAt
        , hit
	from article
    where status =_status
    ;
END