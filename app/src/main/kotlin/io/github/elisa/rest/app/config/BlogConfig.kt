package io.github.elisa.rest.app.config

import io.github.elisa.rest.app.domain.entity.Article
import io.github.elisa.rest.app.domain.entity.User
import io.github.elisa.rest.app.repository.ArticleRepository
import io.github.elisa.rest.app.repository.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfig {

    @Bean
    fun databaseInitializer(
        articleRepository: ArticleRepository,
        userRepository: UserRepository
    ) = ApplicationRunner {
        val smaldini = userRepository.save(User("smaldini", "stéphane", "Maldini"))

        articleRepository.save(
            Article(
                title = "Reactor Bismuth is out",
                headline = "Lorem ipsum",
                content = "dololr sit amet",
                author = smaldini
            )
        )
        articleRepository.save(
            Article(
                title = "Reactor Aluminium has landed",
                headline = "Lorem ipsum",
                content = "dolor sit amet",
                author = smaldini
            )
        )
    }

}