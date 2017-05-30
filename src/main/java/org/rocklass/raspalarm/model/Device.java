package org.rocklass.raspalarm.model;

/**
 * Created by rocklass on 30/05/2017.
 */
public class Device {
    private String token;

    public Device() {
        super();
    }

    public Device(final String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
