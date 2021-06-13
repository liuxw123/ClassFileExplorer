package com.lxw.main.attributes;

import com.lxw.main.ClassBean;
import com.lxw.main.common.number.U2;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: AttributDeprecated.java
 * @Date: 2021-06-12 16:31
 * @Version: V0.0
 */


public class AttributeDeprecated extends AttributeInfo{
    public AttributeDeprecated(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(tag, attributeNameIndex, classBean, component);
    }
}
