package org.rocklass.raspalarm.messaging;

import java.util.Collection;

/**
 * Created by rocklass on 30/05/2017.
 */
public interface DeviceManager {
    void addDevice(String token);
    Collection<String> getDevices();
}
