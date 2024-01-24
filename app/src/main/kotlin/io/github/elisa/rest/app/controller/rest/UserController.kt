package io.github.elisa.rest.app.controller.rest

import io.github.elisa.rest.app.domain.entity.User
import io.github.elisa.rest.app.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/user")
class UserController(private val repository: UserRepository) {

    @GetMapping("/")
    fun findByIdIsNotNull(): List<User>? = repository.findByIdIsNotNull()

    @GetMapping("/{login}")
    fun findOne(@PathVariable login: String) = repository.findByLogin(login) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This user is not exist")

}