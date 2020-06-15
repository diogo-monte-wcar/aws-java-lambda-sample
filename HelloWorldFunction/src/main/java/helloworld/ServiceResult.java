package helloworld;

public class ServiceResult {

  public static final int OK = 200;
  public static final int ERROR = 500;

  private int status;
  private String message;
  private String ip;

  public ServiceResult(String message, String ip) {
    this.status = OK;
    this.message = message;
    this.ip = ip;
  }

  public ServiceResult(String errorMessage) {
    this.status = ERROR;
    this.message = null;
    this.ip = null;
  }

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public String getIp() {
    return ip;
  }
}
