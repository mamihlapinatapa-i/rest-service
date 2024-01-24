package io.github.elisa.rest.app.repository

import io.github.elisa.rest.app.domain.entity.Article
import io.github.elisa.rest.app.domain.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class ArticleRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val articleRepository: ArticleRepository
) {

    @Test
    fun `When findByIdOrNull then return Article`() {
        val starbuck = User("user", "buck", "star");

        entityManager.persist(starbuck)

        val article = Article("Spring Framework 5.0 goes GA", "Dear Spring community ...", "Lorem ipsum", starbuck)

        entityManager.persist(article)
        entityManager.flush()

        val found = articleRepository.findByIdOrNull(article.id!!)

        assertThat(found).isEqualTo(article)
    }

}