
CREATE TABLE `Facet_FluxtreamCaptureObservation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `api` int(11) NOT NULL,
  `comment` longtext,
  `end` bigint(20) NOT NULL,
  `fullTextDescription` longtext,
  `guestId` bigint(20) NOT NULL,
  `isEmpty` char(1) NOT NULL,
  `objectType` int(11) NOT NULL,
  `start` bigint(20) NOT NULL,
  `tags` longtext,
  `timeUpdated` bigint(20) NOT NULL,
  `note` longtext,

  `apiKeyId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `guestId_index` (`guestId`),
  KEY `apiKeyId` (`apiKeyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
