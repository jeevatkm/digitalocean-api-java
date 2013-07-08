/* The MIT License
 *
 * Copyright (c) 2010-2013 Jeevanandam M. (myjeeva.com)
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
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.myjeeva.digitalocean.pojo.Domain;
import com.myjeeva.digitalocean.pojo.DomainRecord;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.pojo.DropletImage;
import com.myjeeva.digitalocean.pojo.DropletSize;
import com.myjeeva.digitalocean.pojo.Region;

/**
 * Utility methods for DigitalOcean API Client
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public final class Utils implements Constants {

	private static Gson gson;

	private static Type dropletListType;

	private static Type imageListType;

	private static Type regionListType;

	private static Type sizeListType;

	private static Type domainListType;

	private static Type domainRecordListType;

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

	public static Type getDropletListType() {
		if (null == dropletListType) {
			dropletListType = new TypeToken<List<Droplet>>() {
			}.getType();
		}
		return dropletListType;
	}

	public static Type getImageListType() {
		if (null == imageListType) {
			imageListType = new TypeToken<List<DropletImage>>() {
			}.getType();
		}
		return imageListType;
	}

	public static Type getRegionListType() {
		if (null == regionListType) {
			regionListType = new TypeToken<List<Region>>() {
			}.getType();
		}
		return regionListType;
	}

	public static Type getSizeListType() {
		if (null == sizeListType) {
			sizeListType = new TypeToken<List<DropletSize>>() {
			}.getType();
		}
		return sizeListType;
	}

	public static Type getDomainListType() {
		if (null == domainListType) {
			domainListType = new TypeToken<List<Domain>>() {
			}.getType();
		}
		return domainListType;
	}

	public static Type getDomainRecordListType() {
		if (null == domainRecordListType) {
			domainRecordListType = new TypeToken<List<DomainRecord>>() {
			}.getType();
		}
		return domainRecordListType;
	}

}
