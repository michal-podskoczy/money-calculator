package com.podskoczy.money.management;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PUBLIC;

@Value
@AllArgsConstructor(access = PRIVATE)
public class ExpenseId {

    private String id;

    public static ExpenseId of() {
        return new ExpenseId(UUID.randomUUID().toString());
    }

    public static ExpenseId of(String id) {
        return new ExpenseId(id);
    }

}
