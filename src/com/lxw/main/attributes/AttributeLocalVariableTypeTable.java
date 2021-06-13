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


public class AttributeLocalVariableTypeTable extends AttributeInfo{
    private LocalVariableTypeInfos localVariableTable;

    public AttributeLocalVariableTypeTable(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(tag, attributeNameIndex, classBean, component);
        localVariableTable = new LocalVariableTypeInfos();
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
        stringBuilder.append(localVariableTable);
        return Utils.format(stringBuilder);
    }
    public int getLocalVariableSize() {
        return (localVariableTable == null)?-1:localVariableTable.size();
    }

    class LocalVariableTypeInfos implements MultipleItemsInterface{

        private U2 localVariableTableLength;
        private LocalVariableTypeInfo[] localVariableInfos;

        public LocalVariableTypeInfos() {
            localVariableTableLength = new U2();
        }

        @Override
        public void readMultiple(DataInputStream dis) {
            localVariableTableLength.read(dis);
            int count = (int) localVariableTableLength.get();
            localVariableInfos = new LocalVariableTypeInfo[count];

            for (int i = 0; i < count; i++) {
                localVariableInfos[i] = new LocalVariableTypeInfo();
                localVariableInfos[i].read(dis);
            }
        }

        @Override
        public LocalVariableTypeInfo get(int index) {
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

    class LocalVariableTypeInfo implements ReaderInterface, PostOperation {

        private U2 startPc;
        private U2 length;
        private U2 nameIndex;
        private U2 signatureIndex;
        private U2 index;

        private ConstantUtf8Info name;
        private ConstantUtf8Info signature;

        public LocalVariableTypeInfo() {
            startPc = new U2();
            length = new U2();
            nameIndex = new U2();
            signatureIndex = new U2();
            index = new U2();
        }

        @Override
        public void read(DataInputStream dis) {
            startPc.read(dis);
            length.read(dis);
            nameIndex.read(dis);
            signatureIndex.read(dis);
            index.read(dis);

            post();
        }

        @Override
        public String toString() {
            return String.format("%5d %5d %5d %s %s", startPc.get(), length.get(), index.get(), name.contentString(), signature.contentString());
        }

        @Override
        public void post() {
            name = (ConstantUtf8Info) get((int) nameIndex.get()-1);
            signature = (ConstantUtf8Info) get((int) signatureIndex.get()-1);
        }
    }
}
