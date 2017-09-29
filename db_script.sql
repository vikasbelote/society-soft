alter table sa_general_head add is_default bit;
alter table sa_general_head add society_id int;
alter table sa_general_head add foreign key(society_id)references sa_society(society_id)on delete set null on update cascade;

alter table sa_transaction add society_id int;
alter table sa_transaction add foreign key(society_id)references sa_society(society_id)on delete set null on update cascade;

INSERT INTO `society_app`.`sa_general_head_section` (`section_id`, `section_name`) VALUES ('1', 'LIABILITIES AND CAPITAL');
INSERT INTO `society_app`.`sa_general_head_section` (`section_id`, `section_name`) VALUES ('2', 'PROPERTIES AND ASSETS');

INSERT INTO `society_app`.`sa_general_head` (`general_head_id`, `general_head_name`, `section_id`, `isDefault`) VALUES ('1', 'SHARE CAPITAL', '1', b'1');
INSERT INTO `society_app`.`sa_general_head` (`general_head_id`, `general_head_name`, `section_id`, `isDefault`) VALUES ('2', 'RESERVES & SURPLUS FUND ', '1', b'1');
INSERT INTO `society_app`.`sa_general_head` (`general_head_id`, `general_head_name`, `section_id`, `isDefault`) VALUES ('3', 'SINKING FUND', '1', b'1');
INSERT INTO `society_app`.`sa_general_head` (`general_head_id`, `general_head_name`, `section_id`, `isDefault`) VALUES ('4', 'MEMBERS CONTRIBUTION TOWARDS', '1', b'1');
INSERT INTO `society_app`.`sa_general_head` (`general_head_id`, `general_head_name`, `section_id`, `isDefault`) VALUES ('5', 'MEMBERS CONTRIBUTION RECEIVED IN ADVANCE', '1', b'1');
INSERT INTO `society_app`.`sa_general_head` (`general_head_id`, `general_head_name`, `section_id`, `isDefault`) VALUES ('6', 'CURRENT LIABILITIES', '1', b'1');
INSERT INTO `society_app`.`sa_general_head` (`general_head_id`, `general_head_name`, `section_id`, `isDefault`) VALUES ('7', 'FIXED ASSETS', '2', b'1');
INSERT INTO `society_app`.`sa_general_head` (`general_head_id`, `general_head_name`, `section_id`, `isDefault`) VALUES ('8', 'INVESTMENTS', '2', b'1');
INSERT INTO `society_app`.`sa_general_head` (`general_head_id`, `general_head_name`, `section_id`, `isDefault`) VALUES ('9', 'CASH & BANK BALANCE', '2', b'1');
INSERT INTO `society_app`.`sa_general_head` (`general_head_id`, `general_head_name`, `section_id`, `isDefault`) VALUES ('10', 'EXCESS OF INCOME OVER EXPENDITURE ', '2', b'1');

