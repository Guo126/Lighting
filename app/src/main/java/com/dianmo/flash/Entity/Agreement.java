package com.dianmo.flash.Entity;

public class Agreement {
    String alter;
    boolean success;

    public Agreement(String alter, boolean success) {
        this.alter = alter;
        this.success = success;
    }

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
}
