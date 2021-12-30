USE process;

DELETE FROM User WHERE id = '1';
DELETE FROM User WHERE name = 'admin@admin.com';

INSERT INTO `User` VALUES ('1', 'admin@admin.com', 'hr');
