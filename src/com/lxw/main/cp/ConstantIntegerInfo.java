package com.lxw.main.cp;

import com.lxw.main.common.number.U4;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: ConstantIntegerInfo.java
 * @Date: 2021-06-11 21:24
 * @Version: V0.0
 */


public class ConstantIntegerInfo extends CpInfo{

    U4 bytes;

    public ConstantIntegerInfo(int tag) {
        super(tag);

        bytes = new U4();
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
        return getTag()+bytes.get();
    }
}
