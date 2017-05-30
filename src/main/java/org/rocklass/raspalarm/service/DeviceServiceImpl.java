package org.rocklass.raspalarm.service;

import org.rocklass.raspalarm.api.DeviceService;
import org.rocklass.raspalarm.messaging.CloudMessagingService;
import org.rocklass.raspalarm.messaging.DeviceManager;
import org.rocklass.raspalarm.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by rocklass on 30/05/2017.
 */
@Component
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    CloudMessagingService cloudMessagingService;
    @Autowired
    DeviceManager deviceManager;

    @Override
    public String send() {
        return cloudMessagingService.sendMessage("Title", "Content");
    }

    @Override
    public ResponseEntity<Device> subscribe(final @RequestBody Device device) {
        deviceManager.addDevice(device.getToken());
        return new ResponseEntity<>(device, HttpStatus.OK);
    }
}
