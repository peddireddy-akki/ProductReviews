/**
* The ProductReviewsController is REST end point for ProductReviews subsystem. The primary purpose of this class is to
* handle the http traffic that comes from Product reviews producing applications.
* @author  Akki
* @version 1.0
* @since   2018-09-24 
*/

package com.akki.productreviews.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.akki.productreviews.common.exceptions.AppConfigException;
import com.akki.productreviews.common.exceptions.ApplicationException;
import com.akki.productreviews.common.exceptions.ContentSizeException;
import com.akki.productreviews.common.exceptions.ObjectionableContentFoundException;
import com.akki.productreviews.model.ProductReview;
import com.akki.productreviews.service.ObjectionableContentCheckerService;

@RestController
@RequestMapping("/productReviews")
public class ProductReviewsController {
	private static final Logger logger = LogManager.getLogger(ProductReviewsController.class);

	@Autowired
	ObjectionableContentCheckerService contentChecker;

	@RequestMapping("productReview/{productReviewID}")
	public ProductReview getProductReview(@PathVariable String productReviewID) {
		logger.info("Request for product review with product: " + productReviewID);
		String tempReviewID = UUID.randomUUID().toString();
		System.out.println("UUID: " + tempReviewID);
		return new ProductReview(tempReviewID, "123", LocalDateTime.now(), "great product");
	}

	@RequestMapping(value = "productReview", method = RequestMethod.POST)
	public ResponseEntity<ProductReview> saveProdctReview(@RequestBody ProductReview productReview)
			throws ApplicationException {
		if (logger.isDebugEnabled()) {
			logger.debug("Product Review Received:" + productReview);
		}

		try {
			validateInputContent(productReview);
			String productReviewContent = productReview.getReviewContent();
			contentChecker.contentObjectionable(productReviewContent);
			productReview.setProductReviewID(UUID.randomUUID().toString());
		} catch (ObjectionableContentFoundException objectionalContentException) {

			objectionalContentException.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
			throw objectionalContentException;
		} catch (ContentSizeException contException) {

			contException.setHttpStatus(HttpStatus.BAD_REQUEST);
			throw contException;
		}
		
		
		catch (AppConfigException appConfigException) {

			appConfigException.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			throw appConfigException;
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("Saved ProductReview :" + productReview);
		}
		return new ResponseEntity<ProductReview>(productReview, HttpStatus.OK);
	}
	private void validateInputContent(ProductReview productReview) throws ContentSizeException {
		String productReviewContent = productReview.getReviewContent();
		if (productReviewContent == null) {
			throw new ContentSizeException("Product review content can't be empty");
		}
		if ((productReviewContent != null) && (productReviewContent.length() <= 20)) {
			throw new ContentSizeException("Product review content should be atleast 20 characters");
		}

		if ((productReviewContent != null) && (productReviewContent.length() > 200)) {
			throw new ContentSizeException("Product review content should be less than 200 characters");
		}

	}

}