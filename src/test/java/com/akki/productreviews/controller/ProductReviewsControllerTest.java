package com.akki.productreviews.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.akki.productreviews.common.exceptions.ObjectionableContentFoundException;
import com.akki.productreviews.common.util.GsonBuilderUtil;
import com.akki.productreviews.model.ProductReview;
import com.akki.productreviews.repository.ObjectionableKeyWordContentFileRepositoryImpl;
import com.akki.productreviews.service.ObjectionableContentCheckerServiceImpl;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductReviewsController.class, secure = false)

public class ProductReviewsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ObjectionableContentCheckerServiceImpl objectionableContentCheckerServiceImpl;

	@MockBean
	private ObjectionableKeyWordContentFileRepositoryImpl objectionableKeyWordContentFileRepositoryImpl;

	@Test
	public void whenValidReviewContent_thenRestServiceReturnHTTP_CREATED_StatusCode() throws Exception {
		ProductReview productReview = new ProductReview();
		productReview.setProductID("123");
		productReview.setReviewContent("Great product. I recommend to buy");

		productReview.setReviewDateTime(LocalDateTime.now());

		Mockito.doNothing().when(objectionableContentCheckerServiceImpl).contentObjectionable(Mockito.anyString());

		Gson gson = GsonBuilderUtil.CreateGsonBuilder();
		String productReviewJson = gson.toJson(productReview);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/productReviews/productReview/")
				.accept(MediaType.APPLICATION_JSON).content(productReviewJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());

		System.out.println("Return Customer as string:" + result.getResponse().getContentAsString());

	}
	
	@Test
	public void ReviewContentWithObjeectionableWords_thenRestServiceReturnHTTP_NOT_ACEEPTABLE_StatusCode() throws Exception {
		ProductReview productReview = new ProductReview();
		productReview.setProductID("123");
		productReview.setReviewContent("Great product. I recommend to buy @ss");

		productReview.setReviewDateTime(LocalDateTime.now());

		Mockito.doThrow(new ObjectionableContentFoundException("Objectionable Content")).when(objectionableContentCheckerServiceImpl).contentObjectionable(Mockito.anyString());

		Gson gson = GsonBuilderUtil.CreateGsonBuilder();
		String productReviewJson = gson.toJson(productReview);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/productReviews/productReview/")
				.accept(MediaType.APPLICATION_JSON).content(productReviewJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), result.getResponse().getStatus());
		
		//HttpStatus.

		System.out.println("Return Customer as string:" + result.getResponse().getContentAsString());

	}


}
