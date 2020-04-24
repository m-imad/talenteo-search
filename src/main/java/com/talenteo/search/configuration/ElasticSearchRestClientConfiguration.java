package com.talenteo.search.configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.talenteo.search.document.ArticleDocument;
import com.talenteo.search.document.Auteur;
import com.talenteo.search.repository.ArticleRepository;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.util.Arrays;


@Configuration
@EnableElasticsearchRepositories(basePackages = "com.talenteo.search.repository")
public class ElasticSearchRestClientConfiguration extends AbstractElasticsearchConfiguration {

	@Autowired private  ElasticsearchOperations elasticsearchOperations;
	@Autowired private ArticleRepository articleRepository;

    
	
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        return new RestHighLevelClient(RestClient.builder(HttpHost.create("localhost:9200")));
    }
    
    
    @PreDestroy
    public void deleteIndex() {
    	elasticsearchOperations.deleteIndex(ArticleDocument.class);
    }
    
    @PostConstruct
    public void insertDataSample() {
        elasticsearchOperations.createIndex(ArticleDocument.class);
        articleRepository.deleteAll();
    	elasticsearchOperations.refresh(ArticleDocument.class);

        articleRepository.save(ArticleDocument.builder().title("Article 1").authors(Arrays.asList(new Auteur("Imad"))).build());
        articleRepository.save(ArticleDocument.builder().title("Article 2").authors(Arrays.asList(new Auteur("Imad"),new Auteur("Jack"))).build());
        articleRepository.save(ArticleDocument.builder().title("Article 3").authors(Arrays.asList(new Auteur("Jack"))).build());
        articleRepository.save(ArticleDocument.builder().title("Article 4").authors(Arrays.asList(new Auteur("Amine"),new Auteur("Rachid"))).build());
        articleRepository.save(ArticleDocument.builder().title("Article 5").authors(Arrays.asList(new Auteur("Rachid"))).build());

    }
}