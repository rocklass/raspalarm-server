package raspalarm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import raspalarm.api.CloudMessagingService;
import raspalarm.api.HomeService;

/**
 * Created by rocklass on 17/05/2017.
 */
@Component
public class HomeServiceImpl implements HomeService {
    @Autowired
    CloudMessagingService cloudMessagingService;

    @Override
    public String home() {
        return "Hello";
    }

    @Override
    public String send() {
        return cloudMessagingService.sendMessage("Title", "Content");
    }
}
