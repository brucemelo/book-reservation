package com.github.brucemelo.bookreservation;

import com.github.brucemelo.bookreservation.domain.User;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class UserControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    @DisplayName("Should list users")
    public void testListUsers() {
        var readResponse = client.toBlocking().exchange(HttpRequest.GET("api/users"), Argument.listOf(User.class));
        assertEquals(HttpStatus.OK, readResponse.getStatus());
    }

}