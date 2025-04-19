USE cravers_corner;

-- Insert Categories Data
INSERT INTO Categories (name, description) VALUES 
('Momo', 'Steamed dumplings filled with minced meat or vegetables.'),
('Pizza', 'A traditional Italian dish made with a round flatbread topped with various ingredients such as cheese, sauce, and toppings.'),
('Katthi Roll', 'A popular street food made with a rolled paratha filled with grilled meat or vegetables.'),
('Chowmein', 'Stir-fried noodles with vegetables and meat or tofu.'),
('Dessert', 'Sweet treats like cakes, ice creams, and pastries.'),
('Snacks', 'Quick, light foods like chips, samosas, and sandwiches.');

-- Insert Users Data (Nepali Romanized Names)
INSERT INTO Users (
    first_name, last_name, email, role, phone, password, username, profile_image_url, current_address, shipping_address
) VALUES
('Shribisha', 'Buddhacharya', 'shribisha@example.com', 'customer', '9801234567', 'hashed_password', 'shribishab', 'https://example.com/profile/shribisha.jpg', 'Pokhara-17, Nepal', 'Pokhara-15, Nepal'),
('Rajeev', 'Sharma', 'rajeeve@example.com', 'customer', '9812345678', 'hashed_password', 'rajeeve', 'https://example.com/profile/rajeeve.jpg', 'Kathmandu-10, Nepal', 'Kathmandu-12, Nepal'),
('Anjali', 'Thapa', 'anjali@example.com', 'admin', '9823456789', 'hashed_password', 'anjaliadmin', 'https://example.com/profile/anjali.jpg', 'Lalitpur-5, Nepal', 'Lalitpur-3, Nepal');

-- Insert Foods Data
INSERT INTO Foods (name, description, price, category_id, image_url, status, serving_size) VALUES
('Chicken Momo', 'Steamed momo filled with chicken, served with achar (sauce).', 150.00, 1, 'https://example.com/Foods/chicken_momo.jpg', 'available', '5 pieces'),
('Veg Pizza', 'Classic pizza topped with cheese, tomatoes, and vegetables.', 300.00, 2, 'https://example.com/Foods/veg_pizza.jpg', 'available', '1 medium'),
('Paneer Katthi Roll', 'Spicy grilled paneer wrapped in paratha.', 120.00, 3, 'https://example.com/Foods/paneer_katthi_roll.jpg', 'available', '1 roll'),
('Chowmein', 'Stir-fried noodles with mixed vegetables and soy sauce.', 100.00, 4, 'https://example.com/Foods/chowmein.jpg', 'available', '1 plate'),
('Chocolate Cake', 'Rich and moist chocolate cake topped with ganache.', 200.00, 5, 'https://example.com/Foods/chocolate_cake.jpg', 'available', '1 slice'),
('French Fries', 'Crispy fried potato sticks, perfect as a snack.', 50.00, 6, 'https://example.com/Foods/french_fries.jpg', 'available', '1 serving');

-- Insert Carts Data
INSERT INTO Carts (customer_id, total_amount) VALUES
(1, 450.00),
(2, 370.00),
(3, 200.00);

-- Insert Cart Items Data
INSERT INTO Cart_items (cart_id, food_id, quantity, price, subtotal) VALUES
(1, 1, 2, 150.00, 300.00),
(1, 4, 1, 100.00, 100.00),
(2, 2, 1, 300.00, 300.00),
(2, 6, 1, 50.00, 50.00),
(3, 5, 1, 200.00, 200.00);

-- Insert Orders Data
INSERT INTO Orders (customer_id, status, total_amount, order_note) VALUES
(1, 'completed', 450.00, 'Enjoying momo and chowmein'),
(2, 'pending', 370.00, 'Waiting for pizza and fries'),
(3, 'completed', 200.00, 'Had a sweet dessert');

-- Insert Order Items Data
INSERT INTO Order_items (order_id, food_id, quantity, price, subtotal) VALUES
(1, 1, 2, 150.00, 300.00),
(1, 4, 1, 100.00, 100.00),
(2, 2, 1, 300.00, 300.00),
(2, 6, 1, 50.00, 50.00),
(3, 5, 1, 200.00, 200.00);
