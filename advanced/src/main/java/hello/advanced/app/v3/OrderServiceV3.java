package hello.advanced.app.v3;

import hello.advanced.logtrace.TraceStatus;
import hello.advanced.logtrace.tracemanager.TraceManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceV3
{
    private final OrderRepositoryV3 orderRepository;
    private final TraceManager traceManager;

    public void order(String itemId)
    {
        TraceStatus status = null;

        try
        {
            status = traceManager.begin("OrderServiceV3");
            orderRepository.save(itemId);
            traceManager.end(status);
        }
        catch (Exception e)
        {
            traceManager.exception(status, e);
            throw e;
        }
    }
}
