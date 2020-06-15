package helloworld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class CheckIPServiceImpl implements CheckIPService {

  private static final String AMAZON_CHECK_IP = "https://checkip.amazonaws.com";

  @Override
  public String getIp() throws IOException {
    URL url = new URL(AMAZON_CHECK_IP);
    try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
      return br.lines().collect(Collectors.joining(System.lineSeparator()));
    }
  }
}
