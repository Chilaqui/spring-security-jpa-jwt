-- Contraseña para adminUser: admin456
INSERT INTO user (user_name, password, role) VALUES
('adminUser', '$2a$12$uh87.HEmnViGSj.PnP.IIuTrhuF5yFvPUfTAFrIgPBQZTV2jY3Jiq', 'ADMIN');

-- Contraseña para developerUser: dev456
INSERT INTO user (user_name, password, role) VALUES
('developerUser', '$2a$12$q1TCLY2y0aTuFTiJlLY/BOtfBbVr8ZqjOCcQ62CnvRTtQ8RcHLA2G', 'DEVELOPER');

-- Contraseña para managerUser: manager456
INSERT INTO user (user_name, password, role) VALUES
('managerUser', '$2a$12$dRymZEx.oeXvGqBBM4hGH.uyAWadU.tldO1fTZ2SDSzHZqNI2eQrK', 'MANAGER');

-- Contraseña para normalUser: user456
INSERT INTO user (user_name, password, role) VALUES
('normalUser', '$2a$12$8ogk2bsrWSvr7NDyQjRxcu12ItTdfv84ZASdnjWII4me2s8JYmnj6', 'USER');
