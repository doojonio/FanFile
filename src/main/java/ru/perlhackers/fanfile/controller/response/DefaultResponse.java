package ru.perlhackers.fanfile.controller.response;

public class DefaultResponse {
    public boolean success;
    public String message;

    public DefaultResponse(boolean success) {
        this.success = success;
    }

    public DefaultResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
