package com.adidas.service.review.dao;

import com.adidas.service.review.entity.Review;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Review DAO, provides access to CRUD operations for the {@link Review} model.
 *
 * @author Pavel Savinov
 */
public interface ReviewDao extends PagingAndSortingRepository<Review, String> {
}
