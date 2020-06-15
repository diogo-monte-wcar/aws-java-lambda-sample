package helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

public class AppHandlerTest {

  private MessageService messageService = Mockito.mock(MessageService.class);
  private AppHandler appHandler = new AppHandler(messageService);

  @Test
  public void successfulResponse() {
    Mockito.when(messageService.getMessage(ArgumentMatchers.anyString())).thenReturn(new ServiceResult("hi", "127.0.0.1"));

    AppRequest appRequest = new AppRequest();
    appRequest.setMessage("hi");

    GatewayResponse result = appHandler.handleRequest(appRequest, null);
    assertEquals(result.getStatusCode(), 200);
    assertEquals(result.getHeaders().get("Content-Type"), "application/json");
    String content = result.getBody();
    assertNotNull(content);
    assertTrue(content.contains("hi"));
  }

  @Test
  public void badRequestResponse() {
    GatewayResponse result = appHandler.handleRequest(null, null);
    assertEquals(result.getStatusCode(), 404);
    assertEquals(result.getHeaders().get("Content-Type"), "application/json");
    String content = result.getBody();
    assertNotNull(content);
    assertTrue(content.contains("Wrong request body"));
  }

  @Test
  public void badInternalErrorResponse() {
    Mockito.when(messageService.getMessage(ArgumentMatchers.anyString())).thenReturn(new ServiceResult(ServiceResult.ERROR));

    AppRequest appRequest = new AppRequest();
    appRequest.setMessage("hi");

    GatewayResponse result = appHandler.handleRequest(appRequest, null);
    assertEquals(result.getStatusCode(), 500);
    assertEquals(result.getHeaders().get("Content-Type"), "application/json");
    String content = result.getBody();
    assertNotNull(content);
    assertTrue(content.contains("Internal error!"));
  }
}
