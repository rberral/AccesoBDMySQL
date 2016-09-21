-- phpMyAdmin SQL Dump
-- version 3.5.5
-- http://www.phpmyadmin.net
--
-- Servidor: sql7.freesqldatabase.com
-- Tiempo de generación: 24-08-2016 a las 11:54:53
-- Versión del servidor: 5.5.49-0ubuntu0.14.04.1
-- Versión de PHP: 5.3.28

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `sql7131893`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evento`
--

CREATE TABLE IF NOT EXISTS `evento` (
  `id_evento` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `descripcion` text CHARACTER SET ascii,
  `fecha_evento` date DEFAULT NULL,
  PRIMARY KEY (`id_evento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evento_invitado`
--

CREATE TABLE IF NOT EXISTS `evento_invitado` (
  `id_evento` int(11) NOT NULL,
  `id_invitado` int(11) NOT NULL,
  PRIMARY KEY (`id_evento`,`id_invitado`),
  KEY `id_evento` (`id_evento`,`id_invitado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `invitado`
--

CREATE TABLE IF NOT EXISTS `invitado` (
  `id_invitado` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` text CHARACTER SET ascii NOT NULL,
  `apellidos` text CHARACTER SET ascii,
  `email` text CHARACTER SET ascii,
  `id_acompannante` int(11) DEFAULT '0',
  `confirma_evento` int(11) DEFAULT '2',
  PRIMARY KEY (`id_invitado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notificacion`
--

CREATE TABLE IF NOT EXISTS `notificacion` (
  `id_notificacion` int(11) NOT NULL,
  `id_invitado` int(11) NOT NULL,
  `tipo_notif` text CHARACTER SET ascii,
  `comentario` text CHARACTER SET ascii,
  PRIMARY KEY (`id_notificacion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Clave',
  `nombre` text CHARACTER SET ascii NOT NULL,
  `apellidos` text CHARACTER SET ascii,
  `email` text CHARACTER SET ascii,
  `telefono` int(11) DEFAULT NULL,
  `fecha_alta` date DEFAULT NULL,
  `fecha_baja` date DEFAULT NULL,
  `usuario_premium` tinyint(1) DEFAULT '0',
  `fecha_inicio_premium` date DEFAULT NULL,
  `fecha_fin_premium` date DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
