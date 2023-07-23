package com.hparadise.admin.common;

import lombok.Getter;

@Getter
public enum AuthStatus {
    NOT_MATCH(601),
    NOT_ENABLED(602),
    NOT_CREDENTIAL(603),
    NOT_LOCK(604);

    private int value;

    AuthStatus(int value) {
        this.value = value;
    }
}
