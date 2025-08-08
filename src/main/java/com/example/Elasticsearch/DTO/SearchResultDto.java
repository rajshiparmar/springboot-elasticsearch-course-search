package com.example.Elasticsearch.DTO;

import java.util.List;
import com.example.Elasticsearch.document.CourseDocument;
import lombok.Data;

@Data
public class SearchResultDto {

	    private long total;
	  
	    private List<CourseDocument> courses;
	    
		public SearchResultDto(long total, List<CourseDocument> courses) {
			super();
			this.total = total;
			this.courses = courses;
		}
}
