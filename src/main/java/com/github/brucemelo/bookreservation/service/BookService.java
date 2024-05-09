package com.github.brucemelo.bookreservation.service;

import com.github.brucemelo.bookreservation.domain.Book;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;
import java.util.Optional;

import static com.github.brucemelo.jooq.generated.Tables.BOOK;


@Singleton
public class BookService {

    @Inject
    private DSLContext dsl;

    public List<Book> findBooks(Optional<String> title, Optional<String> author) {
        var query = dsl.selectFrom(BOOK);
        Condition condition = DSL.trueCondition();
        if (title.isPresent()) {
            condition = condition.and(BOOK.TITLE.likeIgnoreCase("%" + title.get() + "%"));
        }
        if (author.isPresent()) {
            condition = condition.and(BOOK.AUTHOR.likeIgnoreCase("%" + author.get() + "%"));
        }
        return query.where(condition).fetchInto(Book.class);
    }

}
