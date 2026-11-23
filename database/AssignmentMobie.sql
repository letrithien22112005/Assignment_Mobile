-- =============================================================
--  Assignment Mobile - SQL Server database script
--  Run in SQL Server Management Studio (SSMS).
-- =============================================================
IF DB_ID('AssignmentMobie') IS NULL
    CREATE DATABASE AssignmentMobie;
GO
USE AssignmentMobie;
GO

IF OBJECT_ID('dbo.Mobile', 'U') IS NOT NULL DROP TABLE dbo.Mobile;
IF OBJECT_ID('dbo.Users', 'U') IS NOT NULL DROP TABLE dbo.Users;
GO

CREATE TABLE Users (
    userId   VARCHAR(20)  NOT NULL PRIMARY KEY,
    password INT          NOT NULL,
    fullName NVARCHAR(50) NOT NULL,
    role     INT          NOT NULL   -- 1 = staff, 0 = user
);

CREATE TABLE Mobile (
    mobileId         VARCHAR(10)   NOT NULL PRIMARY KEY,  -- format MBxxx
    description      NVARCHAR(250) NULL,
    price            FLOAT         NOT NULL,
    mobileName       NVARCHAR(20)  NOT NULL,
    yearOfProduction INT           NOT NULL,
    quantity         INT           NOT NULL,
    notSale          BIT           NOT NULL DEFAULT 0     -- 1 = not for sale
);
GO

INSERT INTO Users (userId, password, fullName, role) VALUES
('staff',  123,    N'Store Staff',   1),
('user01', 222222, N'Nguyen Van An', 0);

INSERT INTO Mobile (mobileId, description, price, mobileName, yearOfProduction, quantity, notSale) VALUES
('MB004', N'Samsung, 256GB, S Pen',      1099, N'GalaxyS23U',  2023,  8, 0),
('MB005', N'Apple, 128GB',                599, N'iPhone13',    2021,  0, 1),
('MB006', N'Apple, 128GB',                799, N'iPhone15',    2023, 20, 0),
('MB007', N'Apple, 256GB',               1199, N'iPhone15PM',  2023, 12, 0),
('MB008', N'Samsung, 128GB',              449, N'GalaxyA55',   2024, 30, 0),
('MB009', N'Xiaomi, 256GB, Leica camera', 699, N'Xiaomi13',    2023, 10, 0),
('MB010', N'Xiaomi, 128GB',               399, N'Xiaomi13L',   2023, 18, 0),
('MB011', N'Xiaomi, 128GB',               229, N'RedmiNote13', 2024, 40, 0),
('MB012', N'Google, 128GB',               999, N'Pixel8Pro',   2023,  6, 0),
('MB013', N'OPPO, 256GB',                 429, N'OPPOReno11',  2024, 22, 0),
('MB014', N'Vivo, 256GB',                 459, N'VivoV30',     2024, 14, 0),
('MB015', N'Nokia, 128GB',                199, N'NokiaG42',    2023,  0, 1),
('MB016', N'OnePlus, 256GB',              799, N'OnePlus12',   2024,  9, 0);
GO
