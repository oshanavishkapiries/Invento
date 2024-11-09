-- Create the Role table
CREATE TABLE Role
(
    RoleID      INT PRIMARY KEY,
    RoleName    VARCHAR(255),
    Description TEXT
);

-- Create the Department table
CREATE TABLE Department
(
    DepartmentID INT PRIMARY KEY,
    Name         VARCHAR(255),
    Location     VARCHAR(255)
);

-- Create the Product table
CREATE TABLE Product
(
    ProductID       INT PRIMARY KEY,
    Name            VARCHAR(255),
    Category        VARCHAR(255),
    Description     VARCHAR(255),
    Brand           VARCHAR(255),
    Price           DECIMAL(10, 2),
    QuantityInStock INT
);

-- Create the Supplier table
CREATE TABLE Supplier
(
    SupplierID INT PRIMARY KEY,
    Name       VARCHAR(255),
    Phone      VARCHAR(20),
    Email      VARCHAR(255),
    Address    TEXT
);

-- Create the Customer table
CREATE TABLE Customer
(
    CustomerID INT PRIMARY KEY,
    Name       VARCHAR(255),
    Phone      VARCHAR(20),
    Email      VARCHAR(255),
    Address    TEXT
);

-- Create the Employee table
CREATE TABLE Employee
(
    EmployeeID   INT PRIMARY KEY,
    RoleID       INT,
    DepartmentID INT,
    Name         VARCHAR(255),
    Address      TEXT,
    Phone        VARCHAR(20),
    Email        VARCHAR(255),
    Password     VARCHAR(255),
    Position     VARCHAR(255),
    Salary       DECIMAL(10, 2),
    FOREIGN KEY (RoleID) REFERENCES Role (RoleID) ON DELETE CASCADE,
    FOREIGN KEY (DepartmentID) REFERENCES Department (DepartmentID) ON DELETE CASCADE
);

-- Create the Inventory table
CREATE TABLE Inventory
(
    InventoryID       INT PRIMARY KEY,
    ProductID         INT,
    QuantityAvailable INT,
    Location          VARCHAR(255),
    FOREIGN KEY (ProductID) REFERENCES Product (ProductID) ON DELETE CASCADE
);

-- Create the Warranty table
CREATE TABLE Warranty
(
    WarrantyID      INT PRIMARY KEY,
    ProductID       INT,
    WarrantyPeriod  VARCHAR(255),
    CoverageDetails TEXT,
    FOREIGN KEY (ProductID) REFERENCES Product (ProductID) ON DELETE CASCADE
);

-- Create the `Order` table
CREATE TABLE `Order`
(
    OrderID     INT PRIMARY KEY,
    CustomerID  INT,
    OrderDate   DATE,
    TotalAmount DECIMAL(10, 2),
    FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID) ON DELETE CASCADE
);

-- Create the OrderDetail table
CREATE TABLE OrderDetail
(
    OrderDetailID INT PRIMARY KEY,
    OrderID       INT,
    ProductID     INT,
    Quantity      INT,
    Price         DECIMAL(10, 2),
    FOREIGN KEY (OrderID) REFERENCES `Order` (OrderID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES Product (ProductID) ON DELETE CASCADE
);

-- Create the Purchase table
CREATE TABLE Purchase
(
    PurchaseID   INT PRIMARY KEY,
    InventoryID  INT,
    SupplierID   INT,
    EmployeeID   INT,
    PurchaseDate DATE,
    TotalCost    DECIMAL(10, 2),
    FOREIGN KEY (InventoryID) REFERENCES Inventory (InventoryID) ON DELETE CASCADE,
    FOREIGN KEY (SupplierID) REFERENCES Supplier (SupplierID) ON DELETE CASCADE,
    FOREIGN KEY (EmployeeID) REFERENCES Employee (EmployeeID) ON DELETE CASCADE
);

-- Create the Service table
CREATE TABLE Service
(
    ServiceID          INT PRIMARY KEY,
    EmployeeID         INT,
    ProductID          INT,
    ServiceDate        DATE,
    ServiceDescription TEXT,
    FOREIGN KEY (EmployeeID) REFERENCES Employee (EmployeeID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES Product (ProductID) ON DELETE CASCADE
);

-- Create the Shift table
CREATE TABLE Shift
(
    ShiftID    INT PRIMARY KEY,
    EmployeeID INT,
    ShiftDate  DATE,
    StartTime  TIME,
    EndTime    TIME,
    FOREIGN KEY (EmployeeID) REFERENCES Employee (EmployeeID) ON DELETE CASCADE
);

-- Create the Payment table
CREATE TABLE Payment
(
    PaymentID     INT PRIMARY KEY,
    CustomerID    INT,
    PaymentDate   DATE,
    AmountPaid    DECIMAL(10, 2),
    PaymentMethod VARCHAR(255),
    FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID) ON DELETE CASCADE
);
