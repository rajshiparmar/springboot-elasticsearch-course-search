package com.example.Elasticsearch.configuration;

import com.example.Elasticsearch.Repository.CourseRepository;
import com.example.Elasticsearch.document.CourseDocument;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.elasticsearch.core.suggest.Completion;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
	
    private final CourseRepository courseRepository;

    public DataLoader(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());  
        TypeReference<List<CourseDocument>> typeReference = new TypeReference<List<CourseDocument>>() {};
        
        InputStream inputStream = TypeReference.class.getResourceAsStream("/sample-courses.json");
        
        try {
            List<CourseDocument> courses = mapper.readValue(inputStream, typeReference);
        	
        	   courses.forEach(course -> {
                   course.setSuggest(new Completion(new String[]{course.getTitle()}));
               });
        	
            courseRepository.saveAll(courses);
            System.out.println("Sample courses data loaded successfully!");
        } catch (Exception e) {
            System.out.println("Failed to load sample courses: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
