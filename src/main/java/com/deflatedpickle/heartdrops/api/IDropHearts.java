package com.deflatedpickle.heartdrops.api;

public interface IDropHearts {
    default int dropAmount() {
        return 1;
    }

    default boolean doesDropHearts() {
        return true;
    }
}
