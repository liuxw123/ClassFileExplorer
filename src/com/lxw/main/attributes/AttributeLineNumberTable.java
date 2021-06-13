package com.lxw.main.attributes;

import com.lxw.main.ClassBean;
import com.lxw.main.common.Utils;
import com.lxw.main.common.inter.MultipleItemsInterface;
import com.lxw.main.common.inter.ReaderInterface;
import com.lxw.main.common.number.U1;
import com.lxw.main.common.number.U2;
import com.lxw.main.common.number.U4;

import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: AttributeLineNumberTable.java
 * @Date: 2021-06-11 23:28
 * @Version: V0.0
 */


public class AttributeLineNumberTable extends AttributeInfo{

    private LineNumberTableInfos lineNumberTables;
    public AttributeLineNumberTable(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(tag, attributeNameIndex, classBean, component);

        lineNumberTables = new LineNumberTableInfos();
    }

    @Override
    public void read(DataInputStream dis) {
        super.read(dis);
        lineNumberTables.readMultiple(dis);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getPrintLevelString(getLevel()+1) + tag+"\n");
        stringBuilder.append(lineNumberTables);
        return Utils.format(stringBuilder);
    }

    class LineNumberTableInfos implements MultipleItemsInterface {

        private U2 lineNumberTableLength;
        private LineNumberTableInfo[] lineNumberTable;

        public LineNumberTableInfos() {
            lineNumberTableLength = new U2();
        }

        @Override
        public void readMultiple(DataInputStream dis) {
            lineNumberTableLength.read(dis);
            int count = (int) lineNumberTableLength.get();
            lineNumberTable = new LineNumberTableInfo[count];
            for (int i=0;i<count;i++) {
                lineNumberTable[i] = new LineNumberTableInfo();
                lineNumberTable[i].read(dis);
            }
        }

        @Override
        public LineNumberTableInfo get(int index) {
            if (index >= size()) return null;
            return lineNumberTable[index];
        }

        @Override
        public int size() {
            return lineNumberTable.length;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < lineNumberTable.length; i++) {
                stringBuilder.append(getPrintLevelString(getLevel()+2) + lineNumberTable[i]+"\n");
            }
            if (stringBuilder.length() > 0) stringBuilder.deleteCharAt(stringBuilder.length()-1);
            return stringBuilder.toString();
        }
    }

    class LineNumberTableInfo implements ReaderInterface {
        private U2 startPc;
        private U2 lineNumber;

        public LineNumberTableInfo() {
            startPc = new U2();
            lineNumber = new U2();
        }

        @Override
        public void read(DataInputStream dis) {
            startPc.read(dis);
            lineNumber.read(dis);
        }

        @Override
        public String toString() {
            return String.format("line %d: %d", lineNumber.get(), startPc.get());
        }
    }
}
