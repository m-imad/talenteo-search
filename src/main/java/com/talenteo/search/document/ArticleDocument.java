package com.talenteo.search.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "blog")
public class ArticleDocument {
	
	@Id
	private String id;
	
	private String title;
	
	@Field(type = FieldType.Nested, includeInParent = true)
    private List<Auteur> authors;

}
