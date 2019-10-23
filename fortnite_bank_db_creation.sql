-- create the database

create table player (
	id serial primary key,
	first_name varchar(32) not null check(length(first_name) > 0),
	username varchar(16) not null,
	password TEXT NOT null,
	password_salt text not null,
	date_created timestamp default(current_date)
);

create table vbucks_account_type (
	id serial primary key,
	account_type varchar(16) not null
);

create table vbucks_account (
	id serial primary key,
	balance integer default(0),
	date_created date default(current_date),
	account_type integer references vbucks_account_type(id)
);

create table player_vbucks_account (
	id serial primary key,
	player_id integer not null,
	account_id integer not null,
	constraint account_player_id_fkey
		foreign key (player_id)
		references player (id)
		on delete cascade,
	constraint account_account_id_fkey
		foreign key (account_id)
		references vbucks_account (id)
		on delete cascade
);


insert into vbucks_account_type values
	(1, 'Checking'),
	(2, 'Savings');
	

create role VBANK_MANAGER LOGIN password 'p4ssw0rd';
grant all privileges on all tables in schema public to VBANK_MANAGER;
grant all privileges on all sequences in schema public to VBANK_MANAGER;



alter table player
add unique (username);

alter table vbucks_account
add column status varchar(8) default('Open');



-- createVbucksAccount function
create or replace function createVbucksAccount(playerId bigint, accountType varchar)
returns integer as $$
	declare
		accountId integer;
	begin
		insert into vbucks_account (account_type) (select id from vbucks_account_type where account_type = accountType) returning id into accountId;
		insert into player_vbucks_account (player_id, account_id) values (playerId, accountId);
		return accountId;
	end;
$$ language plpgsql;


-- withdrawVbucks function
create or replace function withdrawVbucks(amount bigint, accountId bigint)
returns boolean as $$
    begin
        if ((select status from vbucks_account where id = accountId) = 'Open') then
            update vbucks_account set balance = (balance - amount) where id = accountId;
            return true;
        else return false;
    end if;
end; $$ language plpgsql;


-- depositVbucks function
create or replace function depositVbucks(amount bigint, accountId bigint)
returns boolean as $$
    begin
	    if ((select status from vbucks_account where id = accountId) = 'Open') then
			update vbucks_account set balance = (balance + amount) where id = accountId;
			return true;
		else return false;
    end if;
end; $$ language plpgsql;


-- scamPlayer function
create or replace function scamPlayer(name_of_user text)
returns boolean as $$
	declare
		accountId integer;
	begin
		delete from player_vbucks_account where player_id = (select id from player where player.username = name_of_user) returning account_id into accountId;
		delete from vbucks_account where id = accountId;
		delete from player where username = name_of_user;
		return true;
	end;
$$ language plpgsql;


-- testing functions
select withdrawVbucks(500, 2);
select depositVbucks(500, 2);
select createVbucksAccount(1, 'Savings');
select scamPlayer('meredith');

-- testing queries
select player_account.first_name, player_vbucks_account.* from player_account left join player_vbucks_account on player_account.username = 'weas3ls';



select vbucks_account.id, player.first_name, vbucks_account_type.account_type, vbucks_account.balance, vbucks_account.date_created, player.username from player_vbucks_account 
	inner join
player on player_vbucks_account.player_id = player.id
	inner join
vbucks_account on vbucks_account.id = player_vbucks_account.account_id
	inner join
vbucks_account_type on vbucks_account.account_type = vbucks_account_type.id where player.id = 5;



select player_vbucks_account.*, player_account.first_name from player_vbucks_account left join player_account on player_account.username = 'weas3ls'
	union
select vbucks_account_type.account_type from vbucks_account_type where vbucks_account_type.id = player_vbucks_account.account_type_id;


update player_vbucks_account set balance = balance + 350 where player_id = (select id from player_account where username = 'weas3ls');


insert into player_vbucks_account (player_id, account_type_id) values ((select id from player_account where username = 'weas3ls'), (select id from vbucks_account_type where account_type = 'checking'));

insert into vbucks_account (account_type) (select id from vbucks_account_type where account_type = 'Checking') returning id;