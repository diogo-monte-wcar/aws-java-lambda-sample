package helloworld;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Handler for requests to Lambda function.
 */
public class AppHandler implements RequestHandler<AppRequest, GatewayResponse> {

    private MessageService messageService;

    public AppHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    public GatewayResponse handleRequest(final AppRequest input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        if (Objects.isNull(input.getMessage())) {
            return new GatewayResponse("Bad request", headers, 404);
        }

        ServiceResult serviceResult = messageService.getMessage(input.getMessage());
        if (ServiceResult.OK == serviceResult.getStatus()) {
            return new GatewayResponse(serviceResult.getMessage(), headers, 200);
        }
        return new GatewayResponse("Internal error!", headers, 500);
    }
}
