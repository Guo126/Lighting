package com.dianmo.flash.Entity;

public class AddFriResult {
    String alter;
    boolean success;

    public String getAlter() {
        return alter;
    }

    public void setAlter(String alter) {
        this.alter = alter;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public AddFriResult(String alter, boolean success) {
        this.alter = alter;
        this.success = success;
    }
}
