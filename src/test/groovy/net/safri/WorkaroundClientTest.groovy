package net.safri

import io.micronaut.context.annotation.Property
import io.micronaut.core.util.StringUtils
import io.micronaut.http.HttpStatus
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification
import spock.lang.Unroll

import javax.inject.Inject

@MicronautTest
@Property(name = 'jackson.deserialization.read-unknown-enum-values-as-null', value = StringUtils.TRUE)
class WorkaroundClientTest extends Specification {

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
