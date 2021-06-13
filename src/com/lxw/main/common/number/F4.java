package com.lxw.main.common.number;

import com.lxw.main.common.inter.ReaderInterface;
import com.lxw.main.common.inter.Value;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: Float.java
 * @Date: 2021-06-11 21:27
 * @Version: V0.0
 */


public class F4 implements Value, ReaderInterface {
    private float value;

    @Override
    public void read(DataInputStream dis) {
        try {
            value = dis.readFloat();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float get() {
        return value;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
