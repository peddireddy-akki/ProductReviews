package com.akki.productreviews.model;

import java.time.LocalDateTime;

public class ProductReview {

	private String productReviewID;
	private String productID;
	private LocalDateTime reviewDateTime;
	private String reviewContent;
	public ProductReview(String productReviewID, String productID, LocalDateTime reviewDateTime, String reviewContent) {
		super();
		this.productReviewID=productReviewID;
		this.productID = productID;
		this.reviewDateTime = reviewDateTime;
		this.reviewContent = reviewContent;
	}
	
	public ProductReview()
	{
		super();
	}



	public String getProductReviewID() {
		return productReviewID;
	}



	public void setProductReviewID(String productReviewID) {
		this.productReviewID = productReviewID;
	}



	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public LocalDateTime getReviewDateTime() {
		return reviewDateTime;
	}

	public void setReviewDateTime(LocalDateTime reviewDateTime) {
		this.reviewDateTime = reviewDateTime;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	
	public String toString()
	{
		return "ProductReviewID: "+productReviewID+",ProductID: "+productID+", ReviewDateTime: "+reviewDateTime+",reviewContent: "+reviewContent;

	}

}
