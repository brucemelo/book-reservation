package com.github.brucemelo.bookreservation.service;


import com.github.brucemelo.bookreservation.domain.Review;
import com.github.brucemelo.bookreservation.infrastructure.AppMappers;
import com.github.brucemelo.bookreservation.service.exceptions.BookReviewedByUser;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jooq.DSLContext;

import java.util.List;

import static com.github.brucemelo.jooq.generated.Tables.REVIEW;

@Singleton
public class ReviewService {

    @Inject
    private DSLContext dsl;

    public Review create(Review review) {
        var count = dsl.fetchCount(REVIEW, REVIEW.USER_ID.eq(review.user().id()).and(REVIEW.BOOK_ID.eq(review.book().id())));
        if (count > 0) {
            throw new BookReviewedByUser();
        }
        var reviewRecord = dsl.insertInto(REVIEW, REVIEW.BOOK_ID, REVIEW.USER_ID, REVIEW.REVIEW_DATE, REVIEW.COMMENT, REVIEW.SCORE)
                .values(review.book().id(), review.user().id(), review.reviewDate(), review.comment(), review.score()).returning()
                .fetchOne();
        return AppMappers.INSTANCE.toReview(reviewRecord);
    }

    public List<Review> findAll() {
        return dsl.selectFrom(REVIEW).fetch().map(AppMappers.INSTANCE::toReview);
    }

}
