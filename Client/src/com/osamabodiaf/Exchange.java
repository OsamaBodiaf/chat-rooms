package com.osamabodiaf;

import java.io.File;
import java.io.Serializable;

public class Exchange implements Serializable {
    public String action;
    public String status;
    public String[] tokens;
    public Object[] attachments;

    public Exchange(String action) {
        this.action = action;
    }

    public Exchange(String action, String status) {
        this.action = action;
        this.status = status;
    }

    public Exchange(String action, String status, String[] tokens) {
        this.action = action;
        this.status = status;
        this.tokens = tokens;
    }

    public Exchange(String action, String[] tokens) {
        this.action = action;
        this.tokens = tokens;
    }

    public Exchange(String action, String[] tokens, File[] attachments) {
        this.action = action;
        this.tokens = tokens;
        this.attachments = attachments;
    }
}
