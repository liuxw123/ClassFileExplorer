package com.lxw.main.cp;

import com.lxw.main.ClassFileComponent;
import com.lxw.main.common.inter.MultipleItemsInterface;
import com.lxw.main.common.number.U1;
import com.lxw.main.common.number.U2;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: CpInfos.java
 * @Date: 2021-06-11 19:58
 * @Version: V0.0
 */


public class CpInfos extends ClassFileComponent implements MultipleItemsInterface{
    // length: constantPoolCount - 1
    private U2 constantPoolCount;
    private CpInfo[] constantPool;

    public CpInfos() {
        super(COMPONENT_CONSTANT_POOL);
        constantPoolCount = new U2();
    }

    @Override
    public void readMultiple(DataInputStream dis) {
        constantPoolCount.read(dis);
        long count = constantPoolCount.get();
        constantPool = new CpInfo[(int) count-1];
        boolean skip = false;
        for (long i=0;i<constantPool.length;i++) {
            if (skip) {
                skip = false;
                continue;
            }
            U1 tag = new U1();
            tag.read(dis);
            CpInfo cpInfo = CpInfo.get((int) tag.get());
            cpInfo.read(dis);
            constantPool[(int) i] = cpInfo;
            skip = cpInfo.skipNext();
        }

        skip = false;
        for (long i=0;i<constantPool.length;i++) {
            if (skip) {
                skip = false;
                continue;
            }
            constantPool[(int) i].postHandle(this);
            skip = constantPool[(int) i].skipNext();
        }
    }

    @Override
    public CpInfo get(int index) {
        if (index >= constantPool.length) throw new ArrayIndexOutOfBoundsException();
        return constantPool[index];
    }

    @Override
    public int size() {
        return constantPool.length;
    }

    public String getBlankString(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getLevelString()+"Constant Pool:\n");
        boolean skip = false;
        for (int i = 0; i < size(); i++) {
            if (skip) {
                skip = false;
                continue;
            }
            String orderStr = String.format("#%d", (i + 1));
            stringBuilder.append(String.format("%s%s%s = %s\n", getLevelString(), getBlankString(5-orderStr.length()), orderStr, constantPool[i]));
            skip = constantPool[i].skipNext();
        }

        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }
}
