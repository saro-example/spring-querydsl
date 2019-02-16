# spring-querydsl

# step 1

# step 2


## create data (account)
```
CREATE TABLE account (
	no bigserial NOT NULL,
	account varchar(64) NOT NULL,
	password varchar(512) NOT NULL,
	CONSTRAINT account_pk PRIMARY KEY (no)
);

CREATE UNIQUE INDEX account_account ON account USING btree (account);

CREATE TABLE account_role (
	no bigint NOT NULL,
	role varchar(64) NOT NULL,
	CONSTRAINT account_role_pk PRIMARY KEY (no, role)
);

-- test data
-- d760688da522b4dc3350e6fb68961b0934f911c7d0ff337438cabf4608789ba94ce70b6601d7e08a279ef088716c4b1913b984513fea4c557d404d0598d4f2f1 -> sha3_512('1234')
insert into account values (nextval('account_no_seq'), 'saro', 'd760688da522b4dc3350e6fb68961b0934f911c7d0ff337438cabf4608789ba94ce70b6601d7e08a279ef088716c4b1913b984513fea4c557d404d0598d4f2f1');

insert into account_role values (currval('account_no_seq'), 'master');
insert into account_role values (currval('account_no_seq'), 'admin');
```
