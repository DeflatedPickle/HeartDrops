package com.deflatedpickle.heartdrops.api;

import com.deflatedpickle.heartdrops.configs.GeneralConfig;

public interface IDropHearts {
    default int dropAmount() {
        return 1;
    }

    default int dropRange() {
        return GeneralConfig.dropRange;
    }

    default boolean doesDropHearts() {
        return true;
    }
}
