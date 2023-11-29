CREATE TABLE IF NOT EXISTS `djs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `artist_name` varchar(50) NOT NULL,
  `birth_date` date NOT NULL,
  `country_id_registration` varchar(50) NOT NULL,
  `first_name` varchar(125) NOT NULL,
  `last_name` varchar(125) NOT NULL,
  `city` varchar(125) DEFAULT NULL,
  `complement` varchar(60) DEFAULT NULL,
  `country` varchar(60) DEFAULT NULL,
  `district` varchar(125) DEFAULT NULL,
  `number` bigint DEFAULT NULL,
  `postal_code` varchar(15) DEFAULT NULL,
  `state` varchar(125) DEFAULT NULL,
  `street` varchar(125) DEFAULT NULL,
  PRIMARY KEY (`id`)
);