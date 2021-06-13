package com.lxw.main.attributes;

import com.lxw.main.ClassBean;
import com.lxw.main.ClassFileComponent;
import com.lxw.main.common.inter.MultipleItemsInterface;
import com.lxw.main.common.inter.ReaderInterface;
import com.lxw.main.common.number.U2;
import com.lxw.main.common.number.U4;
import com.lxw.main.cp.CpInfo;

import java.io.DataInputStream;

import static com.lxw.main.attributes.AttributeInfo.*;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: AttributeInfos.java
 * @Date: 2021-06-11 22:49
 * @Version: V0.0
 */


public class AttributeInfos implements MultipleItemsInterface {

    private ClassBean classBean;

    private U2 attributeCount;
    private AttributeInfo[] attributes;
    private int component;

    public AttributeInfos(ClassBean classBean, int component) {
        this.classBean = classBean;
        attributeCount = new U2();
        this.component = component;
    }

    @Override
    public void readMultiple(DataInputStream dis) {
        attributeCount.read(dis);
        int count = (int) attributeCount.get();
        attributes = new AttributeInfo[count];

        for (int i = 0; i < count; i++) {
            U2 attributeNameIndex = new U2();
            attributeNameIndex.read(dis);

            CpInfo cpInfo = classBean.get((int) (attributeNameIndex.get() - 1));
            String attributeName = cpInfo.attributeString();
            AttributeInfo attribute;
            switch (attributeName) {
                case CODE:
                    attribute = new AttributeCode(attributeName, attributeNameIndex, classBean, component);
                    break;
                case LINE_NUMBER_TABLE:
                    attribute = new AttributeLineNumberTable(attributeName, attributeNameIndex, classBean, component);
                    break;
                case LOCAL_VARIABLE_TABLE:
                    attribute = new AttributeLocalVariableTable(attributeName, attributeNameIndex, classBean, component);
                    break;
                case LOCAL_VARIABLE_TYPE_TABLE:
                    attribute = new AttributeLocalVariableTypeTable(attributeName, attributeNameIndex, classBean, component);
                    break;
                case STACK_MAP_TABLE:
                    attribute = new AttributeStackMapTable(attributeName, attributeNameIndex, classBean, component);
                    break;
                case SOURCE_FILE:
                    attribute = new AttributeSourceFile(attributeName, attributeNameIndex, classBean, component);
                    break;
                case BOOTSTRAP_METHODS:
                    attribute = new AttributeBootstrapMethods(attributeName, attributeNameIndex, classBean, component);
                    break;
                case INNER_CLASSES:
                    attribute = new AttributeInnerClasses(attributeName, attributeNameIndex, classBean, component);
                    break;
                case CONSTANT_VALUE:
                    attribute = new AttributeConstantValue(attributeName, attributeNameIndex, classBean, component);
                    break;
                case NEST_HOST:
                    attribute = new AttributeNestHost(attributeName, attributeNameIndex, classBean, component);
                    break;
                case NEST_MEMBERS:
                    attribute = new AttributeNestMembers(attributeName, attributeNameIndex, classBean, component);
                    break;
                case SIGNATURE:
                    attribute = new AttributeSignature(attributeName, attributeNameIndex, classBean, component);
                    break;
                case DEPRECATED:
                    attribute = new AttributeDeprecated(attributeName, attributeNameIndex, classBean, component);
                    break;
                case EXCEPTIONS:
                    attribute = new AttributeExceptions(attributeName, attributeNameIndex, classBean, component);
                    break;
                case ENCLOSING_METHOD:
                    attribute = new AttributeEnclosingMethod(attributeName, attributeNameIndex, classBean, component);
                    break;
                case SOURCE_DEBUG_EXTENSION:
                    attribute = new AttributeSourceDebugExtension(attributeName, attributeNameIndex, classBean, component);
                    break;
                case SYNTHETIC:
                    attribute = new AttributeSynthetic(attributeName, attributeNameIndex, classBean, component);
                    break;
                case RUNTIME_VISIBLE_ANNOTATIONS:
                    attribute = new AttributeRuntimeVisibleAnnotations(attributeName, attributeNameIndex, classBean, component);
                    break;
                case RUNTIME_INVISIBLE_ANNOTATIONS:
                    attribute = new AttributeRuntimeInvisibleAnnotations(attributeName, attributeNameIndex, classBean, component);
                    break;
                case RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS:
                case RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS:
                case RUNTIME_VISIBLE_TYPE_ANNOTATIONS:
                case RUNTIME_INVISIBLE_TYPE_ANNOTATIONS:
                case METHOD_PARAMETERS:
                case MODULE:
                case MODULE_PACKAGES:
                case MODULE_MAIN_CLASS:
                case ANNOTATION_DEFAULT:
                default:
                    throw new IllegalArgumentException("请补充Attribute: "+attributeName);
            }

            attribute.read(dis);
            attributes[i] = attribute;

        }
    }

    @Override
    public AttributeInfo get(int index) {
        if (index >= size()) return null;
        return attributes[index];
    }

    @Override
    public int size() {
        return (attributes==null)?-1:attributes.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            stringBuilder.append(attributes[i]+"\n");
        }

        return stringBuilder.toString();
    }
}
