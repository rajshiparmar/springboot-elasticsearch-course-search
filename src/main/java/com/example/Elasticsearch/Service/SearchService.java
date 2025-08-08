package com.example.Elasticsearch.Service;

import com.example.Elasticsearch.document.CourseDocument;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.json.JsonData;
import com.example.Elasticsearch.DTO.SearchResultDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final ElasticsearchOperations elasticsearchOperations;

    public SearchService(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }
  
    public SearchResultDto searchCourses(String query, Integer minAge, Integer maxAge, String category,
            String type, Double minPrice, Double maxPrice,
            Instant startDate, String sort, int page, int size) {

        BoolQuery.Builder boolQuery = new BoolQuery.Builder();

        if (query != null && !query.isEmpty()) {
            boolQuery.must(q -> q.multiMatch(m -> m
                    .fields("title", "description")
                    .query(query)
                    ));
                }

        if (minAge != null || maxAge != null) {
            boolQuery.filter(q -> q.range(r -> {
                r.field("minAge");
                if (minAge != null) r.gte(JsonData.of(minAge));
                if (maxAge != null) r.lte(JsonData.of(maxAge));
                return r;
            }));
           }

        if (category != null && !category.isEmpty()) {
            boolQuery.filter(q -> q.term(t -> t.field("category.keyword").value(category)));
        }

        if (type != null && !type.isEmpty()) {
            boolQuery.filter(q -> q.term(t -> t.field("type.keyword").value(type)));
        }

        if (minPrice != null || maxPrice != null) {
            boolQuery.filter(q -> q.range(r -> {
                r.field("price");
                if (minPrice != null) r.gte(JsonData.of(minPrice));
                if (maxPrice != null) r.lte(JsonData.of(maxPrice));
                return r;
            }));
        }

        if (startDate != null) {
            boolQuery.filter(q -> q.range(r -> r
                    .field("nextSessionDate")
                    .gte(JsonData.of(startDate.toString()))));
        }

        NativeQuery queryObj = new NativeQueryBuilder()
                .withQuery(q -> q.bool(boolQuery.build()))
                .withPageable(PageRequest.of(page, size))
                .withSort(sort != null && sort.equalsIgnoreCase("pricedesc")
                        ? s -> s.field(f -> f.field("price").order(SortOrder.Desc))
                        : s -> s.field(f -> f.field("price").order(SortOrder.Asc)))
                .build();

        SearchHits<CourseDocument> searchHits = elasticsearchOperations.search(queryObj, CourseDocument.class);

        List<CourseDocument> courses = searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

        return new SearchResultDto(searchHits.getTotalHits(), courses);
    }

    }