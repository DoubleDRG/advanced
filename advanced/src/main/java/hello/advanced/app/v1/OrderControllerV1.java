package hello.advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1
{
    private final OrderServiceV1 orderService;

    @GetMapping("/request/v1")
    public String order(String itemId)
    {
        orderService.order(itemId);
        return "ok";
    }
}
