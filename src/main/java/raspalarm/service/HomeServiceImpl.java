package raspalarm.service;

import org.springframework.stereotype.Component;
import raspalarm.api.HomeService;

/**
 * Created by rocklass on 17/05/2017.
 */
@Component
public class HomeServiceImpl implements HomeService {

    @Override
    public String home() {
        return "Hello";
    }
}
