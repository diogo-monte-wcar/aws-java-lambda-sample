package helloworld;

public class AppResponse {

  private String message;
  private String ipMessage;

  public AppResponse(String message, String ipMessage) {
    this.message = message;
    this.ipMessage = ipMessage;
  }

  public String getMessage() {
    return message;
  }

  public String getIpMessage() {
    return ipMessage;
  }
}
