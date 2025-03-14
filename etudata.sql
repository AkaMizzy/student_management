-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 27, 2024 at 04:47 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `etudata`
--

-- --------------------------------------------------------

--
-- Table structure for table `edata`
--

CREATE TABLE `edata` (
  `Etudiant_ID` int(11) NOT NULL,
  `Prenom` varchar(30) NOT NULL,
  `Nom` varchar(30) NOT NULL,
  `sexe` varchar(30) DEFAULT NULL,
  `Filiere` varchar(20) NOT NULL,
  `Telephone` varchar(20) DEFAULT NULL,
  `dateNaissance` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_520_ci;

--
-- Dumping data for table `edata`
--

INSERT INTO `edata` (`Etudiant_ID`, `Prenom`, `Nom`, `sexe`, `Filiere`, `Telephone`, `dateNaissance`) VALUES
(1, 'hamza', 'zily', 'homme', 'LPGL', '0641298620', '2002-08-13'),
(2, 'hamza', 'ez-zouek', 'homme', 'LPGL', '0652345681', '2003-06-05'),
(3, 'younes', 'alassbat', 'homme', 'LPGC', '0614257895', '2004-05-01'),
(4, 'salwa', 'elhantati', 'femme', 'SDDI3', '0785412369', '2003-10-23'),
(5, 'saad', 'laasri', 'homme', 'SDDI3', '0623547895', '2002-06-18'),
(6, 'najlaa', 'yaaqobi', 'femme', 'LPGL', '0625143698', '2024-01-01'),
(7, 'karim', 'zilali', 'homme', 'SDDI3', '0641256987', '2002-45-85'),
(8, 'hakima', 'korami', 'femme', 'LPGC', '0625897412', '2005-12-27');

-- --------------------------------------------------------

--
-- Table structure for table `etudiant_absence`
--

CREATE TABLE `etudiant_absence` (
  `Etudiant_ID` int(11) NOT NULL,
  `Etudiant_nom` varchar(30) DEFAULT NULL,
  `Etudiant_prenom` varchar(30) DEFAULT NULL,
  `Module` varchar(30) DEFAULT NULL,
  `Nombre_Heure` double DEFAULT NULL,
  `Date_absence` varchar(30) DEFAULT NULL,
  `justifier` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `etudiant_absence`
--

INSERT INTO `etudiant_absence` (`Etudiant_ID`, `Etudiant_nom`, `Etudiant_prenom`, `Module`, `Nombre_Heure`, `Date_absence`, `justifier`) VALUES
(1, 'zily', 'hamza', 'java', 2, '13-12-2024', 'Oui'),
(2, 'ez-zouek', 'hamza', 'nodejs', 0.5, '1-1-2025', 'No'),
(3, 'alassbat', 'younes', 'xml', 5, '11-10-2024', 'Oui'),
(4, 'elhantati', 'salwa', 'java', 1, '23-11-2024', 'No');

-- --------------------------------------------------------

--
-- Table structure for table `etudiant_note`
--

CREATE TABLE `etudiant_note` (
  `Etudiant_ID` int(11) NOT NULL,
  `Etudiant_prenom` varchar(30) DEFAULT NULL,
  `Etudiant_nom` varchar(30) DEFAULT NULL,
  `Java` float NOT NULL,
  `XML` float NOT NULL,
  `UML` float NOT NULL,
  `NodeJS` float NOT NULL,
  `Moyenne_Generale` float GENERATED ALWAYS AS ((`Java` + `XML` + `UML` + `NodeJS`) / 4) STORED,
  `Mention` varchar(20) GENERATED ALWAYS AS (case when `Moyenne_Generale` >= 16 then 'Très Bien' when `Moyenne_Generale` >= 14 then 'Bien' when `Moyenne_Generale` >= 12 then 'Assez Bien' when `Moyenne_Generale` >= 10 then 'Passable' else 'Non Admis' end) STORED
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `etudiant_note`
--

INSERT INTO `etudiant_note` (`Etudiant_ID`, `Etudiant_prenom`, `Etudiant_nom`, `Java`, `XML`, `UML`, `NodeJS`) VALUES
(1, 'hamza', 'zily', 19, 15, 16, 20),
(2, 'hamza', 'ez-zouek', 20, 12, 16, 9),
(3, 'younes', 'alassbat', 17, 18, 17, 19),
(4, 'salwa', 'elhantati', 9, 8, 16, 11),
(5, 'saad', 'laasri', 6, 7, 8, 19),
(6, 'najlaa', 'yaaqobi', 10, 14, 10, 12),
(7, 'karim', 'zilali', 12, 13, 14, 16),
(8, 'hakima', 'korami', 10, 20, 15, 12);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`email`, `password`) VALUES
('hamza@gmail.com', '1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `edata`
--
ALTER TABLE `edata`
  ADD UNIQUE KEY `Etudiant ID` (`Etudiant_ID`);

--
-- Indexes for table `etudiant_absence`
--
ALTER TABLE `etudiant_absence`
  ADD PRIMARY KEY (`Etudiant_ID`);

--
-- Indexes for table `etudiant_note`
--
ALTER TABLE `etudiant_note`
  ADD PRIMARY KEY (`Etudiant_ID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `etudiant_absence`
--
ALTER TABLE `etudiant_absence`
  ADD CONSTRAINT `fk_etudiant_absence` FOREIGN KEY (`Etudiant_ID`) REFERENCES `edata` (`Etudiant_ID`) ON DELETE CASCADE;

--
-- Constraints for table `etudiant_note`
--
ALTER TABLE `etudiant_note`
  ADD CONSTRAINT `FK_Etudiant` FOREIGN KEY (`Etudiant_ID`) REFERENCES `edata` (`Etudiant_ID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
