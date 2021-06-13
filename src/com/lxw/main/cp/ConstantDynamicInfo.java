package com.lxw.main.cp;

import com.lxw.main.common.number.U2;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: COnstantInvokeDynamicInfo.java
 * @Date: 2021-06-12 14:54
 * @Version: V0.0
 */


public class ConstantDynamicInfo extends CpInfo{

    private U2 bootstrapMethodAttrIndex;
    private U2 nameAndTypeIndex;

    public ConstantDynamicInfo(int tag) {
        super(tag);

        bootstrapMethodAttrIndex = new U2();
        nameAndTypeIndex = new U2();
    }

    @Override
    public void read(DataInputStream dis) {
        bootstrapMethodAttrIndex.read(dis);
        nameAndTypeIndex.read(dis);
    }

    @Override
    public void postHandle(CpInfos cpInfos) {

    }

    @Override
    public String toString() {
        return String.format("%s #%d.#%d", getTag(), bootstrapMethodAttrIndex.get(), nameAndTypeIndex.get());
    }
}
