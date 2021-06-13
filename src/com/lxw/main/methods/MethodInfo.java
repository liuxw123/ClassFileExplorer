package com.lxw.main.methods;

import com.lxw.main.ClassBean;
import com.lxw.main.ClassFileComponent;
import com.lxw.main.access.AccessFlag;
import com.lxw.main.attributes.AttributeInfos;
import com.lxw.main.common.Utils;
import com.lxw.main.common.inter.PostOperation;
import com.lxw.main.common.inter.ReaderInterface;
import com.lxw.main.common.number.U2;
import com.lxw.main.cp.ConstantUtf8Info;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: MethodInfo.java
 * @Date: 2021-06-11 01:00
 * @Version: V0.0
 */


public class MethodInfo extends ClassFileComponent implements ReaderInterface, PostOperation {
    private AccessFlag accessFlag;
    private U2 nameIndex;
    private U2 descriptorIndex;
    private AttributeInfos attributes;

    private ConstantUtf8Info name;
    private ConstantUtf8Info descriptor;

    private ClassBean classBean;

    public MethodInfo(ClassBean classBean) {
        super(COMPONENT_METHOD);
        this.classBean = classBean;
        accessFlag = new AccessFlag(AccessFlag.USAGE_METHOD);
        nameIndex = new U2();
        descriptorIndex = new U2();
        attributes = new AttributeInfos(classBean, COMPONENT_METHOD_ATTRIBUTES);
    }

    @Override
    public void read(DataInputStream dis) {
        accessFlag.read(dis);
        nameIndex.read(dis);
        descriptorIndex.read(dis);
        attributes.readMultiple(dis);

        post();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s%s %s %s()\n", getLevelString(), accessFlag.getKeyWordString(), descriptor.getDescriptor(), name.referenceString()));
        stringBuilder.append(String.format("%sdescriptor: %s\n", ClassFileComponent.getPrintLevelString(getLevel()+1), descriptor.referenceString()));
        stringBuilder.append(String.format("%s%s\n", ClassFileComponent.getPrintLevelString(getLevel()+1), accessFlag));
        stringBuilder.append(attributes);
        return Utils.format(stringBuilder);
    }

    @Override
    public void post() {
        name = (ConstantUtf8Info) classBean.get((int) (nameIndex.get()-1));
        descriptor = (ConstantUtf8Info) classBean.get((int) (descriptorIndex.get()-1));
    }
}
