package com.unind.base.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.unind.base.provider.BusinessException;

public class GsonUtils {

	private static Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
		
		@Override
		public boolean shouldSkipField(FieldAttributes arg0) {
			return false;
		}
		
		@Override
		public boolean shouldSkipClass(Class<?> arg0) {
			//不序列号hibernate延迟加载策略的类
			if(arg0.getName().startsWith("org.hibernate.proxy.pojo.javassist")) {
				return true;
			}
			return false;
		}
	}).setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	private static Gson gson4Date = new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
		@Override
		public boolean shouldSkipField(FieldAttributes arg0) {
			return false;
		}
		@Override
		public boolean shouldSkipClass(Class<?> arg0) {
			//不序列号hibernate延迟加载策略的类
			if(arg0.getName().startsWith("org.hibernate.proxy.pojo.javassist")) {
				return true;
			}
			return false;
		}
	}).setDateFormat("yyyy-MM-dd").create();

	public static JsonParser jsonParser = new JsonParser();

	public static Gson getGson() {
		if(null==gson4Date) {
			gson4Date = new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
				
				@Override
				public boolean shouldSkipField(FieldAttributes arg0) {
					return false;
				}
				
				@Override
				public boolean shouldSkipClass(Class<?> arg0) {
					//不序列号hibernate延迟加载策略的类
					if(arg0.getName().startsWith("org.hibernate.proxy.pojo.javassist")) {
						return true;
					}
					return false;
				}
			}).setDateFormat("yyyy-MM-dd").create();
		}
		return gson4Date;
	}

	private GsonUtils(){
		
	}

	public static JsonElement parseJson(String json) {
		return jsonParser.parse(json);
	}

	public static JsonArray getJsonArray(String json) {
		return jsonParser.parse(json).getAsJsonArray();
	}

	public static JsonObject getJsonObject(String json) {
		return jsonParser.parse(json).getAsJsonObject();
	}

	public static JsonArray getJsonArray(JsonElement jsonElement) {
		return jsonElement.getAsJsonArray();
	}

	public static JsonObject getJsonObject(JsonElement jsonElement) {
		return jsonElement.getAsJsonObject();
	}

	public static String get(JsonObject jsonObject, String key, boolean flag, String defVal) {
		try {
			return jsonObject.get(key).getAsString();
		} catch (NullPointerException e) {
			if (flag) {
				return defVal;
			}else {
				throw new NullPointerException(" property: "+key+" is not found");
			}
		}
	}

	public static String get(JsonObject jsonObject, String key, boolean isNull) {
		return jsonObject.get(key).getAsString();
	}

	/**
	 * @deprecated
	 * @param json
	 * @return
	 */
	public static Object toObject(String json) {
//		return gson.fromJson(json, new TypeToken(){}.getType());
		return json;
	}

	public static String toJson(Object object) {
		return gson.toJson(object);
	}

	public static String createJson(JsonObject jsonObject, String key, Object object) {
		if (object instanceof JsonElement) {
			jsonObject.add(key, (JsonElement) object);
		}else if (object instanceof Boolean) {
			jsonObject.addProperty(key, (Boolean) object);
		}else if (object instanceof Double) {
			jsonObject.addProperty(key, new Double(object.toString()) );
		}else if (object instanceof Integer) {
			jsonObject.addProperty(key, Integer.parseInt(object.toString()));
		}else if (object instanceof Long) {
			jsonObject.addProperty(key, Long.parseLong(object.toString()));
		}else if (object instanceof BigDecimal) {
			jsonObject.addProperty(key, new BigDecimal(object.toString()));
		}else if (object instanceof String) {
			jsonObject.addProperty(key, (String) object);
		}else {
			jsonObject.addProperty(key, (String) object);
		}
		return jsonObject.toString();
	}

	public static String createJson(JsonArray jsonArray, JsonObject jsonObject) {
		jsonArray.add(jsonObject);
		return jsonArray.toString();
	}

	public static String createNewPropsJson(String json, String[] keys, Object[] objects, boolean isJsonArray) throws BusinessException {
		if (isJsonArray) {
			JsonArray jsonArray = getJsonArray(json);
			for (int i = 0; i < jsonArray.size(); i++) {
				JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
				for (int j = 0; j < keys.length; j++) {
					createJson(jsonObject, keys[j], objects[j]);
				}
			}
			return jsonArray.toString();
		}else {
			JsonObject jsonObject = getJsonObject(json);
			for (int j = 0; j < keys.length; j++) {
				createJson(jsonObject, keys[j], objects[j]);
			}
			return jsonObject.toString();
		}
	}

	public static String createJson(String[] keys, Object...object) {
		JsonObject jsonObject = new JsonObject();
		for (int i = 0; i < object.length; i++) {
			if (object[i] instanceof ArrayList<?>) {
				createJson(jsonObject, keys[i], parseJson(gson.toJson(object[i])));
			}else if (object[i] instanceof Map<?, ?>) {
				createJson(jsonObject, keys[i], parseJson(gson.toJson(object[i])));
			}else {
				createJson(jsonObject, keys[i], object[i]);
			}
		}
		return jsonObject.toString();
	}
}
