INSERT INTO bank_details(id, account_number, account_type, bank_name, branchcode) VALUES(1,'621214785', 'CHEQUE', 'TEBA BANK', '26704');

INSERT INTO person(id, name, surname, gender, identitynumber, dateofbirth, age, bank_details_id) VALUES(1, 'Tshepo', 'Khumalo', 'Male', '8201315548083', '19820430', 40, 1);

INSERT INTO product(id, name, type, balance) VALUES (1, 'product1', 'RETIREMENT',500000.00),
                                                    (2, 'product2', 'SAVINGS',30000.00);

INSERT INTO person_products(person_id, products_id) VALUES(1,1), (1,2);

INSERT INTO address(id, address_line1, address_line2, address_line3, address_type, city, postal_code, preffered_address, province)
        VALUES(1, '336', 'Lion Cres', 'Eastbank', 'residential', 'Alexandra', '2090', 'residential', 'Gauteng');

INSERT INTO person_address(person_id, address_id) VALUES(1,1);

INSERT INTO contact(id, cellphone_no, email_address, preferred_contact_method, telephone_number) VALUES (1, '083562444', 'INFO@EMAIL.CO.ZA', 'email', '0123654789');

INSERT INTO person_contact(person_id, contact_id) VALUES(1,1);

