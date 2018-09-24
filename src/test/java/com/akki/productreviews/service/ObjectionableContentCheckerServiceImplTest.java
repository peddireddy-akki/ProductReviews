package com.akki.productreviews.service;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.akki.productreviews.common.exceptions.AppConfigException;
import com.akki.productreviews.common.exceptions.ApplicationException;
import com.akki.productreviews.common.exceptions.ContentSizeException;
import com.akki.productreviews.common.exceptions.ObjectionableContentFoundException;
import com.akki.productreviews.model.ObjectionableKeyWord;
import com.akki.productreviews.repository.ObjectionableKeyWordContentRepository;

@RunWith(SpringRunner.class)
public class ObjectionableContentCheckerServiceImplTest {

	@TestConfiguration
	static class ObjectionableKeyWordContentFileRepositoryImplConfiguration {
		@Bean
		public ObjectionableContentCheckerServiceImpl ObjectionableContentCheckerServiceImpl() {
			return new ObjectionableContentCheckerServiceImpl();
		}
	}

	@Autowired
	private ObjectionableContentCheckerServiceImpl objectionableContentCheckerServiceImpl;

	@MockBean
	private ObjectionableKeyWordContentRepository objectionableKeyWordContentRepository;

	@Before
	public void setUp() throws AppConfigException {

		List<ObjectionableKeyWord> objectionableKeyWordContent = new ArrayList<ObjectionableKeyWord>();
		ObjectionableKeyWord objKeyWord1 = new ObjectionableKeyWord("ape");
		ObjectionableKeyWord objKeyWord2 = new ObjectionableKeyWord("monkey");
		ObjectionableKeyWord objKeyWord3 = new ObjectionableKeyWord("sucks");
		ObjectionableKeyWord objKeyWord4 = new ObjectionableKeyWord("ugly");
		ObjectionableKeyWord objKeyWord5 = new ObjectionableKeyWord("shit");
		ObjectionableKeyWord objKeyWord6 = new ObjectionableKeyWord("bad word");

		objectionableKeyWordContent.add(objKeyWord1);
		objectionableKeyWordContent.add(objKeyWord2);
		objectionableKeyWordContent.add(objKeyWord3);
		objectionableKeyWordContent.add(objKeyWord4);
		objectionableKeyWordContent.add(objKeyWord5);
		objectionableKeyWordContent.add(objKeyWord6);

		Mockito.when(objectionableKeyWordContentRepository.readObjectionableKeyWords())
				.thenReturn(objectionableKeyWordContent);

	}

	@Test
	public void whenValidReviewContent_thenServiceRetunNoBadWordsList()
			throws ObjectionableContentFoundException, AppConfigException, ContentSizeException {

		String reviewComments = "Great product";
		objectionableContentCheckerServiceImpl.contentObjectionable(reviewComments);
	}

	@Test
	public void whenReviewCommentsContainObjectionableKeyWords_thenServiceRetunsAllObjectionableKeyWords() {

		try {
			String reviewComments = "Product is OK, mone3y, @pe, $hit, sssssuuuuccckkks, ugly and bad word";
			objectionableContentCheckerServiceImpl.contentObjectionable(reviewComments);
		} catch (ApplicationException appException) {
			String errorDetails = appException.getErrorDetails();

			assertEquals("Sucks found", true, errorDetails.contains("sucks"));
			assertEquals("Shit found", true, errorDetails.contains("shit"));
			assertEquals("Ape found", true, errorDetails.contains("ape"));
			assertEquals("Ugly found", true, errorDetails.contains("ugly"));
			assertEquals("Bad word found", true, errorDetails.contains("bad word"));
		}

	}
}
