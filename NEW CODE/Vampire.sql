-- --------------------------------------------------------
-- Host:                         192.168.1.19
-- Server version:               5.0.45-community-nt - MySQL Community Edition (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             8.1.0.4588
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for vampire_db
CREATE DATABASE IF NOT EXISTS `vampire_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `vampire_db`;


-- Dumping structure for table vampire_db.node_info
CREATE TABLE IF NOT EXISTS `node_info` (
  `Node_Name` varchar(15) NOT NULL,
  `Node_IP` varchar(15) NOT NULL,
  `Node_Port` int(5) unsigned NOT NULL,
  `Node_Status` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table vampire_db.topo_const
CREATE TABLE IF NOT EXISTS `topo_const` (
  `Source_Node` varchar(5) NOT NULL,
  `Dest_Node` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
