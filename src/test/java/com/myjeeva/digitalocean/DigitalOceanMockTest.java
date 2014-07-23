package com.myjeeva.digitalocean;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.myjeeva.digitalocean.impl.DigitalOceanClient;
import junit.framework.TestCase;
import mockit.Expectations;
import mockit.Mocked;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@SuppressWarnings("unused")
public class DigitalOceanMockTest extends TestCase {

  private @Mocked
  DefaultHttpClient defaultHttpClient;

  public void testSnapshoWithName() throws Exception {

    new Expectations() {
      {
        defaultHttpClient.execute((HttpUriRequest) with(new Object() {

          void validate(HttpUriRequest httpUriRequest) throws MalformedURLException,
              URISyntaxException {
            assertEquals(
                new URL(
                    "https://api.digitalocean.com/v1/droplets/1234/snapshot/?client_id=id&api_key=key&name=snapshot-name")
                    .toURI(), httpUriRequest.getURI());
          }
        }));
        BasicHttpResponse basicHttpResponse = new BasicHttpResponse(HttpVersion.HTTP_1_1, 400, "");
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("status", new JsonPrimitive("OK"));
        basicHttpResponse.setEntity(new StringEntity(jsonObject.toString(),
            ContentType.APPLICATION_JSON));
        result = basicHttpResponse;

      }
    };

    DigitalOcean digitalOcean = new DigitalOceanClient("id", "key");
    digitalOcean.takeDropletSnapshot(1234, "snapshot-name");
  }
}
