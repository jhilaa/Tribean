package com.jhilaa.tribean.model.param;

public enum Rating {
    ONE(1),
    TWO(2),
    THREE(3);

    private int value;

    private Rating(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

