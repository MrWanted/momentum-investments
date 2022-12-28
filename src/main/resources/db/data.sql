INSERT INTO person
      (id, name, surname, gender, identitynumber, dateofbirth, age)
VALUES(1, 'Tshepo', 'Khumalo', 'Male', '8201315548083', '19820430', 40),
      (2, 'Jacqueline', 'Cruise', 'Female', '9204295548083', '19920429', 30);

INSERT INTO product
      (id, name, type, balance)
      VALUES (1, 'product1', 'RETIREMENT',500000.00),
             (2, 'product2', 'SAVINGS',30000.00);

INSERT INTO person_product(product_id,person_id) VALUES(1,1), (1,2);

