/**
* The primary responsibility of the class ObjectionableContentCheckerServiceImpl is to identify the objectionable 
* keywords present from the product review comments. Objectionable keywords master list are configurable through file
* @author  Akki
* @version 1.0
* @since   2018-09-24 
*/


package com.akki.productreviews.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akki.productreviews.common.exceptions.AppConfigException;
import com.akki.productreviews.common.exceptions.ContentSizeException;
import com.akki.productreviews.common.exceptions.ObjectionableContentFoundException;
import com.akki.productreviews.controller.ProductReviewsController;
import com.akki.productreviews.model.ObjectionableKeyWord;
import com.akki.productreviews.repository.ObjectionableKeyWordContentRepository;

@Service
public class ObjectionableContentCheckerServiceImpl implements ObjectionableContentCheckerService {

	private static final Logger logger = LogManager.getLogger(ProductReviewsController.class);
	@Autowired
	private ObjectionableKeyWordContentRepository objectionableKeyWordContentRepository;

	public void contentObjectionable(String reviewComments)
			throws ObjectionableContentFoundException, AppConfigException, ContentSizeException {

		if (reviewComments == null) {
			throw new ContentSizeException("Review comments should not be empty");
		}
		if (reviewComments.isEmpty()) {
			throw new ContentSizeException("Review comments should not be empty");
		}
		List<ObjectionableKeyWord> objectionableKeyWordContent = objectionableKeyWordContentRepository
				.readObjectionableKeyWords();
		List<String> objectionableContentFoundFromReviewComments = new ArrayList<String>();

		String revewComentsAllInLowerCase = reviewComments.toLowerCase();
		String purifiedReviewComments = purifyContent(revewComentsAllInLowerCase, objectionableKeyWordContent);
		boolean isMatchFound;
		for (ObjectionableKeyWord objKeyWord : objectionableKeyWordContent) {

			Pattern pattern = Pattern.compile("\\b" + objKeyWord.getKeyWord().toLowerCase() + "\\b");

			Matcher matcher = pattern.matcher(purifiedReviewComments);
			isMatchFound = matcher.find();

			if (isMatchFound) {
				objectionableContentFoundFromReviewComments.add(objKeyWord.getKeyWord());
				if (logger.isDebugEnabled()) {
					logger.debug("Matched with bad word: " + objKeyWord.getKeyWord());
				}
			}

		}
		if (objectionableContentFoundFromReviewComments.size() > 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("List of objectionable keywords found from review comments: " + objectionableContentFoundFromReviewComments);
			}
			throw new ObjectionableContentFoundException(
					"The review comments has objectionable keywords that gives the meening of " + objectionableContentFoundFromReviewComments);
		}

	}

	private String purifyContent(String reviewComment, List<ObjectionableKeyWord> objectionableKeyWordContent)
			throws ContentSizeException {

		if (logger.isDebugEnabled()) {
			logger.debug("input review comment:" + reviewComment);
		}

		if ((reviewComment == null) && (reviewComment.isEmpty())) {
			throw new ContentSizeException("Review comments should not be empty");
		}
		if(reviewComment.contains("!"))
		{
			reviewComment = reviewComment.replaceAll("!", "i");
			if (logger.isDebugEnabled()) {
				logger.debug("Review comments after replacing ! with i :" + reviewComment);
			}
		}
		
		if(reviewComment.contains("1"))
		{
				reviewComment = reviewComment.replaceAll("1", "i");
				if (logger.isDebugEnabled()) {
					logger.debug("Review comments after replacing 1 with i :" + reviewComment);
				}
		}
		
		if(reviewComment.contains("3"))
		{
			reviewComment = reviewComment.replaceAll("3", "e");
			if (logger.isDebugEnabled()) {
				logger.debug("Review comments after replacing 3 with 3 :" + reviewComment);
			}
		}
		
		if(reviewComment.contains("4"))
		{
				reviewComment = reviewComment.replaceAll("4", "a");
				if (logger.isDebugEnabled()) {
					logger.debug("Review comments after replacing 4 with a :" + reviewComment);
				}
		}
		
		if(reviewComment.contains("@"))
		{
				reviewComment = reviewComment.replaceAll("@", "a");
				if (logger.isDebugEnabled()) {
					logger.debug("Review comments after replacing @ with a :" + reviewComment);
				}
		}
		
		if(reviewComment.contains("5"))
		{				
				reviewComment = reviewComment.replaceAll("5", "s");
				if (logger.isDebugEnabled()) {
					logger.debug("Review comments after replacing 5 with s :" + reviewComment);
				}
		}
		
		if(reviewComment.contains("7"))
		{
			reviewComment = reviewComment.replaceAll("7", "t");
			if (logger.isDebugEnabled()) {
				logger.debug("Review comments after replacing t with t :" + reviewComment);
			}
		}
		
		if(reviewComment.contains("0"))
		{
			reviewComment = reviewComment.replaceAll("0", "o");
			if (logger.isDebugEnabled()) {
				logger.debug("Review comments after replacing 0 with o :" + reviewComment);
			}
			
		}
		
		if(reviewComment.contains("9"))
		{
			reviewComment = reviewComment.replaceAll("9", "g");
			if (logger.isDebugEnabled()) {
				logger.debug("Review comments after replacing 9 with g :" + reviewComment);
			}
		}
		
		if(reviewComment.contains("$"))
		{
			reviewComment = reviewComment.replaceAll("\\$", "s");
			if (logger.isDebugEnabled()) {
				logger.debug("Review comments after replacing \\$ with s :" + reviewComment);
			}
		}

		if(reviewComment.contains("\\s"))
		{
			reviewComment = reviewComment.replaceAll("[\\s]+", " ");
			if (logger.isDebugEnabled()) {
				logger.debug("Review comments after replacing consecutive spaces with single space :" + reviewComment);
			}
		}
		
		
		if(reviewComment.contains("\\s"))
		{
			reviewComment = reviewComment.replaceAll("[^a-zA-Z0-9\\s]", "");
			if (logger.isDebugEnabled()) {
				logger.debug("Review comments after replacing non alphabets, non digits and non spaces with nothing :" + reviewComment);
			}
		}

		for (ObjectionableKeyWord objKeyWord : objectionableKeyWordContent) {
			reviewComment = reviewComment.replaceAll(objKeyWord.getRegExpression(), objKeyWord.getKeyWord());

		}
		if (logger.isDebugEnabled()) {
			logger.debug("Purified review content:" + reviewComment);
		}

		return reviewComment;
	}

}
