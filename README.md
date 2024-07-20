Customer Order Management System (Console Application)


Introduction:
This project is a menu-based console application developed in Core Java, utilizing MySQL for database storage and JDBC for database connectivity. The application simulates a customer order management system for a retail business, providing functionalities to manage customers, products, and orders.

Functionalities:
1)Customer Management
2)Product Management
3)Order Management

Database Schema:
The application uses MySQL database with the following schema:

Customer Table:

customer_id (Primary Key)
name
email
phone
address

Product Table:

product_id (Primary Key)
name
category
price
stock_quantity

Order Table:

order_id (Primary Key)
customer_id (Foreign Key references Customer Table)
order_date
status (pending/confirmed/cancelled)
Order_Item Table:

Requirements:
Developed using Core Java.
Uses JDBC for interactions with the MySQL database.
Implements menu-driven options for managing customers, products, and orders.
Efficiently handles customer and product data operations.
Effective exception handling with user-friendly error messages.
Code is clean, well-documented, and follows standard coding conventions.

Setup Instructions:

Database Setup:

Install MySQL and create a database named order_management.
Execute the SQL scripts provided in schema.sql to create the necessary tables.

Java Project Setup:

Clone this repository to your local machine.
Import the project into your favorite IDE (Eclipse, IntelliJ, etc.)
Ensure JDBC driver (e.g., MySQL Connector/J) is included in the project.

Configuration:

Update the database connection details in DatabaseConnection class.
To view Schema of different tables run a "SELECT * FROM TableName"

Usage:

Run the OrderManagementApp.java file to start the application.
Follow the on-screen instructions to navigate through the menus and perform operations.

Future Enhancement:

This application can be used to outline managerial projects.

