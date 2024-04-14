package hello.advanced.templatemethod;

import hello.advanced.logtrace.TraceStatus;
import hello.advanced.logtrace.tracemanager.TraceManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class LogTraceTemplate<T>
{
    private final TraceManager traceManager;

    public T execute(String message)
    {
        TraceStatus status = null;
        T returnValue = null;

        try
        {
            status = traceManager.begin(message);
            returnValue = call();
            traceManager.end(status);
        }
        catch (Exception e)
        {
            traceManager.exception(status, e);
            throw e;
        }

        return returnValue;
    }

    protected abstract T call();
}
