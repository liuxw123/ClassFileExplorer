package com.lxw.main;

import com.lxw.main.access.AccessFlag;
import com.lxw.main.attributes.AttributeInfos;
import com.lxw.main.common.inter.ReaderInterface;
import com.lxw.main.common.number.U2;
import com.lxw.main.common.number.U4;
import com.lxw.main.cp.ConstantClassInfo;
import com.lxw.main.cp.CpInfo;
import com.lxw.main.cp.CpInfos;
import com.lxw.main.fields.FieldInfos;
import com.lxw.main.interfaces.Interfaces;
import com.lxw.main.magic.Magic;
import com.lxw.main.methods.MethodInfos;
import com.lxw.main.version.Version;

import java.io.DataInputStream;
import java.io.IOException;

import static com.lxw.main.access.AccessFlag.USAGE_CLASS;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: ClassBean.java
 * @Date: 2021-06-10 23:35
 * @Version: V0.0
 */


public class ClassBean extends ClassFileComponent implements ReaderInterface {
    private boolean isValid;

    private Magic magic;
    Version version;

    private CpInfos cpInfos;

    private AccessFlag accessFlag;
    private U2 thisClassIndex;
    private U2 superClassIndex;

    private ConstantClassInfo thisClass;
    private ConstantClassInfo superClass;

    private Interfaces interfaces;
    private FieldInfos fields;
    private MethodInfos methods;
    private AttributeInfos attributes;

    public ClassBean() {
        super(COMPONENT_CLASS_INFO);
        magic = new Magic();
        version = new Version(1);
        cpInfos = new CpInfos();

        accessFlag = new AccessFlag(USAGE_CLASS);
        thisClassIndex = new U2();
        superClassIndex = new U2();

        interfaces = new Interfaces(this);
        fields = new FieldInfos(this);
        methods = new MethodInfos(this);
        attributes = new AttributeInfos(this, COMPONENT_CLASS_ATTRIBUTES);
    }

    @Override
    public void read(DataInputStream dis) {
        magic.read(dis);
        version.read(dis);
        cpInfos.readMultiple(dis);
        accessFlag.read(dis);
        thisClassIndex.read(dis);
        superClassIndex.read(dis);
        thisClass = (ConstantClassInfo) cpInfos.get((int) (thisClassIndex.get()-1));
        superClass = (ConstantClassInfo) cpInfos.get((int) (superClassIndex.get()-1));

        interfaces.readMultiple(dis);
        fields.readMultiple(dis);
        methods.readMultiple(dis);
        attributes.readMultiple(dis);

        isValid = finish(dis);
    }

    private boolean finish(DataInputStream dis) {
        try {
            dis.readByte();
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    public CpInfo get(int index) {
        if (index >= cpInfos.size()) return null;
        return cpInfos.get(index);
    }

    public boolean isValid() {
        return isValid;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("%s%s\n", getLevelString(), magic));
        stringBuilder.append(String.format("%sversion: %s\n", getLevelString(), version));
        stringBuilder.append(String.format("%s%s\n", getLevelString(), accessFlag));
        stringBuilder.append(String.format("%sthis class: #%d // %s\n", getLevelString(), thisClassIndex.get(), thisClass.referenceString()));
        stringBuilder.append(String.format("%ssuper class: #%d // %s\n", getLevelString(), superClassIndex.get(), superClass.referenceString()));
        stringBuilder.append(String.format("%sinterfaces: %d, fields: %d, methods: %d, attributes: %d\n", getLevelString(), interfaces.size(), fields.size(), methods.size(), attributes.size()));
        stringBuilder.append(String.format("%s\n", cpInfos));
        stringBuilder.append("{\n");
        stringBuilder.append(String.format("%s\n", fields));
        stringBuilder.append(String.format("%s\n", methods));
        stringBuilder.append("}\n");
        stringBuilder.append(String.format("%s\n", attributes));

        return stringBuilder.toString();
    }
}
