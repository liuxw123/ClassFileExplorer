package com.lxw.main.interfaces;

import com.lxw.main.ClassBean;
import com.lxw.main.common.inter.MultipleItemsInterface;
import com.lxw.main.common.number.U2;
import com.lxw.main.cp.CpInfo;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: Interfaces.java
 * @Date: 2021-06-11 22:13
 * @Version: V0.0
 */


public class Interfaces implements MultipleItemsInterface {

    // length: interfacesCount; item: constantPool index
    private U2 interfacesCount;
    private U2[] interfaces;
    private CpInfo[] interfacesRef;

    private ClassBean classBean;

    public Interfaces(ClassBean classBean) {
        interfacesCount = new U2();
        this.classBean = classBean;
    }

    @Override
    public void readMultiple(DataInputStream dis) {
        interfacesCount.read(dis);
        int count = (int) interfacesCount.get();
        interfaces = new U2[count];

        for (int i = 0; i < count; i++) {
            interfaces[i] = new U2();
            interfaces[i].read(dis);
        }

        postHandle();
    }

    @Override
    public CpInfo get(int index) {
        return null;
    }

    @Override
    public int size() {
        return interfaces.length;
    }

    private void postHandle() {
        interfacesRef = new CpInfo[interfaces.length];

        for (int i=0; i<interfaces.length;i++) {
            interfacesRef[i] = classBean.get((int) interfaces[i].get());
        }
    }

    @Override
    public String toString() {
        return String.format("interfaces: %d", size());
    }
}
