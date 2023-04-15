package com.jhilaa.tribean.model.enumeration;

public enum Rating {
    ONE(1),
    TWO(2),
    THREE(3);

    private Integer value;

    private Rating(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}

