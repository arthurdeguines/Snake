drop table if exists utilisateur;
SET FOREIGN_KEY_CHECKS=1; 


-- debut création table --


CREATE TABLE utilisateur (
  pseudo char(30),
  score int,
  acceleration boolean,
  enleverPoint boolean,
  nomPc char(255)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


