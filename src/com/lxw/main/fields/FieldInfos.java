package com.lxw.main.fields;

import com.lxw.main.ClassBean;
import com.lxw.main.attributes.AttributeInfos;
import com.lxw.main.common.inter.MultipleItemsInterface;
import com.lxw.main.common.number.U2;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: FieldInfos.java
 * @Date: 2021-06-11 22:32
 * @Version: V0.0
 */


public class FieldInfos implements MultipleItemsInterface {

    private ClassBean classBean;

    private U2 fieldsCount;
    private FieldInfo[] fields;

    public FieldInfos(ClassBean classBean) {
        this.classBean = classBean;
        fieldsCount = new U2();
    }

    @Override
    public void readMultiple(DataInputStream dis) {
        fieldsCount.read(dis);
        int count = (int) fieldsCount.get();
        fields = new FieldInfo[count];
        for (int i = 0; i < count; i++) {
            fields[i] = new FieldInfo(classBean);
            fields[i].read(dis);
        }
    }

    @Override
    public FieldInfo get(int index) {
        if (index >= size()) return null;
        return fields[index];
    }

    @Override
    public int size() {
        return fields==null?-1:fields.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            stringBuilder.append(get(i)+"\n");
        }

        return stringBuilder.toString();
    }
}
