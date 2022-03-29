package io.cambium.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.amazonaws.serverless.proxy.internal.LambdaContainerHandler;
import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.Context;

import io.cambium.aws.StreamLambdaHandler;
import io.cambium.types.requests.GetLookupsRequest;

/**
 * LambdaTest.
 *
 * @author com.amazonaws.serverless.archetypes:aws-serverless-springboot2-archetype:1.7
 * @author Baruch Speiser, Cambium.
 */
public class LambdaTest {
  private static StreamLambdaHandler handler;
  private static Context lambdaContext;

  @BeforeAll
  public static void setUp() {
    handler = new StreamLambdaHandler();
    lambdaContext = new MockLambdaContext();
  }

  @Test
  public void testLookups() {
    InputStream requestIS = 
        new AwsProxyRequestBuilder("/api/lookups", HttpMethod.POST)
          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
          .body(new GetLookupsRequest("pets"))
          .buildStream();
    
    
    ByteArrayOutputStream responseOS = new ByteArrayOutputStream();
    handle(requestIS, responseOS);

    AwsProxyResponse response = readResponse(responseOS);
    assertNotNull(response);
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCode());
    assertFalse(response.isBase64Encoded());
    assertTrue(response.getBody().contains("lookups"));
    assertTrue(response.getBody().contains("[]")); //we didn't prime the database here so the content should be an empty array

    assertTrue(response.getMultiValueHeaders().containsKey(HttpHeaders.CONTENT_TYPE));
    assertTrue(response.getMultiValueHeaders().getFirst(HttpHeaders.CONTENT_TYPE)
                .startsWith(MediaType.APPLICATION_JSON));
  }

  private void handle(InputStream is, ByteArrayOutputStream os) {
    try {
      handler.handleRequest(is, os, lambdaContext);
    } catch(IOException e) {
      e.printStackTrace();
      fail(e.getMessage());
    }
  }

  private AwsProxyResponse readResponse(ByteArrayOutputStream responseStream) {
    try {
      return LambdaContainerHandler.getObjectMapper().readValue(responseStream.toByteArray(), AwsProxyResponse.class);
    } catch(IOException e) {
      e.printStackTrace();
      fail("Error while parsing response: " + e.getMessage());
    }
    return null;
  }
}
