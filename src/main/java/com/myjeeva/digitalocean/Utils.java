/* The MIT License
 *
 * Copyright (c) 2010-2014 Jeevanandam M. (myjeeva.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE. 
 * 
 */
package com.myjeeva.digitalocean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.myjeeva.digitalocean.pojo.DomainRecord;

/**
 * Utility methods for DigitalOcean API Client
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public final class Utils implements Constants {

	private static Gson gson;

	public static Gson getGson() {
		if (null == gson) {
			gson = new Gson();
		}
		return gson;
	}

	public static Object byClass(JsonElement jsonElement, Class<?> clazz) {
		return getGson().fromJson(jsonElement.toString(), clazz);
	}

	public static Object byType(JsonElement jsonElement, Type type) {
		return getGson().fromJson(jsonElement.toString(), type);
	}

	public static String readInputStream(InputStream input) throws IOException {

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		int n = 0;
		byte[] buffer = new byte[4096];
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}

		return output.toString(UTF_8);
	}

	public static Map<String, String> prepareDomainRecordParams(
			DomainRecord domainRecord) {
		Map<String, String> qp = new HashMap<String, String>();

		if (null != domainRecord.getRecordType()) {
			qp.put(PARAM_RECORD_TYPE, domainRecord.getRecordType());
		}

		if (null != domainRecord.getData()) {
			qp.put(PARAM_DATA, domainRecord.getData());
		}

		if (null != domainRecord.getName()) {
			qp.put(PARAM_NAME, domainRecord.getName());
		}

		if (null != domainRecord.getPriority()) {
			qp.put(PARAM_PRIORITY, domainRecord.getPriority());
		}

		if (null != domainRecord.getPort()) {
			qp.put(PARAM_PORT, String.valueOf(domainRecord.getPort()));
		}

		if (null != domainRecord.getWeight()) {
			qp.put(PARAM_WEIGHT, String.valueOf(domainRecord.getWeight()));
		}

		return qp;
	}
}
