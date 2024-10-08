CREATE TABLE `restaurant` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `address_street_or_avenue` varchar(255) NOT NULL,
  `address_number` varchar(255) NOT NULL,
  `address_complement` varchar(255) DEFAULT NULL,
  `address_neighborhood` varchar(255) NOT NULL,
  `address_city_id` bigint NOT NULL,
  `address_zip_code` varchar(255) NOT NULL,
  `creation_date` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `open` bit(1) NOT NULL,
  `ship_rate` decimal(38,2) NOT NULL,
  `update_date` datetime NOT NULL,
  `kitchen_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `address_city_fk_1` (`address_city_id`),
  KEY `kitchen_fk_1` (`kitchen_id`),
  CONSTRAINT `address_city_fk_1` FOREIGN KEY (`address_city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `kitchen_fk_1` FOREIGN KEY (`kitchen_id`) REFERENCES `kitchen` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci