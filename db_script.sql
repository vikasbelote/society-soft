create table sa_transaction_description
(
	 desc_id int auto_increment
    ,transaction_description varchar(500)
    ,general_head_id int
    ,society_id int
    
    ,primary key(desc_id)
    ,foreign key(general_head_id)references sa_general_head(general_head_id)on delete set null on update cascade
    ,foreign key(society_id)references sa_society(society_id)on delete set null on update cascade
);