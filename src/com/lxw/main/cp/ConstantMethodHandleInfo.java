package com.lxw.main.cp;

import com.lxw.main.common.number.U1;
import com.lxw.main.common.number.U2;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: ConstantMethodHandleInfo.java
 * @Date: 2021-06-12 15:02
 * @Version: V0.0
 */


public class ConstantMethodHandleInfo extends CpInfo{

    private static final int REF_GET_FIELD = 1;
    private static final int REF_GET_STATIC = 2;
    private static final int REF_PUT_FIELD = 3;
    private static final int REF_PUT_STATIC = 4;
    private static final int REF_INVOKE_VIRTUAL = 5;
    private static final int REF_INVOKE_STATIC = 6;
    private static final int REF_INVOKE_SPECIAL = 7;
    private static final int REF_NEW_INVOKE_SPECIAL = 8;
    private static final int REF_INVOKE_INTERFEACE = 9;

    public static int getKind(int kind) {
        switch (kind) {
            case REF_GET_FIELD: return REF_GET_FIELD;
            case REF_GET_STATIC: return REF_GET_STATIC;
            case REF_PUT_FIELD: return REF_PUT_FIELD;
            case REF_PUT_STATIC: return REF_PUT_STATIC;
            case REF_INVOKE_VIRTUAL: return REF_INVOKE_VIRTUAL;
            case REF_INVOKE_STATIC: return REF_INVOKE_STATIC;
            case REF_INVOKE_SPECIAL: return REF_NEW_INVOKE_SPECIAL;
            case REF_INVOKE_INTERFEACE: return REF_INVOKE_INTERFEACE;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static String getKindString(int kind) {
        kind = getKind(kind);

        switch (kind) {
            case REF_GET_FIELD: return "REF_getField";
            case REF_GET_STATIC: return "REF_getStatic";
            case REF_PUT_FIELD: return "REF_putField";
            case REF_PUT_STATIC: return "REF_putStatic";
            case REF_INVOKE_VIRTUAL: return "REF_invokeVirtual";
            case REF_INVOKE_STATIC: return "REF_invokeStatic";
            case REF_INVOKE_SPECIAL: return "REF_invokeSpecial";
            case REF_INVOKE_INTERFEACE: return "REF_invokeInterface";
            default:
                throw new IllegalArgumentException();
        }
    }

    private U1 referenceKind;
    private int kind;
    private U2 referenceIndex;
    private CpInfo reference;

    public ConstantMethodHandleInfo(int tag) {
        super(tag);
        referenceKind = new U1();
        referenceIndex = new U2();
    }

    @Override
    public void read(DataInputStream dis) {
        referenceKind.read(dis);
        referenceIndex.read(dis);
    }

    @Override
    public void postHandle(CpInfos cpInfos) {
        kind = getKind((int) referenceKind.get());
        reference = cpInfos.get((int) (referenceIndex.get()-1));
    }

    @Override
    public String toString() {
        return String.format("%s #%d.#%d // %s", getTag(), referenceKind.get(), referenceIndex.get(), referenceString());
    }

    @Override
    public String referenceString() {
        return String.format("%s %s", getKindString(kind), reference.referenceString());
    }
}
