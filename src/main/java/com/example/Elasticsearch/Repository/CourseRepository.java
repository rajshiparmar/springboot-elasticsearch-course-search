package com.example.Elasticsearch.Repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.example.Elasticsearch.document.CourseDocument;

public interface CourseRepository extends ElasticsearchRepository<CourseDocument, String>  {  }
