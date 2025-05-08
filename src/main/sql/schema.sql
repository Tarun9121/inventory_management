create database inventory_management;

use inventory_management;

create table admins (
	id int primary key auto_increment,
    name varchar(100),
    email varchar(100) unique,
    phone varchar(15),
    address text,
    password varchar(225)
);

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    phone VARCHAR(15),
    address TEXT,
    role ENUM('CUSTOMER', 'SELLER', 'ADMIN') DEFAULT 'CUSTOMER'
);

CREATE TABLE categories (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(100)
);

CREATE TABLE products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    description TEXT,
    price DECIMAL(10, 2),
    category_id INT,
    supplier_id INT, -- new column
    image_url VARCHAR(255),
    FOREIGN KEY (category_id) REFERENCES categories(category_id),
    FOREIGN KEY (supplier_id) REFERENCES supplier(id)
);

CREATE TABLE product_variants (
    variant_id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT,
    size VARCHAR(10), -- e.g., 'S', 'M', 'L', 'XL'
    stock INT,
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE TABLE Supplier (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20)
);

CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2),
    status ENUM('PENDING', 'SHIPPED', 'DELIVERED', 'CANCELLED') DEFAULT 'PENDING',
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE order_items (
    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    product_id INT, -- newly added foreign key
    variant_id INT,
    quantity INT,
    price DECIMAL(10, 2), -- snapshot of price at purchase time
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (variant_id) REFERENCES product_variants(variant_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE TABLE wishlist (
    wishlist_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    product_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE TABLE cart (
    cart_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    variant_id INT,
    quantity INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (variant_id) REFERENCES product_variants(variant_id)
);