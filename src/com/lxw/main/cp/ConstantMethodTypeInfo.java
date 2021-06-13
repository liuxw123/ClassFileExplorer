package com.lxw.main.cp;

import com.lxw.main.common.number.U2;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: ConstantMethodTypeInfo.java
 * @Date: 2021-06-12 15:06
 * @Version: V0.0
 */


public class ConstantMethodTypeInfo extends CpInfo{

    private U2 descriptorIndex;
    private ConstantUtf8Info descriptor;
    public ConstantMethodTypeInfo(int tag) {
        super(tag);
        descriptorIndex = new U2();
    }

    @Override
    public void read(DataInputStream dis) {
        descriptorIndex.read(dis);
    }

    @Override
    public void postHandle(CpInfos cpInfos) {
        descriptor = (ConstantUtf8Info) cpInfos.get((int) (descriptorIndex.get()-1));
    }

    @Override
    public String toString() {
        return String.format("%s #%d // %s", getTag(), descriptorIndex.get(), referenceString());
    }

    @Override
    public String referenceString() {
        return descriptor.referenceString();
    }
}
