package com.podskoczy.money.management;

import lombok.Value;

import java.util.UUID;


@Value
public class RefId {

    private String refId;

    public static RefId of() {
        return new RefId(UUID.randomUUID().toString());
    }

    public static RefId of(String value) {
        return new RefId(value);
    }

}
