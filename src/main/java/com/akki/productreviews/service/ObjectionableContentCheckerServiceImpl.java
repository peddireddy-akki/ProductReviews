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
import com.akki.productreviews.controller.model.ObjectionableKeyWord;
import com.akki.productreviews.repository.ObjectionableKeyWordContentRepository;

@Service
public class ObjectionableContentCheckerServiceImpl implements ObjectionableContentCheckerService {

	private static final Logger logger = LogManager.getLogger(ProductReviewsController.class);
	@Autowired
	private ObjectionableKeyWordContentRepository objectionableKeyWordContentRepository;

	public void contentObjectionable(String reviewComments)
			throws ObjectionableContentFoundException, AppConfigException, ContentSizeException {
		
		
		if ((reviewComments == null) && (reviewComments.isEmpty())) {
			throw new ContentSizeException("Review comments should not be empty");
		}
		List<ObjectionableKeyWord> objectionableKeyWordContent = objectionableKeyWordContentRepository
				.readObjectionableKeyWords();
		List<String> objectContent = new ArrayList<String>();

		//String revComents = new String(reviewComments);
		String revComents = reviewComments.toLowerCase();
		String purifiedReviewComments = purifyContent(revComents, objectionableKeyWordContent);
		boolean isMatchFound;
		for (ObjectionableKeyWord objKeyWordCon : objectionableKeyWordContent) {

			Pattern pattern = Pattern.compile("\\b" + objKeyWordCon.getKeyWord().toLowerCase() + "\\b");

			Matcher matcher = pattern.matcher(purifiedReviewComments);
			isMatchFound = matcher.find();

			if (isMatchFound) {
				if(logger.isDebugEnabled())
				{
					logger.debug("Matched with bad word: " + objKeyWordCon.getKeyWord());
					objectContent.add(objKeyWordCon.getKeyWord());
				}
			}

		}
		if (objectContent.size() > 0) {
			throw new ObjectionableContentFoundException(
					"The review comments has objectionable keywords that gives the meening of " + objectContent);
		}

	}

	private String purifyContent(String reviewComment, List<ObjectionableKeyWord> objectionableKeyWordContent)
			throws ContentSizeException {
		
		if(logger.isDebugEnabled())
		{
			logger.debug("input review comment:"+reviewComment);
		}

		if ((reviewComment == null) && (reviewComment.isEmpty())) {
			throw new ContentSizeException("Review comments should not be empty");
		}
		reviewComment = reviewComment.replaceAll("!", "i");
		reviewComment = reviewComment.replaceAll("1", "i");
		reviewComment = reviewComment.replaceAll("3", "e");
		reviewComment = reviewComment.replaceAll("4", "a");
		reviewComment = reviewComment.replaceAll("@", "a");
		reviewComment = reviewComment.replaceAll("5", "s");
		reviewComment = reviewComment.replaceAll("7", "t");
		reviewComment = reviewComment.replaceAll("0", "o");
		reviewComment = reviewComment.replaceAll("9", "g");
		reviewComment = reviewComment.replaceAll("\\$", "s");

		reviewComment = reviewComment.replaceAll("[\\s]+", " ");
		reviewComment = reviewComment.replaceAll("[^a-zA-Z0-9\\s]", "");

		for (ObjectionableKeyWord objKeyWordCon : objectionableKeyWordContent) {
			reviewComment = reviewComment.replaceAll(objKeyWordCon.getRegExpression(), objKeyWordCon.getKeyWord());

		}
		if(logger.isDebugEnabled())
		{
			logger.debug("Purified review content:" + reviewComment);		
		}
		
		return reviewComment;
	}

}
