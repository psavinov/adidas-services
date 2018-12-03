package com.adidas.service.review.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Pavel Savinov
 */
@Entity
@Table(name = "Review")
public class Review {

	@Id
	private String productId;
	private Integer reviewsNumber;
	private Double averageScore;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getReviewsNumber() {
		return reviewsNumber;
	}

	public void setReviewsNumber(Integer reviewsNumber) {
		this.reviewsNumber = reviewsNumber;
	}

	public Double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(Double averageScore) {
		this.averageScore = averageScore;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		Review review = (Review) obj;

		if (getProductId() != null &&
			!getProductId().equalsIgnoreCase(review.getProductId())) {

			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public int hashCode() {
		if (getProductId() != null) {
			return getProductId().hashCode();
		}

		return super.hashCode();
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();

		stringBuffer.append("Review {").append(getProductId()).append(", ")
			.append(getReviewsNumber()).append(", ").append(getAverageScore())
			.append("}");

		return stringBuffer.toString();
	}

}
