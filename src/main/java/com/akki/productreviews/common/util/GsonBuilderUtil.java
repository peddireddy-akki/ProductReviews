package com.akki.productreviews.common.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonBuilderUtil {

	public static Gson CreateGsonBuilder() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateJsonDeserializer());
		gsonBuilder.registerTypeAdapter(Timestamp.class, new TimeStampJsonDeserializer());
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter());
		return gsonBuilder.create();
	}

}
