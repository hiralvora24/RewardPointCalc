/* Table Creation */
create table customer (customer_id integer, customer_name character(50) );
create table transaction (transaction_id integer, customer_id integer, transaction_date date, amount double);

/* Data Insertion */

/* Data Insert into Customer Table */

insert into customer(customer_id, customer_name) values (1, 'Hiral');
insert into customer(customer_id, customer_name) values (2, 'John');
insert into customer(customer_id, customer_name) values (3, 'Sita');
insert into customer(customer_id, customer_name) values (4, 'Chaitanya');
insert into customer(customer_id, customer_name) values (5, 'Ram');

/* Data Insert into Transaction Table */
insert into transaction(transaction_id, customer_id, transaction_date, amount) values (101, 1, '2022-09-08', 125);
insert into transaction(transaction_id, customer_id, transaction_date, amount) values (102, 1, '2022-08-01', 238);
insert into transaction(transaction_id, customer_id, transaction_date, amount) values (103, 1, '2022-07-14', 98);
insert into transaction(transaction_id, customer_id, transaction_date, amount) values (104, 2, '2022-09-25', 340);
insert into transaction(transaction_id, customer_id, transaction_date, amount) values (105, 2, '2023-08-28', 136);
insert into transaction(transaction_id, customer_id, transaction_date, amount) values (106, 2, '2022-07-05', 142);
insert into transaction(transaction_id, customer_id, transaction_date, amount) values (107, 3, '2022-09-05', 340);
insert into transaction(transaction_id, customer_id, transaction_date, amount) values (108, 3, '2022-08-07', 65);
insert into transaction(transaction_id, customer_id, transaction_date, amount) values (109, 3, '2022-07-03', 125);
insert into transaction(transaction_id, customer_id, transaction_date, amount) values (110, 4, '2022-09-01', 238);
insert into transaction(transaction_id, customer_id, transaction_date, amount) values (111, 4, '2022-08-14', 98);
insert into transaction(transaction_id, customer_id, transaction_date, amount) values (112, 4, '2022-07-25', 340);
insert into transaction(transaction_id, customer_id, transaction_date, amount) values (113, 5, '2023-09-28', 136);
insert into transaction(transaction_id, customer_id, transaction_date, amount) values (114, 5, '2022-08-05', 142);
insert into transaction(transaction_id, customer_id, transaction_date, amount) values (115, 5, '2022-07-05', 340);

/* Commit Transactions */

COMMIT;