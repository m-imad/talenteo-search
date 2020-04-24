package com.talenteo.search.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.talenteo.search.document.ArticleDocument;
import com.talenteo.search.service.ArticleService;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api")
@AllArgsConstructor
public class ArticleResource {
	
	private final ArticleService articleService;
	
	@ApiOperation("create an article")
	@PostMapping(path = "/v1/articles",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDocument> createArticle(@RequestBody ArticleDocument articleDocument) {
		articleDocument = articleService.createArticle(articleDocument);
		final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/v1/articles").build().expand(articleDocument.getId()).toUri();
        return ResponseEntity.created(location).body(articleDocument);
    }

	@ApiOperation("update an article")
	@PutMapping(path = "/v1/articles",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDocument> updateArticle(@RequestBody ArticleDocument articleDocument) {
		articleDocument = articleService.createArticle(articleDocument);
        return ResponseEntity.ok().body(articleDocument);
    }
	
	@ApiOperation("delete article")
    @DeleteMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/articles/{id}")
    public ResponseEntity<Void> DeleteAddress(@PathVariable String id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
	
	@ApiOperation("search all article")
    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/articles")
    ResponseEntity<List<ArticleDocument>> getAllArticles() {
        List<ArticleDocument> articles = articleService.getAllArticles();
        return ResponseEntity.ok().body(articles);
    }
	
	
	 @ApiOperation("search article by document id")
	    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/articles/{id}")
	    ResponseEntity<ArticleDocument> getArticleById(@PathVariable String id) {
	        Optional<ArticleDocument> articleDocument = articleService.getArticleById(id);
	        return articleDocument.map(article -> ResponseEntity.ok().body(article)).orElseGet(() -> ResponseEntity.notFound().build());
	    }
	 
	 
	 @ApiOperation("search article by author's name")
	    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/v1/articles/author/{authorName}")
	    ResponseEntity<List<ArticleDocument>> getArticleByAuthorName(@PathVariable String authorName) {
	        List<ArticleDocument> articleDocument = articleService.getArticleByAuthorsName(authorName);
	        return ResponseEntity.ok().body(articleDocument);
	    }
	

}
