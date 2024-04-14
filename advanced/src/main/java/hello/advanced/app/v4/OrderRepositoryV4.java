package hello.advanced.app.v4;

import hello.advanced.logtrace.tracemanager.TraceManager;
import hello.advanced.templatemethod.LogTraceTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryV4
{
    private final TraceManager traceManager;

    public void save(String itemId)
    {
        LogTraceTemplate<Object> logTraceTemplate = new LogTraceTemplate<>(traceManager)
        {
            @Override
            protected Object call()
            {
                if (itemId.equals("ex"))
                {
                    throw new IllegalStateException("예외 발생!!");
                }
                sleep(1000);
                return null;
            }
        };
        logTraceTemplate.execute("OrderRepositoryV4.save()");
    }

    private void sleep(long ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
