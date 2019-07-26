package net.safri


import io.micronaut.http.client.annotation.Client

@Client('${url:`http://localhost:8080`}')
interface TestClient extends TestOperations {
}
