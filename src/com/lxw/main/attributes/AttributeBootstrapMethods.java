package com.lxw.main.attributes;

import com.lxw.main.ClassBean;
import com.lxw.main.common.Utils;
import com.lxw.main.common.inter.MultipleItemsInterface;
import com.lxw.main.common.inter.PostOperation;
import com.lxw.main.common.inter.ReaderInterface;
import com.lxw.main.common.number.U2;
import com.lxw.main.cp.ConstantMethodHandleInfo;
import com.lxw.main.cp.CpInfo;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: AttributeBootstrapMethods.java
 * @Date: 2021-06-12 15:09
 * @Version: V0.0
 */


public class AttributeBootstrapMethods extends AttributeInfo{

    private BootstrapMethods bootstrapMethods;
    public AttributeBootstrapMethods(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(tag, attributeNameIndex, classBean, component);
        bootstrapMethods = new BootstrapMethods();
    }

    @Override
    public void read(DataInputStream dis) {
        super.read(dis);
        bootstrapMethods.readMultiple(dis);

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getLevelString()+getTag()+":\n");
        stringBuilder.append(bootstrapMethods);
        return Utils.format(stringBuilder);
    }

    class BootstrapMethods implements MultipleItemsInterface {
        private U2 numBootstrapMethods;
        private BootstrapMethod[] bootstrapMethods;

        public BootstrapMethods() {
            numBootstrapMethods = new U2();
        }

        @Override
        public void readMultiple(DataInputStream dis) {
            numBootstrapMethods.read(dis);
            int count = (int) numBootstrapMethods.get();
            bootstrapMethods = new BootstrapMethod[count];

            for (int i = 0; i < count; i++) {
                bootstrapMethods[i] = new BootstrapMethod();
                bootstrapMethods[i].read(dis);
            }
        }

        @Override
        public BootstrapMethod get(int index) {
            if (index >= size()) return null;
            return bootstrapMethods[index];
        }

        @Override
        public int size() {
            return (bootstrapMethods == null)?-1:bootstrapMethods.length;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < bootstrapMethods.length; i++) {
                stringBuilder.append(String.format("%s%d: %s\n", getPrintLevelString(getLevel()+1), i, bootstrapMethods[i]));
            }
            return stringBuilder.toString();
        }
    }

    class BootstrapMethod implements ReaderInterface, PostOperation {

        private U2 bootstrapMethodRef;
        private ConstantMethodHandleInfo bootstrapMethod;
        private U2 numBootstrapArguments;
        private U2[] bootstrapArgumentIndexes;
        private CpInfo[] bootstrapArguments;

        public BootstrapMethod() {
            bootstrapMethodRef = new U2();
            numBootstrapArguments = new U2();
        }

        @Override
        public void read(DataInputStream dis) {
            bootstrapMethodRef.read(dis);
            numBootstrapArguments.read(dis);
            int count = (int) numBootstrapArguments.get();
            bootstrapArgumentIndexes = new U2[count];
            bootstrapArguments = new CpInfo[count];
            for (int i = 0; i < count; i++) {
                bootstrapArgumentIndexes[i] = new U2();
                bootstrapArgumentIndexes[i].read(dis);
            }
            post();
        }

        @Override
        public void post() {
            for (int i = 0; i < bootstrapArgumentIndexes.length; i++) {
                bootstrapArguments[i] = get((int) (bootstrapArgumentIndexes[i].get()-1));
            }

            bootstrapMethod = (ConstantMethodHandleInfo) get((int) (bootstrapMethodRef.get()-1));
        }

        @Override
        public String toString() {
            String blank = getPrintLevelString(getLevel()+2);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("#%d %s\n", bootstrapMethodRef.get(), bootstrapMethod.referenceString()));
            stringBuilder.append(getPrintLevelString(getLevel()+2)+"Method arguments:\n");
            for (int i = 0; i < bootstrapArguments.length; i++) {
                stringBuilder.append(String.format("%s#%d %s\n", getPrintLevelString(getLevel()+3), bootstrapArgumentIndexes[i].get(), bootstrapArguments[i].referenceString()));
            }
            if (stringBuilder.length()>0) stringBuilder.deleteCharAt(stringBuilder.length()-1);
            return stringBuilder.toString();
        }
    }
}
