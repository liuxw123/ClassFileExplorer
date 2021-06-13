package com.lxw.main.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: Utils.java
 * @Date: 2021-06-13 13:44
 * @Version: V0.0
 */


public class Utils {
    public static String format(StringBuilder stringBuilder) {
        boolean lastIsEnter = false;
        List<Integer> remove = new ArrayList<>();
        for (int i = stringBuilder.length()-1; i >= 0; i--) {
            if (lastIsEnter && stringBuilder.charAt(i) == '\n') remove.add(i);
            lastIsEnter = stringBuilder.charAt(i) == '\n';
        }

        for (int p: remove) {
            stringBuilder.deleteCharAt(p);
        }

        return stringBuilder.toString();
    }

    public static String format(String s) {
        return format(new StringBuilder(s));
    }
}
