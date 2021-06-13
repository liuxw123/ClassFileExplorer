package com.lxw.main.attributes;

import com.lxw.main.ClassBean;
import com.lxw.main.common.number.U1;
import com.lxw.main.common.number.U2;
import com.lxw.main.common.number.U4;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: AttributeSourceDebugExtension.java
 * @Date: 2021-06-12 16:46
 * @Version: V0.0
 */


public class AttributeSourceDebugExtension extends AttributeInfo{
    private U1[] debugExtension;

    public AttributeSourceDebugExtension(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(tag, attributeNameIndex, classBean, component);
    }

    @Override
    public void read(DataInputStream dis) {
        super.read(dis);
        debugExtension = new U1[getAttributeLength()];

        for (int i = 0; i < debugExtension.length; i++) {
            debugExtension[i] = new U1();
            debugExtension[i].read(dis);
        }
    }
}
