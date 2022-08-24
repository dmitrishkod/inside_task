drop database `inside_task`;
create database `inside_task`;
use `inside_task`;

Create table `user`(
                       id int(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                       login VARCHAR(32) NOT NULL,
                       password VARCHAR(16) NOT NULL,
                       firstname VARCHAR(255),
                       lastname VARCHAR(255),
                       age VARCHAR(255)
);

Create table `user_data`(
                            id int(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                            user INT(11) UNSIGNED NOT NULL,
                            name VARCHAR(32) NOT NULL,
                            address VARCHAR(255) NOT NULL,
                            mail VARCHAR(255) NOT NULL,
                            INDEX (user),
                            foreign key (user)
                                references user(id)
                                ON Delete Restrict
);

Create table `user_message`(
                               id int(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                               user_id INT(11) UNSIGNED NOT NULL,
                               name VARCHAR(255) NOT NULL,
                               message VARCHAR(255) NOT NULL,
                               INDEX (user_id),
                               foreign key (user_id)
                                   references user(id)
                                   ON Delete Restrict
);

INSERT INTO `inside_task`.`user` (`login`, `password`,`firstname`,`lastname`,`age`) VALUES ('dmitry', 'qwerty','Dmitry','Petrov', 33);
INSERT INTO `inside_task`.`user` (`login`, `password`,`firstname`,`lastname`,`age`) VALUES ('johnybanana', 'qwertyqwerty','Johny','Banana',22);

INSERT INTO `inside_task`.`user_data` (`user`, `name`, `address`, `mail`) VALUES ('1', 'Petrov Dmitry Dmitrievich', 'Red Square, Moscow, 109012', 'dmitry.petrov@gmail.com');
INSERT INTO `inside_task`.`user_data` (`user`, `name`, `address`, `mail`) VALUES ('2', 'Johny Banana','1600 Pennsylvania Avenue NW, Washington, D.C., DC 20500, United States','banana@gmail.com' );