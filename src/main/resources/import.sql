-- 1) Roles
INSERT IGNORE INTO `role` (`name`)
VALUES ('ROLE_ADMIN');

INSERT IGNORE INTO `role` (`name`)
VALUES ('ROLE_USER');

-- 2) Usuario administrador
-- BCrypt de “123”: $2a$10$hD9PhD5grk1nPz7aO/Z3SeWu4/EtX6eJtHvpe50NWUNRRABfKT9rO
INSERT IGNORE INTO `users` (`name`, `email`, `password`, `enabled`)
VALUES (
  'Jefe de Ventas',
  'jefeventas@gmail.com',
  '$2a$10$hD9PhD5grk1nPz7aO/Z3SeWu4/EtX6eJtHvpe50NWUNRRABfKT9rO',
  true
);

-- 3) Relación usuario ↔ ROLE_ADMIN
INSERT IGNORE INTO `users_roles` (`USER_ID`, `ROLE_ID`)
SELECT u.ID, r.ID
FROM `users` u
JOIN `role` r ON r.name = 'ROLE_ADMIN'
WHERE u.email = 'jefeventas@gmail.com';