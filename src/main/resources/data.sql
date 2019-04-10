insert into CUSTOMER
values('john.citizen@gmail.com','John Citizen');

insert into CUSTOMER
values('f_mercury@hotmail.com','Freddie Mercury');

insert into CUSTOMER
values('flash@gordon.com','Flash Gordon');

insert into PHONE_NUMBER
values(123456, TRUE, 'john.citizen@gmail.com');

insert into PHONE_NUMBER
values(123457, TRUE, 'john.citizen@gmail.com');

insert into PHONE_NUMBER
values(911, TRUE, 'f_mercury@hotmail.com');

insert into PHONE_NUMBER
values(123, TRUE, 'f_mercury@hotmail.com');

insert into PHONE_NUMBER
values(321, FALSE, 'f_mercury@hotmail.com');

insert into PHONE_NUMBER
values(999999, TRUE, 'flash@gordon.com');