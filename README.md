"# springboot-elasticsearch-course-search" 

A Spring Boot application integrated with Elasticsearch to provide fast and efficient course search functionality.  
It indexes course data and offers REST APIs for searching and retrieving course information.


## 🚀 Features
- Full-text search on course titles and descriptions
- Pagination and sorting support
- Elasticsearch index setup via Spring Data Elasticsearch
- REST APIs for search and suggestions
- Preloaded sample course data


## 🛠 Tech Stack
- **Java 17** 
- **Spring Boot**
- **Spring Data Elasticsearch**
- **Elasticsearch** 
- **Maven**
- **Docker**
- **Lombok**


## 📦 Project Structure
src/
├── main/
│   ├── java/com/example/Elasticsearch
│   │   ├── configuration/
│   │   │   ├── DataLoader.java                     # Loads sample data into Elasticsearch
│   │   │   └── ElasticsearchConfig.java            # Elasticsearch configuration
│   │   ├── Controller/
│   │   │   └── ElasticSearchController.java        # REST API endpoints
│   │   ├── document/
│   │   │   └── CourseDocument.java                 # Elasticsearch document mapping
│   │   ├── DTO/
│   │   │   └── SearchResultDto.java                # Data Transfer Object for search results
│   │   ├── Repository/
│   │   │   └── ElasticSearchRepository.java        # Spring Data Elasticsearch repository
│   │   ├── Service/
│   │   │   └── ElasticSearchService.java           # Business logic for search
│   │   └── ElasticsearchApplication.java           # Main Spring Boot application class
│   └── resources/
│       ├── application.properties                  # Application configuration
│       └── sample-courses.json                     # Sample data file
└── deployment/
    └── docker-compose.yml                          # Docker setup for Elasticsearch


## ⚙️ Setup & Run

### 1️⃣ Clone the repository
-------------------------------
1. Open a terminal (Linux/macOS) or Command Prompt/PowerShell (Windows).
2. Run:
  ```bash
   git clone https://github.com/rajshiparmar/springboot-elasticsearch-course-search.git
   cd springboot-elasticsearch-course-search
  ```

### 2️⃣ Start Elasticsearch using Docker
-------------------------------
1. Ensure **Docker Desktop** (or Docker Engine) is running.
2. Navigate to the project folder if not already there:
    ```bash
    cd springboot-elasticsearch-course-search
   ```
3. Start Elasticsearch container:
   ```bash
   docker-compose -f deployment/docker-compose.yml up -d
   ```
4. Verify Elasticsearch is running:
   ```bash
   curl http://localhost:9200
   ```
   (Expected output: JSON response with "cluster_name", "version", etc.)

### 3️⃣ Run the Spring Boot application
-------------------------------
1. Run the application
   ```bash
    mvn spring-boot:run
   ```

### 4️⃣ Test the API
-------------------------------
1. Open your browser and visit:
   ```bash
   http://localhost:8080/api/search
   ```
   (You should see the list of all sample courses in JSON format.)
2. To test search queries, try with curl or Postman:
  ```bash
  http://localhost:8080/api/search?q=java
  ```
   (This returns courses matching the search term "java".)

### 5️⃣ Stop the services
-------------------------------
1. To stop the Elasticsearch Docker container:
```bash
   docker-compose -f deployment/docker-compose.yml down
```
2. Stop the Spring Boot application by pressing `Ctrl+C` in its terminal.


## 🚀 API Testing Guide

Test the course search API with these sample requests:

### **1. Keyword Search**
```bash
http://localhost:8080/api/search?q=Python
```

### **2. Category Filter**
```bash
http://localhost:8080/api/search?category=Data%20Science
```

### **3. Price Range Filter**
```bash
http://localhost:8080/api/search?minPrice=300&maxPrice=500
```

### **4. Age Filter**
```bash
http://localhost:8080/api/search?minAge=15
http://localhost:8080/api/search?maxAge=18
```

### **5. Course Type Filter**
```bash
http://localhost:8080/api/search?type=In-Person
```

### **6. Pagination Filter**
```bash
http://localhost:8080/api/search?category=Programming&page=0&size=5
```

### **7. Sorting by Price (Ascending)**
```bash
http://localhost:8080/api/search?sort=price_asc
```

### **8. Text Search with Age+Category**
```bash
http://localhost:8080/api/search?q=artificial%20intelligence&minAge=15&category=AI/ML
```
