package com.myjeeva.digitalocean;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.execchain.PublicHttpResponseProxy;
import org.apache.http.message.BasicHttpResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.myjeeva.digitalocean.impl.DigitalOceanClient;

import mockit.Expectations;
import mockit.Mocked;

@SuppressWarnings("unused")
@RunWith(JUnit4.class)
public class DigitalOceanMockTest {

  private @Mocked CloseableHttpClient defaultHttpClient;

  @Test
  public void testSnapshotWithName() throws Exception {

    new Expectations() {
      {
        defaultHttpClient.execute((HttpUriRequest)with(new Object() {

          void validate(HttpUriRequest httpUriRequest) throws MalformedURLException,
              URISyntaxException {
            assertEquals(new URL("https://api.digitalocean.com/v2/droplets/1234/actions").toURI(),
                httpUriRequest.getURI());
          }
        }));
        
        BasicHttpResponse basicHttpResponse = new BasicHttpResponse(HttpVersion.HTTP_1_1, 200, "");
        basicHttpResponse
            .setEntity(new StringEntity(
                "{   \"action\": {     \"id\": 36805022,     \"status\": \"in-progress\",     \"type\": \"snapshot\",     \"started_at\": \"2014-11-14T16:34:39Z\",     \"completed_at\": null,     \"resource_id\": 3164450,     \"resource_type\": \"droplet\",     \"region\": \"nyc3\"   } }",
                ContentType.APPLICATION_JSON));
        basicHttpResponse.setHeader("RateLimit-Limit", "1200");
        basicHttpResponse.setHeader("RateLimit-Remaining", "900");
        basicHttpResponse.setHeader("RateLimit-Reset", "1415984218");

        result = new PublicHttpResponseProxy(basicHttpResponse);
      }
    };

    DigitalOcean digitalOcean = new DigitalOceanClient("key");
    digitalOcean.takeDropletSnapshot(1234, "snapshot-name");
  }
}
