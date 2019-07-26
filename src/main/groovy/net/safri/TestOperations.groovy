package net.safri

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Get

import javax.annotation.Nullable
import javax.validation.Valid

interface TestOperations {
    @Get("/{?req*}")
    HttpStatus test(@Nullable @Valid TestCommand req)
}
