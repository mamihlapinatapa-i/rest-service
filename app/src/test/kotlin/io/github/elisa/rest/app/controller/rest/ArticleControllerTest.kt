package io.github.elisa.rest.app.controller.rest

import com.ninjasquad.springmockk.MockkBean
import io.github.elisa.rest.app.domain.entity.Article
import io.github.elisa.rest.app.domain.entity.User
import io.github.elisa.rest.app.repository.ArticleRepository
import io.github.elisa.rest.app.repository.UserRepository
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class ArticleControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var articleRepository: ArticleRepository

    @MockkBean
    private lateinit var userRepository: UserRepository

    @Test
    fun `list articles`() {
        val juergen = User("springjuergen", "Juergen", "Hoeller")
        val spring5Article = Article(
            "Spring Framework 5.0 goes GA",
            "Dear Spring community...",
            "Lorem Ipsum",
            juergen
        )

        val spring43Article = Article(
            "Spring Framework 4.3 goes GA",
            "Dear Spring community...",
            "Lorem ipsum",
            juergen
        )

        every {
            articleRepository.findAllByOrderByAddedAtDesc()
        } returns listOf(spring5Article, spring43Article)

        mockMvc.perform(get("/api/article/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].author.login").value(juergen.login))
            .andExpect(jsonPath("\$.[0].slug").value(spring5Article.slug))
            .andExpect(jsonPath("\$.[1].author.login").value(juergen.login))
            .andExpect(jsonPath("\$.[1].slug").value(spring43Article.slug))
    }

}