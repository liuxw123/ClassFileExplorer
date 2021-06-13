package com.lxw.main.cp;

import com.lxw.main.common.number.U2;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: ConstantMethodRefInfo.java
 * @Date: 2021-06-11 21:17
 * @Version: V0.0
 */


public class ConstantMethodRefInfo extends CpInfo{
    private U2 classIndex;
    private U2 nameAndTypeIndex;
    private ConstantClassInfo clazz;
    private ConstantNameAndTypeInfo nameAndType;

    public ConstantMethodRefInfo(int tag) {
        super(tag);

        classIndex = new U2();
        nameAndTypeIndex = new U2();
    }

    @Override
    public void read(DataInputStream dis) {
        classIndex.read(dis);
        nameAndTypeIndex.read(dis);
    }

    @Override
    public void postHandle(CpInfos cpInfos) {
        clazz = (ConstantClassInfo) cpInfos.get((int) (classIndex.get()-1));
        nameAndType = (ConstantNameAndTypeInfo) cpInfos.get((int) (nameAndTypeIndex.get()-1));
    }

    @Override
    public String toString() {
        return String.format("%s #%d.#%d // %s", getTag(), classIndex.get(), nameAndTypeIndex.get(), referenceString());
    }

    @Override
    public String referenceString() {
        return String.format("%s.%s", clazz.referenceString(), nameAndType.referenceString());
    }
}
