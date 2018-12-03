package com.adidas.service.review.dao;

import com.adidas.service.review.entity.Review;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Pavel Savinov
 */
public interface ReviewDao extends PagingAndSortingRepository<Review, String> {
}
