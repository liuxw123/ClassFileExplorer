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


public class ConstantPackageInfo extends CpInfo implements AttributeString {

    private U2 nameIndex;
    private ConstantUtf8Info name;

    public ConstantPackageInfo(int tag) {
        super(tag);
        nameIndex = new U2();
    }

    @Override
    public void postHandle(CpInfos cpInfos) {
        name = (ConstantUtf8Info) cpInfos.get((int) (nameIndex.get()-1));
    }

    @Override
    public void read(DataInputStream dis) {
        nameIndex.read(dis);
    }

    @Override
    public String toString() {
        return String.format("%s #%d // %s", getTag(), nameIndex.get(), referenceString());
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
