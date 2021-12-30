USE login;

DELETE FROM User WHERE id = 1;
DELETE FROM User WHERE email = 'admin@admin.com';

INSERT INTO `User` VALUES (1, 'admin@admin.com', 'admin', 'hr');