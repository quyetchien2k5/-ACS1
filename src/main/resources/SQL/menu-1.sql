-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 01, 2024 at 08:34 AM
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
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `image` varchar(500) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id`, `name`, `type`, `image`, `price`) VALUES
('CA_001', 'Ca cam nướng mỡ hành', 'Món khai vị', 'D:\\-ACS1_1\\DACS1\\src\\main\\resources\\Image\\Menu\\Món Cá\\cacamnuongmohanh.png', 170000),
('KV_002', 'Mực Xóc Muối', 'Món khai vị', 'D:\\-ACS1_1\\DACS1\\src\\main\\resources\\Image\\Menu\\Khai vị\\mucxocmuoi.png', 100000),
('KV_001', 'Khoai Tây Chiên', 'Món khai vị', 'D:\\\\-ACS1_1\\\\DACS1\\\\src\\\\main\\\\resources\\\\Image\\\\Menu\\\\Khai vị\\\\khoaitaychien.png', 60000),
('CA_002', 'Cá chim nướng muối ớt', 'Món cá', 'D:\\\\-ACS1_1\\\\DACS1\\\\src\\\\main\\\\resources\\\\Image\\\\Menu\\\\Món Cá\\\\cachimnuongmuoiot.png', 41000);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
