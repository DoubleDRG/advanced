package hello.advanced.templatemethod;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodPattern
{
    @Test
    void test1()
    {
        //아무것도 적용X
        //basicLogic1();
        //basicLogic2();

        //템플릿메서드 패턴 적용.
        MyAbstractTemplate<Void> myLogic1 = new MyAbstractTemplate<>()
        {
            @Override
            protected Void call()
            {
                log.info("logic1 로직 실행");
                sleep(1000);
                return null;
            }
        };

        MyAbstractTemplate<Object> myLogic2 = new MyAbstractTemplate<>()
        {
            @Override
            protected Object call()
            {
                log.info("logic2 로직 실행");
                sleep(1000);
                return null;
            }
        };

        myLogic1.execute();
        myLogic2.execute();
    }

    void basicLogic1()
    {
        long startTimeMs = System.currentTimeMillis();
        log.info("logic1의 로직 실행");
        sleep(1000);
        long endTimeMs = System.currentTimeMillis();
        log.info("time = {}ms", endTimeMs - startTimeMs);
    }

    void basicLogic2()
    {
        long startTimeMs = System.currentTimeMillis();
        log.info("logic2의 로직 실행");
        sleep(1000);
        long endTimeMs = System.currentTimeMillis();
        log.info("time = {}ms", endTimeMs - startTimeMs);
    }

    void sleep(long millis)
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

    abstract class MyAbstractTemplate<T>
    {
        public T execute()
        {
            long startTimeMs = System.currentTimeMillis();
            T ret = call();
            long endTimeMs = System.currentTimeMillis();
            log.info("time={}ms", endTimeMs - startTimeMs);
            return ret;
        }

        protected abstract T call();
    }
}
