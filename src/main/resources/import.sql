insert into item(id, name, price, special_price_quantity, special_price) values (1, 'A', 40, 3, 70);
insert into item(id, name, price, special_price_quantity, special_price) values (2, 'B', 10, 2, 15);
insert into item(id, name, price, special_price_quantity, special_price) values (3, 'C', 30, 4, 60);
insert into item(id, name, price, special_price_quantity, special_price) values (4, 'D', 25, 2, 404);

insert into special_promotion(id, first_item_id, second_item_id, discount_price, is_active) values (1, 1, 2, 10, true);
insert into special_promotion(id, first_item_id, second_item_id, discount_price, is_active) values (2, 3, 4, 20, true);