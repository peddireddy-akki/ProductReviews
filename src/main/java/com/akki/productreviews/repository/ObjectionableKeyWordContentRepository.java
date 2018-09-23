package com.akki.productreviews.repository;

import java.util.List;

import com.akki.productreviews.common.exceptions.AppConfigException;
import com.akki.productreviews.model.ObjectionableKeyWord;

public interface ObjectionableKeyWordContentRepository {

	public List<ObjectionableKeyWord> readObjectionableKeyWords()throws AppConfigException;
}
