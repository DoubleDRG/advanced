package hello.advanced.app.v3;

import hello.advanced.logtrace.TraceStatus;
import hello.advanced.logtrace.tracemanager.TraceManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class OrderRepositoryV3
{
    private final TraceManager traceManager;

    public void save(String itemId)
    {
        TraceStatus status = traceManager.begin("OrderRepositoryV3");

        if (itemId.equals("ex"))
        {
            IllegalStateException error = new IllegalStateException("예외 발생!!");
            traceManager.exception(status, error);
            throw error;
        }

        sleep(2000);
        traceManager.end(status);
    }

    private void sleep(long millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
