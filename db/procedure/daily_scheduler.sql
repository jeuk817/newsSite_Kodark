CREATE EVENT 
IF NOT EXISTS dailyScheduler
    ON SCHEDULE
           EVERY 1 DAY STARTS '2021-01-13 00:00:00'
    ON COMPLETION PRESERVE ENABLE
    COMMENT '매일 자정에 작동하는 스케줄러'
    DO 
    DELETE FROM auth_string WHERE date_add(created_at, interval 30 minute) < current_timestamp();
    DELETE FROM forbidden WHERE end_date > current_timestamp();