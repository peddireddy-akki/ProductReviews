package com.akki.productreviews.service;

import com.akki.productreviews.common.exceptions.AppConfigException;
import com.akki.productreviews.common.exceptions.ContentSizeException;
import com.akki.productreviews.common.exceptions.ObjectionableContentFoundException;

public interface ObjectionableContentCheckerService {

	public void contentObjectionable(String reviewComments)
			throws ObjectionableContentFoundException, AppConfigException, ContentSizeException;

}
