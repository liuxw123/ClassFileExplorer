package com.lxw.main.attributes;

import com.lxw.main.ClassBean;
import com.lxw.main.common.inter.MultipleItemsInterface;
import com.lxw.main.common.inter.ReaderInterface;
import com.lxw.main.common.number.U2;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: AttributeRuntimeVisibleAnnotations.java
 * @Date: 2021-06-12 16:51
 * @Version: V0.0
 */


public class AttributeRuntimeVisibleAnnotations extends AttributeInfo{
    public AttributeRuntimeVisibleAnnotations(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(tag, attributeNameIndex, classBean, component);
    }

    @Override
    public void read(DataInputStream dis) {
        super.read(dis);
    }

    class Annotations implements MultipleItemsInterface {

        private U2 numAnnotations;
        private Annotation[] annotations;

        public Annotations() {
            numAnnotations = new U2();
        }

        @Override
        public void readMultiple(DataInputStream dis) {
            numAnnotations.read(dis);
            int count = (int) numAnnotations.get();
            annotations = new Annotation[count];
            for (int i = 0; i < count; i++) {
                annotations[i] = new Annotation();
                annotations[i].read(dis);
            }
        }

        @Override
        public Annotation get(int index) {
            if (index >= size()) return null;
            else return annotations[index];
        }

        @Override
        public int size() {
            return (annotations == null)? -1: annotations.length;
        }
    }

    class Annotation implements ReaderInterface {

        @Override
        public void read(DataInputStream dis) {
            throw new IllegalCallerException("待实现");
        }
    }
}
