package com.ljnewmap.modules.sys.enums;

/**
 * 用户状态
 *
 */
public enum UserStatusEnum {
    DISABLE(0),
    ENABLED(1);

    private Integer value;

    UserStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return this.value;
    }
}
