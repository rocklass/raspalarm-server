package org.rocklass.raspalarm.service;

import org.rocklass.raspalarm.api.HomeService;
import org.springframework.stereotype.Component;

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
