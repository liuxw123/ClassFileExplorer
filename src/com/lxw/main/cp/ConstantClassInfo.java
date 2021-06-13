package com.lxw.main.cp;

import com.lxw.main.common.inter.AttributeString;
import com.lxw.main.common.number.U2;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: ConstantClassInfo.java
 * @Date: 2021-06-11 20:10
 * @Version: V0.0
 */


public class ConstantClassInfo extends CpInfo implements AttributeString {

    private U2 index;
    private ConstantUtf8Info name;

    public ConstantClassInfo(int tag) {
        super(tag);
        index = new U2();
    }

    @Override
    public void postHandle(CpInfos cpInfos) {
        name = (ConstantUtf8Info) cpInfos.get((int) (index.get()-1));
    }

    @Override
    public void read(DataInputStream dis) {
        index.read(dis);
    }

    @Override
    public String toString() {
        return String.format("%s #%d // %s", getTag(), index.get(), referenceString());
    }

    @Override
    public String attributeString() {
        return null;
    }

    @Override
    public String referenceString() {
        return name.referenceString();
    }
}
