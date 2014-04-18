CREATE TABLE `FluxtreamCaptureTopic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `guestId` bigint(20) NOT NULL,

  `creationTime` bigint(20) NOT NULL,
  `timeUpdated` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `step` int(11) DEFAULT NULL,
  `defaultValue` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,

  PRIMARY KEY (`id`),
  KEY `guestId_index` (`guestId`),
  KEY `guestId_TimeUpdated_index` (`guestId`, `timeUpdated`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;