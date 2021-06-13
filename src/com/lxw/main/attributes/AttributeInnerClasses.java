package com.lxw.main.attributes;

import com.lxw.main.ClassBean;
import com.lxw.main.access.AccessFlag;
import com.lxw.main.common.Utils;
import com.lxw.main.common.inter.MultipleItemsInterface;
import com.lxw.main.common.inter.PostOperation;
import com.lxw.main.common.inter.ReaderInterface;
import com.lxw.main.common.number.U2;
import com.lxw.main.cp.ConstantClassInfo;
import com.lxw.main.cp.ConstantUtf8Info;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: AttributeInnerClasses.java
 * @Date: 2021-06-12 15:26
 * @Version: V0.0
 */


public class AttributeInnerClasses extends AttributeInfo{
    Classes classes;
    public AttributeInnerClasses(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(tag, attributeNameIndex, classBean, component);
        classes = new Classes();
    }

    @Override
    public void read(DataInputStream dis) {
        super.read(dis);
        classes.readMultiple(dis);
    }

    @Override
    public String toString() {
        return Utils.format(new StringBuilder(""+classes));
    }

    class Classes implements MultipleItemsInterface {
        private U2 numberOfClasses;
        private Clazz[] classes;

        public Classes() {
            numberOfClasses = new U2();
        }

        @Override
        public void readMultiple(DataInputStream dis) {
            numberOfClasses.read(dis);
            int count = (int) numberOfClasses.get();
            classes = new Clazz[count];

            for (int i = 0; i < count; i++) {
                classes[i] = new Clazz();
                classes[i].read(dis);
            }
        }

        @Override
        public Clazz get(int index) {
            if (index >= size()) return null;
            return classes[index];
        }

        @Override
        public int size() {
            return (classes == null)?-1: classes.length;
        }

        @Override
        public String toString() {
            String blank  =getPrintLevelString(getLevel()+1);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getLevelString()+getTag()+":\n");
            for (int i = 0; i < size(); i++) {
                stringBuilder.append(blank+get(i)+"\n");
            }
            return stringBuilder.toString();
        }
    }

    class Clazz implements ReaderInterface, PostOperation {

        private U2 innerClassInfoIndex;
        private U2 outerClassInfoIndex;
        private U2 innerNameIndex;
        private AccessFlag innerClassAccessFlags;

        private ConstantClassInfo innerClassInfo;
        private ConstantClassInfo outerClassInfo;
        private ConstantUtf8Info innerName;

        public Clazz() {
            innerClassInfoIndex = new U2();
            outerClassInfoIndex = new U2();
            innerNameIndex = new U2();
            innerClassAccessFlags = new AccessFlag(AccessFlag.USAGE_INNER_CLASS);
        }

        @Override
        public void read(DataInputStream dis) {
            innerClassInfoIndex.read(dis);
            outerClassInfoIndex.read(dis);
            innerNameIndex.read(dis);
            innerClassAccessFlags.read(dis);

            post();
        }

        @Override
        public void post() {
            innerClassInfo = (ConstantClassInfo) get((int) (innerClassInfoIndex.get()-1));
            outerClassInfo = (outerClassInfoIndex.get() == 0)?null: (ConstantClassInfo) get((int) (outerClassInfoIndex.get()-1));
            innerName = (innerNameIndex.get() == 0)?null: (ConstantUtf8Info) get((int) (innerNameIndex.get()-1));
        }

        @Override
        public String toString() {
            if (innerName == null || outerClassInfo == null) {
                System.out.println();
            }
            return String.format("%s #%d= #%d of #%d // %s= class %s of class %s", innerClassAccessFlags.getKeyWordString(), innerNameIndex.get(), innerClassInfoIndex.get(), outerClassInfoIndex.get(),
                    (innerName == null)?"null":innerName.referenceString(), innerClassInfo.referenceString(), (outerClassInfo == null)?"null":outerClassInfo.referenceString());
        }
    }

}
