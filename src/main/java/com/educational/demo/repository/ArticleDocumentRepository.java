package com.educational.demo.repository;

import com.educational.demo.dto.ArticleDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 17875
 */
@Repository
public interface ArticleDocumentRepository extends ElasticsearchCrudRepository<ArticleDocument, Long> {

    List<ArticleDocument> findDistinctByTitleLikeOrSummaryLikeOrContentLike(String title, String summary, String content);
}
