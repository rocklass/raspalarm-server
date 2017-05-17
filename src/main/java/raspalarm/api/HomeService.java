package raspalarm.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pbrochado on 17/05/2017.
 */
@RestController
public interface HomeService {
    @RequestMapping("/")
    String home();
}
