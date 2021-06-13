package com.lxw.main.attributes;

import com.lxw.main.ClassBean;
import com.lxw.main.common.Utils;
import com.lxw.main.common.inter.PostOperation;
import com.lxw.main.common.number.U2;
import com.lxw.main.cp.ConstantClassInfo;
import com.lxw.main.cp.CpInfo;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: AttributeNestHost.java
 * @Date: 2021-06-12 15:46
 * @Version: V0.0
 */


public class AttributeNestHost extends AttributeInfo implements PostOperation {
    private U2 hostClassIndex;
    private ConstantClassInfo hostClass;

    public AttributeNestHost(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(tag, attributeNameIndex, classBean, component);
        hostClassIndex = new U2();
    }

    @Override
    public void read(DataInputStream dis) {
        super.read(dis);
        hostClassIndex.read(dis);
        post();
    }

    @Override
    public void post() {
        hostClass = (ConstantClassInfo) get((int) (hostClassIndex.get()-1));
    }

    @Override
    public String toString() {
        return Utils.format(String.format("%s%s: class %s", getLevelString(), getTag(), hostClass.referenceString()));
    }
}
