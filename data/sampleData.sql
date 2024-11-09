-- Insert data into Role table
INSERT INTO Role (RoleID, RoleName, Description)
VALUES (1, 'Manager', 'Oversees operations in the department'),
       (2, 'Salesperson', 'Handles customer interactions and sales'),
       (3, 'Warehouse Staff', 'Manages stock and inventory'),
       (4, 'Technician', 'Performs maintenance and repairs');

-- Insert data into Department table
INSERT INTO Department (DepartmentID, Name, Location)
VALUES (1, 'Sales', 'Building A'),
       (2, 'Warehouse', 'Building B'),
       (3, 'Service', 'Building C'),
       (4, 'Human Resources', 'Building A');

-- Insert data into Product table
INSERT INTO Product (ProductID, Name, Category, Description, Brand, Price, QuantityInStock)
VALUES (1, 'Laptop', 'Electronics', 'Description', 'BrandA', 1200.00, 50),
       (2, 'Smartphone', 'Electronics', 'Description', 'BrandB', 800.00, 100),
       (3, 'Headphones', 'Accessories', 'Description', 'BrandC', 150.00, 200),
       (4, 'Monitor', 'Electronics', 'Description', 'BrandD', 300.00, 30);

-- Insert data into Supplier table
INSERT INTO Supplier (SupplierID, Name, Phone, Email, Address)
VALUES (1, 'TechSupply Co.', '123-456-7890', 'contact@techsupply.com', '123 Tech Street, City, Country'),
       (2, 'GadgetParts Ltd.', '987-654-3210', 'sales@gadgetparts.com', '456 Gadget Avenue, City, Country');

-- Insert data into Customer table
INSERT INTO Customer (CustomerID, Name, Phone, Email, Address)
VALUES (1, 'Alice Johnson', '555-0101', 'alice@example.com', '789 Oak St, City, Country'),
       (2, 'Bob Smith', '555-0102', 'bob@example.com', '123 Pine St, City, Country'),
       (3, 'Charlie Brown', '555-0103', 'charlie@example.com', '321 Maple St, City, Country');

-- Insert data into Employee table
INSERT INTO Employee (EmployeeID, RoleID, DepartmentID, Name, Address, Phone, Email, Password, Position, Salary)
VALUES (1, 1, 1, 'David Green', '101 Main St, City, Country', '555-1001', 'david@example.com', 'password1',
        'Sales Manager', 55000.00),
       (2, 2, 2, 'Eve White', '202 Elm St, City, Country', '555-1002', 'eve@example.com', 'password2',
        'Warehouse Staff', 30000.00),
       (3, 3, 3, 'Frank Blue', '303 Cedar St, City, Country', '555-1003', 'frank@example.com', 'password3',
        'Technician', 40000.00);

-- Insert data into Inventory table
INSERT INTO Inventory (InventoryID, ProductID, QuantityAvailable, Location)
VALUES (1, 1, 30, 'Warehouse A'),
       (2, 2, 75, 'Warehouse B'),
       (3, 3, 150, 'Warehouse C'),
       (4, 4, 25, 'Warehouse A');

-- Insert data into Warranty table
INSERT INTO Warranty (WarrantyID, ProductID, WarrantyPeriod, CoverageDetails)
VALUES (1, 1, '2 years', 'Covers manufacturing defects and hardware failure'),
       (2, 2, '1 year', 'Covers manufacturing defects only'),
       (3, 3, '6 months', 'Covers hardware defects only');

-- Insert data into `Order` table
INSERT INTO `Order` (OrderID, CustomerID, OrderDate, TotalAmount)
VALUES (1, 1, '2024-11-01', 1600.00),
       (2, 2, '2024-11-02', 800.00),
       (3, 3, '2024-11-03', 350.00);

-- Insert data into OrderDetail table
INSERT INTO OrderDetail (OrderDetailID, OrderID, ProductID, Quantity, Price)
VALUES (1, 1, 1, 1, 1200.00),
       (2, 1, 3, 2, 150.00),
       (3, 2, 2, 1, 800.00),
       (4, 3, 4, 1, 350.00);

-- Insert data into Purchase table
INSERT INTO Purchase (PurchaseID, InventoryID, SupplierID, EmployeeID, PurchaseDate, TotalCost)
VALUES (1, 1, 1, 2, '2024-10-01', 12000.00),
       (2, 2, 2, 2, '2024-10-02', 7500.00);

-- Insert data into Service table
INSERT INTO Service (ServiceID, EmployeeID, ProductID, ServiceDate, ServiceDescription)
VALUES (1, 3, 1, '2024-10-10', 'Replaced faulty motherboard'),
       (2, 3, 2, '2024-10-12', 'Software troubleshooting and repair');

-- Insert data into Shift table
INSERT INTO Shift (ShiftID, EmployeeID, ShiftDate, StartTime, EndTime)
VALUES (1, 2, '2024-11-01', '09:00:00', '17:00:00'),
       (2, 3, '2024-11-01', '10:00:00', '18:00:00'),
       (3, 1, '2024-11-02', '09:00:00', '17:00:00');

-- Insert data into Payment table
INSERT INTO Payment (PaymentID, CustomerID, PaymentDate, AmountPaid, PaymentMethod)
VALUES (1, 1, '2024-11-01', 1600.00, 'Credit Card'),
       (2, 2, '2024-11-02', 800.00, 'Cash'),
       (3, 3, '2024-11-03', 350.00, 'Debit Card');