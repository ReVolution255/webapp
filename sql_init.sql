CREATE TABLE users (
id bigserial PRIMARY KEY,
name varchar
);
CREATE TABLE groups (
id bigserial PRIMARY KEY,
name varchar,
parent_id bigint REFERENCES groups(id)
);
CREATE TABLE roles (
id bigserial PRIMARY KEY,
name varchar
);
CREATE TABLE permissions (
id bigserial PRIMARY KEY,
name varchar
);
CREATE TABLE user_groups (
id bigserial PRIMARY KEY,
user_id bigserial REFERENCES users(id),
group_id bigserial REFERENCES groups(id)
);
CREATE TABLE role_permissions (
id bigserial PRIMARY KEY,
permission_id bigserial REFERENCES permissions(id),
role_id bigserial REFERENCES roles(id)
);
CREATE TABLE user_roles (
id bigserial PRIMARY KEY,
role_id bigserial REFERENCES roles(id),
user_id bigserial REFERENCES users(id)
);
