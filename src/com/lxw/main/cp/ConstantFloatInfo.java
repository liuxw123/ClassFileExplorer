package com.lxw.main.cp;

import com.lxw.main.common.number.F4;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: ConstantFloatInfo.java
 * @Date: 2021-06-11 21:26
 * @Version: V0.0
 */


public class ConstantFloatInfo extends CpInfo{
    F4 bytes;

    public ConstantFloatInfo(int tag) {
        super(tag);
        bytes = new F4();
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
}
