 DROP DATABASE `lojavirtual`;
 
 CREATE SCHEMA `lojavirtual` ;
 
 USE lojavirtual;
 
 CREATE TABLE `lojavirtual`.`produto` (
  `idProduto` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idProduto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

  
INSERT INTO produto (idProduto, nome, descricao) values
            (1, 'Geladeira', 'Duas portas');
			
INSERT INTO produto (idProduto, nome, descricao) values
            (2, 'Ferro de passar', 'Com vaporizador');

INSERT INTO produto (idProduto, nome, descricao) values
            (3, 'NoteBook Acer', 'I7');	

INSERT INTO produto (idProduto, nome, descricao) values
            (4, 'Cadeira', 'Office');	

INSERT INTO produto (idProduto, nome, descricao) values
            (5, 'Mesa', 'Estudo');				
			
CREATE TABLE `lojavirtual`.`categoria` (
  `IDCATEGORIA` INT NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(255) NULL,
  PRIMARY KEY (`IDCATEGORIA`));

  
ALTER TABLE `lojavirtual`.`produto` 
ADD COLUMN `Id_categoria` INT NULL AFTER `descricao`;

ALTER TABLE `lojavirtual`.`produto` 
ADD INDEX `fk_categoriaProduto_idx` (`Id_categoria` ASC) VISIBLE;
;
ALTER TABLE `lojavirtual`.`produto` 
ADD CONSTRAINT `fk_categoriaProduto`
  FOREIGN KEY (`Id_categoria`)
  REFERENCES `lojavirtual`.`categoria` (`IDCATEGORIA`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  
INSERT INTO CATEGORIA (nome) VALUES ('Eletrodomésticos');
INSERT INTO CATEGORIA (nome) VALUES ('Eletrônico');
INSERT INTO CATEGORIA (nome) VALUES ('Móvel');

UPDATE PRODUTO SET ID_CATEGORIA = 1 WHERE IDPRODUTO IN (1,2);
UPDATE PRODUTO SET ID_CATEGORIA = 2 WHERE IDPRODUTO IN (3);
UPDATE PRODUTO SET ID_CATEGORIA = 3 WHERE IDPRODUTO IN (4,5);
