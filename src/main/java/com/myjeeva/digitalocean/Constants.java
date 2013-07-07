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

/**
 * DigitalOcean API client Constants
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public interface Constants {
	// HTTPS Scheme
	String HTTPS_SCHEME = "https";

	// HTTP Param name
	String PARAM_CLIENT_ID = "client_id";
	String PARAM_API_KEY = "api_key";
	String PARAM_NAME = "name";
	String PARAM_SIDE_ID = "size_id";
	String PARAM_REGION_ID = "region_id";
	String PARAM_IMAGE_ID = "image_id";
	String PARAM_SSH_KEY_IDS = "ssh_key_ids";

	// JSON Element Name
	String STATUS = "status";
}
