package com.example.Elasticsearch.document;

import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.CompletionField;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.suggest.Completion;
import lombok.Data;

@Document(indexName = "courses")
@Data
public class CourseDocument {

	@Id
	String id;
	
	String title;
	
	String description;
	
	String category;
	
	String type;
	
	String gradeRange;
	
	 int minAge;
	 
	 int maxAge;
	 
	 double price;
	 
	 @Field(type = FieldType.Date, format = DateFormat.epoch_millis)
	 Instant  nextSessionDate;
	 
	 @CompletionField(maxInputLength = 100) 
	    private Completion suggest;
}
