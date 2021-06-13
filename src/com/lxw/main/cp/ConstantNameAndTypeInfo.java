package com.lxw.main.cp;

import com.lxw.main.common.number.U2;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: COnstantNameAndTypeInfo.java
 * @Date: 2021-06-11 21:22
 * @Version: V0.0
 */


public class ConstantNameAndTypeInfo extends CpInfo{
    private U2 nameIndex;
    private U2 descriptorIndex;
    private ConstantUtf8Info name;
    private ConstantUtf8Info descriptor;

    public ConstantNameAndTypeInfo(int tag) {
        super(tag);
        nameIndex = new U2();
        descriptorIndex = new U2();
    }

    @Override
    public void read(DataInputStream dis) {
        nameIndex.read(dis);
        descriptorIndex.read(dis);
    }

    @Override
    public void postHandle(CpInfos cpInfos) {
        name = (ConstantUtf8Info) cpInfos.get((int) (nameIndex.get()-1));
        descriptor = (ConstantUtf8Info) cpInfos.get((int) (descriptorIndex.get()-1));
    }

    @Override
    public String toString() {
        return String.format("%s #%d:#%d // %s", getTag(), nameIndex.get(), descriptorIndex.get(), referenceString());
    }

    @Override
    public String referenceString() {
        return String.format("%s:%s", name.referenceString(), descriptor.referenceString());
    }
}
