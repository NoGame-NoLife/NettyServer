package com.kuuhaku.heartbeat.protocol;

import java.io.Serializable;

public class CustomProtocol implements Serializable {
    private static final long serialVersionUID = -1387161444097900151L;
    private int id;
    private String content;

    public CustomProtocol(){
    }

    public CustomProtocol(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
