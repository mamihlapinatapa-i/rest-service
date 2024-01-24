package io.github.elisa.rest.app.repository

import io.github.elisa.rest.app.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    fun findByIdIsNotNull(): List<User>?
    fun findByLogin(login: String): User?

}