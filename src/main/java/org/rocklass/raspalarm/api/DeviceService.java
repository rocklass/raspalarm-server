package org.rocklass.raspalarm.api;

import org.rocklass.raspalarm.model.Device;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pbrochado on 30/05/2017.
 */
@RestController
@RequestMapping("device")
public interface DeviceService {
    @RequestMapping("/send")
    String send();

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Device> subscribe(Device device);
}
