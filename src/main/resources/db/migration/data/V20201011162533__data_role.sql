--Тестовые данные роли пользователя--
INSERT INTO role (id, name, rank) VALUES (seq_user_role.nextval, 'Администратор', 1);
INSERT INTO role (id, name, rank) VALUES (seq_user_role.nextval, 'Преподаватель', 2);
INSERT INTO role (id, name, rank) VALUES (seq_user_role.nextval, 'Студент', 3);