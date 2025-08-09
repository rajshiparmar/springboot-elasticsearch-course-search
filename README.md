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
```bash
git clone https://github.com/rajshiparmar/springboot-elasticsearch-course-search.git
cd springboot-elasticsearch-course-search

### 2️⃣ Start Elasticsearch using Docker

first start docker

docker-compose -f deployment/docker-compose.yml up -d

Verify it run or not-
curl http://localhost:9200

### 3️⃣ Run the Spring Boot application
 in browser use check
it will give return all Jason courses data
http://localhost:8080/api/search

