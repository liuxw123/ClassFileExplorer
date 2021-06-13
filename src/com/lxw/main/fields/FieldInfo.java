package com.lxw.main.fields;

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

import static com.lxw.main.ClassFileComponent.COMPONENT_FIELD_ATTRIBUTES;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: FieldInfo.java
 * @Date: 2021-06-11 00:59
 * @Version: V0.0
 */


public class FieldInfo extends ClassFileComponent implements ReaderInterface, PostOperation {

    private AccessFlag accessFlag;
    private U2 nameIndex;
    private U2 descriptorIndex;
    private AttributeInfos attributes;

    private ConstantUtf8Info name;
    private ConstantUtf8Info descriptor;

    private ClassBean classBean;

    public FieldInfo(ClassBean classBean) {
        super(COMPONENT_FIELD);
        this.classBean = classBean;
        accessFlag = new AccessFlag(AccessFlag.USAGE_FIELD);
        nameIndex = new U2();
        descriptorIndex = new U2();
        attributes = new AttributeInfos(classBean, COMPONENT_FIELD_ATTRIBUTES);
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
        stringBuilder.append(String.format("%s%s %s %s\n", getLevelString(), accessFlag.getKeyWordString(), descriptor.getDescriptor(), name.referenceString()));
        stringBuilder.append(String.format("%sdescriptor: %s\n", getPrintLevelString(getLevel()+1), descriptor.referenceString()));
        stringBuilder.append(String.format("%s%s\n", getPrintLevelString(getLevel()+1), accessFlag));
        return Utils.format(stringBuilder);
    }

    @Override
    public void post() {
        name = (ConstantUtf8Info) classBean.get((int) (nameIndex.get()-1));
        descriptor = (ConstantUtf8Info) classBean.get((int) (descriptorIndex.get()-1));
    }
}
