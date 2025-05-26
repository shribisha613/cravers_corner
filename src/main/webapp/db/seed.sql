USE cravers_corner;

INSERT INTO categories (name, description, created_at, updated_at) VALUES
('Korean', 'Traditional dishes from Korea, including rice, vegetables, and meats.', '2025-05-18 10:42:42', '2025-05-18 10:42:42'),
('Chinese', 'Classic Chinese cuisine with a variety of stir-fried, steamed, and noodle-based dishes.', '2025-05-18 10:42:42', '2025-05-18 10:42:42'),
('Nepali', 'Traditional Nepali foods such as dal bhat, momo, and gundruk.', '2025-05-18 10:42:42', '2025-05-18 10:42:42'),
('Western', 'Popular Western dishes including burgers, pizzas, steaks, and pastas.', '2025-05-18 10:42:42', '2025-05-18 10:42:42'),
('Tibetan', 'Dishes native to Tibet, including thukpa, momos, and butter tea.', '2025-05-18 10:42:42', '2025-05-18 10:42:42'),
('Snacks', 'Light and easy-to-eat foods such as chips, samosas, and spring rolls.', '2025-05-18 10:42:42', '2025-05-18 10:42:42'),
('Dessert', 'Sweet treats like cakes, ice creams, pastries, and puddings.', '2025-05-18 10:42:42', '2025-05-18 14:20:23');


INSERT INTO users (first_name, last_name, email, role, phone, password, created_at, updated_at, username, profile_image_url, current_address, shipping_address) VALUES
('Admin', 'User', 'admin@gmail.com', 'admin', '8888888888', 'LqPh0bR/ESRvAzeOXerSWQ==', '2025-05-08 14:49:05', '2025-05-08 14:49:42', 'adminuser', 'profile_photos/default_profile.png', 'Pokhara', 'Pokhara'),
('Diya', 'Gurung', 'diya@gmail.com', 'customer', '6666666666', 'aXkNkZIF12+XKikhtvvLog==', '2025-05-08 14:50:56', '2025-05-08 14:50:56', 'diyagurung', 'profile_photos/default_profile.png', 'Malepatan', 'Malepatan'),
('Juna', 'Chhetri', 'juna@gmail.com', 'customer', '4444444444', 'EuzEDjLNR7vXG29+L3BQKw==', '2025-05-08 14:51:34', '2025-05-08 14:51:34', 'chitrakala', 'profile_photos/default_profile.png', 'Talchowk', 'Talchowk'),
('Mishama', 'Thapa', 'mishma@gmail.com', 'customer', '8888888888', 'jMY2KtYh0Y6DKVg0nk/j8g==', '2025-05-08 14:52:10', '2025-05-08 14:52:10', 'mishmathapa', 'profile_photos/default_profile.png', 'Chipledhunga', 'Chipledhunga'),
('Yashica', 'Magar', 'yashica@gmail.com', 'customer', '4444444444', 'ExY59wNACaHp3z3iOYvF8Q==', '2025-05-08 14:52:53', '2025-05-08 14:52:53', 'yashica', 'profile_photos/default_profile.png', 'Lakeside Pokhara', 'Lakeside Pokhara'),
('Shribisha', 'Buddhacharya', 'shribisha@gmail.com', 'customer', '9807654321', 'C1GZTvGGNKMnV5j0z9nNlg==', '2025-05-08 14:03:31', '2025-05-08 14:49:48', 'shribisha', 'profile_photos/jeykey.jpg', 'Kathmandu, Nepal', 'Kathmandu, Nepal');



INSERT INTO foods (name, description, price, category_id, image_url, status, serving_size, created_at, updated_at) VALUES
('Nepali Thakali Bhat', 'Thakali Bhat', 400.00, 3, 'food_images/bhat.jpg', 'available', 'Small', '2025-05-08 14:14:21', '2025-05-08 14:14:21'),
('Bibimbap Korean', 'Bibimbap Korean', 700.00, 1, 'food_images/Bibimbap.jpg', 'available', 'Medium', '2025-05-08 14:23:20', '2025-05-08 14:23:20'),
('Buff Salad', 'Fresh Buff Salad', 800.00, 6, 'food_images/BuffSalad.png', 'available', 'Large', '2025-05-08 14:27:57', '2025-05-08 14:28:43'),
('Chicken Burger', 'Chicken Burger (Extra Cheese)', 400.00, 4, 'food_images/ChickenBurger.jpg', 'available', 'Small', '2025-05-08 14:33:34', '2025-05-08 14:33:34'),
('Western Food Combo', 'French fries, pizza and more', 1500.00, 4, 'food_images/Favplatter.jpg', 'available', 'Large', '2025-05-08 14:40:28', '2025-05-08 14:40:28'),
('Kimbap', 'Korean Kimbap', 700.00, 1, 'food_images/gimbap.jpg', 'available', 'Medium', '2025-05-08 14:41:02', '2025-05-08 14:41:02'),
('Burritos', 'Burritos', 400.00, 4, 'food_images/Burritos.jpg', 'available', 'Small', '2025-05-08 14:41:25', '2025-05-08 14:41:25'),
('Bacon Burger', 'Best Bacon Burger', 450.00, 4, 'food_images/BaconBurger.jpg', 'available', 'Small', '2025-05-08 14:41:54', '2025-05-08 14:41:54'),
('Momo', 'Chicken Momo', 120.00, 3, 'food_images/cutemomo.jpg', 'available', 'Small', '2025-05-08 14:42:31', '2025-05-08 14:42:31'),
('Chicken Sisame', 'Sesame Chicken', 700.00, 6, 'food_images/sesameChicken.jpg', 'available', 'Medium', '2025-05-08 14:43:39', '2025-05-08 14:43:39'),
('Mixed Icecream', 'Vanilla, Butterscotch', 300.00, 7, 'food_images/download.jpg', 'available', 'Medium', '2025-05-08 14:44:18', '2025-05-08 14:44:18'),
('BlackBean', 'Black bean noodles', 600.00, 5, 'food_images/blackbean.jpg', 'available', 'Large', '2025-05-08 14:44:41', '2025-05-08 14:44:41'),
('Chinese Food', 'Best Chinese', 678.00, 2, 'food_images/Bibim.jpg', 'available', 'Medium', '2025-05-19 20:00:15', '2025-05-20 15:58:03'),
('Noodles', 'Noodles', 400.00, 1, 'food_images/JholLaphing.jpg', 'available', 'Large', '2025-05-20 16:00:10', '2025-05-20 16:00:10');



INSERT INTO carts (customer_id, total_amount, created_at, updated_at) VALUES
(6, 400.00, '2025-05-18 10:45:55', '2025-05-20 13:02:16'),
(2, 2556.00, '2025-05-19 23:32:44', '2025-05-20 01:42:55'),
(4, 450.00, '2025-05-19 23:40:18', '2025-05-20 01:15:56'),
(3, 800.00, '2025-05-20 00:49:48', '2025-05-20 00:49:48'),
(5, 450.00, '2025-05-20 00:51:24', '2025-05-20 00:57:14');



INSERT INTO cart_items (cart_id, food_id, quantity, price, subtotal, created_at, updated_at) VALUES
(4, 3, 1, 800.00, 800.00, '2025-05-20 00:49:48', '2025-05-20 00:49:48'),
(5, 8, 1, 450.00, 450.00, '2025-05-20 00:57:10', '2025-05-20 00:57:10'),
(3, 8, 1, 450.00, 450.00, '2025-05-20 01:15:53', '2025-05-20 01:15:53');


INSERT INTO orders (customer_id, order_date, status, total_amount, order_note, created_at, updated_at) VALUES
(6, '2025-05-20 00:37:42', 'completed', 2178.00, 'Please make it fast', '2025-05-20 00:37:42', '2025-05-20 13:51:41'),
(2, '2025-05-20 00:40:52', 'completed', 678.00, 'Dont make it spicy', '2025-05-20 00:40:52', '2025-05-20 13:52:02'),
(3, '2025-05-20 00:49:50', 'completed', 800.00, 'Please Deliver It in 2 hours', '2025-05-20 00:49:50', '2025-05-20 14:09:22'),
(5, '2025-05-20 00:51:26', 'completed', 1800.00, 'Please make it less spicy', '2025-05-20 00:51:26', '2025-05-20 14:18:03'),
(6, '2025-05-20 00:52:49', 'completed', 900.00, 'My fav item', '2025-05-20 00:52:49', '2025-05-20 14:09:44'),
(6, '2025-05-20 00:54:34', 'pending', 2100.00, 'ALso add wassabi', '2025-05-20 00:54:34', '2025-05-20 00:55:00'),
(5, '2025-05-20 00:57:16', 'pending', 450.00, 'Extra Cheese please', '2025-05-20 00:57:16', '2025-05-20 00:57:35'),
(2, '2025-05-20 00:59:24', 'pending', 1400.00, 'No wassabi', '2025-05-20 00:59:24', '2025-05-20 00:59:35'),
(6, '2025-05-20 01:03:17', 'pending', 400.00, NULL, '2025-05-20 01:03:17', '2025-05-20 01:03:20'),
(6, '2025-05-20 01:07:22', 'pending', 800.00, 'Make it spicy', '2025-05-20 01:07:22', '2025-05-20 01:07:33'),
(4, '2025-05-20 01:12:15', 'pending', 3000.00, 'I need it in an hour', '2025-05-20 01:12:15', '2025-05-20 01:12:29'),
(4, '2025-05-20 01:15:57', 'pending', 450.00, 'No Tomato', '2025-05-20 01:15:57', '2025-05-20 01:16:06'),
(6, '2025-05-20 01:23:35', 'pending', 800.00, 'I like this', '2025-05-20 01:23:35', '2025-05-20 01:23:45'),
(6, '2025-05-20 01:24:45', 'completed', 1200.00, NULL, '2025-05-20 01:24:45', '2025-05-20 14:05:23'),
(6, '2025-05-20 01:26:34', 'pending', 1200.00, NULL, '2025-05-20 01:26:34', '2025-05-20 01:26:35'),
(4, '2025-05-20 01:27:27', 'pending', 1200.00, NULL, '2025-05-20 01:27:27', '2025-05-20 01:27:29'),
(6, '2025-05-20 01:28:44', 'completed', 800.00, NULL, '2025-05-20 01:28:44', '2025-05-20 14:05:16'),
(6, '2025-05-20 01:39:46', 'pending', 360.00, 'Extra Chatni and sauce', '2025-05-20 01:39:46', '2025-05-20 01:39:57'),
(2, '2025-05-20 01:42:57', 'completed', 2556.00, 'Make it faster Please', '2025-05-20 01:42:57', '2025-05-20 14:05:06'),
(5, '2025-05-20 11:38:47', 'pending', 400.00, 'Please make fast', '2025-05-20 11:38:47', '2025-05-20 11:38:58');

USE cravers_corner;

INSERT INTO order_items (order_id, food_id, quantity, price, subtotal, created_at, updated_at) VALUES
(1, 3, 1, 800.00, 800.00, '2025-05-20 00:37:42', '2025-05-20 13:51:41'),
(2, 13, 1, 678.00, 678.00, '2025-05-20 00:40:52', '2025-05-20 13:52:02'),
(3, 3, 1, 800.00, 800.00, '2025-05-20 00:49:50', '2025-05-20 14:09:22'),
(4, 8, 1, 450.00, 450.00, '2025-05-20 00:51:26', '2025-05-20 14:18:03'),
(5, 1, 1, 400.00, 400.00, '2025-05-20 00:52:49', '2025-05-20 14:09:44'),
(6, 2, 3, 700.00, 2100.00, '2025-05-20 00:54:34', '2025-05-20 00:55:00'),
(7, 8, 1, 450.00, 450.00, '2025-05-20 00:57:16', '2025-05-20 00:57:35'),
(8, 2, 2, 700.00, 1400.00, '2025-05-20 00:59:24', '2025-05-20 00:59:35'),
(9, 1, 1, 400.00, 400.00, '2025-05-20 01:03:17', '2025-05-20 01:03:20'),
(10, 3, 1, 800.00, 800.00, '2025-05-20 01:07:22', '2025-05-20 01:07:33'),
(11, 5, 2, 1500.00, 3000.00, '2025-05-20 01:12:15', '2025-05-20 01:12:29'),
(12, 8, 1, 450.00, 450.00, '2025-05-20 01:15:57', '2025-05-20 01:16:06'),
(13, 2, 1, 800.00, 800.00, '2025-05-20 01:23:35', '2025-05-20 01:23:45'),
(14, 6, 2, 600.00, 1200.00, '2025-05-20 01:24:45', '2025-05-20 14:05:23'),
(15, 6, 2, 600.00, 1200.00, '2025-05-20 01:26:34', '2025-05-20 01:26:35'),
(16, 6, 2, 600.00, 1200.00, '2025-05-20 01:27:27', '2025-05-20 01:27:29'),
(17, 2, 1, 800.00, 800.00, '2025-05-20 01:28:44', '2025-05-20 14:05:16'),
(18, 9, 2, 180.00, 360.00, '2025-05-20 01:39:46', '2025-05-20 01:39:57'),
(19, 3, 3, 852.00, 2556.00, '2025-05-20 01:42:57', '2025-05-20 14:05:06'),
(20, 1, 1, 400.00, 400.00, '2025-05-20 11:38:47', '2025-05-20 11:38:58');



