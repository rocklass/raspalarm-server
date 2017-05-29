package org.rocklass.raspalarm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by rocklass on 29/05/2017.
 */
public class AlertData {
    private final String title;
    private final String content;

    public AlertData(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("content")
    public String getContent() {
        return content;
    }
}
