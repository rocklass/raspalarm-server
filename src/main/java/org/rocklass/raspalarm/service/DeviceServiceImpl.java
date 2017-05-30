package org.rocklass.raspalarm.service;

import org.rocklass.raspalarm.api.DeviceService;
import org.rocklass.raspalarm.messaging.CloudMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rocklass on 30/05/2017.
 */
@Component
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    CloudMessagingService cloudMessagingService;

    @Override
    public String send() {
        return cloudMessagingService.sendMessage("Title", "Content");
    }
}
