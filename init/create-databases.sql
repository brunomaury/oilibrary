create user "oilibrary-dev" with password 'oilibrary-dev';
create database "oilibrary-dev";
Alter Database "oilibrary-dev" Owner To "oilibrary-dev";

create user "oilibrary-test" with password 'oilibrary-test';
create database "oilibrary-test";
Alter Database "oilibrary-test" Owner To "oilibrary-test";

GRANT ALL ON "oilibrary-dev" To "oilibrary-dev";
GRANT ALL ON "oilibrary-test" To "oilibrary-test";