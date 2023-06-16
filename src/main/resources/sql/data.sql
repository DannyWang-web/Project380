-- INSERT INTO user_roles(user_name, role) VALUES ('User1', 'ROLE_USER');
-- INSERT INTO user_roles(user_name, role) VALUES ('User1', 'ROLE_ADMIN');
--
-- INSERT INTO user_roles(user_name, role) VALUES ('User2', 'ROLE_USER');
-- INSERT INTO user_roles(user_name, role) VALUES ('User3', 'ROLE_USER');
-- INSERT INTO user_roles(user_name, role) VALUES ('User4', 'ROLE_USER');
-- INSERT INTO user_roles(user_name, role) VALUES ('User5', 'ROLE_USER');
-- INSERT INTO user_roles(user_name, role) VALUES ('User6', 'ROLE_USER');
-- INSERT INTO user_roles(user_name, role) VALUES ('User7', 'ROLE_USER');


INSERT INTO user_table(user_name, user_password) VALUES ('user1', '{noop}user1pw');
INSERT INTO user_roles(user_name, role) VALUES ('user1', 'ROLE_USER');
INSERT INTO user_roles(user_name, role) VALUES ('user1', 'ROLE_ADMIN');

INSERT INTO user_table(user_name, user_password) VALUES ('user2', '{noop}user2pw');
INSERT INTO user_roles(user_name, role) VALUES ('user2', 'ROLE_ADMIN');

