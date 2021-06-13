package com.lxw.main.attributes;

import com.lxw.main.ClassBean;
import com.lxw.main.common.Utils;
import com.lxw.main.common.inter.PostOperation;
import com.lxw.main.common.number.U2;
import com.lxw.main.cp.ConstantUtf8Info;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: AttributeSourceFile.java
 * @Date: 2021-06-12 14:26
 * @Version: V0.0
 */


public class AttributeSourceFile extends AttributeInfo implements PostOperation {
    private U2 sourceFileIndex;
    private ConstantUtf8Info sourceFile;

    public AttributeSourceFile(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(tag, attributeNameIndex, classBean, component);
        sourceFileIndex = new U2();
    }

    @Override
    public void read(DataInputStream dis) {
        super.read(dis);
        sourceFileIndex.read(dis);
        post();
    }

    @Override
    public void post() {
        sourceFile = (ConstantUtf8Info) get((int) (sourceFileIndex.get()-1));
    }

    @Override
    public String toString() {
        return Utils.format(String.format("%s%s: \"%s\"", getLevelString(), getTag(), sourceFile.contentString()));
    }
}
