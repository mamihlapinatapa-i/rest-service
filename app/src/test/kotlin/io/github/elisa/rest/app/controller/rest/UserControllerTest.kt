package io.github.elisa.rest.app.controller.rest

import com.ninjasquad.springmockk.MockkBean
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
class UserControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var articleRepository: ArticleRepository

    @MockkBean
    private lateinit var userRepository: UserRepository

    @Test
    fun `List users`() {
        val juergen = User("springjuergen", "Juergen", "Hoeller")
        val smaldini = User("smaldini", "Stéphane", "Maldini")

        every {
            userRepository.findByIdIsNotNull()
        } returns listOf(juergen, smaldini)

        mockMvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType(MediaType.APPLICATION_JSON)))
            .andExpect(jsonPath("\$.[0].login").value(juergen.login))
            .andExpect(jsonPath("\$[1].login").value(smaldini.login))
    }

}