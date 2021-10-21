 --drop table if exists branches CASCADE;
 --drop table if exists cars CASCADE;
 --drop table if exists employees CASCADE;
 --drop table if exists invoice CASCADE;
 --drop table if exists reservations CASCADE;
 --drop table if exists users CASCADE;

INSERT INTO `car_rental`.`branches` (`branch_id`, `additional_info`, `address`, `city`, `name`) VALUES ('1', 'Pagrindinis biuras ', 'Savanorių pr. 191', 'Vilnius', 'Vilniaus filialas');
INSERT INTO `car_rental`.`branches` (`branch_id`, `additional_info`, `address`, `city`, `name`) VALUES ('2', 'Pirmas filialas', 'Laisvės pr. 7', 'Kaunas', 'Kauno filialas');
INSERT INTO `car_rental`.`branches` (`branch_id`, `additional_info`, `address`, `city`, `name`) VALUES ('3', 'Pajūris', 'Danės g. 15', 'Klaipdėda', 'Klaipėdos filialas');
INSERT INTO `car_rental`.`branches` (`branch_id`, `additional_info`, `address`, `city`, `name`) VALUES ('4', 'Naujausias padalinys', 'Staniūnų g. 22', 'Panevėžys', 'Panevėžio filialas');

INSERT INTO `car_rental`.`invoice` (`invoice_id`, `address_line1`, `address_line2`, `address_line3`, `business_compid`, `business_name`, `contract_number`, `expense_amount`, `invoice_number`, `service_description`, `total_count`) VALUES ('1', 'Laisvės al. 37', 'Kaunas', 'LT-31323', '123456789', 'Žvaigždūnė Tvarijonaitė', 'ĄąČčĘęĖėĮįŠšŲųŪūŽž /55/CAR-21-Nr.01/2021', 198.37, 'CAR-21 NR.1', 'Car invoicing', '1');
INSERT INTO `car_rental`.`invoice` (`invoice_id`, `address_line1`, `address_line2`, `address_line3`, `business_compid`, `business_name`, `contract_number`, `expense_amount`, `invoice_number`, `service_description`, `total_count`) VALUES ('2', 'Vilniaus g. 20', 'Kaunas', 'LT-80555', '123456789', 'Kristijonas Butkus', 'ĄąČčĘęĖėĮįŠšŲųŪūŽž /55/CAR-21-Nr.01/2021', 222.37, 'CAR-19 NR.2', 'Car invoicing', '1');
INSERT INTO `car_rental`.`invoice` (`invoice_id`, `address_line1`, `address_line2`, `address_line3`, `business_compid`, `business_name`, `contract_number`, `expense_amount`, `invoice_number`, `service_description`, `total_count`) VALUES ('3', 'Gedimino pr. 66-2', 'Vilnius', 'LT-54222', '123456789', 'Tomas Batonas', 'ĄąČčĘęĖėĮįŠšŲųŪūŽž /55/CAR-21-Nr.01/2021', 188.37, 'CAR-11 NR.3', 'Car invoicing', '1');

INSERT INTO `car_rental`.`cars` (`car_id`, `body_type`, `brand`, `color`, `mileage`, `model`, `seat_count`, `status`, `year`, `price`) VALUES ('1', 'SEDAN', 'VW', 'Grey', '12000', 'Passat', '5', 'A', '2008', '45');
INSERT INTO `car_rental`.`cars` (`car_id`, `body_type`, `brand`, `color`, `mileage`, `model`, `seat_count`, `status`, `year`, `price`) VALUES ('2', 'HATCHBACK', 'VW', 'Silver', '5020', 'Golf 8', '5', 'A', '2020', '50');
INSERT INTO `car_rental`.`cars` (`car_id`, `body_type`, `brand`, `color`, `mileage`, `model`, `seat_count`, `status`, `year`, `price`) VALUES ('3', 'HATCHBACK', 'VW', 'Blue', '3569', 'Golf 8', '5', 'A', '2021', '50');
INSERT INTO `car_rental`.`cars` (`car_id`, `body_type`, `brand`, `color`, `mileage`, `model`, `seat_count`, `status`, `year`, `price`) VALUES ('4', 'COUPE', 'VW', 'Yellow', '7589', 'Beetle', '5', 'A', '2020', '35');
INSERT INTO `car_rental`.`cars` (`car_id`, `body_type`, `brand`, `color`, `mileage`, `model`, `seat_count`, `status`, `year`, `price`) VALUES ('5', 'COUPE', 'Mercedes-Benz', 'Black', '2555', 'SLK 200', '4', 'A', '2020', '85');
INSERT INTO `car_rental`.`cars` (`car_id`, `body_type`, `brand`, `color`, `mileage`, `model`, `seat_count`, `status`, `year`, `price`) VALUES ('6', 'SUV', 'Nissan', 'Black', '24485', 'Qashqai', '4', 'A', '2018', '50');
INSERT INTO `car_rental`.`cars` (`car_id`, `body_type`, `brand`, `color`, `mileage`, `model`, `seat_count`, `status`, `year`, `price`) VALUES ('7', 'SUV', 'VW', 'White', '14485', 'T-Roc', '4', 'A', '2019', '50');
INSERT INTO `car_rental`.`cars` (`car_id`, `body_type`, `brand`, `color`, `mileage`, `model`, `seat_count`, `status`, `year`, `price`) VALUES ('8', 'SUV', 'BMW', 'Black', '5111', 'X1', '4', 'A', '2020', '58');
INSERT INTO `car_rental`.`cars` (`car_id`, `body_type`, `brand`, `color`, `mileage`, `model`, `seat_count`, `status`, `year`, `price`) VALUES ('9', 'PICKUP_TRUCK', 'Honda', 'Blue', '511', 'Ridgeline', '4', 'A', '2021', '100');
INSERT INTO `car_rental`.`cars` (`car_id`, `body_type`, `brand`, `color`, `mileage`, `model`, `seat_count`, `status`, `year`, `price`) VALUES ('10', 'PICKUP_TRUCK', 'Jeep', 'Blue', '3001', 'Gladiator', '4', 'A', '2021', '100');

INSERT INTO `car_rental`.`users` (`user_id`, `email`, `first_name`, `last_name`, `password`, `role`) VALUES ('1', 'andrius@mail.lt', 'Andrius', 'Radžiukynas', 'vienas1', 'USER');
INSERT INTO `car_rental`.`users` (`user_id`, `email`, `first_name`, `last_name`, `password`, `role`) VALUES ('2', 'alvydas@mail.lt', 'Alvydas', 'Sauliauskas', 'du2', 'USER');
INSERT INTO `car_rental`.`users` (`user_id`, `email`, `first_name`, `last_name`, `password`, `role`) VALUES ('3', 'alius@one.lt', 'Alius', 'Nedzinksas', 'trys3', 'ADMIN');
INSERT INTO `car_rental`.`users` (`user_id`, `email`, `first_name`, `last_name`, `password`, `role`) VALUES ('4', 'tomas@gmail.lt', 'Tomas', 'Batonas', 'keturi4', 'GUEST');
INSERT INTO `car_rental`.`users` (`user_id`, `email`, `first_name`, `last_name`, `password`, `role`) VALUES ('5', 'kestis@yahoo.com', 'Kęstutis', 'Brolislovas', 'penki5', 'USER');
INSERT INTO `car_rental`.`users` (`user_id`, `email`, `first_name`, `last_name`, `password`, `role`) VALUES ('6', 'ieva@eiva.com', 'Ieva', 'Ievutė', 'sesi6', 'GUEST');

INSERT INTO `car_rental`.`employees` (`emp_id`, `first_name`, `last_name`, `position`, `working_branch`) VALUES ('1', 'Kęstas', 'Malinauskas', 'admin', 'Kaunas');
INSERT INTO `car_rental`.`employees` (`emp_id`, `first_name`, `last_name`, `position`, `working_branch`) VALUES ('2', 'Saulius', 'Vasilionis', 'supervisor', 'Vilnius');
INSERT INTO `car_rental`.`employees` (`emp_id`, `first_name`, `last_name`, `position`, `working_branch`) VALUES ('3', 'Aldas', 'Genys', 'manager', 'Kaunas');

INSERT INTO `car_rental`.`reservations` (`reservation_id`, `amount`, `date_from`, `date_to`, `branch_id`, `emp_id`, `user_id`, `car_id`) VALUES ('1', '100', '2021-05-01', '2021-06-01', '1', '1', '1', '1');
INSERT INTO `car_rental`.`reservations` (`reservation_id`, `amount`, `date_from`, `date_to`, `branch_id`, `emp_id`, `user_id`, `car_id`) VALUES ('2', '300', '2021-06-01', '2021-08-01', '2', '1', '2', '2');
INSERT INTO `car_rental`.`reservations` (`reservation_id`, `amount`, `date_from`, `date_to`, `branch_id`, `emp_id`, `user_id`, `car_id`) VALUES ('3', '1000', '2021-04-01', '2021-08-01', '3', '1', '1', '3');
INSERT INTO `car_rental`.`reservations` (`reservation_id`, `amount`, `date_from`, `date_to`, `branch_id`, `emp_id`, `user_id`, `car_id`) VALUES ('4', '55', '2021-05-11', '2021-05-12', '1', '2', '3', '4');
INSERT INTO `car_rental`.`reservations` (`reservation_id`, `amount`, `date_from`, `date_to`, `branch_id`, `emp_id`, `user_id`, `car_id`) VALUES ('5', '35', '2021-08-01', '2021-08-01', '4', '1', '2', '5');
INSERT INTO `car_rental`.`reservations` (`reservation_id`, `amount`, `date_from`, `date_to`, `branch_id`, `emp_id`, `user_id`, `car_id`) VALUES ('6', '150', '2021-06-21', '2021-07-01', '2', '1', '3', '6');
INSERT INTO `car_rental`.`reservations` (`reservation_id`, `amount`, `date_from`, `date_to`, `branch_id`, `emp_id`, `user_id`, `car_id`) VALUES ('7', '200', '2021-07-22', '2021-08-01', '3', '3', '1', '7');
INSERT INTO `car_rental`.`reservations` (`reservation_id`, `amount`, `date_from`, `date_to`, `branch_id`, `emp_id`, `user_id`, `car_id`) VALUES ('8', '320', '2021-06-01', '2021-07-14', '2', '1', '3', '8');
INSERT INTO `car_rental`.`reservations` (`reservation_id`, `amount`, `date_from`, `date_to`, `branch_id`, `emp_id`, `user_id`, `car_id`) VALUES ('9', '500', '2021-03-01', '2021-04-01', '2', '2', '3', '9');
INSERT INTO `car_rental`.`reservations` (`reservation_id`, `amount`, `date_from`, `date_to`, `branch_id`, `emp_id`, `user_id`, `car_id`) VALUES ('10', '100', '2021-07-28', '2021-08-03', '2', '1', '2', '10');




