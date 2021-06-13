package com.lxw.main.attributes;

import com.lxw.main.ClassBean;
import com.lxw.main.common.Utils;
import com.lxw.main.common.inter.MultipleItemsInterface;
import com.lxw.main.common.inter.PostOperation;
import com.lxw.main.common.inter.ReaderInterface;
import com.lxw.main.common.number.U2;
import com.lxw.main.cp.ConstantUtf8Info;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: AttributeLocalVariableTable.java
 * @Date: 2021-06-12 11:04
 * @Version: V0.0
 */


public class AttributeLocalVariableTable extends AttributeInfo{
    private LocalVariableInfos localVariableTable;

    public AttributeLocalVariableTable(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(tag, attributeNameIndex, classBean, component);
        localVariableTable = new LocalVariableInfos();
    }

    @Override
    public void read(DataInputStream dis) {
        super.read(dis);
        localVariableTable.readMultiple(dis);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getPrintLevelString(getLevel()+1)+tag+":\n");
        stringBuilder.append(getPrintLevelString(getLevel()+2)+" Start Length   Slot Name Signature\n");
        stringBuilder.append(localVariableTable);
        return Utils.format(stringBuilder);
    }

    public int getLocalVariableSize() {
        return localVariableTable.size();
    }

    class LocalVariableInfos implements MultipleItemsInterface{

        private U2 localVariableTableLength;
        private LocalVariableInfo[] localVariableInfos;

        public LocalVariableInfos() {
            localVariableTableLength = new U2();
        }

        @Override
        public void readMultiple(DataInputStream dis) {
            localVariableTableLength.read(dis);
            int count = (int) localVariableTableLength.get();
            localVariableInfos = new LocalVariableInfo[count];

            for (int i = 0; i < count; i++) {
                localVariableInfos[i] = new LocalVariableInfo();
                localVariableInfos[i].read(dis);
            }
        }

        @Override
        public LocalVariableInfo get(int index) {
            if (index >= size()) return null;
            return localVariableInfos[index];
        }

        @Override
        public int size() {
            return (localVariableInfos == null)?-1:localVariableInfos.length;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < localVariableInfos.length; i++) {
                stringBuilder.append(getPrintLevelString(getLevel()+2)+localVariableInfos[i]+"\n");
            }
            if (stringBuilder.length() > 0) stringBuilder.deleteCharAt(stringBuilder.length()-1);
            return stringBuilder.toString();
        }
    }

    class LocalVariableInfo implements ReaderInterface, PostOperation {

        private U2 startPc;
        private U2 length;
        private U2 nameIndex;
        private U2 descriptorIndex;
        private U2 index;

        private ConstantUtf8Info name;
        private ConstantUtf8Info descriptor;

        public LocalVariableInfo() {
            startPc = new U2();
            length = new U2();
            nameIndex = new U2();
            descriptorIndex = new U2();
            index = new U2();
        }

        @Override
        public void read(DataInputStream dis) {
            startPc.read(dis);
            length.read(dis);
            nameIndex.read(dis);
            descriptorIndex.read(dis);
            index.read(dis);

            post();
        }

        @Override
        public String toString() {
            return String.format("%6d %6d %6d %s   %s", startPc.get(), length.get(), index.get(), name.contentString(), descriptor.contentString());
        }

        @Override
        public void post() {
            name = (ConstantUtf8Info) get((int) nameIndex.get()-1);
            descriptor = (ConstantUtf8Info) get((int) descriptorIndex.get()-1);
        }
    }
}
