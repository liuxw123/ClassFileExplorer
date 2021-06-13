package com.lxw.main.attributes;

import com.lxw.main.ClassBean;
import com.lxw.main.common.inter.MultipleItemsInterface;
import com.lxw.main.common.inter.PostOperation;
import com.lxw.main.common.number.U2;
import com.lxw.main.cp.ConstantClassInfo;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: AttributeNestMembers.java
 * @Date: 2021-06-12 15:49
 * @Version: V0.0
 */


public class AttributeNestMembers extends AttributeInfo{
    private Classes classes;
    public AttributeNestMembers(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(tag, attributeNameIndex, classBean, component);
        classes = new Classes();
    }

    @Override
    public void read(DataInputStream dis) {
        super.read(dis);
        classes.readMultiple(dis);
    }

    class Classes implements MultipleItemsInterface {

        private U2 numberOfClasses;
        private U2[] classesIndex;
        private ConstantClassInfo[] classes;

        public Classes() {
            numberOfClasses = new U2();
        }

        @Override
        public void readMultiple(DataInputStream dis) {
            numberOfClasses.read(dis);
            int count = (int) numberOfClasses.get();
            classesIndex = new U2[count];
            classes = new ConstantClassInfo[count];
            for (int i = 0; i < count; i++) {
                classesIndex[i] = new U2();
                classesIndex[i].read(dis);
                classes[i] = (ConstantClassInfo) get((int) (classesIndex[i].get()-1));
            }
        }

        @Override
        public ConstantClassInfo get(int index) {
            if (index >= size())return null;
            else return classes[index];
        }

        @Override
        public int size() {
            return (classes==null)?-1:classes.length;
        }

    }
}
