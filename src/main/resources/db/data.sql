INSERT INTO bank_details(account_number, account_type, bank_name, branchcode) VALUES('621214785', 'CHEQUE', 'TEBA BANK', '26704');

INSERT INTO person(name, surname, gender, identitynumber, dateofbirth, age, bank_details_id) VALUES('Tshepo', 'Khumalo', 'Male', '8201315548083', '19820430', 40, 1);

INSERT INTO product(name, type) VALUES ('ret-001', 'RETIREMENT'),
                                                    ('svng-001', 'SAVINGS');

INSERT INTO person_products(person_id, products_id) VALUES(1,1), (1,2);

INSERT INTO address(address_line1, address_line2, address_line3, address_type, city, postal_code, preffered_address, province)
        VALUES('2063', 'Lion Cres', 'Eastbank', 'residential', 'Alexandra', '2090', 'residential', 'Gauteng');

INSERT INTO person_address(person_id, address_id) VALUES(1,1);

INSERT INTO contact(cellphone_no, email_address, preferred_contact_method, telephone_number) VALUES ('083562444', 'INFO@EMAIL.CO.ZA', 'email', '0123654789');

INSERT INTO person_contact(person_id, contact_id) VALUES(1,1);

INSERT INTO statement(created_by, created_date, last_mod_date, modified_by, available_balance, investor_id, product_id, status,withdrawal_amount)
VALUES('user-1','2022-12-28T22:55:28.905Z','2022-12-28T22:55:28.905Z','user-1',500000.00,'1','1','started',5000),
      ('user-2','2022-12-28T22:53:29.905Z','2022-12-28T22:55:28.905Z','user-2',36000.00,'1','2','not started',0);

--CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START 1;
--SELECT setval('hibernate_sequence',(SELECT max(id) FROM public.statement));

