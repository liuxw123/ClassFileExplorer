package com.lxw.main.attributes;

import com.lxw.main.ClassBean;
import com.lxw.main.ClassFileComponent;
import com.lxw.main.common.inter.ReaderInterface;
import com.lxw.main.common.number.U2;
import com.lxw.main.common.number.U4;
import com.lxw.main.cp.CpInfo;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: attributes.java
 * @Date: 2021-06-11 01:00
 * @Version: V0.0
 */


public abstract class AttributeInfo extends ClassFileComponent implements ReaderInterface {

    public final static String CODE = "Code";
    public final static String CONSTANT_VALUE = "ConstantValue";
    public final static String DEPRECATED = "Deprecated";
    public final static String EXCEPTIONS = "Exceptions";
    public final static String ENCLOSING_METHOD = "EnclosingMethod";
    public final static String INNER_CLASSES = "InnerClasses";
    public final static String LINE_NUMBER_TABLE = "LineNumberTable";
    public final static String LOCAL_VARIABLE_TABLE = "LocalVariableTable";
    public final static String STACK_MAP_TABLE = "StackMapTable";
    public final static String SIGNATURE = "Signature";
    public final static String SOURCE_FILE = "SourceFile";
    public final static String SOURCE_DEBUG_EXTENSION = "SourceDebugExtension";
    public final static String SYNTHETIC = "Synthetic";
    public final static String LOCAL_VARIABLE_TYPE_TABLE = "LocalVariableTypeTable";
    public final static String RUNTIME_VISIBLE_ANNOTATIONS = "RuntimeVisibleAnnotations";
    public final static String RUNTIME_INVISIBLE_ANNOTATIONS = "RuntimeInvisibleAnnotations";
    public final static String RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS = "RuntimeVisibleParameterAnnotations";
    public final static String RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS = "RuntimeInvisibleParameterAnnotations";
    public final static String ANNOTATION_DEFAULT = "AnnotationDefault";
    public final static String BOOTSTRAP_METHODS = "BootstrapMethods";
    public final static String RUNTIME_VISIBLE_TYPE_ANNOTATIONS = "RuntimeVisibleTypeAnnotations";
    public final static String RUNTIME_INVISIBLE_TYPE_ANNOTATIONS = "RuntimeInvisibleTypeAnnotations";
    public final static String METHOD_PARAMETERS = "MethodParameters";
    public final static String MODULE = "Module";
    public final static String MODULE_PACKAGES = "ModulePackages";
    public final static String MODULE_MAIN_CLASS = "ModuleMainClass";
    public final static String NEST_HOST = "NestHost";
    public final static String NEST_MEMBERS = "NestMembers";

    private int level;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    protected String tag;
    protected U2 attributeNameIndex;

    public int getAttributeLength() {
        return (int) attributeLength.get();
    }

    protected U4 attributeLength;
    protected ClassBean classBean;

    public AttributeInfo(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(component);
        this.tag = tag;
        this.attributeNameIndex = attributeNameIndex;
        this.classBean = classBean;
        this.attributeLength = new U4();

    }

    public CpInfo get(int index) {
        return classBean.get(index);
    }

    @Override
    public void read(DataInputStream dis) {
        attributeLength.read(dis);
    }
}
