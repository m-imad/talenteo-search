package com.talenteo.search.repository;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.talenteo.search.document.ArticleDocument;

@Repository
public interface ArticleRepository extends ElasticsearchRepository<ArticleDocument, String>{
	
	List<ArticleDocument> findByAuthorsName(String name);
	
	@Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
	List<ArticleDocument> findByAuthorsNameUsingCustomQuery(String name);

}
