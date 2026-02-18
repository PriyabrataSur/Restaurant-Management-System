## Restaurant Management System (Backend)   
A lightweight RESTful API built with Java and Spring Boot to manage restaurant operations, including menu items, orders, and customer billing.

### Features
* Menu Management: Perform CRUD operations on food and beverage items.  
* Order Processing: Create and track customer orders in real-time.  
* Billing System: Generate invoices based on active orders and tax calculations.  
* Table Management: Monitor table availability and assignments.  

### Project Structure  

src/main/java/com/restaurant/  
├── controller/  &emsp;  # REST API Endpoints  
├── model/ &emsp;&emsp;        # Entity classes (Menu, Order, Table)  
├── repository/ &emsp;   # JPA Repositories  
├── service/   &emsp;&emsp;    # Business logic implementation  
└── RestaurantSystemApplication.java  

