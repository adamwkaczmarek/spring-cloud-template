INSERT INTO users(user_name,password,enabled) VALUES ('john.carnell','$2a$04$NX3QTkBJB00upxKeaKqFBeoIVc9JHvwVnj1lItxNphRj34wNx5wlu', true);
INSERT INTO users(user_name,password,enabled) VALUES ('william.woodward','$2a$04$lM2hIsZVNYrQLi8mhvnTA.pheZtmzeivz6fyxCr9GZ6YSfP6YibCW', true);
INSERT INTO users(user_name,password,enabled) VALUES ('adam','$2a$04$tUp2nft/GBMo3Jh1gzq.Ru998sB3lWsgd3z1Y1OPxBE/fgekZY3Ky', true);
INSERT INTO users(user_name,password,enabled) VALUES ('device','$2a$04$tUp2nft/GBMo3Jh1gzq.Ru998sB3lWsgd3z1Y1OPxBE/fgekZY3Ky', true);

INSERT INTO user_roles (user_name, role) VALUES ('john.carnell', 'ROLE_USER');
INSERT INTO user_roles (user_name, role) VALUES ('william.woodward', 'ROLE_ADMIN');
INSERT INTO user_roles (user_name, role) VALUES ('william.woodward', 'ROLE_USER');
INSERT INTO user_roles (user_name, role) VALUES ('adam', 'ROLE_ADMIN');
INSERT INTO user_roles (user_name, role) VALUES ('device', 'DEVICE');