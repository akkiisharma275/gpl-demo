package com.viratrigger.api;

public class ResponsDetails {
    private String code;
    private String message;
    private Object data;    // For additional data (if needed)
    private Object message1; // For detailed error messages (if any)

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getMessage1() {
        return message1;
    }

    public void setMessage1(Object message1) {
        this.message1 = message1;
    }
}