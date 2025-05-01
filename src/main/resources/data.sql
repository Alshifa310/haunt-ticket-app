INSERT INTO tickets (name, price, phonenum, email, age, progday) VALUES
('Alshifa', 18.00, '7897897890', 'alshifa@example.com', 21, 'Friday 6PM - 12AM'),
('Jeff', 16.75, '1236541236', 'jeff@example.com', 23, 'Weekends 10AM - 12 PM'),
('Rob', 22.00, '3334445556', 'rob@example.com', 26, 'Weekdays 5PM - 10 PM'),
('Sweety', 20.50, '4445556667', 'sweety@example.com', 28, 'Friday 6PM - 12AM'),
('Jessica', 19.00, '5556667778', 'jessica@example.com', 24, 'Weekends 10AM - 12 PM'),
('Alshifa', 18.50, '7897897891', 'alshifa2@example.com', 22, 'Weekdays 5PM - 10 PM'),
('Jeff', 17.00, '1236541237', 'jeff2@example.com', 25, 'Friday 6PM - 12AM'),
('Rob', 21.00, '3334445557', 'rob2@example.com', 30, 'Weekends 10AM - 12 PM'),
('Sweety', 19.25, '4445556668', 'sweety2@example.com', 26, 'Weekdays 5PM - 10 PM'),
('Jessica', 20.00, '5556667779', 'jessica2@example.com', 27, 'Friday 6PM - 12AM');


insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jon', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Rob', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Alshifa', '$2a$10$ic2vQc1t//PrLP8a7gtRs./du1YB8998BKp6XroGdXpPKjY58U5ci', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jeff', '$2a$10$ic2vQc1t//PrLP8a7gtRs./du1YB8998BKp6XroGdXpPKjY58U5ci', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jessica', '$2a$10$ic2vQc1t//PrLP8a7gtRs./du1YB8998BKp6XroGdXpPKjY58U5ci', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Sweety', '$2a$10$ic2vQc1t//PrLP8a7gtRs./du1YB8998BKp6XroGdXpPKjY58U5ci', 1);
 
insert into sec_role (roleName)
values ('ROLE_GUEST');
 
insert into sec_role (roleName)
values ('ROLE_VENDER');

insert into user_role (userId, roleId)
values (1, 2);

insert into user_role (userId, roleId)
values (2, 1);

insert into user_role (userId, roleId)
values (3, 1);

insert into user_role (userId, roleId)
values (4, 1);

insert into user_role (userId, roleId)
values (5, 1);

insert into user_role (userId, roleId)
values (6, 1);
