package com.lxw.main.cp;

import com.lxw.main.common.number.U2;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: ConstantStringInfo.java
 * @Date: 2021-06-11 21:43
 * @Version: V0.0
 */


public class ConstantStringInfo extends CpInfo{
    private U2 index;
    private ConstantUtf8Info name;

    public ConstantStringInfo(int tag) {
        super(tag);
        index = new U2();
    }

    @Override
    public void read(DataInputStream dis) {
        index.read(dis);
    }

    @Override
    public void postHandle(CpInfos cpInfos) {
        name = (ConstantUtf8Info) cpInfos.get((int) (index.get()-1));
    }

    @Override
    public String toString() {
        return String.format("%s #%d // %s", getTag(), index.get(), referenceString());
    }

    @Override
    public String referenceString() {
        return name.referenceString();
    }
}
