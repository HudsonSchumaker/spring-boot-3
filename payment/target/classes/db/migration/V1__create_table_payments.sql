CREATE TABLE IF NOT EXISTS payments (
  id BIGINT AUTO_INCREMENT NOT NULL,
   value DECIMAL NOT NULL,
   name VARCHAR(100) NULL,
   number VARCHAR(19) NULL,
   expiration VARCHAR(7) NULL,
   code VARCHAR(3) NULL,
   status VARCHAR(255) NOT NULL,
   order_id BIGINT NOT NULL,
   payment_method BIGINT NOT NULL,
   CONSTRAINT pk_payments PRIMARY KEY (id)
);
