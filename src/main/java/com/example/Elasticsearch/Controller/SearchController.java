package com.example.Elasticsearch.Controller;

import java.time.Instant;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Elasticsearch.DTO.SearchResultDto;
import com.example.Elasticsearch.Service.SearchService;

@RestController
@RequestMapping("/api")
public class SearchController {

	    private final SearchService searchService;
	    
	    public SearchController(SearchService searchService) {
	        this.searchService = searchService;
	    }
	    
	    @GetMapping("/search")
	    public SearchResultDto  search(
	            @RequestParam(required = false) String q,
	            @RequestParam(required = false) Integer minAge,
	            @RequestParam(required = false) Integer maxAge,
	            @RequestParam(required = false) String category,
	            @RequestParam(required = false) String type,
	            @RequestParam(required = false) Double minPrice,
	            @RequestParam(required = false) Double maxPrice,
	            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant startDate,
	            @RequestParam(required = false) String sort,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size) {
	    	
	        return searchService.searchCourses(q, minAge, maxAge, category, type, 
	                                         minPrice, maxPrice, startDate, sort, page, size);
	    }
}
