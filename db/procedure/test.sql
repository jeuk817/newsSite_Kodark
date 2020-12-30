CREATE DEFINER=`jack`@`localhost` PROCEDURE `test_procedure`()
BEGIN
	insert into auth_string(email, auth_string) values('jeuk817@gmail.com', '123456');
END