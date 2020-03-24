-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-03-2020 a las 09:48:39
-- Versión del servidor: 10.1.36-MariaDB
-- Versión de PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `papeleriadb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `precio` varchar(255) NOT NULL,
  `folio` int(10) NOT NULL,
  `tipo_id` int(10) NOT NULL,
  `proveedor_id` int(10) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `descripcion`, `precio`, `folio`, `tipo_id`, `proveedor_id`, `cantidad`) VALUES
(1, 'lapizin', 'descripcin', '10.9', 1, 1, 1, 12),
(2, 'Cuaderno', 'De lineas', '13.5', 998, 1, 1, 11),
(3, 'Kola loka', 'Pega de locuraaa', '10.56', 12334, 2, 2, 6),
(4, 'lapiz234', 'oh lol', '12.50', 1234543, 1, 1, 15),
(5, 'HOJA BLANCA', 'MUUY BLANCA', '1.00', 12345678, 1, 2, 15),
(6, 'hojas de colores', 'xd', '2.50', 12345678, 1, 1, 15),
(7, 'resistol', '5000', '15.56', 2213, 2, 1, 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidoPaterno` varchar(50) NOT NULL,
  `apellidoMaterno` varchar(50) NOT NULL,
  `telefono` varchar(255) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `empresa` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id`, `nombre`, `apellidoPaterno`, `apellidoMaterno`, `telefono`, `direccion`, `correo`, `empresa`) VALUES
(1, 'Enrique', 'Gonzalez', 'Espinoza', '2323234', 'SKKSSDDS', 'SDSDDS', 'SDDS'),
(2, 'Santiago', 'Lopez', 'Ballinas', '741255896', 'Tuxtla', 'santy@gmail.com', 'ninguna'),
(4, 'Ibraham Surisadai', 'Jimenez', 'Lopez', '9611231810', 'Enrique Segoviano', 'isuriloop@gmail.com', 'SuriTeCH');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoproducto`
--

CREATE TABLE `tipoproducto` (
  `id` int(10) UNSIGNED NOT NULL,
  `tipo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipoproducto`
--

INSERT INTO `tipoproducto` (`id`, `tipo`) VALUES
(1, 'escolar'),
(2, 'Random');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(10) UNSIGNED NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidoPaterno` varchar(50) NOT NULL,
  `apellidoMaterno` varchar(50) NOT NULL,
  `tipoUsuario` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `username`, `password`, `nombre`, `apellidoPaterno`, `apellidoMaterno`, `tipoUsuario`) VALUES
(1, 'admin', 'admin2020', 'Francisco Moises', 'Orantes', 'Trejo', 'Administrador'),
(2, 'cajero', '123456', 'Santiago', 'Lopez', 'Ballinas', 'Cajero');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id` int(10) UNSIGNED NOT NULL,
  `producto_id` int(10) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `monto` float NOT NULL,
  `fecha` varchar(255) NOT NULL,
  `folio` varchar(50) NOT NULL,
  `usuario_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`id`, `producto_id`, `cantidad`, `monto`, `fecha`, `folio`, `usuario_id`) VALUES
(6, 1, 5, 54.5, '09-mar-2020', 'h0ud0ek3ho9g783s3gcocqtcnv', 1),
(7, 2, 2, 27, '09-mar-2020', 'h0ud0ek3ho9g783s3gcocqtcnv', 1),
(8, 1, 3, 32.7, '09-mar-2020', 'mkeptvo1a076m4v57louqh5fp2', 1),
(9, 2, 5, 67.5, '09-mar-2020', 'mkeptvo1a076m4v57louqh5fp2', 1),
(10, 1, 3, 32.7, '10-mar-2020', 'iea81i6rf61n6f4q8vgerbn268', 1),
(11, 2, 4, 54, '10-mar-2020', 'iea81i6rf61n6f4q8vgerbn268', 1),
(12, 3, 5, 52.8, '10-mar-2020', 'iea81i6rf61n6f4q8vgerbn268', 1),
(13, 1, 3, 32.7, '10-mar-2020', '8gpsgtgs1tj77dou1terd1nlhf', 1),
(14, 2, 4, 54, '10-mar-2020', '8gpsgtgs1tj77dou1terd1nlhf', 1),
(15, 3, 5, 52.8, '10-mar-2020', '8gpsgtgs1tj77dou1terd1nlhf', 1),
(16, 1, 2, 21.8, '10-mar-2020', '8vtoqvh8qemklt5gr2tr31gc1e', 1),
(17, 2, 1, 13.5, '10-mar-2020', '8vtoqvh8qemklt5gr2tr31gc1e', 1),
(18, 3, 2, 21.12, '10-mar-2020', '8vtoqvh8qemklt5gr2tr31gc1e', 1),
(19, 1, 3, 32.7, '10-mar-2020', '12okdaei423i0k1li5r334u7o8', 1),
(20, 2, 5, 67.5, '10-mar-2020', '12okdaei423i0k1li5r334u7o8', 1),
(21, 3, 2, 21.12, '10-mar-2020', '12okdaei423i0k1li5r334u7o8', 1),
(22, 1, 7, 76.3, '10-mar-2020', 'cki3nojdh9b1rqm5jom08omhl7', 1),
(23, 2, 5, 67.5, '10-mar-2020', 'cki3nojdh9b1rqm5jom08omhl7', 1),
(24, 3, 8, 84.48, '10-mar-2020', 'cki3nojdh9b1rqm5jom08omhl7', 1),
(25, 1, 3, 32.7, '10-mar-2020', 'ifnf0qcgcr9g4i3bq2sefliin3', 1),
(26, 2, 5, 67.5, '10-mar-2020', 'ifnf0qcgcr9g4i3bq2sefliin3', 1),
(27, 3, 2, 21.12, '10-mar-2020', 'ifnf0qcgcr9g4i3bq2sefliin3', 1),
(28, 1, 7, 76.3, '10-mar-2020', 'o9a5evqg30uesojil86vj8sop7', 1),
(29, 2, 5, 67.5, '10-mar-2020', 'o9a5evqg30uesojil86vj8sop7', 1),
(30, 3, 8, 84.48, '10-mar-2020', 'o9a5evqg30uesojil86vj8sop7', 1),
(31, 1, 5, 54.5, '10-mar-2020', '4ka8j1lmj4it6nqkjc8fbr44cl', 1),
(32, 2, 3, 40.5, '10-mar-2020', '4ka8j1lmj4it6nqkjc8fbr44cl', 1),
(33, 3, 2, 21.12, '10-mar-2020', '4ka8j1lmj4it6nqkjc8fbr44cl', 1),
(34, 1, 5, 54.5, '10-mar-2020', 'c1rkqkf6vcr9elvpi5eupmmb50', 1),
(35, 2, 7, 94.5, '10-mar-2020', 'c1rkqkf6vcr9elvpi5eupmmb50', 1),
(36, 3, 8, 84.48, '10-mar-2020', 'c1rkqkf6vcr9elvpi5eupmmb50', 1),
(37, 1, 5, 54.5, '10-mar-2020', '1bg5b75metvai2nkrlgtn9v372', 1),
(38, 2, 3, 40.5, '10-mar-2020', '1bg5b75metvai2nkrlgtn9v372', 1),
(39, 3, 8, 84.48, '10-mar-2020', '1bg5b75metvai2nkrlgtn9v372', 1),
(40, 1, 5, 54.5, '10-mar-2020', 'boahpana5aocfoc00a7r64ogtb', 1),
(41, 2, 7, 94.5, '10-mar-2020', 'boahpana5aocfoc00a7r64ogtb', 1),
(42, 3, 2, 21.12, '10-mar-2020', 'boahpana5aocfoc00a7r64ogtb', 1),
(43, 1, 3, 32.7, '10-mar-2020', 'lr9rn8un6eonltg65ips13m16k', 1),
(44, 2, 4, 54, '10-mar-2020', 'lr9rn8un6eonltg65ips13m16k', 1),
(45, 3, 5, 52.8, '10-mar-2020', 'lr9rn8un6eonltg65ips13m16k', 1),
(46, 1, 2, 21.8, '10-mar-2020', 'oso9rr8of5tnhtsindb68ms5he', 1),
(47, 2, 3, 40.5, '10-mar-2020', 'oso9rr8of5tnhtsindb68ms5he', 1),
(48, 3, 4, 42.24, '10-mar-2020', 'oso9rr8of5tnhtsindb68ms5he', 1),
(49, 1, 3, 32.7, '10-mar-2020', '8etmtffau5fc5dkb4gu6mpbr9j', 1),
(50, 2, 5, 67.5, '10-mar-2020', '8etmtffau5fc5dkb4gu6mpbr9j', 1),
(51, 3, 2, 21.12, '10-mar-2020', '8etmtffau5fc5dkb4gu6mpbr9j', 1),
(52, 1, 7, 76.3, '10-mar-2020', 'h8l06eeps320rar458fsvfk2vo', 1),
(53, 2, 5, 67.5, '10-mar-2020', 'h8l06eeps320rar458fsvfk2vo', 1),
(54, 3, 8, 84.48, '10-mar-2020', 'h8l06eeps320rar458fsvfk2vo', 1),
(55, 1, 1, 10.9, '10-mar-2020', 'hoeipf0573qd7lnsdu0qcs5m9r', 1),
(56, 2, 3, 40.5, '10-mar-2020', 'hoeipf0573qd7lnsdu0qcs5m9r', 1),
(57, 3, 1, 10.56, '10-mar-2020', 'hoeipf0573qd7lnsdu0qcs5m9r', 1),
(58, 1, 3, 32.7, '21-mar-2020', 'i2rq8fufg0vhvuadalpdb8e457', 1),
(59, 2, 3, 40.5, '21-mar-2020', 'i2rq8fufg0vhvuadalpdb8e457', 1),
(60, 3, 3, 31.68, '21-mar-2020', 'i2rq8fufg0vhvuadalpdb8e457', 1),
(61, 1, 7, 76.3, '21-mar-2020', 'bn5759gruf3cglda963lpt14uh', 1),
(62, 2, 2, 27, '21-mar-2020', 'bn5759gruf3cglda963lpt14uh', 1),
(63, 3, 3, 31.68, '21-mar-2020', 'bn5759gruf3cglda963lpt14uh', 1),
(64, 1, 5, 54.5, '21-mar-2020', 'ek3ofgrm6iqhih7odsrd0tpfcv', 1),
(65, 2, 5, 67.5, '21-mar-2020', 'ek3ofgrm6iqhih7odsrd0tpfcv', 1),
(66, 3, 4, 42.24, '21-mar-2020', 'ek3ofgrm6iqhih7odsrd0tpfcv', 1),
(67, 1, 5, 54.5, '21-mar-2020', '9o89ev4rf32m1rtee84ef621v3', 1),
(68, 2, 10, 135, '21-mar-2020', '9o89ev4rf32m1rtee84ef621v3', 1),
(69, 3, 5, 52.8, '21-mar-2020', '9o89ev4rf32m1rtee84ef621v3', 1),
(70, 1, 5, 54.5, '21-mar-2020', 'ijjcs84o2ibdtg3gem7cpgaab2', 1),
(71, 2, 10, 135, '21-mar-2020', 'ijjcs84o2ibdtg3gem7cpgaab2', 1),
(72, 3, 5, 52.8, '21-mar-2020', 'ijjcs84o2ibdtg3gem7cpgaab2', 1),
(73, 2, 5, 67.5, '21-mar-2020', 'v1lc17vjbbsvni0s0q53b0ci8n', 1),
(74, 1, 1, 10.9, '21-mar-2020', 'r1s4fo9do73r4jlmnmr304rs4', 1),
(75, 2, 2, 27, '21-mar-2020', 'r1s4fo9do73r4jlmnmr304rs4', 1),
(76, 3, 3, 31.68, '21-mar-2020', 'r1s4fo9do73r4jlmnmr304rs4', 1),
(77, 1, 1, 10.9, '21-mar-2020', 'adurldi61bm0lakem527ocr3a8', 1),
(78, 2, 2, 27, '21-mar-2020', 'adurldi61bm0lakem527ocr3a8', 1),
(79, 3, 3, 31.68, '21-mar-2020', 'adurldi61bm0lakem527ocr3a8', 1),
(80, 1, 1, 10.9, '21-mar-2020', 'h2fcsbpco7qus7j5ehd3u2g7j0', 1),
(81, 2, 2, 27, '21-mar-2020', 'h2fcsbpco7qus7j5ehd3u2g7j0', 1),
(82, 3, 3, 31.68, '21-mar-2020', 'h2fcsbpco7qus7j5ehd3u2g7j0', 1),
(83, 1, 1, 10.9, '21-mar-2020', 'sciq5l78eu62v88hj16u14pbd1', 1),
(84, 2, 2, 27, '21-mar-2020', 'sciq5l78eu62v88hj16u14pbd1', 1),
(85, 3, 3, 31.68, '21-mar-2020', 'sciq5l78eu62v88hj16u14pbd1', 1),
(86, 1, 1, 10.9, '21-mar-2020', 'edp8gdrqqn394863qihm4ft1j', 1),
(87, 2, 2, 27, '21-mar-2020', 'edp8gdrqqn394863qihm4ft1j', 1),
(88, 3, 3, 31.68, '21-mar-2020', 'edp8gdrqqn394863qihm4ft1j', 1),
(89, 1, 1, 10.9, '21-mar-2020', '87ujsf61isl7irf2jsvehuk1g0', 1),
(90, 2, 2, 27, '21-mar-2020', '87ujsf61isl7irf2jsvehuk1g0', 1),
(91, 3, 3, 31.68, '21-mar-2020', '87ujsf61isl7irf2jsvehuk1g0', 1),
(92, 1, 1, 10.9, '21-mar-2020', 'i971acvv3uk2oh4cgknl8l85jj', 1),
(93, 2, 2, 27, '21-mar-2020', 'i971acvv3uk2oh4cgknl8l85jj', 1),
(94, 3, 3, 31.68, '21-mar-2020', 'i971acvv3uk2oh4cgknl8l85jj', 1),
(95, 1, 1, 10.9, '21-mar-2020', 'mufqe0bq4cgnhq5vhvllmlqpb6', 1),
(96, 2, 2, 27, '21-mar-2020', 'mufqe0bq4cgnhq5vhvllmlqpb6', 1),
(97, 3, 3, 31.68, '21-mar-2020', 'mufqe0bq4cgnhq5vhvllmlqpb6', 1),
(98, 3, 2, 21.12, '22-mar-2020', 'av7ib963qi96nous4g2ihkerpp', 1),
(99, 1, 2, 21.8, '24-mar-2020', 'vtfn3ec39cgg4sbc9ep9ba867r', 1),
(100, 2, 3, 40.5, '24-mar-2020', 'hv2q2nbp53630ojj38orhrpvra', 1),
(101, 3, 3, 31.68, '24-mar-2020', 'hdnbb4gqnqg0j1uu45manti9ts', 1),
(102, 1, 3, 32.7, '24-mar-2020', 'm7kkpov47d88nad8s1kua9j1ve', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipoproducto`
--
ALTER TABLE `tipoproducto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tipoproducto`
--
ALTER TABLE `tipoproducto`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=103;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
