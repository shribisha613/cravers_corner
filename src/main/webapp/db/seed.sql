USE cravers_corner;

-- Insert Categories Data
INSERT INTO Categories (name, description) VALUES 
('Korean', 'Traditional dishes from Korea, including rice, vegetables, and meats often served with kimchi.'),
('Chinese', 'Classic Chinese cuisine with a variety of stir-fries, rice, noodles, and dim sum.'),
('Nepali', 'Traditional Nepali foods such as dal bhat, momo, and chatamari, with diverse regional flavors.'),
('Western', 'Popular Western dishes including burgers, pizzas, pasta, and salads.'),
('Tibetan', 'Dishes native to Tibet, including thukpa, momos, and traditional barley bread.'),
('Snacks', 'Light and easy-to-eat foods such as chips, samosas, and finger foods.'),
('Dessert', 'Sweet treats like cakes, ice creams, pastries, and puddings.'),
('Cold Drinks', 'Refreshing beverages like sodas, iced teas, and juices served cold.'),
('Indian', 'Rich and spicy curries, biryanis, and tandoori dishes from India.'),
('Italian', 'Italian favorites such as pizza, pasta, and risotto.'),
('Mexican', 'Flavorsome Mexican dishes like tacos, burritos, and enchiladas.'),
('American', 'American comfort food such as fried chicken, hot dogs, and mac n’ cheese.'),
('Vegetarian', 'Delicious vegetarian options including plant-based burgers, salads, and pasta dishes.'),
('Fast Food', 'Quickly served foods like sandwiches, fries, and pizza.'),
('Beverages', 'A wide selection of hot and cold beverages such as coffee, tea, and smoothies.');

-- Insert Users Data (Only one user as per your request)
INSERT INTO Users (
    first_name, last_name, email, role, phone, password, username, profile_image_url, current_address, shipping_address
) VALUES
('Shribisha', 'Buddhacharya', 'shribisha@gmail.com', 'admin', '9807654321', 'C1GZTvGGNKMnV5j0z9nNlg==', 'shribisha', 'profile_photos/jeykey.jpg', 'Kathmandu, Nepal', 'Kathmandu, Nepal');
