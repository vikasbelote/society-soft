create table sa_general_head
(
	 general_head_id int auto_increment
    ,general_head_name varchar(100)
    
    ,primary key(general_head_id)
);

create table sa_transaction
(
	 transaction_id int auto_increment
    ,general_head_id int
    ,transaction_type varchar(10)
    ,transaction_amount double
    ,transaction_description varchar(500)
    ,transaction_date datetime
    
    ,primary key(transaction_id)
    ,foreign key(general_head_id)references sa_general_head(general_head_id)on delete set null on update cascade
);

INSERT INTO `society_app`.`sa_menu` (`menu_id`, `menu_name`) VALUES ('7', 'General Head');
INSERT INTO `society_app`.`sa_menu` (`menu_id`, `menu_name`) VALUES ('8', 'Record Transaction');
