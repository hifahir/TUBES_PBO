-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 13, 2023 at 06:55 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pegawai`
--

-- --------------------------------------------------------

--
-- Table structure for table `badan_keuangan`
--

CREATE TABLE `badan_keuangan` (
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `saldo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `jabatan` varchar(50) NOT NULL,
  `gaji` int(11) NOT NULL,
  `bisalembur` tinyint(1) NOT NULL,
  `pajak` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `kodelembur`
--

CREATE TABLE `kodelembur` (
  `kode` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `kodelemburterpakai`
--

CREATE TABLE `kodelemburterpakai` (
  `kode` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transaksigaji`
--

CREATE TABLE `transaksigaji` (
  `gajiWaktuItu` text NOT NULL,
  `pajakWaktuItu` text NOT NULL,
  `bulanTahun` varchar(64) NOT NULL,
  `username` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transaksilembur`
--

CREATE TABLE `transaksilembur` (
  `lemburWaktuItu` text NOT NULL,
  `pajakWaktuItu` text NOT NULL,
  `haribulantahun` varchar(64) NOT NULL,
  `username` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `transaksigaji`
--
ALTER TABLE `transaksigaji`
  ADD KEY `fk_usernameKaryawan` (`username`);

--
-- Indexes for table `transaksilembur`
--
ALTER TABLE `transaksilembur`
  ADD KEY `fk_karyawan` (`username`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksigaji`
--
ALTER TABLE `transaksigaji`
  ADD CONSTRAINT `fk_usernameKaryawan` FOREIGN KEY (`username`) REFERENCES `karyawan` (`username`);

--
-- Constraints for table `transaksilembur`
--
ALTER TABLE `transaksilembur`
  ADD CONSTRAINT `fk_karyawan` FOREIGN KEY (`username`) REFERENCES `karyawan` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
