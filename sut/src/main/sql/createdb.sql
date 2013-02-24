DROP DATABASE IF EXISTS agdp2013;
CREATE DATABASE agdp2013;
GRANT ALL ON agdp2013.* to 'agdp'@'%' identified by '2013';
USE agdp2013;
CREATE TABLE customer (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  street_address varchar(255) NOT NULL,
  version bigint(20) NOT NULL default 0,
  city_address varchar(255) NOT NULL,
  balance decimal(19,2) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE invoice (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  version bigint(20) NOT NULL DEFAULT 0,
  amount decimal(19,2) NOT NULL,
  customer_id bigint(20) NOT NULL,
  due_date datetime DEFAULT NULL,
  invoice_date datetime NOT NULL,
  invoice_number decimal(19,2) NOT NULL,
  payment_date datetime DEFAULT NULL,
  payment_status varchar(255) NOT NULL,
  PRIMARY KEY (id),
  KEY cust_fk (customer_id),
  CONSTRAINT cust_fk FOREIGN KEY (customer_id) REFERENCES customer (id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
