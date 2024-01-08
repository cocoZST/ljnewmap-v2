package com.ljnewmap.modules.sys.enums;

/**
 * 菜单类型枚举
 *
 */
public enum MenuTypeEnum {
    /**
     * 菜单
    */
    MENU(0),
    /**
     * 按钮
    */
    BUTTON(1);

    private int value;

    MenuTypeEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
