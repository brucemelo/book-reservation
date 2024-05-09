package com.github.brucemelo.bookreservation.service;

import com.github.brucemelo.bookreservation.domain.User;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jooq.DSLContext;

import java.util.List;

import static com.github.brucemelo.jooq.generated.Tables.USER_PROFILE;


@Singleton
public class UserService {

    @Inject
    private DSLContext dsl;

    public List<User> findAll() {
        return dsl.selectFrom(USER_PROFILE).fetchInto(User.class);
    }

}
