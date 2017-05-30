package org.rocklass.raspalarm.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pbrochado on 30/05/2017.
 */
@RestController
@RequestMapping("device")
public interface DeviceService {
    @RequestMapping("/send")
    String send();
}
