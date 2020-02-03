-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time:  7 яну 2020 в 13:40
-- Версия на сървъра: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shop`
--

-- --------------------------------------------------------

--
-- Структура на таблица `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Схема на данните от таблица `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Пица'),
(2, 'Спагети'),
(3, 'Ориз'),
(4, 'Напитки');

-- --------------------------------------------------------

--
-- Структура на таблица `item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE IF NOT EXISTS `item` (
  `order_id` int(10) UNSIGNED NOT NULL,
  `product_id` int(10) UNSIGNED NOT NULL,
  `quantity` tinyint(3) UNSIGNED NOT NULL,
  `price` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура на таблица `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(500) NOT NULL,
  `note` varchar(500) DEFAULT NULL,
  `created` datetime NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура на таблица `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(500) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `price` int(10) UNSIGNED NOT NULL,
  `category_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Схема на данните от таблица `product`
--

INSERT INTO `product` (`id`, `name`, `description`, `image`, `price`, `category_id`) VALUES
(1, 'Премиум микс за пица', 'Premium смесена пица с 9 вида гарнитури: колбаси, шунка, говеждо месо, пиле, ананас, гъби, лук, чушки и маслини', '01.png', 18, 1),
(2, 'Пица с морски дарове сос Песто', 'Премиум пица с морски дарове Pesto със скариди, калмари, миди и гъби върху типичен сос Песто, гарниран със сирене моцарела и листа босилек.', '02.png', 24, 1),
(3, 'Пица с морски дарове', 'Пица с морски дарове Tornado с калмари, миди, раци пръчка, ананас, зелен пипер, лук на сос хиляди острови и първокласно сирене моцарела.', '17.png', 17, 1),
(4, 'Смесена пица', 'Смесена пица с традиционни аромати с говеждо месо, наденица, черен пипер, гъби и лук, гарнирана с прочуто сирене моцарела.', '05.png', 15, 1),
(5, 'Спагети със сос от черен пипер', 'Спагети със сос от черен пипер.', 'FA21.png', 13, 2),
(6, 'Спагети с кремообразен сос', 'Спагети с кремообразен сос', 'FA23.png', 16, 2),
(7, 'Пица с морски дарове с сос от черен пипер', 'Пица с морски дарове със сос от черен пипер, известен със скариди, калмари, ракови пръчици, лук, покрит с ароматен сос от черен пипер и сирене моцарела.', '04.png', 12, 1),
(8, 'Пица с месо и колбаси', 'Вкусна и белтъчна пица месо и колбаси със осолено месо, говеждо месо, шунка и колбаси.', '03.png', 8, 1),
(9, 'Пица с риба тон', 'Tuna Pizza има лек морски вкус с риба тон, пръчица от раци, лук и ананас в соса на Хилядите острови.', '09.png', 7, 1),
(10, 'Пиле с пица сос от черен пипер', 'Пиле, пилешка наденица, гъби, лук, зелени чушки, моцарела.', '07.png', 7, 1),
(11, 'Тестени кюфтета в доматен сос', 'Тестени кюфтета в доматен сос', 'FA22.png', 5, 2),
(12, 'Спагети с доматен сос от месо', 'Спагети с доматен сос от месо', 'FA17.png', 4, 1),
(13, 'Пържен ориз с пилешки крилca с чесън и скара', 'Пържен ориз с пилешки криле с чесън и скара', 'GA09.png', 9, 3),
(14, 'Пържен ориз с морски дарове', 'Пържен ориз с морски дарове', 'GA08.png', 22, 3),
(15, 'Coca-Cola 330ml', 'Coca-Cola 330ml', 'NC27.png', 2, 4),
(16, 'Sprite 330ml', 'Sprite 330ml', 'NC29.png', 2, 4);

-- --------------------------------------------------------

--
-- Структура на таблица `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Схема на данните от таблица `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_CUSTOMER');

-- --------------------------------------------------------

--
-- Структура на таблица `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Схема на данните от таблица `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`) VALUES
(2, 'Viko', 'viko@gmail.com', '$2a$10$sVIlYshxsqadMvJvXhOfXeWMxyeVtG9GtVWIWo9yspieQsrTooo7i');

-- --------------------------------------------------------

--
-- Структура на таблица `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` int(10) UNSIGNED NOT NULL,
  `role_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Схема на данните от таблица `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(2, 1),
(2, 2);

--
-- Ограничения за дъмпнати таблици
--

--
-- Ограничения за таблица `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `FKd1g72rrhgq1sf7m4uwfvuhlhe` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKtbipb7uob2ud46fp9qcn6jl86` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения за таблица `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения за таблица `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения за таблица `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
