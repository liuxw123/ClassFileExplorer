package com.lxw.main.access;

import com.lxw.main.ClassFileComponent;
import com.lxw.main.common.inter.ReaderInterface;
import com.lxw.main.common.number.U2;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: AccessFlag.java
 * @Date: 2021-06-12 22:11
 * @Version: V0.0
 */


public class AccessFlag implements ReaderInterface {

    private final static int ACC_PUBLIC = 0x0001;
    private final static int ACC_PRIVATE = 0x0002;
    private final static int ACC_PROTECTED = 0x0004;
    private final static int ACC_STATIC = 0x0008;
    private final static int ACC_FINAL = 0x0010;
    private final static int ACC_SUPER = 0x0020;
    private final static int ACC_SYNCHRONIZED = 0x0020;
    private final static int ACC_VOLATILE = 0x0040;
    private final static int ACC_BRIDGE = 0x0040;
    private final static int ACC_TRANSIENT = 0x0080;
    private final static int ACC_VARARGS = 0x0080;
    private final static int ACC_NATIVE = 0x0100;
    private final static int ACC_INTERFACE = 0x0200;
    private final static int ACC_ABSTRACT = 0x0400;
    private final static int ACC_STRICT = 0x0800;
    private final static int ACC_SYNTHETIC = 0x1000;
    private final static int ACC_ANNOTATION = 0x2000;
    private final static int ACC_ENUM = 0x4000;
    private final static int ACC_MODULE = 0x8000;


    public final static int USAGE_INNER_CLASS = 1;
    public final static int USAGE_METHOD = 2;
    public final static int USAGE_FIELD = 3;
    public final static int USAGE_CLASS = 4;
    public final static int USAGE_INTERFACE = 4;

    private static int maskClass = ACC_PUBLIC | ACC_FINAL | ACC_SUPER | ACC_INTERFACE | ACC_ABSTRACT |
                                    ACC_SYNTHETIC | ACC_ANNOTATION | ACC_ENUM | ACC_MODULE;
    private static int maskField = ACC_PUBLIC | ACC_PRIVATE | ACC_PROTECTED | ACC_STATIC | ACC_FINAL |
                                    ACC_VOLATILE | ACC_TRANSIENT | ACC_SYNTHETIC | ACC_ENUM;
    private static int maskMethod = ACC_PUBLIC | ACC_PRIVATE | ACC_PROTECTED | ACC_STATIC | ACC_FINAL | ACC_SYNCHRONIZED |
                                    ACC_BRIDGE | ACC_VARARGS | ACC_NATIVE | ACC_ABSTRACT | ACC_STRICT | ACC_SYNTHETIC;
    private static int maskInnerClass = ACC_PUBLIC | ACC_PRIVATE | ACC_PROTECTED | ACC_STATIC | ACC_FINAL |
                                    ACC_INTERFACE | ACC_ABSTRACT | ACC_SYNTHETIC | ACC_ANNOTATION | ACC_ENUM;

    private int usage;
    private int mask;
    private int accessFlags;
    private U2 access;

    public static int getUsage(int usage) {
        switch (usage) {
            case USAGE_INNER_CLASS: return USAGE_INNER_CLASS;
            case USAGE_METHOD: return USAGE_METHOD;
            case USAGE_FIELD: return USAGE_FIELD;
            case USAGE_CLASS: return USAGE_CLASS;
            default: throw new IllegalArgumentException();
        }
    }

    public static void checkFlags(int usage, int flags) {
        usage = getUsage(usage);
        int mask = getMask(usage);
        int index = 0x0001;

        for (int i = 0; i < 16; i++) {
            if ((index & mask) == 0 && (index & flags) != 0) throw new IllegalArgumentException("Access Flags Illegal.");
            index = index << 1;
        }
    }

    public static int getMask(int usage) {
        usage = getUsage(usage);

        switch (usage) {
            case USAGE_INTERFACE: return maskClass;
            case USAGE_METHOD: return maskMethod;
            case USAGE_FIELD: return maskField;
            case USAGE_INNER_CLASS: return maskInnerClass;
        }

        return 0;
    }

    public String getMaskStringForClass() {
        StringBuilder stringBuilder = new StringBuilder();

        if ((accessFlags & ACC_PUBLIC) != 0) stringBuilder.append(" ACC_PUBLIC");
        if ((accessFlags & ACC_FINAL) != 0) stringBuilder.append(" ACC_FINAL");
        if ((accessFlags & ACC_SUPER) != 0) stringBuilder.append(" ACC_SUPER");
        if ((accessFlags & ACC_INTERFACE) != 0) stringBuilder.append(" ACC_INTERFACE");
        if ((accessFlags & ACC_ABSTRACT) != 0) stringBuilder.append(" ACC_ABSTRACT");
        if ((accessFlags & ACC_SYNTHETIC) != 0) stringBuilder.append(" ACC_SYNTHETIC");
        if ((accessFlags & ACC_ANNOTATION) != 0) stringBuilder.append(" ACC_ANNOTATION");
        if ((accessFlags & ACC_ENUM) != 0) stringBuilder.append(" ACC_ENUM");
        if ((accessFlags & ACC_MODULE) != 0) stringBuilder.append(" ACC_MODULE");

        return stringBuilder.toString();
    }

    public String getKeyWordForClass() {
        StringBuilder stringBuilder = new StringBuilder();

        if ((accessFlags & ACC_PUBLIC) != 0) stringBuilder.append("public ");
        if ((accessFlags & ACC_FINAL) != 0) stringBuilder.append("final ");
        // if ((accessFlags & ACC_SUPER) != 0) stringBuilder.append("");
        if ((accessFlags & ACC_INTERFACE) != 0) stringBuilder.append("interface ");
        else stringBuilder.append("class ");
        if ((accessFlags & ACC_INTERFACE) == 0)
            if ((accessFlags & ACC_ABSTRACT) != 0) stringBuilder.append("abstract ");
        // if ((accessFlags & ACC_SYNTHETIC) != 0) stringBuilder.append("");
        // if ((accessFlags & ACC_ANNOTATION) != 0) stringBuilder.append("");
        if ((accessFlags & ACC_ENUM) != 0) stringBuilder.append("enum ");
        // if ((accessFlags & ACC_MODULE) != 0) stringBuilder.append("");

        if (stringBuilder.length() > 0) stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    public String getMaskStringForInnerClass() {
        StringBuilder stringBuilder = new StringBuilder();

        if ((accessFlags & ACC_PUBLIC) != 0) stringBuilder.append(" ACC_PUBLIC");
        if ((accessFlags & ACC_PRIVATE) != 0) stringBuilder.append(" ACC_PRIVATE");
        if ((accessFlags & ACC_PROTECTED) != 0) stringBuilder.append(" ACC_PROTECTED");
        if ((accessFlags & ACC_STATIC) != 0) stringBuilder.append(" ACC_STATIC");
        if ((accessFlags & ACC_FINAL) != 0) stringBuilder.append(" ACC_FINAL");
        if ((accessFlags & ACC_INTERFACE) != 0) stringBuilder.append(" ACC_INTERFACE");
        if ((accessFlags & ACC_ABSTRACT) != 0) stringBuilder.append(" ACC_ABSTRACT");
        if ((accessFlags & ACC_SYNTHETIC) != 0) stringBuilder.append(" ACC_SYNTHETIC");
        if ((accessFlags & ACC_ANNOTATION) != 0) stringBuilder.append(" ACC_ANNOTATION");
        if ((accessFlags & ACC_ENUM) != 0) stringBuilder.append(" ACC_ENUM");

        return stringBuilder.toString();
    }

    public String getKeyWordForInnerClass() {
        StringBuilder stringBuilder = new StringBuilder();

        if ((accessFlags & ACC_PUBLIC) != 0) stringBuilder.append("public ");
        if ((accessFlags & ACC_PRIVATE) != 0) stringBuilder.append("private ");
        if ((accessFlags & ACC_PROTECTED) != 0) stringBuilder.append("protected ");
        if ((accessFlags & ACC_STATIC) != 0) stringBuilder.append("static ");
        if ((accessFlags & ACC_FINAL) != 0) stringBuilder.append("final ");
        if ((accessFlags & ACC_INTERFACE) != 0) stringBuilder.append("interface ");
        if ((accessFlags & ACC_INTERFACE) == 0)
            if ((accessFlags & ACC_ABSTRACT) != 0) stringBuilder.append("abstract ");
        // if ((accessFlags & ACC_SYNTHETIC) != 0) stringBuilder.append("");
        // if ((accessFlags & ACC_ANNOTATION) != 0) stringBuilder.append("");
        if ((accessFlags & ACC_ENUM) != 0) stringBuilder.append("enum ");

        if (stringBuilder.length() > 0) stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    public String getMaskStringForField() {
        StringBuilder stringBuilder = new StringBuilder();

        if ((accessFlags & ACC_PUBLIC) != 0) stringBuilder.append(" ACC_PUBLIC");
        if ((accessFlags & ACC_PRIVATE) != 0) stringBuilder.append(" ACC_PRIVATE");
        if ((accessFlags & ACC_PROTECTED) != 0) stringBuilder.append(" ACC_PROTECTED");
        if ((accessFlags & ACC_STATIC) != 0) stringBuilder.append(" ACC_STATIC");
        if ((accessFlags & ACC_FINAL) != 0) stringBuilder.append(" ACC_FINAL");
        if ((accessFlags & ACC_VOLATILE) != 0) stringBuilder.append(" ACC_VOLATILE");
        if ((accessFlags & ACC_TRANSIENT) != 0) stringBuilder.append(" ACC_TRANSIENT");
        if ((accessFlags & ACC_SYNTHETIC) != 0) stringBuilder.append(" ACC_SYNTHETIC");
        if ((accessFlags & ACC_ENUM) != 0) stringBuilder.append(" ACC_ENUM");

        return stringBuilder.toString();
    }

    public String getKeyWordForField() {
        StringBuilder stringBuilder = new StringBuilder();

        if ((accessFlags & ACC_PUBLIC) != 0) stringBuilder.append("public ");
        if ((accessFlags & ACC_PRIVATE) != 0) stringBuilder.append("private ");
        if ((accessFlags & ACC_PROTECTED) != 0) stringBuilder.append("protected ");
        if ((accessFlags & ACC_STATIC) != 0) stringBuilder.append("static ");
        if ((accessFlags & ACC_FINAL) != 0) stringBuilder.append("final ");
        if ((accessFlags & ACC_VOLATILE) != 0) stringBuilder.append("volatile ");
        if ((accessFlags & ACC_TRANSIENT) != 0) stringBuilder.append("transient ");
        // if ((accessFlags & ACC_SYNTHETIC) != 0) stringBuilder.append("");
        // if ((accessFlags & ACC_ENUM) != 0) stringBuilder.append(" ACC_ENUM");

        if (stringBuilder.length() > 0) stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    public String getMaskStringForMethod() {
        StringBuilder stringBuilder = new StringBuilder();

        if ((accessFlags & ACC_PUBLIC) != 0) stringBuilder.append(" ACC_PUBLIC");
        if ((accessFlags & ACC_PRIVATE) != 0) stringBuilder.append(" ACC_PRIVATE");
        if ((accessFlags & ACC_PROTECTED) != 0) stringBuilder.append(" ACC_PROTECTED");
        if ((accessFlags & ACC_STATIC) != 0) stringBuilder.append(" ACC_STATIC");
        if ((accessFlags & ACC_FINAL) != 0) stringBuilder.append(" ACC_FINAL");
        if ((accessFlags & ACC_SYNCHRONIZED) != 0) stringBuilder.append(" ACC_SYNCHRONIZED");
        if ((accessFlags & ACC_BRIDGE) != 0) stringBuilder.append(" ACC_BRIDGE");
        if ((accessFlags & ACC_VARARGS) != 0) stringBuilder.append(" ACC_VARARGS");
        if ((accessFlags & ACC_NATIVE) != 0) stringBuilder.append(" ACC_NATIVE");
        if ((accessFlags & ACC_ABSTRACT) != 0) stringBuilder.append(" ACC_ABSTRACT");
        if ((accessFlags & ACC_STRICT) != 0) stringBuilder.append(" ACC_STRICT");
        if ((accessFlags & ACC_SYNTHETIC) != 0) stringBuilder.append(" ACC_SYNTHETIC");

        return stringBuilder.toString();
    }

    public String getKeyWordForMethod() {
        StringBuilder stringBuilder = new StringBuilder();

        if ((accessFlags & ACC_PUBLIC) != 0) stringBuilder.append("public ");
        if ((accessFlags & ACC_PRIVATE) != 0) stringBuilder.append("private ");
        if ((accessFlags & ACC_PROTECTED) != 0) stringBuilder.append("protected ");
        if ((accessFlags & ACC_STATIC) != 0) stringBuilder.append("static ");
        if ((accessFlags & ACC_FINAL) != 0) stringBuilder.append("final ");
        if ((accessFlags & ACC_SYNCHRONIZED) != 0) stringBuilder.append("synchronized ");
        // if ((accessFlags & ACC_BRIDGE) != 0) stringBuilder.append("");
        // if ((accessFlags & ACC_VARARGS) != 0) stringBuilder.append(" ACC_VARARGS");
        if ((accessFlags & ACC_NATIVE) != 0) stringBuilder.append("native ");
        if ((accessFlags & ACC_ABSTRACT) != 0) stringBuilder.append("abstract ");
        // if ((accessFlags & ACC_STRICT) != 0) stringBuilder.append("");
        // if ((accessFlags & ACC_SYNTHETIC) != 0) stringBuilder.append("");

        if (stringBuilder.length() > 0) stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    public String getMaskString() {
        switch (usage) {
            case USAGE_CLASS: return getMaskStringForClass();
            case USAGE_INNER_CLASS: return getMaskStringForInnerClass();
            case USAGE_FIELD: return getMaskStringForField();
            case USAGE_METHOD: return getMaskStringForMethod();
            default: return "";
        }
    }

    public String getKeyWordString() {
        switch (usage) {
            case USAGE_CLASS: return getKeyWordForClass();
            case USAGE_INNER_CLASS: return getKeyWordForInnerClass();
            case USAGE_FIELD: return getKeyWordForField();
            case USAGE_METHOD: return getKeyWordForMethod();
            default: return "";
        }
    }

    public int mask(int usage) {
        return getMask(usage);
    }

    public AccessFlag(int usage) {
        access = new U2();
        this.usage = getUsage(usage);
        mask = mask(this.usage);
    }

    @Override
    public void read(DataInputStream dis) {
        access.read(dis);
        checkFlags(this.usage, (int) access.get());
        this.accessFlags = (int) access.get();
    }

    @Override
    public String toString() {
        return String.format("flags: (0x%04X) %s", accessFlags, getMaskString());
    }
}
