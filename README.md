# spring-querydsl

# STEP 1
## init data
```
/* ex) postgresql */
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

# STEP 2
## set application.properties
```
# ex postgresql
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url= jdbc:postgresql://127.0.0.1:5432/dbname?charSet=UTF-8&prepareThreshold=1 
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation: true
```

# STEP 3
## build
```
gradle task :compileQuerydsl
# apply source folder src/main/generated
```

# STEP 4
## execute
```
-- list
http://localhost:8080/account/0
-- insert
http://localhost:8080/account/save/ac/myaccount/pw/mypassword
-- search
http://localhost:8080/find/sa
```
