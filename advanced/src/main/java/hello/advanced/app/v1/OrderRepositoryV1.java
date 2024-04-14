package hello.advanced.app.v1;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV1
{
    public void save(String itemId)
    {
        if (itemId.equals("ex"))
        {
            throw new IllegalStateException("예외 발생!!");
        }

        sleep(2000);
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
