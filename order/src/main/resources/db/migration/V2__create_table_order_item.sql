CREATE TABLE order_item (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  order_id bigint(20) NOT NULL,
  quantity int(11) NOT NULL,
  description varchar(255) DEFAULT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES orders(id)
)