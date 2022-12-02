CREATE TABLE IF NOT EXISTS payments (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  expiration varchar(255), NOT NULL
  code varchar(4) NOT NULL,
  status varchar(255) NOT NULL,
  order_id bigint(20) NOT NULL,
  payment_method bigint(20) NOT NULL,
  PRIMARY KEY (id)
)
