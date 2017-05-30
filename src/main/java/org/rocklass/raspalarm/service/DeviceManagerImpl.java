package org.rocklass.raspalarm.service;

import org.rocklass.raspalarm.messaging.DeviceManager;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rocklass on 30/05/2017.
 */
@Component
public class DeviceManagerImpl implements DeviceManager {
    private Set<String> devices;

    public DeviceManagerImpl() {
        super();
        devices = new HashSet<>();
    }

    @Override
    public void addDevice(String token) {
        devices.add(token);
    }

    @Override
    public Collection<String> getDevices() {
        return devices;
    }
}
