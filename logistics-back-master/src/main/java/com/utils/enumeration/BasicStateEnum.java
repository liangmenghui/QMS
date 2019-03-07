package com.utils.enumeration;

/**
 * 基本枚举类
 * @author Shen
 *
 */
public enum BasicStateEnum {

    /**
     * 否 0
     */
    FALSE(BasicEnumConstants.FALSE),

    /**
     * 是 1
     */
    TRUE(BasicEnumConstants.TRUE);

    private int value;

    BasicStateEnum(int value){ this.value = value; }

    public int intValue() {
        return this.value;
    }
}
