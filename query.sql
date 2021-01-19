-- Project phase 1
-- inserts
-- Craig Donato - crd69
-- Sam Skupien - sss78
--
-- BoutiqueCoffee database

-- STORE table inserts
-- ------------------------------------------------------------------------------------------------------------------
insert into store (name, address, store_type, gps_long, gps_lat)
values ('Coffee Grounds','123 Pacific Street', 'Kiosk', -124.453125, 6.158027 );
insert into store (name, address, store_type, gps_long, gps_lat)
values ('The Java Cup', '863 Atlantic Drive', 'Cafe', -46.77861, 28142864);
insert into store(name, address, store_type, gps_long, gps_lat)
values ('Caffeine Champion', '1024 Bearing Road', 'Hangout', 179.427242, 55.9538);
-- ------------------------------------------------------------------------------------------------------------------

-- MEMBERLEVEL table inserts
-- ------------------------------------------------------------------------------------------------------------------
insert into memberlevel (name, booster_factor) values ('Steel', 0.10);
insert into memberlevel (name, booster_factor) values ('Bronze', 0.20);
insert into memberlevel (name, booster_factor) values ('Silver', 0.30);
insert into memberlevel (name, booster_factor) values ('Gold', 0.40);
insert into memberlevel (name, booster_factor) values ('Employee', 0.50);
-- ------------------------------------------------------------------------------------------------------------------

-- CUSTOMER table inserts
-- ------------------------------------------------------------------------------------------------------------------
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Sam', 'Skupien', 'sss78@pitt.edu', 5);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Craig', 'Donato', 'crd69@pitt.edu', 5);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Sandra', 'Someone','',1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Peter', 'Perterson', '', 1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Billy', 'Billiard', 'poolShark@fake.com', 2);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Julia', 'Jackson', '', 3);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Steve', 'Jackson', '', 4);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Dave', 'Davely', 'dd@fakery.com', 2);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Victor', 'Hickory', 'trees@fake.com', 3);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Monica', 'Labrador', '', 1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Audrey', 'Campbell', '', 1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Alexander', 'Bailey', '', 1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Hank', 'Hill', 'propain@fake.com', 2);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Colin', 'Bower', '', 1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Elizabeth', 'Newman', '', 1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Ronald', 'McDonald', 'burgers@fake.com', 3);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Gordon', 'Walker', '', 1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Molly', 'Robertson', 'random@fake.com', 2);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Harry', 'Skinner', '', 1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Jack', 'Wilson', 'jw@fake.com', 3);
-- ------------------------------------------------------------------------------------------------------------------

-- COFFEE table inserts
-- ------------------------------------------------------------------------------------------------------------------
insert into coffee (name, description, intensity, price, reward_points, redeem_points)
values ('Coffee Espresso', 'Full flavor', 9, 2.80, 10, 250);
insert into coffee (name, description, intensity, price, reward_points, redeem_points)
values ('Coffee Light', 'Weak flavor', 2, 1.50, 5, 100);
insert into coffee (name, description, intensity, price, reward_points, redeem_points)
values ('Coffee Medium', 'full flavor', 5, 2.00, 5, 100);
insert into coffee (name, description, intensity, price, reward_points, redeem_points)
values ('Coffee Dark', 'bold flavor', 8, 2.00, 5, 100);
insert into coffee (name, description, intensity, price, reward_points, redeem_points)
values ('Green Tea', 'Light and earthy', 1, 1.80, 10, 150);
insert into coffee (name, description, intensity, price, reward_points, redeem_points)
values ('Black Tea', 'Bold and earthy', 5, 2.20, 10, 200);
-- ------------------------------------------------------------------------------------------------------------------

-- OFFERCOFFEE table inserts
-- ------------------------------------------------------------------------------------------------------------------
insert into offercoffee values (1, 2);
insert into offercoffee values (1, 3);
insert into offercoffee values (1, 4);
insert into offercoffee values (2, 1);
insert into offercoffee values (2, 2);
insert into offercoffee values (2, 3);
insert into offercoffee values (2, 5);
insert into offercoffee values (3, 1);
insert into offercoffee values (3, 2);
insert into offercoffee values (3, 3);
insert into offercoffee values (3, 4);
insert into offercoffee values (3, 5);
-- ------------------------------------------------------------------------------------------------------------------

-- PROMOTION table inserts
-- ------------------------------------------------------------------------------------------------------------------
insert into promotion (name, start_date, end_date) values ('Dark Promo', '2019-07-09', '2019-08-31');
insert into promotion (name, start_date, end_date) values ('Light Promo', '2019-08-01', '2019-08-31');
-- ------------------------------------------------------------------------------------------------------------------

-- HASPROMOTION table inserts
-- ------------------------------------------------------------------------------------------------------------------
insert into haspromotion values (3, 1);
insert into haspromotion values (3, 2);
insert into haspromotion values (1, 1);
-- ------------------------------------------------------------------------------------------------------------------

-- PROMOTEFOR table inserts
-- ------------------------------------------------------------------------------------------------------------------
insert into promotefor values (1, 4);
insert into promotefor values (2, 2);
-- ------------------------------------------------------------------------------------------------------------------

-- Test inserts for view with trigger for purchases
-- ------------------------------------------------------------------------------------------------------------------
-- purchases
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (1, 3, 5, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (2, 3, 1, 2, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (20, 1, 2, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (10, 2, 2, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (3, 3, 5, 5, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (5, 3, 5, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (6, 3, 5, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (7, 1, 5, 2, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (4, 1, 4, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (14, 2, 4, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (4, 2, 4, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (20, 2, 1, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (14, 1, 2, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (15, 3, 2, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (13, 3, 4, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (10, 3, 4, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (9, 3, 6, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (8, 1, 6, 10, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (7, 3, 5, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (20, 1, 2, 6, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (19, 1, 3, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (17, 1, 3, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (15, 3, 2, 20, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (14, 2, 1, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (3, 2, 4, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (2, 3, 4, 30, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (2, 3, 4, 2, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (6, 2, 4, 2, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (7, 3, 3, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (12, 1, 1, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (13, 3, 5, 2, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (17, 2, 5, 2, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (3, 2, 6, 3, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (1, 3, 4, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (13, 2, 3, 4, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (17, 3, 3, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (7, 3, 2, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (2, 3, 2, 4, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (19, 3, 2, 10, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (18, 2, 4, 9, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (20, 1, 4, 8, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (14, 1, 5, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (15, 3, 5, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (10, 3, 1, 4, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (3, 3, 2, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (4, 3, 3, 2, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (5, 1, 4, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (1, 1, 5, 3, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (6, 1, 6, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (9, 3, 1, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (11, 2, 3, 5, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (16, 3, 4, 1, 0);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (16, 3, 4, 20, 0);
-- redeems
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (8, 3, 4, 0, 1);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (2, 3, 6, 0, 1);
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity) values (20, 3, 3, 0, 1);
-- ------------------------------------------------------------------------------------------------------------------
