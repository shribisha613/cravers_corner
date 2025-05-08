USE cravers_corner;

-- Insert Categories Data
INSERT INTO Categories (name, description) VALUES 
('Korean', 'Traditional dishes from Korea, including rice, vegetables, and meats often served with kimchi.'),
('Chinese', 'Classic Chinese cuisine with a variety of stir-fries, rice, noodles, and dim sum.'),
('Nepali', 'Traditional Nepali foods such as dal bhat, momo, and chatamari, with diverse regional flavors.'),
('Western', 'Popular Western dishes including burgers, pizzas, pasta, and salads.'),
('Tibetan', 'Dishes native to Tibet, including thukpa, momos, and traditional barley bread.'),
('Snacks', 'Light and easy-to-eat foods such as chips, samosas, and finger foods.'),
('Dessert', 'Sweet treats like cakes, ice creams, pastries, and puddings.');

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
('BlackBean', 'Black bean noodles', 600.00, 5, 'food_images/blackbean.jpg', 'available', 'Large', '2025-05-08 14:44:41', '2025-05-08 14:44:41');


-- Insert Users Data (Only one user as per your request)
INSERT INTO users (first_name, last_name, email, role, phone, password, created_at, updated_at, username, profile_image_url, current_address, shipping_address) VALUES
('Admin', 'User', 'admin@gmail.com', 'admin', '8888888888', 'LqPh0bR/ESRvAzeOXerSWQ==', '2025-05-08 14:49:05', '2025-05-08 14:49:42', 'adminuser', 'profile_photos/default_profile.png', 'Pokhara', 'Pokhara'),
('Diya', 'Gurung', 'diya@gmail.com', 'customer', '6666666666', 'aXkNkZIF12+XKikhtvvLog==', '2025-05-08 14:50:56', '2025-05-08 14:50:56', 'diyagurung', 'profile_photos/default_profile.png', 'Malepatan', 'Malepatan'),
('Juna', 'Chhetri', 'juna@gmail.com', 'customer', '4444444444', 'EuzEDjLNR7vXG29+L3BQKw==', '2025-05-08 14:51:34', '2025-05-08 14:51:34', 'chitrakala', 'profile_photos/default_profile.png', 'Talchowk', 'Talchowk'),
('Mishama', 'Thapa', 'mishma@gmail.com', 'customer', '8888888888', 'jMY2KtYh0Y6DKVg0nk/j8g==', '2025-05-08 14:52:10', '2025-05-08 14:52:10', 'mishmathapa', 'profile_photos/default_profile.png', 'Chipledhunga', 'Chipledhunga'),
('Yashica', 'Magar', 'yashica@gmail.com', 'customer', '4444444444', 'ExY59wNACaHp3z3iOYvF8Q==', '2025-05-08 14:52:53', '2025-05-08 14:52:53', 'yashica', 'profile_photos/default_profile.png', 'Lakeside Pokhara', 'Lakeside Pokhara'),
('Shribisha', 'Buddhacharya', 'shribisha@gmail.com', 'customer', '9807654321', 'C1GZTvGGNKMnV5j0z9nNlg==', '2025-05-08 14:03:31', '2025-05-08 14:49:48', 'shribisha', 'profile_photos/jeykey.jpg', 'Kathmandu, Nepal', 'Kathmandu, Nepal');
