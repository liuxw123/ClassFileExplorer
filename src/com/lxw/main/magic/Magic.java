package com.lxw.main.magic;

import com.lxw.main.common.inter.ReaderInterface;
import com.lxw.main.common.number.U4;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: Magic.java
 * @Date: 2021-06-13 13:59
 * @Version: V0.0
 */


public class Magic implements ReaderInterface {
    private U4 magic;

    public Magic() {
        magic = new U4();
    }

    @Override
    public void read(DataInputStream dis) {
        magic.read(dis);
        if (((int) magic.get()) != 0xCAFEBABE) throw new IllegalArgumentException("非标准class文件");
    }

    @Override
    public String toString() {
        return String.format("magic: %s", magic);
    }
}
