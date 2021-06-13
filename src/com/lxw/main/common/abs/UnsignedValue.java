package com.lxw.main.common.abs;

import com.lxw.main.common.inter.Value;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: UnsignedValue.java
 * @Date: 2021-06-10 23:46
 * @Version: V0.0
 */


public abstract class UnsignedValue implements Value {
    protected Reader reader;

    public void read(DataInputStream dis) {
        reader.read(dis);
    }

    public abstract long get();
    protected abstract class Reader extends ReaderDefault {
    }
}
