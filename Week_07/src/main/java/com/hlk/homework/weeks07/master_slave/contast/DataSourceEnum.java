package com.hlk.homework.weeks07.master_slave.contast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum DataSourceEnum {
    PRIMARY("master"), SECONDARY("slave1"), TERTIARY("slave2");
    static final List<DataSourceEnum> list = new ArrayList<>();

    static {
        list.add(SECONDARY);
        list.add(TERTIARY);
    }

    private String value;

    private DataSourceEnum(String value) {
        this.value = value;
    }

    public static DataSourceEnum getRandomSlaveEnum() {
        int next = ThreadLocalRandom.current().nextInt(0, list.size());
        return list.get(next);
    }

    public String getValue() {
        return value;
    }
}
