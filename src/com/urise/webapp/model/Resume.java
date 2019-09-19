package com.urise.webapp.model;

public class Resume {
    // уникальныый идентификатор
    private String uuid;

    @Override
    public String toString() {
        return this.uuid;
    }

    // вернуть уникальныый идентификатор
    public String getUuid() {
        return this.uuid;
    }

    // установить значение уникального идентификатора
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
