package hello.advanced.logtrace.tracemanager;

import hello.advanced.logtrace.TraceStatus;

public interface TraceManager
{
    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
