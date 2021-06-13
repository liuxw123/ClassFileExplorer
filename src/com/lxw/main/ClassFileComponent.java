package com.lxw.main;

import com.lxw.main.common.inter.PrintLevelInterface;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: ClassFileCompnent.java
 * @Date: 2021-06-13 00:35
 * @Version: V0.0
 */


public class ClassFileComponent implements PrintLevelInterface {
    public ClassFileComponent(int component) {
        this.component = getComponent(component);
        this.level = getLevel(this.component);
    }

    private static final int NUMBER_BACKSPACE_LEVEL = 1;

    public static final int COMPONENT_CLASS_INFO = 1;
    public static final int COMPONENT_CONSTANT_POOL = 2;
    public static final int COMPONENT_METHOD = 3;
    public static final int COMPONENT_FIELD = 4;
    public static final int COMPONENT_CLASS_ATTRIBUTES = 5;
    public static final int COMPONENT_METHOD_ATTRIBUTES = 6;
    public static final int COMPONENT_FIELD_ATTRIBUTES = 7;

    private static final int LEVEL_CLASS_INFO = 1;
    private static final int LEVEL_CONSTANT_POOL = 0;
    private static final int LEVEL_METHOD = 1;
    private static final int LEVEL_FIELD = 1;
    private static final int LEVEL_CLASS_ATTRIBUTES = 0;
    private static final int LEVEL_METHOD_ATTRIBUTES = LEVEL_METHOD + 1;
    private static final int LEVEL_FIELD_ATTRIBUTES = LEVEL_FIELD + 1;

    private int component;

    public static int getComponent(int component) {
        switch (component) {
            case COMPONENT_CLASS_ATTRIBUTES: return COMPONENT_CLASS_ATTRIBUTES;
            case COMPONENT_CONSTANT_POOL: return COMPONENT_CONSTANT_POOL;
            case COMPONENT_CLASS_INFO: return COMPONENT_CLASS_INFO;
            case COMPONENT_METHOD: return COMPONENT_METHOD;
            case COMPONENT_METHOD_ATTRIBUTES: return COMPONENT_METHOD_ATTRIBUTES;
            case COMPONENT_FIELD_ATTRIBUTES: return COMPONENT_FIELD_ATTRIBUTES;
            case COMPONENT_FIELD: return COMPONENT_FIELD;
            default: throw new IllegalArgumentException();
        }
    }

    public static int getLevel(int component) {
        component = getComponent(component);
        switch (component) {
            case COMPONENT_CLASS_ATTRIBUTES: return LEVEL_CLASS_ATTRIBUTES;
            case COMPONENT_CONSTANT_POOL: return LEVEL_CONSTANT_POOL;
            case COMPONENT_CLASS_INFO: return LEVEL_CLASS_INFO;
            case COMPONENT_METHOD: return LEVEL_METHOD;
            case COMPONENT_METHOD_ATTRIBUTES: return LEVEL_METHOD_ATTRIBUTES;
            case COMPONENT_FIELD: return LEVEL_FIELD;
            case COMPONENT_FIELD_ATTRIBUTES: return LEVEL_FIELD_ATTRIBUTES;
            default: throw new IllegalArgumentException();
        }
    }


    public int getLevel() {
        return level;
    }

    private void setLevel(int level) {
        if (level < 0) throw new IllegalArgumentException();
        this.level = level;
    }

    private int level;

    @Override
    public String getLevelString() {
        return getPrintLevelString(level);
    }

    public static String getPrintLevelString(int level) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < level<<NUMBER_BACKSPACE_LEVEL; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}
