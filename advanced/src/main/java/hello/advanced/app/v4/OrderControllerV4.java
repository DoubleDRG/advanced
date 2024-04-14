package hello.advanced.app.v4;

import hello.advanced.logtrace.tracemanager.TraceManager;
import hello.advanced.templatemethod.LogTraceTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderControllerV4
{
    private final OrderServiceV4 orderService;
    private final TraceManager traceManager;

    @GetMapping("/request/v4")
    public String order(String itemId)
    {
        LogTraceTemplate<Object> logTraceTemplate = new LogTraceTemplate<>(traceManager)
        {
            @Override
            protected Object call()
            {
                orderService.order(itemId);
                return null;
            }
        };

        logTraceTemplate.execute("OrderControllerV4.order()");

        return "ok";
    }
}
