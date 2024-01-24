package io.github.elisa.rest.app.repository

import io.github.elisa.rest.app.domain.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class UserRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: UserRepository) {

    @Test
    fun `When findByLogin then return User`() {
        val starbuck = User("user", "buck", "star");

        entityManager.persist(starbuck)
        entityManager.flush()

        val user = userRepository.findByLogin(starbuck.login)

        assertThat(user).isEqualTo(starbuck)
    }

}