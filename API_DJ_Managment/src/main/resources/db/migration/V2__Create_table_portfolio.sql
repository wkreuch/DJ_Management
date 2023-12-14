CREATE TABLE IF NOT EXISTS `portfolios` (
  `id_portfolio` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(500) DEFAULT NULL,
  `link` varchar(2000) DEFAULT NULL,
  `type_portfolio` enum('MUSIC','PICTURE','VIDEO','YOUTUBE') DEFAULT NULL,
  `id_dj` bigint DEFAULT NULL,
  PRIMARY KEY (`id_portfolio`),
  KEY `FK3o4xk1ykihfy1i89wo2vhyc29` (`id_dj`),
  CONSTRAINT `FK3o4xk1ykihfy1i89wo2vhyc29` FOREIGN KEY (`id_dj`) REFERENCES `djs` (`id`)
);