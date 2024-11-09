DROP DATABASE IF EXISTS uc11;
CREATE DATABASE uc11;
USE uc11;

CREATE TABLE produtos (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100),
  valor INT,
  status VARCHAR(100)
);


INSERT INTO produtos (id, nome, valor, status) VALUES
(1, 'PS4', 1500, 'Vendido'),
(2, 'Xbox 360', 800, 'Vendido'),
(3, 'Iphone 12', 4800, 'Vendido'),
(4, 'PS2', 400, 'A Venda');