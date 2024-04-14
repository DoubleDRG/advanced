package hello.advanced.app.v4;

import hello.advanced.logtrace.tracemanager.TraceManager;
import hello.advanced.templatemethod.LogTraceTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceV4
{
    private final OrderRepositoryV4 orderRepository;
    private final TraceManager traceManager;

    public void order(String itemId)
    {
        LogTraceTemplate<Object> logTraceTemplate = new LogTraceTemplate<>(traceManager)
        {
            @Override
            protected Object call()
            {
                orderRepository.save(itemId);
                return null;
            }
        };

        logTraceTemplate.execute("OrderRepositoryV4.order()");
    }
}
