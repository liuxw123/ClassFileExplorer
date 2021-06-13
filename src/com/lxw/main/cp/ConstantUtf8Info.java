package com.lxw.main.cp;

import com.lxw.main.common.inter.AttributeString;
import com.lxw.main.common.inter.PostOperation;
import com.lxw.main.common.number.U1;
import com.lxw.main.common.number.U2;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: ConstantUtf8Info.java
 * @Date: 2021-06-11 20:24
 * @Version: V0.0
 */


public class ConstantUtf8Info extends CpInfo implements AttributeString, PostOperation {
    private U2 length;
    private U1[] bytes;
    private String string;

    public ConstantUtf8Info(int tag) {
        super(tag);
        length = new U2();
    }

    @Override
    public void postHandle(CpInfos cpInfos) {

    }

    @Override
    public void read(DataInputStream dis) {
        length.read(dis);
        bytes = new U1[(int) length.get()];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = new U1();
            bytes[i].read(dis);
        }

        post();
    }

    @Override
    public String toString() {
        return String.format("%s %s", getTag(), string);
    }

    @Override
    public String attributeString() {
        return string;
    }

    @Override
    public String referenceString() {
        return string;
    }

    @Override
    public String contentString() {
        return string;
    }

    @Override
    public void post() {
        byte[] bs = new byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            bs[i] = (byte) bytes[i].get();
        }

        string = new String(bs);
    }
}
