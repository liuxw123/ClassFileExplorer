package com.lxw.main.attributes;

import com.lxw.main.ClassBean;
import com.lxw.main.common.inter.PostOperation;
import com.lxw.main.common.number.U2;
import com.lxw.main.cp.CpInfo;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: AttributeConstantValue.java
 * @Date: 2021-06-12 15:40
 * @Version: V0.0
 */


public class AttributeConstantValue extends AttributeInfo implements PostOperation {
    private U2 constantValueIndex;
    private CpInfo constantValue;

    public AttributeConstantValue(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(tag, attributeNameIndex, classBean, component);
        constantValueIndex = new U2();
    }

    @Override
    public void read(DataInputStream dis) {
        super.read(dis);
        constantValueIndex.read(dis);
        post();
    }

    @Override
    public void post() {
        constantValue = get((int) (constantValueIndex.get()-1));
    }
}
