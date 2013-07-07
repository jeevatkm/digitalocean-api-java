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

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * Utility methods for DigitalOcean API client wrapper methods
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public class DoUtils {
	private final static Logger LOG = LoggerFactory.getLogger(DoUtils.class);

	private static Gson gson;

	public static DefaultHttpClient getHttpClient() {
		try {
			SSLSocketFactory sf = null;
			try {
				sf = new SSLSocketFactory(new TrustStrategy() {
					@Override
					public boolean isTrusted(X509Certificate[] chain,
							String authType) throws CertificateException {
						return true;
					}
				});
			} catch (KeyManagementException kme) {
				LOG.error(kme.getMessage());
			} catch (UnrecoverableKeyException uke) {
				LOG.error(uke.getMessage());
			} catch (NoSuchAlgorithmException nsae) {
				LOG.error(nsae.getMessage());
			} catch (KeyStoreException kse) {
				LOG.error(kse.getMessage());
			}

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("https", 443, sf));
			ClientConnectionManager ccm = new PoolingClientConnectionManager(
					registry);
			return new DefaultHttpClient(ccm);
		} catch (Exception e) {
			LOG.error("SSL Socket factory initialization failed, resume to default");
			return new DefaultHttpClient();
		}
	}

	public static Gson getGson() {
		if (null == gson) {
			gson = new Gson();

			// register type adapters
		}

		return gson;
	}

}
