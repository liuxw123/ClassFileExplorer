package com.lxw.main.attributes;

import com.lxw.main.ClassBean;
import com.lxw.main.common.number.U2;
import com.lxw.main.cp.ConstantUtf8Info;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: AttributeSignature.java
 * @Date: 2021-06-12 15:59
 * @Version: V0.0
 */


public class AttributeSignature extends AttributeInfo{
    private U2 signatureIndex;
    private ConstantUtf8Info signature;

    public AttributeSignature(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(tag, attributeNameIndex, classBean, component);
        signatureIndex = new U2();
    }

    @Override
    public void read(DataInputStream dis) {
        super.read(dis);
        signatureIndex.read(dis);
        signature = (ConstantUtf8Info) get((int) (signatureIndex.get()-1));
    }
}
