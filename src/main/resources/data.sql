INSERT INTO public.user_data (id, building_number, city, email, first_name, last_name, password, phone_number, postal_code, street) VALUES ('7209afb7-d37f-4772-9b31-b21133ea9540', 0, '', 'zaq134215@gmail.com', 'Maciek', 'Mackowy', '$2a$12$sl.J.SlDTlL1xW2F5qS1nuXhjyD5KDWhydPi/ev.YDzLpaMS.jO4.', '782942320', '', '');
INSERT INTO public.user_data (id, building_number, city, email, first_name, last_name, password, phone_number, postal_code, street) VALUES ('9049b16f-e50a-47cc-934e-51abeb03d079', 9, 'Janow', 'zaq134215@wp.pl', 'Kacper', 'Tarasiuk', '$2a$12$sl.J.SlDTlL1xW2F5qS1nuXhjyD5KDWhydPi/ev.YDzLpaMS.jO4.', '721398192', '16-130', 'Sokólska');
INSERT INTO public.user_data (id, building_number, city, email, first_name, last_name, password, phone_number, postal_code, street) VALUES ('315057dc-b051-11ec-b909-0242ac120002', 0, '', 'randomemail@kozak.pl', 'Szklana', 'Walendzewicz', '$2a$12$sl.J.SlDTlL1xW2F5qS1nuXhjyD5KDWhydPi/ev.YDzLpaMS.jO4.', '721398191', '', '');
--
INSERT INTO public.principle_groups (id, code, name) VALUES ('012778fd-f2fb-47c9-a8c0-5af83d8c4840', 'C00', 'Clients');
INSERT INTO public.principle_groups (id, code, name) VALUES ('cec34027-7f0c-400a-90b7-9ec8010af265', 'A00', 'Admins');
INSERT INTO public.principle_groups (id, code, name) VALUES ('3270c2d8-ef43-41ff-a2d5-27c0b34d66b3', 'E00', 'Employees');
INSERT INTO public.user_groups (customer_id, group_id) VALUES ('9049b16f-e50a-47cc-934e-51abeb03d079', 'cec34027-7f0c-400a-90b7-9ec8010af265');
INSERT INTO public.user_groups (customer_id, group_id) VALUES ('7209afb7-d37f-4772-9b31-b21133ea9540', '3270c2d8-ef43-41ff-a2d5-27c0b34d66b3');

INSERT INTO public.user_groups (customer_id, group_id) VALUES ('315057dc-b051-11ec-b909-0242ac120002', '012778fd-f2fb-47c9-a8c0-5af83d8c4840');
INSERT INTO public.client (id, user_id) VALUES ('7387f968-3ee6-4455-82be-f9ac05b88fe2', '9049b16f-e50a-47cc-934e-51abeb03d079');

INSERT INTO public.client (id, user_id) VALUES ('d2007d7e-b051-11ec-b909-0242ac120002', '315057dc-b051-11ec-b909-0242ac120002');

INSERT INTO public.employee (id, user_id) VALUES ('44129e12-87eb-457c-9d50-6aaa36b0ff71', '7209afb7-d37f-4772-9b31-b21133ea9540');
