package net.safri

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller

import javax.annotation.Nullable
import javax.validation.Valid

@Controller
class TestController implements TestOperations {

    @Override
    HttpStatus test(@Nullable @Valid TestCommand req) {
        return HttpStatus.OK
    }
}
