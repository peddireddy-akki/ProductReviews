package com.akki.productreviews.common.utility;

import java.time.LocalDate;
import java.sql.Timestamp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonBuilderUtil {

	public static Gson CreateGsonBuilder() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateJsonDeserializer());
		gsonBuilder.registerTypeAdapter(Timestamp.class, new TimeStampJsonDeserializer());
		return  gsonBuilder.create();
	}

}
