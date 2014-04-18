
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
  `apiKeyId` bigint(20) DEFAULT NULL,
  `timeUpdated` bigint(20) NOT NULL,
  `tags` longtext,

  `creationTime` bigint(20) NOT NULL,
  `topicID` bigint(20) NOT NULL,
  `timezoneOffset` int(11) NOT NULL,
  `value` int(11) DEFAULT NULL,

  PRIMARY KEY (`id`),
  KEY `isEmpty_index` (`isEmpty`),
  KEY `end_index` (`end`),
  KEY `start_index` (`start`),
  KEY `api_index` (`api`),
  KEY `objectType_index` (`objectType`),
  KEY `guestId_index` (`guestId`),
  KEY `timeUpdated_index` (`timeUpdated`),
  KEY `apiKeyId` (`apiKeyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
