package io.github.elisa.rest.app.repository

import io.github.elisa.rest.app.domain.entity.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository: JpaRepository<Article, Long> {

    fun findBySlug(slug: String): Article?
    fun findAllByOrderByAddedAtDesc(): Iterable<Article>

}