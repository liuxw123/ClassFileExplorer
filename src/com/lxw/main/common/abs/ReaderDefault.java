package com.lxw.main.common.abs;

import com.lxw.main.common.inter.ReaderInterface;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: ReaderDefault.java
 * @Date: 2021-06-11 00:27
 * @Version: V0.0
 */

public abstract class ReaderDefault implements ReaderInterface {
    private static int position;
    public static void add(int length) {
        position += length;
    }

    public static int getPosition() {
        return position;
    }
}
