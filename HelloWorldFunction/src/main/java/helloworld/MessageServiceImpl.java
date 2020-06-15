package helloworld;

import java.io.IOException;

public class MessageServiceImpl implements MessageService {

  private CheckIPService checkIPService;

  public MessageServiceImpl(CheckIPService checkIPService) {
    this.checkIPService = checkIPService;
  }

  @Override
  public ServiceResult getMessage(String message) {
    try {
      return new ServiceResult("Hello, your request message was: " + message, checkIPService.getIp());
    } catch (IOException e) {
      return new ServiceResult(ServiceResult.ERROR);
    }
  }
}
