package com.github.brucemelo.bookreservation.web;

import com.github.brucemelo.bookreservation.domain.User;
import com.github.brucemelo.bookreservation.service.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/users")
public class UserController {

    @Inject
    private UserService userService;

    @Get
    public HttpResponse<List<User>> findAll() {
        return HttpResponse.ok(userService.findAll());
    }

}
