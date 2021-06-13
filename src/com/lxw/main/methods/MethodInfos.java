package com.lxw.main.methods;

import com.lxw.main.ClassBean;
import com.lxw.main.ClassFileComponent;
import com.lxw.main.attributes.AttributeInfos;
import com.lxw.main.common.inter.MultipleItemsInterface;
import com.lxw.main.common.number.U2;
import com.lxw.main.fields.FieldInfo;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: MethodInfos.java
 * @Date: 2021-06-11 22:46
 * @Version: V0.0
 */


public class MethodInfos extends ClassFileComponent implements MultipleItemsInterface {
    private ClassBean classBean;

    private U2 methodsCount;
    private MethodInfo[] methods;


    public MethodInfos(ClassBean classBean) {
        super(COMPONENT_METHOD);
        this.classBean = classBean;
        methodsCount = new U2();
    }

    @Override
    public void readMultiple(DataInputStream dis) {
        methodsCount.read(dis);
        int count = (int) methodsCount.get();
        methods = new MethodInfo[count];
        for (int i = 0; i < count; i++) {
            methods[i] = new MethodInfo(classBean);
            methods[i].read(dis);
        }
    }

    @Override
    public MethodInfo get(int index) {
        if (index >= size()) return null;
        return methods[index];
    }

    @Override
    public int size() {
        return methods==null?-1:methods.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            stringBuilder.append(methods[i]+"\n");
        }

        return stringBuilder.toString();
    }
}
