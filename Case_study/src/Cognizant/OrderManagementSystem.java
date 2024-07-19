package Cognizant;

import java.sql.*;
import java.util.Scanner;

public class OrderManagementSystem {
    private static final String URL = "jdbc:mysql://localhost:3306/order_management";
    private static final String USER = "root";  // Replace with your MySQL username
    private static final String PASSWORD = "root";  // Replace with your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD); 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 

        while (true) {
            System.out.println("1. Manage Customers");
            System.out.println("2. Manage Products");
            System.out.println("3. Manage Orders");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manageCustomers(scanner);
                    break;
                case 2:
                    manageProducts(scanner);
                    break;
                case 3:
                    manageOrders(scanner);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageCustomers(Scanner scanner) {
    	
        System.out.println("1. Add Customer");
        System.out.println("2. View Customer");
        System.out.println("3. Update Customer");
        System.out.println("4. Delete Customer");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter name: ");
                String name = scanner.next();
                System.out.print("Enter email: ");
                String email = scanner.next();
                System.out.print("Enter phone: ");
                String phone = scanner.next();
                System.out.print("Enter address: ");
                String address = scanner.next();
                addCustomer(name, email, phone, address);
                break;
            case 2:
                System.out.print("Enter customer ID: ");
                int customerId = scanner.nextInt();
                viewCustomer(customerId);
                break;
            case 3:
                System.out.print("Enter customer ID: ");
                customerId = scanner.nextInt();
                System.out.print("Enter new name: ");
                name = scanner.next();
                System.out.print("Enter new email: ");
                email = scanner.next();
                System.out.print("Enter new phone: ");
                phone = scanner.next();
                System.out.print("Enter new address: ");
                address = scanner.next();
                updateCustomer(customerId, name, email, phone, address);
                break;
            case 4:
                System.out.print("Enter customer ID: ");
                customerId = scanner.nextInt();
                deleteCustomer(customerId);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        
    }

    private static void manageProducts(Scanner scanner) {
        System.out.println("1. Add Product");
        System.out.println("2. View Product");
        System.out.println("3. Update Product");
        System.out.println("4. Delete Product");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter name: ");
                String name = scanner.next();
                System.out.print("Enter category: ");
                String category = scanner.next();
                System.out.print("Enter price: ");
                double price = scanner.nextDouble();
                System.out.print("Enter stock quantity: ");
                int stockQuantity = scanner.nextInt();
                addProduct(name, category, price, stockQuantity);
                break;
            case 2:
                System.out.print("Enter product ID: ");
                int productId = scanner.nextInt();
                viewProduct(productId);
                break;
            case 3:
                System.out.print("Enter product ID: ");
                productId = scanner.nextInt();
                System.out.print("Enter new name: ");
                name = scanner.next();
                System.out.print("Enter new category: ");
                category = scanner.next();
                System.out.print("Enter new price: ");
                price = scanner.nextDouble();
                System.out.print("Enter new stock quantity: ");
                stockQuantity = scanner.nextInt();
                updateProduct(productId, name, category, price, stockQuantity);
                break;
            case 4:
                System.out.print("Enter product ID: ");
                productId = scanner.nextInt();
                deleteProduct(productId);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void manageOrders(Scanner scanner) {
        System.out.println("1. Place Order");
        System.out.println("2. View Order");
        System.out.println("3. Update Order Status");
        System.out.println("4. Cancel Order");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter customer ID: ");
                int customerId = scanner.nextInt();
                System.out.print("Enter order date (YYYY-MM-DD): ");
                String orderDate = scanner.next();
                System.out.print("Enter status (pending/confirmed/cancelled): ");
                String status = scanner.next();
                placeOrder(customerId, Date.valueOf(orderDate), status);
                break;
            case 2:
                System.out.print("Enter order ID: ");
                int orderId = scanner.nextInt();
                viewOrder(orderId);
                break;
            case 3:
                System.out.print("Enter order ID: ");
                orderId = scanner.nextInt();
                System.out.print("Enter new status (pending/confirmed/cancelled): ");
                status = scanner.next();
                updateOrderStatus(orderId, status);
                break;
            case 4:
                System.out.print("Enter order ID: ");
                orderId = scanner.nextInt();
                cancelOrder(orderId);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
   

    private static void addCustomer(String name, String email, String phone, String address) {
        String query = "INSERT INTO Customer (name, email, phone, address) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, address);
            pstmt.executeUpdate();
            System.out.println("Customer added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewCustomer(int customerId) {
        String query = "SELECT * FROM Customer WHERE customer_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("customer_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Address: " + rs.getString("address"));
            } else {
                System.out.println("Customer not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    private static void updateCustomer(int customerId, String name, String email, String phone, String address) {
        String query = "UPDATE Customer SET name = ?, email = ?, phone = ?, address = ? WHERE customer_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, address);
            pstmt.setInt(5, customerId);
            pstmt.executeUpdate();
            System.out.println("Customer updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteCustomer(int customerId) {
        String query = "DELETE FROM Customer WHERE customer_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, customerId);
            pstmt.executeUpdate();
            System.out.println("Customer deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addProduct(String name, String category, double price, int stockQuantity) {
        String query = "INSERT INTO Product (name, category, price, stock_quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, category);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, stockQuantity);
            pstmt.executeUpdate();
            System.out.println("Product added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewProduct(int productId) {
        String query = "SELECT * FROM Product WHERE product_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("product_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Category: " + rs.getString("category"));
                System.out.println("Price: " + rs.getDouble("price"));
                System.out.println("Stock Quantity: " + rs.getInt("stock_quantity"));
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateProduct(int productId, String name, String category, double price, int stockQuantity) {
        String query = "UPDATE Product SET name = ?, category = ?, price = ?, stock_quantity = ? WHERE product_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, category);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, stockQuantity);
            pstmt.setInt(5, productId);
            pstmt.executeUpdate();
            System.out.println("Product updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteProduct(int productId) {
        String query = "DELETE FROM Product WHERE product_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();
            System.out.println("Product deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void placeOrder(int customerId, Date orderDate, String status) {
        String query = "INSERT INTO `Order` (customer_id, order_date, status) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, customerId);
            pstmt.setDate(2, orderDate);
            pstmt.setString(3, status);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int orderId = rs.getInt(1);
                System.out.println("Order placed successfully. Order ID: " + orderId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewOrder(int orderId) {
        String query = "SELECT * FROM `Order` WHERE order_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Order ID: " + rs.getInt("order_id"));
                System.out.println("Customer ID: " + rs.getInt("customer_id"));
                System.out.println("Order Date: " + rs.getDate("order_date"));
                System.out.println("Status: " + rs.getString("status"));
            } else {
                System.out.println("Order not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateOrderStatus(int orderId, String status) {
        String query = "UPDATE `Order` SET status = ? WHERE order_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, orderId);
            pstmt.executeUpdate();
            System.out.println("Order status updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    private static void cancelOrder(int orderId) {
        String query = "DELETE FROM `Order` WHERE order_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();
            System.out.println("Order cancelled successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
