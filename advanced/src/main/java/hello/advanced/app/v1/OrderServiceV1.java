package hello.advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceV1
{
    private final OrderRepositoryV1 orderRepository;

    public void order(String itemId)
    {
        orderRepository.save(itemId);
    }
}
