package com.lxw.main.common.number;

import com.lxw.main.common.abs.UnsignedValue;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: u4.java
 * @Date: 2021-06-10 23:37
 * @Version: V0.0
 */


public class U4 extends UnsignedValue {
    private Long value;

    public U4() {
        reader = new Reader();
    }

    @Override
    public long get() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("0x%08X", value);
    }

    protected class Reader extends UnsignedValue.Reader {
        @Override
        public void read(DataInputStream dis) {
            try {
                value = Long.parseUnsignedLong(String.format("%X", dis.readInt()), 16);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
