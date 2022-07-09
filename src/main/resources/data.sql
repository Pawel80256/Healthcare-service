-- INSERT INTO role (id) VALUES (0),(1),(2);

-- INSERT INTO admin (id,username, password)
-- SELECT 'admin','admin', 'admin'
--     WHERE NOT EXISTS (SELECT * FROM admin WHERE username='admin');
--
-- INSERT into admin_roles (admin_id, roles_id)
-- SELECT 'admin','2'
--     WHERE NOT EXISTS(SELECT * FROM admin_roles WHERE admin_id='admin')