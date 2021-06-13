package com.lxw.main.cp;

import com.lxw.main.ClassFileComponent;
import com.lxw.main.attributes.AttributeInfo;
import com.lxw.main.common.inter.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: CpInfo.java
 * @Date: 2021-06-11 00:54
 * @Version: V0.0
 */


public abstract class CpInfo extends ClassFileComponent implements ReaderInterface, AttributeString, ContentString, ReferenceString, ConstantSkipNext {
    public static final int CONSTANT_UTF8                   = 1;
    public static final int CONSTANT_INTEGER                = 3;
    public static final int CONSTANT_FLOAT                  = 4;
    public static final int CONSTANT_LONG                   = 5;
    public static final int CONSTANT_DOUBLE                 = 6;
    public static final int CONSTANT_CLASS                  = 7;
    public static final int CONSTANT_STRING                 = 8;
    public static final int CONSTANT_FIELDREF               = 9;
    public static final int CONSTANT_METHODREF              = 10;
    public static final int CONSTANT_INTERFACE_METHODREF    = 11;
    public static final int CONSTANT_NAME_AND_TYPE          = 12;
    public static final int CONSTANT_METHOD_HANDLE          = 15;
    public static final int CONSTANT_METHOD_TYPE            = 16;
    public static final int CONSTANT_DYNAMIC                = 17;
    public static final int CONSTANT_INVOKE_DYNAMIC         = 18;
    public static final int CONSTANT_MODULE                 = 19;
    public static final int CONSTANT_PACKAGE                = 20;

    protected int tag;

    public CpInfo(int tag) {
        super(COMPONENT_CONSTANT_POOL);
        this.tag = tag;
    }

    public abstract void postHandle(CpInfos cpInfos);

    public String getTag() {
        return getTag(tag);
    }

    public static String getTag(int tag) {
        switch (tag) {
            case CONSTANT_UTF8: return "Utf8";
            case CONSTANT_INTEGER: return "Integer";
            case CONSTANT_FLOAT: return "Float";
            case CONSTANT_LONG: return "Long";
            case CONSTANT_DOUBLE: return "Double";
            case CONSTANT_CLASS: return "Class";
            case CONSTANT_STRING: return "String";
            case CONSTANT_FIELDREF: return "Fieldref";
            case CONSTANT_METHODREF: return "Methodref";
            case CONSTANT_INTERFACE_METHODREF: return "InterfaceMethodref";
            case CONSTANT_NAME_AND_TYPE: return "NameAndType";
            case CONSTANT_METHOD_HANDLE: return "MethodHandle";
            case CONSTANT_METHOD_TYPE: return "MethodType";
            case CONSTANT_DYNAMIC: return "Dynamic";
            case CONSTANT_INVOKE_DYNAMIC: return "InvokeDynamic";
            case CONSTANT_MODULE: return "Module";
            case CONSTANT_PACKAGE: return "Package";
            default: new IllegalArgumentException();
        }

        return "Error!!!";
    }

    public static CpInfo get(int tag) {
        CpInfo cpInfo;
        switch (tag) {
            case CONSTANT_UTF8:
                cpInfo = new ConstantUtf8Info(CpInfo.CONSTANT_UTF8);
                break;
            case CONSTANT_INTEGER:
                cpInfo = new ConstantIntegerInfo(CpInfo.CONSTANT_INTEGER);
                break;
            case CONSTANT_FLOAT:
                cpInfo = new ConstantFloatInfo(CpInfo.CONSTANT_FLOAT);
                break;
            case CONSTANT_LONG:
                cpInfo = new ConstantLongInfo(CpInfo.CONSTANT_LONG);
                break;
            case CONSTANT_DOUBLE:
                cpInfo = new ConstantDoubleInfo(CpInfo.CONSTANT_DOUBLE);
                break;
            case CONSTANT_CLASS:
                cpInfo = new ConstantClassInfo(CpInfo.CONSTANT_CLASS);
                break;
            case CONSTANT_STRING:
                cpInfo = new ConstantStringInfo(CpInfo.CONSTANT_STRING);
                break;
            case CONSTANT_FIELDREF:
                cpInfo = new ConstantFieldRefInfo(CONSTANT_FIELDREF);
                break;
            case CONSTANT_METHODREF:
                cpInfo = new ConstantMethodRefInfo(CpInfo.CONSTANT_METHODREF);
                break;
            case CONSTANT_INTERFACE_METHODREF:
                cpInfo = new ConstantInterfaceMethodRefInfo(CpInfo.CONSTANT_INTERFACE_METHODREF);
                break;
            case CONSTANT_NAME_AND_TYPE:
                cpInfo = new ConstantNameAndTypeInfo(CpInfo.CONSTANT_NAME_AND_TYPE);
                break;
            case CONSTANT_INVOKE_DYNAMIC:
                cpInfo = new ConstantInvokeDynamicInfo(CpInfo.CONSTANT_INVOKE_DYNAMIC);
                break;
            case CONSTANT_DYNAMIC:
                cpInfo = new ConstantDynamicInfo(CpInfo.CONSTANT_DYNAMIC);
                break;
            case CONSTANT_METHOD_HANDLE:
                cpInfo = new ConstantMethodHandleInfo(CpInfo.CONSTANT_METHOD_HANDLE);
                break;
            case CONSTANT_METHOD_TYPE:
                cpInfo = new ConstantMethodTypeInfo(CpInfo.CONSTANT_METHOD_TYPE);
                break;
            case CONSTANT_MODULE:
                cpInfo = new ConstantModuleInfo(CpInfo.CONSTANT_MODULE);
                break;
            case CONSTANT_PACKAGE:
                cpInfo = new ConstantPackageInfo(CpInfo.CONSTANT_PACKAGE);
                break;
            default: throw new IllegalArgumentException(String.format("请补充Constant: %d %s", tag, getTag(tag)));
        }
        return cpInfo;
    }

    @Override
    public String attributeString() {
        return "";
    }

    @Override
    public String contentString() {
        return "";
    }

    @Override
    public String referenceString() {
        return "";
    }

    @Override
    public boolean skipNext() {
        return false;
    }

    private static Map<String, String> descritorKeyWord;

    static {
        descritorKeyWord = new HashMap<>();
        descritorKeyWord.put("B", "byte");
        descritorKeyWord.put("C", "char");
        descritorKeyWord.put("D", "double");
        descritorKeyWord.put("F", "float");
        descritorKeyWord.put("I", "int");
        descritorKeyWord.put("J", "long");
        descritorKeyWord.put("S", "short");
        descritorKeyWord.put("Z", "boolean");
        descritorKeyWord.put("V", "void");
    }

    public String getDescriptor() {
        if (this instanceof ConstantUtf8Info) {
            if (descritorKeyWord.containsKey(referenceString())) return descritorKeyWord.get(referenceString());
        }
        return referenceString();
    }
}
