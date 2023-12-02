/*LocationModel init data*/
INSERT INTO location_model (continent, country, city)
values ('Europe', 'France', 'Paris');
INSERT INTO location_model (continent, country, city)
values ('Europe', 'Italy', 'Rome');
INSERT INTO location_model (continent, country, city)
values ('Europe', 'United Kingdom', 'London');
INSERT INTO location_model (continent, country, city)
values ('Europe', 'Czech Republic', 'Prague');
INSERT INTO location_model (continent, country, city)
values ('Europe', 'Spain', 'Barcelona');
INSERT INTO location_model (continent, country, city)
values ('Europe', 'Spain', 'Costa del Sol');
INSERT INTO location_model (continent, country, city)
values ('Europe', 'Spain', 'Costa Brava');
INSERT INTO location_model (continent, country, city)
values ('Europe', 'Spain', 'Tenerife');
INSERT INTO location_model (continent, country, city)
values ('Europe', 'Spain', 'Fuerteventura');
INSERT INTO location_model (continent, country, city)
values ('Europe', 'Spain', 'Gran Canaria');
INSERT INTO location_model (continent, country, city)
values ('Europe', 'Spain', 'Lanzarote');
INSERT INTO location_model (continent, country, city)
values ('Asia', 'China', 'Beijing');
INSERT INTO location_model (continent, country, city)
values ('Europe', 'Poland', 'Warsaw');
INSERT INTO location_model (continent, country, city)
values ('Europe', 'Poland', 'Cracow');

/* AddressModel init data*/
/*HOTELE*/
INSERT INTO address_model (street, postal_code, location_id)
values ('15 Pl. Vendôme', '75001', 1);
INSERT INTO address_model (street, postal_code, location_id)
values ('Via Carlo Cattaneo 31', '00185', 2);
INSERT INTO address_model (street, postal_code, location_id)
values ('Circus Road West 15 Switch House West', 'SW11-8BD', 3);
INSERT INTO address_model (street, postal_code, location_id)
values ('157 Waterloo Road', 'DEW-134-SW', 3);
INSERT INTO address_model (street, postal_code, location_id)
values ('380 Kensington High Street', 'W14 8NL', 3);
INSERT INTO address_model (street, postal_code, location_id)
values ('Žitná 1670/5', '110 00', 4);
INSERT INTO address_model (street, postal_code, location_id)
values ('Nad Královskou oborou 920/53', '170 00', 4);
INSERT INTO address_model (street, postal_code, location_id)
values ('Gran Via de les Corts Catalanes 543', '08011', 5);

/*ADDRESY (LOTNISKA)*/
INSERT INTO address_model (street, postal_code, location_id)
values ('Horley', 'RH6 0NP', 3);
INSERT INTO address_model (street, postal_code, location_id)
values ('234 Bath Road', 'UB3 5AP Hayes', 3);
INSERT INTO address_model (street, postal_code, location_id)
values ('Airport Way', 'Luton LU2 9LY', 3);
INSERT INTO address_model (street, postal_code, location_id)
values ('Aviatická', '161 00', 4);
INSERT INTO address_model (street, postal_code, location_id)
values ('Żwirki i Wigury 1', '02-143', 13);
INSERT INTO address_model (street, postal_code, location_id)
values ('Medweckiego 1', '32-083', 14);

/* AirportHotel init data*/
/*LOTNISKA*/
INSERT INTO airport_model (name, address_id)
values ('Vaclav Havel Airport Prague', 12);
INSERT INTO airport_model (name, address_id)
values ('Luton Airport', 11);
INSERT INTO airport_model (name, address_id)
values ('London Heathrow Airport', 10);
INSERT INTO airport_model (name, address_id)
values ('London Gatwick Airport', 9);
INSERT INTO airport_model (name, address_id)
values ('Warsaw Chopin Airport', 5);
INSERT INTO airport_model (name, address_id)
values ('Cracow Airport', 6);

/*HotelModel init data*/
INSERT INTO hotel_model (name, standard, description, address_id)
values ('Ritz', 5, 'Nice hotel', 1);
INSERT INTO hotel_model (name, standard, description, address_id)
values ('Hampton by Hilton London Waterloo', 3, 'Nice hotel', 4);
INSERT INTO hotel_model (name, standard, description, address_id)
values ('Hotel Viking', 2, 'Nice hotel', 3);
INSERT INTO hotel_model (name, standard, description, address_id)
values ('Art Hotel Prague', 4, 'Nice hotel', 7);
INSERT INTO hotel_model (name, standard, description, address_id)
values ('Hotel Prague 1', 4, 'Nice hotel', 6);

/*TripModel init data*/
//TODO
   INSERT INTO trip_model (start_date, end_date, duration, number_of_places, is_promoted, airport_from_id, airport_to_id, hotel_id,price_id)
    values('2024-01-01','2024-01-08',7,50,'true',6,1,5,1);

 INSERT INTO food_model(food_option)
 values('allinc');

  INSERT INTO price_model (price_for_adult,price_for_children, food_id)
 values(120,70,1);



