package com.myjeeva.digitalocean;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.http.client.utils.URIBuilder;
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
 * 
 * 
 * @author Jeevanandam (jeeva@myjeeva.com)
 * 
 */

public class DoUtils {
	private final static Logger LOG = LoggerFactory.getLogger(DoUtils.class);

	private static Gson gson;
	
	public static URIBuilder getBaseUri() {		
		URIBuilder builder = new URIBuilder();
		builder.setScheme("https").setHost("api.digitalocean.com");
		return builder;
	}

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
		}

		return gson;
	}

}
