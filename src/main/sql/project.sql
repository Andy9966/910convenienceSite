create table table_comment
(
	comment_id varchar(40) not null
		primary key,
	user_id varchar(40) not null,
	content text not null,
	create_time datetime not null
)
engine=InnoDB
;

create table table_memo
(
	memo_id varchar(50) not null
		primary key,
	edit_time datetime not null,
	send_time datetime not null,
	user_id varchar(50) not null,
	state int(2) not null,
	memo_content varchar(255) not null
)
engine=InnoDB
;

create table table_user
(
	user_id varchar(50) not null comment '用户Id'
		primary key,
	user_nickname varchar(20) not null comment '用户昵称',
	user_password varchar(32) not null comment '用户密码',
	user_email varchar(50) not null comment '用户邮箱
	',
	acti_state int not null comment '激活状态，0表示未激活，1表示激活',
	acti_code varchar(50) not null comment '随机验证码',
	salt varchar(50) not null comment '随机盐，用于加密密码',
	token_exptime datetime default CURRENT_TIMESTAMP not null comment '用于判断邮箱链接有效时间'
)
engine=InnoDB
;

