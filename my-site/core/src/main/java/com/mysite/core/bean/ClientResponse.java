package com.mysite.core.bean;

import org.apache.commons.lang3.StringUtils;

public class ClientResponse {

    private int statusCode = 0;
    private String data = StringUtils.EMPTY;
    public void setStatusCode(int status) {
        this.statusCode = status;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getData() {
        return data;
    }
}
