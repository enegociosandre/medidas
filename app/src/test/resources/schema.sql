
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `celular` varchar(20) DEFAULT NULL,
  `data_cadastro` date DEFAULT NULL,
  `email` varchar(254) DEFAULT NULL,
  `nome` varchar(150) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `pet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data_cadastro` date DEFAULT NULL,
  `nome` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS  `endereco` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bairro` varchar(200) DEFAULT NULL,
  `cep` varchar(20) DEFAULT NULL,
  `cidade` varchar(200) DEFAULT NULL,
  `complemento` varchar(10) DEFAULT NULL,
  `data_cadastro` date DEFAULT NULL,
  `numero` varchar(10) DEFAULT NULL,
  `rua` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS  `atendimento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data_cadastro` date DEFAULT NULL,
  `descricao` varchar(150) NOT NULL,
  `cliente_id` int NOT NULL,
  `endereco_id` int DEFAULT NULL,
  `pet_id` int NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`),
  FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`),
  FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
);