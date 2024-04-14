package hello.advanced.logtrace.tracemanager;

import hello.advanced.logtrace.TraceId;
import hello.advanced.logtrace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ThreadLocalTraceManager implements TraceManager
{
    private static final String BEGIN_PREFIX = "-->";
    private static final String END_PREFIX = "<--";
    private static final String EXCEPTION_PREFIX = "<X-";

    ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

    @Override
    public TraceStatus begin(String message)
    {
        TraceId traceId = syncTraceId();
        String id = traceId.getId();
        Integer level = traceId.getLevel();
        long startTimeMs = System.currentTimeMillis();
        TraceStatus traceStatus = new TraceStatus(traceId, startTimeMs, message);

        log.info("[{}] {}{}", id, addSpace(BEGIN_PREFIX, level), message);
        return traceStatus;
    }

    @Override
    public void end(TraceStatus status)
    {
        complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception e)
    {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e)
    {
        TraceId traceId = status.getTraceId();

        String id = traceId.getId();
        int level = traceId.getLevel();
        long resultTime = System.currentTimeMillis() - status.getStartTimeMs();
        String message = status.getMessage();

        //오류가 없는 경우.
        if (e == null)
        {
            log.info("[{}] {}{} time={}ms", id, addSpace(END_PREFIX, level), message, resultTime);
        }

        //오류가 있는 경우.
        if (e != null)
        {
            String errorMessage = e.toString();
            log.info("[{}] {}{} time={}ms ex={}", id, addSpace(EXCEPTION_PREFIX, level), message, resultTime, errorMessage);
        }

        releaseTraceId();
    }

    private TraceId syncTraceId()
    {
        TraceId traceId = traceIdHolder.get();

        //TraceIdHolder에 값이 없으면 새로 생성하고 반환.
        if (traceId == null)
        {
            traceIdHolder.set(new TraceId());
        }

        //TraceIdHolder에 값이 있으면 그 값의 다음 레벨을 반환.
        if (traceId != null)
        {
            traceIdHolder.set(traceId.createNextId());
        }

        return traceIdHolder.get();
    }

    private void releaseTraceId()
    {
        TraceId traceId = traceIdHolder.get();

        //traceIdHolder의 traceId 레벨이 0이면 null을 할당.
        if (traceId.isFirstLevel())
        {
            traceIdHolder.set(null);
        }

        //traceIdHolder의 traceId 레벨이 0이 아니면, 이전 레벨의 traceId를 할당.
        if (!traceId.isFirstLevel())
        {
            traceIdHolder.set(traceId.createPreviousId());
        }
    }

    private String addSpace(String prefix, int level)
    {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < level; i++)
        {
            if (i + 1 == level)
            {
                sb.append("|" + prefix);
            }
            else
            {
                sb.append("|    ");
            }
        }

        return sb.toString();
    }
}
