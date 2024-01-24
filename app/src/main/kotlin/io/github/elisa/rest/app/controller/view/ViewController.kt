package io.github.elisa.rest.app.controller.view

import io.github.elisa.rest.app.domain.entity.Article
import io.github.elisa.rest.app.domain.entity.User
import io.github.elisa.rest.app.prop.BlogProp
import io.github.elisa.rest.app.repository.ArticleRepository
import io.github.elisa.rest.utilities.Extensions.Companion.format
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Controller
class ViewController(private val repository: ArticleRepository, private val prop: BlogProp) {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = prop.title
        model["banner"] = prop.banner
        model["articles"] = repository.findAllByOrderByAddedAtDesc().map { it.render() }

        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        val article = repository.findBySlug(slug)?.render() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")

        model["title"] = article.title
        model["article"] = article

        return "article"
    }

    fun Article.render() = RenderedArticle(
        slug,
        title,
        headline,
        content,
        author,
        addedAt.format()
    )

    data class RenderedArticle(
        val slug: String,
        val title: String,
        val headline: String,
        val content: String,
        val author: User,
        val addedAt: String
    )

}