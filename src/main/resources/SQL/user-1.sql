-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 01, 2024 at 08:35 AM
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
-- Database: `đacs1`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `age` int(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `date` date NOT NULL,
  `job` date DEFAULT NULL,
  `workingdays` int(50) DEFAULT NULL,
  `salary` double NOT NULL,
  `avatar` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `gender`, `age`, `password`, `date`, `job`, `workingdays`, `salary`, `avatar`) VALUES
(2, 'Hua Huynh Anh', 'anhhuahuynh@gmail.com', 'Nam', 18, 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '2024-04-30', '0000-00-00', 0, 12000000, 'D:\\\\-ACS1\\\\src\\\\main\\\\resources\\\\Image\\\\background.png'),
(3, 'Chien', 'chien@gmail.com', 'Nam', 18, 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '2024-04-30', '0000-00-00', 0, 12345678, 'D:\\\\-ACS1\\\\src\\\\main\\\\resources\\\\Image\\\\background.png'),
(4, 'Hoang', 'hoang@gmail.com', 'Nam', 18, 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', '2024-05-01', '0000-00-00', 0, 7000000, 'D:\\\\-ACS1\\\\src\\\\main\\\\resources\\\\Image\\\\background.png'),
(5, 'Anh', NULL, NULL, 0, NULL, '2024-05-01', '2024-05-02', 1, 410000, 'D:\\\\-ACS1_1\\\\DACS1\\\\src\\\\main\\\\resources\\\\Image\\\\background.png'),
(6, 'Tai', NULL, NULL, 0, NULL, '2024-05-01', '2024-05-03', 2, 770000, '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
