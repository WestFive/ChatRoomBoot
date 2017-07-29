DROP DATABASE IF EXISTS chatBootDB;

CREATE DATABASE chatBootDB DEFAULT CHARACTER SET 'utf8'
  DEFAULT COLLATE 'utf8_unicode_ci';

USE chatBootDB;

CREATE TABLE user (
  userId BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(30) NOT NULL,
  hashedPassword BINARY(60) NOT NULL,
  accountNonExpired BOOLEAN NOT NULL,
  accountNonLocked BOOLEAN NOT NULL,
  credentialsNonExpired BOOLEAN NOT NULL,
  enabled BOOLEAN NOT NULL,
  CONSTRAINT user_unique UNIQUE (username)
) ENGINE = InnoDB;

CREATE TABLE user_Authority (
  userId BIGINT UNSIGNED NOT NULL,
  authority VARCHAR(100) NOT NULL,
  UNIQUE KEY user_Authority_User_Authority (userId, authority),
  CONSTRAINT user_Authority_UserId FOREIGN KEY (userId)
    REFERENCES user (userId) ON DELETE CASCADE
) ENGINE = InnoDB;



INSERT INTO user (username, hashedPassword, accountNonExpired,
                           accountNonLocked, credentialsNonExpired, enabled)
VALUES ( -- s1a2t3o4r
  'Carol', '$2a$10$.6kYphQ8VqJ9NPKtMje.JeWt1aoX4/ZRzVFGiO7Cen.rk88laGTCi',
  TRUE, TRUE, TRUE, TRUE
);

INSERT INTO user (username, hashedPassword, accountNonExpired,
                           accountNonLocked, credentialsNonExpired, enabled)
VALUES ( -- a5r6e7p8o
  'Albert', '$2a$10$CXNlyhzVkeHbqNpIJNBTl.WXP9WVouQwqh7M7IkI/WTXywakU5kha',
  TRUE, TRUE, TRUE, TRUE
);

INSERT INTO user (username, hashedPassword, accountNonExpired,
                           accountNonLocked, credentialsNonExpired, enabled)
VALUES ( -- t4e3n2e1t
  'Werner', '$2a$10$BKSkkYAh5COX/vvQrwPwuucL77Ydf61EEd97kwaBndKtxHJktQX/S',
  TRUE, TRUE, TRUE, TRUE
);

INSERT INTO user (username, hashedPassword, accountNonExpired,
                           accountNonLocked, credentialsNonExpired, enabled)
VALUES ( -- o8p7e6r5a
  'Alice', '$2a$10$fDcpl4fYkqyaKwVfBOFwTu5igi7yXzCw2AWp0oSkZ0iwMXzZsZ2t.',
  TRUE, TRUE, TRUE, TRUE
);

INSERT INTO user (username, hashedPassword, accountNonExpired,
                           accountNonLocked, credentialsNonExpired, enabled)
VALUES ( -- r1o2t3a4s
  'Richard', '$2a$10$ej/Cmw3p0b8UbWOQAhiEW.2LW03nspV2yLFaoli0CdxK8./miktoW',
  TRUE, TRUE, TRUE, TRUE
);



INSERT INTO user_Authority (UserId, Authority)
  VALUES (1, 'CHAT');

INSERT INTO user_Authority (UserId, Authority)
  VALUES (2, 'CHAT');

INSERT INTO user_Authority (UserId, Authority)
  VALUES (3, 'CHAT');

INSERT INTO user_Authority (UserId, Authority)
  VALUES (4, 'CHAT');

INSERT INTO user_Authority (UserId, Authority)
  VALUES (5, 'CHAT');

   
