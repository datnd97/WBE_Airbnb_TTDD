package com.security.demospringsecurity.message.response;

import lombok.Data;

@Data
public class ResponseMessage {
    private String message;
    private boolean success;
    private Object data;
    public ResponseMessage(String message) {
        this.message = message;
    }

    public ResponseMessage(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

}
