DROP TABLE IF EXISTS GEOLOCATION;
CREATE TABLE GEOLOCATION (
id INT AUTO_INCREMENT  PRIMARY KEY,
device_id VARCHAR(50) NOT NULL,
log_date DATE NOT NULL,
latitude INT(8) NOT NULL,
longitude INT(8) NOT NULL
);