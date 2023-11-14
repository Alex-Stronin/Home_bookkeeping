insert into users (user_name, user_login,user_password, user_starting_capital ) values ('Pawlowa N.A.', 'natali', 'nataly85', 100000.52);
insert into users (user_name, user_login,user_password, user_starting_capital ) values ('Pawlow I.S.', 'ivan', 'wany', 150000.12);
insert into users (user_name, user_login,user_password, user_starting_capital ) values ('Pawlow A.I.', 'student', 'st-21', 60000.39);
insert into users (user_name, user_login,user_password, user_starting_capital ) values ('Pawlow W.S.', 'wowa', 'w89', 200000.15);

insert into users (user_name, user_login, user_password, user_role, user_starting_capital ) values ('John', 'sa', '123', 'ADMIN', 0);

insert into expenses_category (name_expense_category) values ('коммунальные платежи');
insert into expenses_category (name_expense_category) values ('расходы на питание');
insert into expenses_category (name_expense_category) values ('покупки непродовольственных товаров');
insert into expenses_category (name_expense_category) values ('транспортные расходы');
insert into expenses_category (name_expense_category) values ('расходы на мобильную связь и интернет');
insert into expenses_category (name_expense_category) values ('покупка лекарственных средств');
insert into expenses_category (name_expense_category) values ('непредвиденные расходы');

insert into incomes_category (source_income_category) values ('заработная плата');
insert into incomes_category (source_income_category) values ('премия');
insert into incomes_category (source_income_category) values ('доходы от ценных бумаг');
insert into incomes_category (source_income_category) values ('стипендия');
insert into incomes_category (source_income_category) values ('доходы от предпринимательской  деятельности');
insert into incomes_category (source_income_category) values ('доходы от других источников');

insert into expenses (expense_amount, user_id, expense_category_id) values (5000,  1, 1);
insert into expenses (expense_amount, user_id, expense_category_id) values (3000,  1, 2);
insert into expenses (expense_amount, user_id, expense_category_id) values (23000, 1, 3);
insert into expenses (expense_amount, user_id, expense_category_id) values (300,   1, 4);
insert into expenses (expense_amount, user_id, expense_category_id) values (600,   1, 5);
insert into expenses (expense_amount, user_id, expense_category_id) values (1000,  1, 6);

insert into expenses (expense_amount, user_id, expense_category_id) values (3000,  2, 1);
insert into expenses (expense_amount, user_id, expense_category_id) values (5000,  2, 2);
insert into expenses (expense_amount, user_id, expense_category_id) values (10000, 2, 3);
insert into expenses (expense_amount, user_id, expense_category_id) values (100,   2, 4);
insert into expenses (expense_amount, user_id, expense_category_id) values (300,   2, 5);
insert into expenses (expense_amount, user_id, expense_category_id) values (600,   2, 6);

insert into expenses (expense_amount, user_id, expense_category_id) values (4000, 3, 1);
insert into expenses (expense_amount, user_id, expense_category_id) values (1000, 3, 2);
insert into expenses (expense_amount, user_id, expense_category_id) values (100,  3, 4);
insert into expenses (expense_amount, user_id, expense_category_id) values (400,  3, 5);
insert into expenses (expense_amount, user_id, expense_category_id) values (400,  3, 6);

insert into expenses (expense_amount, user_id, expense_category_id) values (6000,   4, 1);
insert into expenses (expense_amount, user_id, expense_category_id) values (8000,   4, 2);
insert into expenses (expense_amount, user_id, expense_category_id) values (36000,  4, 3);
insert into expenses (expense_amount, user_id, expense_category_id) values (4000,   4, 4);
insert into expenses (expense_amount, user_id, expense_category_id) values (400,    4, 5);
insert into expenses (expense_amount, user_id, expense_category_id) values (11000,  4, 6);
insert into expenses (expense_amount, user_id, expense_category_id) values (40000,  4, 7);

insert into incomes (income_amount, user_id, income_category_id) values (55000, 1, 1);
insert into incomes (income_amount, user_id, income_category_id) values (41250, 1, 2);
insert into incomes (income_amount, user_id, income_category_id) values (4000,  1, 3);

insert into incomes (income_amount, user_id, income_category_id) values (62000,  2, 1);
insert into incomes (income_amount, user_id, income_category_id) values (46500,  2, 2);
insert into incomes (income_amount, user_id, income_category_id) values (6000,   2, 3);
insert into incomes (income_amount, user_id, income_category_id) values (26000,  2, 5);

insert into incomes (income_amount, user_id, income_category_id) values (10000,  3, 4);
insert into incomes (income_amount, user_id, income_category_id) values (17000,  3, 6);

insert into incomes (income_amount, user_id, income_category_id) values (84000,  4, 1);
insert into incomes (income_amount, user_id, income_category_id) values (63000,  4, 2);
insert into incomes (income_amount, user_id, income_category_id) values (52000,  4, 5);