package hello.advanced.app.v3;

import hello.advanced.logtrace.TraceStatus;
import hello.advanced.logtrace.tracemanager.TraceManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderControllerV3
{
    private final OrderServiceV3 orderService;
    private final TraceManager traceManager;

    @GetMapping("/request/v3")
    public String order(String itemId)
    {
        TraceStatus status = null;

        try
        {
            status = traceManager.begin("OrderControllerV3");
            orderService.order(itemId);
            traceManager.end(status);
        }
        catch (Exception e)
        {
            traceManager.exception(status, e);
            throw e;
        }

        return "ok";
    }
}
