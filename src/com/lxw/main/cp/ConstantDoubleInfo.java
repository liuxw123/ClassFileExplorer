package com.lxw.main.cp;

import com.lxw.main.common.number.D8;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: ConstantDoubleInfo.java
 * @Date: 2021-06-11 21:34
 * @Version: V0.0
 */


public class ConstantDoubleInfo extends CpInfo{
    private D8 bytes;
    public ConstantDoubleInfo(int tag) {
        super(tag);
        bytes = new D8();
    }

    @Override
    public void read(DataInputStream dis) {
        bytes.read(dis);
    }

    @Override
    public void postHandle(CpInfos cpInfos) {

    }

    @Override
    public String toString() {
        return String.format("%s %f", getTag(), bytes.get());
    }

    @Override
    public boolean skipNext() {
        return true;
    }
}
