INSERT INTO ROLE(ROLE_ID, ROLE) VALUES (1, 'ROLE_ADMIN')
INSERT INTO USER(USER_ID, ACTIVE, EMAIL, LAST_NAME, NAME, PASSWORD) VALUES(1,1,'admin@admin.pl','Admin','Admin','$2a$10$LKRShxlm/Fy60EJ1czBiNemJb2rZubs.j1.2Wz8D1z7n6nGtcfp2m')
INSERT INTO USER_ROLE(USER_ID, ROLE_ID) VALUES (1,1)

INSERT INTO ROLE(ROLE_ID, ROLE) VALUES (2, 'ROLE_MANAGER')
INSERT INTO USER(USER_ID, ACTIVE, EMAIL, LAST_NAME, NAME, PASSWORD) VALUES(2,1,'manager@manager.pl','Manager','Manager','$2a$10$LKRShxlm/Fy60EJ1czBiNemJb2rZubs.j1.2Wz8D1z7n6nGtcfp2m')
INSERT INTO USER_ROLE(USER_ID, ROLE_ID) VALUES (2,2)

INSERT INTO ROLE(ROLE_ID, ROLE) VALUES (3, 'ROLE_OCCUPANT')
INSERT INTO USER(USER_ID, ACTIVE, EMAIL, LAST_NAME, NAME, PASSWORD) VALUES(3,1,'user@user.pl','User','User','$2a$10$LKRShxlm/Fy60EJ1czBiNemJb2rZubs.j1.2Wz8D1z7n6nGtcfp2m')
INSERT INTO USER_ROLE(USER_ID, ROLE_ID) VALUES (3,3)

--$2a$10$LKRShxlm/Fy60EJ1czBiNemJb2rZubs.j1.2Wz8D1z7n6nGtcfp2m