insert into course
  (id, name, created_date, last_updated_date)
values
  (10001, 'Course1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (10002, 'Thinking in Java', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (10003, 'Course2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (10004, 'Java2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (10005, 'Java3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (10006, 'Java4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (10007, 'Java5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (10008, 'Java6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into passport
  (id, number)
values
  (30001, 'E12111'),
  (30002, 'F12222'),
  (30003, 'E33333');

insert into student
  (id, name, passport_id)
values
  (20001, 'Student1', 30001),
  (20002, 'Student2', 30002),
  (20003, 'Student3', 30003);

insert into review
  (id, rating, description, course_id)
values
  (40001, '5', 'Great', 10001),
  (40002, '4', 'Good', 10001),
  (40003, '3' ,'Normal', 10003);

insert into student_course
  (student_id, course_id)
values
  (20001, 10001),
  (20002, 10001),
  (20003, 10001),
  (20001, 10003);