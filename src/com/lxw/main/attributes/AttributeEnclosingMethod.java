package com.lxw.main.attributes;

import com.lxw.main.ClassBean;
import com.lxw.main.common.inter.PostOperation;
import com.lxw.main.common.number.U2;
import com.lxw.main.cp.ConstantClassInfo;
import com.lxw.main.cp.ConstantNameAndTypeInfo;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: AttributeEnclosingMethod.java
 * @Date: 2021-06-12 16:40
 * @Version: V0.0
 */


public class AttributeEnclosingMethod extends AttributeInfo implements PostOperation {
    private U2 classIndex;
    private U2 methodIndex;
    private ConstantClassInfo clazz;
    private ConstantNameAndTypeInfo mathod;

    public AttributeEnclosingMethod(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(tag, attributeNameIndex, classBean, component);
        classIndex = new U2();
        methodIndex = new U2();
    }

    @Override
    public void read(DataInputStream dis) {
        super.read(dis);
        classIndex.read(dis);
        methodIndex.read(dis);

        post();

    }

    @Override
    public void post() {
        clazz = (ConstantClassInfo) get((int) (classIndex.get()-1));
        mathod = (methodIndex.get() == 0)?null: (ConstantNameAndTypeInfo) get((int) (methodIndex.get()-1));
    }
}
