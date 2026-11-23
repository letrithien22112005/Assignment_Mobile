# 📱 Assignment Mobile – Mobile Phone Store (Java Servlet/JSP)

A Java web application for managing and selling mobile phones, built with **Servlet/JSP** following the **MVC** pattern and a **Front Controller** (`MainController`), using **JDBC** with **Microsoft SQL Server**.
Built as the course project for PRJ301 (Java Web Application Development) at FPT University.

## Features

**Login and roles**
- Login with user ID and password; the user is stored in the session (`USER_LOGIN`)
- Two roles: **Staff** (`role = 1`) and **User/Customer** (`role = 0`), each sent to a different page
- Pages and controllers check the session and redirect to the login page if the user is not logged in
- Logout

**Staff – phone management (CRUD)**
- Search phones by exact ID or part of the name
- Add a new phone with server-side validation:
  - ID must match `MBxxx` (MB + 3 digits) and must not already exist
  - Name 2–20 characters, price and quantity must be non-negative numbers, year must be an integer
  - Error messages shown next to each field
- Update price, description, quantity and "not for sale" status
- Delete a phone (with a confirmation page)
- View phone details

**User – shopping**
- Browse phones and filter by price range (min – max)
- Shopping cart stored in the session: add to cart (adding again increases the quantity), update quantity (must be greater than 0), remove items, total quantity and total amount

## Tech stack

| Layer | Technology |
|---|---|
| Language | Java 8 |
| Web | Java Servlet, JSP |
| Server | Apache Tomcat 9 |
| Database | Microsoft SQL Server, JDBC (`sqljdbc4`) |
| IDE / build | Apache NetBeans, Ant |
| Testing | Playwright end-to-end tests (in a separate test project) |

## Architecture

All forms post to a single **Front Controller** (`MainController`), which reads the `action` parameter and forwards the request to the matching controller. Controllers call the DAO layer, put the result in the request/session, and forward to a JSP view.

```
JSP form ──(action=...)──► MainController ──► XxxController ──► DAO ──► SQL Server
                                                   │
                                                   └──► JSP view
```

```
src/java/sample/
├── controller/  MainController (front controller)
│                LoginController, LogoutController, SearchController, DetailController,
│                AddController, UpdateController, DeleteController,
│                AddCartController, ViewCartController, UpdateCartController, DeleteCartController
├── dao/         UserDAO, MobileDAO          (JDBC with PreparedStatement)
├── dto/         UserDTO, MobileDTO, CartDTO, MobileError
└── utils/       DBUtils (database connection), MobileCookie
web/             login.jsp, staff.jsp, user.jsp, addMobile.jsp, update.jsp, delete.jsp, cart.jsp
database/        AssignmentMobie.sql (schema + sample data)
```

| `action` | Controller | Who |
|---|---|---|
| `Login` / `Logout` | LoginController / LogoutController | everyone |
| `Search` | SearchController | Staff (by ID/name), User (by price range) |
| `Detail` | DetailController | Staff |
| `Add` / `Update` / `Delete` | AddController / UpdateController / DeleteController | Staff |
| `AddCart` / `ViewCart` / `UpdateCart` / `DeleteCart` | cart controllers | User |

## Database

Two tables: `Users (userId, password, fullName, role)` and `Mobile (mobileId, description, price, mobileName, yearOfProduction, quantity, notSale)`.
Run [`database/AssignmentMobie.sql`](database/AssignmentMobie.sql) in SQL Server Management Studio to create the database with sample data.

## How to run

1. **Requirements:** JDK 8+, Apache NetBeans, Apache Tomcat 9 (not 10+, because the project uses `javax.servlet`), SQL Server.
2. **Database:** run `database/AssignmentMobie.sql` in SSMS.
3. **Connection:** check the database name, user and password in `src/java/sample/utils/DBUtils.java` (default `sa` / `sa`).
4. **Open in NetBeans:** *File > Open Project*, choose this folder.
   - If you see *Missing Java EE Server*: right-click the project > *Resolve Missing Server Problem* and choose Tomcat 9 (add it under *Tools > Servers* first if needed).
   - If the SQL Server driver is missing: right-click *Libraries > Add JAR/Folder* and choose `sqljdbc4.jar` / `mssql-jdbc.jar`.
5. **Run** the project and open http://localhost:8080/Assignment_Mobile/

**Sample accounts** (from the SQL script)

| Role | User ID | Password |
|---|---|---|
| Staff | `staff` | `123` |
| User | `user01` | `222222` |

## What I learned
- Building a web app with the MVC pattern and a front controller using Servlet/JSP
- Session management for login and the shopping cart
- Server-side form validation and showing errors back on the form
- Database access with JDBC and `PreparedStatement`
- Writing automated end-to-end tests with Playwright for the main flows
