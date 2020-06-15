package helloworld;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;

public class MessageServiceImplTest {

  private CheckIPService checkIPService = Mockito.mock(CheckIPServiceImpl.class);
  private MessageService messageService = new MessageServiceImpl(checkIPService);

  @Test
  @DisplayName("it should return status ok and and ip from the ip service")
  public void testServiceResultOk() throws IOException {
    Mockito.when(checkIPService.getIp()).thenReturn("127.0.0.1");
    ServiceResult serviceResult = messageService.getMessage("My first lambda");
    Assert.assertEquals(ServiceResult.OK , serviceResult.getStatus());
    Assert.assertEquals("Hello, your request message was: My first lambda", serviceResult.getMessage());
    Assert.assertEquals("127.0.0.1", serviceResult.getIp());
  }

  @Test
  @DisplayName("it should return status error in case of IOException")
  public void testServiceResultErrorIoException() throws IOException {
    Mockito.when(checkIPService.getIp()).thenThrow(IOException.class);
    ServiceResult serviceResult = messageService.getMessage("My first lambda");
    Assert.assertEquals(ServiceResult.ERROR , serviceResult.getStatus());
    Assert.assertNull(serviceResult.getMessage());
    Assert.assertNull(serviceResult.getIp());
  }

}