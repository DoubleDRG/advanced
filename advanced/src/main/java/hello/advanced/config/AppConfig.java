package hello.advanced.config;

import hello.advanced.logtrace.tracemanager.ThreadLocalTraceManager;
import hello.advanced.logtrace.tracemanager.TraceManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig
{
    @Bean
    public TraceManager traceManager()
    {
        return new ThreadLocalTraceManager();
    }
}
