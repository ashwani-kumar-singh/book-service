Create Table Books(
id int NOT NULL AUTO_INCREMENT,
title varchar(255) NOT NULL,
description varchar(512),
author varchar(255) NOT NULL,
category varchar(255) NOT NULL,
total_pages int,
cost int,
isbn varchar(50) UNIQUE NOT NULL,
published_date DATE,
publisher_id int NOT NULL,
created_by varchar(20) NOT NULL,
updated_by varchar (20),
created_at TIMESTAMP DEFAULT NOW(),
update_at TIMESTAMP,
PRIMARY KEY (id)
);