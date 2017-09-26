create table sa_menu
(
	 menu_id int auto_increment
    ,menu_name varchar(100)
    
    ,primary key(menu_id)
);

create table sa_user_access_rights
(
	 user_id int
    ,menu_id int
    
    ,primary key(user_id, menu_id)
);


INSERT INTO `society_app`.`sa_menu` (`menu_id`, `menu_name`) VALUES ('1', 'Create User');
INSERT INTO `society_app`.`sa_menu` (`menu_id`, `menu_name`) VALUES ('2', 'Reminder');
INSERT INTO `society_app`.`sa_menu` (`menu_id`, `menu_name`) VALUES ('3', 'Balance Sheet');
INSERT INTO `society_app`.`sa_menu` (`menu_id`, `menu_name`) VALUES ('4', 'Income & Expense');
INSERT INTO `society_app`.`sa_menu` (`menu_id`, `menu_name`) VALUES ('5', 'Maintenence');
INSERT INTO `society_app`.`sa_menu` (`menu_id`, `menu_name`) VALUES ('6', 'User List');
