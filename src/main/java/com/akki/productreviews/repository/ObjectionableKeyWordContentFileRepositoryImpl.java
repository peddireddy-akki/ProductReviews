package com.akki.productreviews.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.akki.productreviews.common.exceptions.AppConfigException;
import com.akki.productreviews.model.ObjectionableKeyWord;

@Repository
public class ObjectionableKeyWordContentFileRepositoryImpl implements ObjectionableKeyWordContentRepository {

	@Autowired
	private ResourceLoader resourceLoader;
	List<ObjectionableKeyWord> objKeywrdList;

	private static final Logger logger = LogManager.getLogger(ObjectionableKeyWordContentFileRepositoryImpl.class);

	public List<ObjectionableKeyWord> readObjectionableKeyWords() throws AppConfigException {

		if (objKeywrdList == null) {
			synchronized (ObjectionableKeyWordContentFileRepositoryImpl.class) {
				if (objKeywrdList == null) {
					objKeywrdList = new ArrayList<ObjectionableKeyWord>();

					Resource resource = null;
					InputStream objectionableKeyWordStream = null;
					BufferedReader keyWordBuffere = null;

					try {

						resource = resourceLoader.getResource("classpath:ObjectionableKeyWordContent.txt");
						objectionableKeyWordStream = resource.getInputStream();
						keyWordBuffere = new BufferedReader(new InputStreamReader(objectionableKeyWordStream, "UTF-8"));

						String keyWord = null;
						while (((keyWord = keyWordBuffere.readLine()) != null) && !keyWord.trim().isEmpty()) {
							ObjectionableKeyWord objKeyWord = new ObjectionableKeyWord(keyWord);
							objKeywrdList.add(objKeyWord);
						}
						if(logger.isDebugEnabled())
						{
							logger.debug("Objectionable content: " + objKeywrdList);
						}
						
					} catch (IOException ex) {
						throw new AppConfigException("Objectionable keyword content file missing");

					} finally {

						try {
							if (keyWordBuffere != null) {
								keyWordBuffere.close();
							}

							if (objectionableKeyWordStream != null) {
								objectionableKeyWordStream.close();
							}
						} catch (Exception exception) {
							
							logger.error(exception);
						}

					}
				}
			}
		}
		return objKeywrdList;

	}
}
