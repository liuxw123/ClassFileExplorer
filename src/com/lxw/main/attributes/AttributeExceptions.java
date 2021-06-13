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
 * @File: AttributeExceptions.java
 * @Date: 2021-06-12 16:33
 * @Version: V0.0
 */


public class AttributeExceptions extends AttributeInfo{

    ExceptionIndexTables exceptionIndexTable;
    public AttributeExceptions(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(tag, attributeNameIndex, classBean, component);
        exceptionIndexTable = new ExceptionIndexTables();
    }

    @Override
    public void read(DataInputStream dis) {
        super.read(dis);
        exceptionIndexTable.readMultiple(dis);
    }

    class ExceptionIndexTables implements MultipleItemsInterface {

        private U2 numberOfExceptions;
        private U2[] exceptionIndexTableIndexes;
        private ConstantClassInfo[] exceptionIndexTable;

        public ExceptionIndexTables() {
            numberOfExceptions = new U2();
        }

        @Override
        public void readMultiple(DataInputStream dis) {
            numberOfExceptions.read(dis);
            int count = (int) numberOfExceptions.get();
            exceptionIndexTableIndexes = new U2[count];
            exceptionIndexTable = new ConstantClassInfo[count];

            for (int i = 0; i < count; i++) {
                exceptionIndexTableIndexes[i] = new U2();
                exceptionIndexTableIndexes[i].read(dis);
                exceptionIndexTable[i] = get((int) (exceptionIndexTableIndexes[i].get()-1));
            }
        }

        @Override
        public ConstantClassInfo get(int index) {
            if (index >= size()) return null;
            return exceptionIndexTable[index];
        }

        @Override
        public int size() {
            return (exceptionIndexTable == null)?-1:exceptionIndexTable.length;
        }
    }
}
