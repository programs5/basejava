package com.urise.webapp.model;

public class Resume {
    // уникальныый идентификатор
    private String uuid;

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }

    // вернуть уникальныый идентификатор
    public String getUuid() {
        return uuid;
    }
}
