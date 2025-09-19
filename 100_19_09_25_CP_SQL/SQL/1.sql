/*
Write a query to find the top 5 products by global on-hand quantity

Database: inv_mgmt

products:
=========
  product_id   INT PRIMARY KEY AUTO_INCREMENT,
  sku          VARCHAR(32) NOT NULL UNIQUE,
  name         VARCHAR(128) NOT NULL,
  category     VARCHAR(64)  NOT NULL,
  unit_price   DECIMAL(10,2) NOT NULL,
  reorder_point INT NOT NULL DEFAULT 0,
  discontinued TINYINT(1) NOT NULL DEFAULT 0


warehouses
===========
  warehouse_id INT PRIMARY KEY AUTO_INCREMENT,
  name         VARCHAR(64) NOT NULL,
  city         VARCHAR(64) NOT NULL,
  state        VARCHAR(64) NOT NULL,
  capacity     INT NOT NULL  -- conceptual: max on-hand units


stock_movements
================
  movement_id   BIGINT PRIMARY KEY AUTO_INCREMENT,
  product_id    INT NOT NULL,
  warehouse_id  INT NOT NULL,
  movement_date DATE NOT NULL,
  movement_type ENUM('IN','OUT','ADJUST') NOT NULL,
  qty           INT NOT NULL,      -- positive for IN/ADJUST(add), negative for OUT/ADJUST(remove)
  unit_cost     DECIMAL(10,2) NULL,
  reference     VARCHAR(64) NULL,
  CONSTRAINT fk_sm_prod FOREIGN KEY (product_id) REFERENCES products(product_id),
  CONSTRAINT fk_sm_wh   FOREIGN KEY (warehouse_id) REFERENCES warehouses(warehouse_id),
  INDEX ix_sm_prod_wh_date (product_id, warehouse_id, movement_date),
  INDEX ix_sm_date (movement_date)
  
  
Output
------

+------------+--------+---------+                                                                                       
| product_id | sku    | on_hand |                                                                                       
+------------+--------+---------+                                                                                       
|          5 | HD-005 |     630 |                                                                                       
|          8 | RM-008 |     365 |                                                                                       
|          1 | KB-001 |     360 |                                                                                       
|          2 | MS-002 |     360 |                                                                                       
|          6 | SD-006 |     255 |                                                                                       
+------------+--------+---------+
  
*/

use inv_mgmt;

select p.product_id , p.sku, 
(select sum(qty) from stock_movements where product_id=p.product_id) as on_hand 
from products p order by on_hand desc limit 5
;

