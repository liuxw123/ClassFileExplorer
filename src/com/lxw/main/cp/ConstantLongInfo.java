package com.lxw.main.cp;

import com.lxw.main.common.number.L8;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: ConstantLongInfo.java
 * @Date: 2021-06-11 21:36
 * @Version: V0.0
 */


public class ConstantLongInfo extends CpInfo{
    private L8 bytes;

    public ConstantLongInfo(int tag) {
        super(tag);
        bytes = new L8();
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
        return String.format("%s %d", getTag(), bytes.get());
    }

    @Override
    public boolean skipNext() {
        return true;
    }
}
