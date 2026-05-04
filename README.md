Library Management System (JPA)
==================================

A Java-based library management application developed using Jakarta Persistence (JPA). This system manages book records, inventory tracking, and advanced data analysis using a structured Data Access Object (DAO) pattern.

* * * * *

 Features
-----------

-   **JPA Persistence:** Efficient data handling using Hibernate / Jakarta Persistence

-   **Smart Data Loading:** Prevents duplication by inserting test data only when the database is empty

-   **Advanced Querying:** Filter books by genre, author, stock levels, and publication year

-   **Statistical Analysis:** Compute average prices, find the oldest records, and identify top-priced books

-   **Aggregated Reports:** Generate grouped insights such as counts and averages per genre

* * * * *

 Tech Stack
--------------

-   **Language:** Java

-   **API:** Jakarta Persistence (JPA)

-   **Architecture:** DAO (Data Access Object) Pattern

-   **Persistence Provider:** Hibernate

* * * * *

Requirements & Structure
---------------------------

### 1\. Book Entity (POJO)

The core model includes:

-   `id` → Primary key (auto-generated)

-   `title`, `author`, `isbn`, `genre` → Textual metadata

-   `publicationYear` → Integer

-   `price` → Decimal value

-   `availableCopies` → Stock count

-   `isBestSeller` → Boolean flag

* * * * *

### 2\. Data Access Object (BookDAO)

#### 🔹 CRUD Operations

-   Insert

-   Delete by ID

-   Get by ID

-   Get all records

#### 🔹 Filters

-   Books by genre

-   Books with stock < 3

-   Books published after year 2000

-   Books by author

#### 🔹 Analytics

-   Total book count

-   Average price

-   Oldest book

#### 🔹 Grouped Reports

-   Book count per genre

-   Average price per genre

-   Genres with high stock (>100 copies)

* * * * *

 Usage Example
----------------

The `Main` class demonstrates the full lifecycle:

-   Initializes the `EntityManagerFactory`

-   Populates the database if needed

-   Executes queries and reports

* * * * *

 Data Idempotency
-------------------

Ensures consistent results across multiple runs:

```
if (dao.getTotalBookCount() == 0) {
    // Initial data insertion happens only once
    dao.insertBook(new Book(...));
}

```

* * * * *

Result Processing
--------------------

Aggregated results (e.g., counts or averages by genre) are returned as a `List<Object[]>`.

-   `row[0]` → Grouping key (e.g., genre name)

-   `row[1]` → Calculated value (e.g., count or average)

```
for (Object[] row : dao.getBookCountByGenre()) {
    System.out.println(row[0] + ": " + row[1]);
}

```

* * * * *

 Setup
--------

Ensure your `persistence.xml` is located at:

```
src/main/resources/META-INF/

```

### Requirements

-   Valid database credentials

-   Persistence unit named `"Book"`

* * * * *
