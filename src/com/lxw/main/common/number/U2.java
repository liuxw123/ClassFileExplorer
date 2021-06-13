package com.lxw.main.common.number;

import com.lxw.main.common.abs.UnsignedValue;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: U2.java
 * @Date: 2021-06-10 23:54
 * @Version: V0.0
 */


public class U2 extends UnsignedValue{
    private Integer value;

    public U2() {
        reader = new Reader();
    }

    @Override
    public long get() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("0x%04X", value);
    }

    protected class Reader extends UnsignedValue.Reader {
        @Override
        public void read(DataInputStream dis) {
            try {
                value = Integer.parseUnsignedInt(String.format("%X", dis.readShort()), 16);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
