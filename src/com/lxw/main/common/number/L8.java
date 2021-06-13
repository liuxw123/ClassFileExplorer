package com.lxw.main.common.number;

import com.lxw.main.common.abs.UnsignedValue;
import com.lxw.main.common.inter.ReaderInterface;
import com.lxw.main.common.inter.Value;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: L8.java
 * @Date: 2021-06-11 21:36
 * @Version: V0.0
 */


public class L8 extends UnsignedValue {
    private Long value;

    public L8() {
        reader = new Reader();
    }

    @Override
    public long get() {
        return value;
    }

    @Override
    public String toString() {
        return ""+value;
    }

    protected class Reader extends UnsignedValue.Reader {
        @Override
        public void read(DataInputStream dis) {
            try {
                value = Long.parseUnsignedLong(String.format("%X", dis.readLong()), 16);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
