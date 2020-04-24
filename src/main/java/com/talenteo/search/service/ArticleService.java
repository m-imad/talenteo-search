package com.talenteo.search.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.talenteo.search.document.ArticleDocument;
import com.talenteo.search.repository.ArticleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticleService {
	
	private final ArticleRepository articleRepository;
	
	public ArticleDocument createArticle(ArticleDocument articleDocument) {
		return articleRepository.save(articleDocument);
	}
	
	public ArticleDocument updateArticle(String id, ArticleDocument articleDocument) {
		getArticleById(id);
		return articleRepository.save(articleDocument);
	}
	
	public void deleteArticle(String id) {
		getArticleById(id);
		articleRepository.deleteById(id);
	}
	
	public List<ArticleDocument> getAllArticles(){
		List<ArticleDocument> articleDocuments = new ArrayList<ArticleDocument>();
		articleRepository.findAll().forEach(articleDocuments::add);
		return articleDocuments;
	}
	
	public List<ArticleDocument> getArticleByAuthorsName(String name) {
		return articleRepository.findByAuthorsName(name);
	}
	
	public Optional<ArticleDocument> getArticleById(String id) {
		return articleRepository.findById(id);
	}
	

}
