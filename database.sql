CREATE DATABASE opennews;

use opennews;

create table title (
	id int NOT NULL AUTO_INCREMENT,
    genre varchar(255) not null,
    PRIMARY KEY (id)
);

create table posts (
	id int NOT NULL AUTO_INCREMENT,
    content varchar(255) not null,
    amount_like int not null default(0),
    title_id int not null,
    writing_date date default(CURRENT_DATE),
    CONSTRAINT FK_Title FOREIGN KEY (title_id)
    REFERENCES title(id),
    PRIMARY KEY (id)
);