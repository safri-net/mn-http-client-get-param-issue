package net.safri


import io.micronaut.http.HttpStatus
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification
import spock.lang.Unroll

import javax.inject.Inject

@MicronautTest
class FailingClientTest extends Specification {

    @Inject
    TestClient client

    @Unroll("test client with name: #name and type: #type")
    def "test client"() {
        expect:
        client.test(new TestCommand(name: name, type: type)) == result

        where:
        name   | type   | result
        "John" | Type.A | HttpStatus.OK
        null   | Type.A | HttpStatus.OK
        "John" | null   | HttpStatus.OK
    }
}
