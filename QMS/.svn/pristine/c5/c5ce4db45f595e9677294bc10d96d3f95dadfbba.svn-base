package com.unind.base.utils;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonTimestampAdapter implements JsonSerializer<Timestamp>, JsonDeserializer<Timestamp> {
	private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public Timestamp deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		if (!(arg0 instanceof JsonPrimitive)) {
			throw new JsonParseException("The date should be a string value");
		}

		try {
			Date date = format.parse(arg0.getAsString());
			return new Timestamp(date.getTime());
		} catch (ParseException e) {
			throw new JsonParseException(e);
		}
	}

	@Override
	public JsonElement serialize(Timestamp arg0, Type arg1, JsonSerializationContext arg2) {
		return new JsonPrimitive(format.format(new Date(arg0.getTime())));
	}

}
