package com.lxw.main.attributes;

import com.lxw.main.ClassBean;
import com.lxw.main.common.Utils;
import com.lxw.main.common.inter.MultipleItemsInterface;
import com.lxw.main.common.inter.PostOperation;
import com.lxw.main.common.inter.ReaderInterface;
import com.lxw.main.common.number.U1;
import com.lxw.main.common.number.U2;
import com.lxw.main.common.number.U4;
import com.lxw.main.cp.ConstantClassInfo;
import com.lxw.main.cp.CpInfo;

import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: AttributeLineNumberTable.java
 * @Date: 2021-06-11 23:28
 * @Version: V0.0
 */


public class AttributeCode extends AttributeInfo {

    private U2 maxStack;
    private U2 maxLocals;
    private Codes codes;
    private ExceptionInfos exceptions;
    private AttributeInfos attributes;

    public AttributeCode(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(tag, attributeNameIndex, classBean, component);
        maxStack = new U2();
        maxLocals = new U2();
        codes = new Codes();
        exceptions = new ExceptionInfos();
        attributes = new AttributeInfos(classBean, component);
    }

    @Override
    public void read(DataInputStream dis) {
        super.read(dis);
        maxStack.read(dis);
        maxLocals.read(dis);
        codes.readMultiple(dis);
        exceptions.readMultiple(dis);
        attributes.readMultiple(dis);
    }

    private int getArgSize() {
        for (int i = 0; i < attributes.size(); i++) {
            AttributeInfo attributeInfo = attributes.get(i);
            if (attributeInfo.getTag().equals(LOCAL_VARIABLE_TABLE)) {
                return ((AttributeLocalVariableTable) attributeInfo).getLocalVariableSize();
            } else if (attributeInfo.getTag().equals(LOCAL_VARIABLE_TYPE_TABLE)) {
                return ((AttributeLocalVariableTypeTable) attributeInfo).getLocalVariableSize();
            }
        }

        return 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getLevelString() + tag + ":\n");
        stringBuilder.append(String.format("%sstack=%d, locals=%d, args_size=%d\n", getPrintLevelString(getLevel()+1), maxStack.get(), maxLocals.get(), getArgSize()));
        stringBuilder.append(codes+"\n");
        stringBuilder.append(exceptions + "\n");
        stringBuilder.append(attributes);
        if (attributes.size() > 0) stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return Utils.format(stringBuilder);
    }

    class ExceptionInfos implements MultipleItemsInterface {
        private U2 exceptionTableLength;
        private ExceptionInfo[] exceptionInfos;

        public ExceptionInfos() {
            exceptionTableLength = new U2();
        }

        @Override
        public void readMultiple(DataInputStream dis) {
            exceptionTableLength.read(dis);
            int count = (int) exceptionTableLength.get();
            exceptionInfos = new ExceptionInfo[count];
            for (int i = 0; i < count; i++) {
                exceptionInfos[i] = new ExceptionInfo();
                exceptionInfos[i].read(dis);
            }
        }

        @Override
        public ExceptionInfo get(int index) {
            if (index >= size()) return null;
            return exceptionInfos[index];
        }

        @Override
        public int size() {
            return exceptionInfos == null ? -1 : exceptionInfos.length;
        }

        @Override
        public String toString() {
            if (size() <= 0) return "";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getPrintLevelString(getLevel()+1)+"Exception table:\n");
            stringBuilder.append(getPrintLevelString(getLevel()+2)+" from    to target type\n");
            for (int i = 0; i < size(); i++) {
                stringBuilder.append(getPrintLevelString(getLevel()+2)+get(i)+"\n");
            }
            if (stringBuilder.length() > 0) stringBuilder.deleteCharAt(stringBuilder.length()-1);
            return stringBuilder.toString();
        }
    }


    class ExceptionInfo implements ReaderInterface, PostOperation {
        private U2 startPc;
        private U2 endPc;
        private U2 handlerPc;
        private U2 catchType;

        private ConstantClassInfo exception;

        public ExceptionInfo() {
            startPc = new U2();
            endPc = new U2();
            handlerPc = new U2();
            catchType = new U2();
        }

        @Override
        public void read(DataInputStream dis) {
            startPc.read(dis);
            endPc.read(dis);
            handlerPc.read(dis);
            catchType.read(dis);
            post();
        }

        @Override
        public void post() {
            int type = (int) catchType.get();
            exception = (type == 0) ? null : (ConstantClassInfo) get(type - 1);
        }

        @Override
        public String toString() {
            return String.format("%5d %5d %5d %s", startPc.get(), endPc.get(), handlerPc.get(), (exception==null)?"any":exception.referenceString());
        }
    }

    class Codes implements MultipleItemsInterface {

        private static Map<Byte, String> codeTable;

        private U4 codeLength;
        private U1[] code;
        private String[] codeString;

        public Codes() {
            codeLength = new U4();
        }

        @Override
        public void readMultiple(DataInputStream dis) {
            codeLength.read(dis);
            long count = codeLength.get();
            code = new U1[(int) count];
            codeString = new String[(int) count];
            for (long i = 0; i < count; i++) {
                code[(int) i] = new U1();
                code[(int) i].read(dis);
                codeString[(int) i] = get((int) code[(int) i].get());
            }
        }

        @Override
        public String get(int index) {
            return get((byte) index);
        }

        public String get(byte key) {
            if (codeTable.containsKey(key)) return codeTable.get(key);
            return "not match code.";
        }

        @Override
        public int size() {
            return code.length;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < codeString.length; i++) {
                stringBuilder.append(String.format("%s%4d: %s\n", getPrintLevelString(getLevel()+1), i, codeString[i]));
            }
            if (stringBuilder.length() > 0) stringBuilder.deleteCharAt(stringBuilder.length()-1);
            return stringBuilder.toString();
        }

        static {
            codeTable = new HashMap<>(256);
            codeTable.put((byte) 0x00, "nop");
            codeTable.put((byte) 0x01, "aconst_null");
            codeTable.put((byte) 0x02, "iconst_m1");
            codeTable.put((byte) 0x03, "iconst_0");
            codeTable.put((byte) 0x04, "iconst_1");
            codeTable.put((byte) 0x05, "iconst_2");
            codeTable.put((byte) 0x06, "iconst_3");
            codeTable.put((byte) 0x07, "iconst_4");
            codeTable.put((byte) 0x08, "iconst_5");
            codeTable.put((byte) 0x09, "lconst_0");
            codeTable.put((byte) 0x0A, "lconst_1");
            codeTable.put((byte) 0x0B, "fconst_0");
            codeTable.put((byte) 0x0C, "fconst_1");
            codeTable.put((byte) 0x0D, "fconst_2");
            codeTable.put((byte) 0x0E, "dconst_0");
            codeTable.put((byte) 0x0F, "dconst_1");

            codeTable.put((byte) 0x10, "bipush");
            codeTable.put((byte) 0x11, "sipush");
            codeTable.put((byte) 0x12, "ldc");
            codeTable.put((byte) 0x13, "ldc_w");
            codeTable.put((byte) 0x14, "ldc2_w");
            codeTable.put((byte) 0x15, "iload");
            codeTable.put((byte) 0x16, "lload");
            codeTable.put((byte) 0x17, "fload");
            codeTable.put((byte) 0x18, "dload");
            codeTable.put((byte) 0x19, "aload");
            codeTable.put((byte) 0x1A, "iload_0");
            codeTable.put((byte) 0x1B, "iload_1");
            codeTable.put((byte) 0x1C, "iload_2");
            codeTable.put((byte) 0x1D, "iload_3");
            codeTable.put((byte) 0x1E, "lload_0");
            codeTable.put((byte) 0x1F, "lload_1");

            codeTable.put((byte) 0x20, "lload_2");
            codeTable.put((byte) 0x21, "lload_3");
            codeTable.put((byte) 0x22, "fload_0");
            codeTable.put((byte) 0x23, "fload_1");
            codeTable.put((byte) 0x24, "fload_2");
            codeTable.put((byte) 0x25, "fload_3");
            codeTable.put((byte) 0x26, "dload_0");
            codeTable.put((byte) 0x27, "dload_1");
            codeTable.put((byte) 0x28, "dload_2");
            codeTable.put((byte) 0x29, "dload_3");
            codeTable.put((byte) 0x2A, "aload_0");
            codeTable.put((byte) 0x2B, "aload_1");
            codeTable.put((byte) 0x2C, "aload_2");
            codeTable.put((byte) 0x2D, "aload_3");
            codeTable.put((byte) 0x2E, "iaload");
            codeTable.put((byte) 0x2F, "laload");

            codeTable.put((byte) 0x30, "faload");
            codeTable.put((byte) 0x31, "daload");
            codeTable.put((byte) 0x32, "aaload");
            codeTable.put((byte) 0x33, "baload");
            codeTable.put((byte) 0x34, "caload");
            codeTable.put((byte) 0x35, "saload");
            codeTable.put((byte) 0x36, "istore");
            codeTable.put((byte) 0x37, "lstore");
            codeTable.put((byte) 0x38, "fstore");
            codeTable.put((byte) 0x39, "dstore");
            codeTable.put((byte) 0x3A, "astore");
            codeTable.put((byte) 0x3B, "istore_0");
            codeTable.put((byte) 0x3C, "istore_1");
            codeTable.put((byte) 0x3D, "istore_2");
            codeTable.put((byte) 0x3E, "istore_3");
            codeTable.put((byte) 0x3F, "lstore_0");

            codeTable.put((byte) 0x40, "lstore_1");
            codeTable.put((byte) 0x41, "lstore_2");
            codeTable.put((byte) 0x42, "lstore_3");
            codeTable.put((byte) 0x43, "fstore_0");
            codeTable.put((byte) 0x44, "fstore_1");
            codeTable.put((byte) 0x45, "fstore_2");
            codeTable.put((byte) 0x46, "fstore_3");
            codeTable.put((byte) 0x47, "dstore_0");
            codeTable.put((byte) 0x48, "dstore_1");
            codeTable.put((byte) 0x49, "dstore_2");
            codeTable.put((byte) 0x4A, "dstore_3");
            codeTable.put((byte) 0x4B, "astore_0");
            codeTable.put((byte) 0x4C, "astore_1");
            codeTable.put((byte) 0x4D, "astore_2");
            codeTable.put((byte) 0x4E, "astore_3");
            codeTable.put((byte) 0x4F, "iastore");

            codeTable.put((byte) 0x50, "lastore");
            codeTable.put((byte) 0x51, "fastore");
            codeTable.put((byte) 0x52, "dastore");
            codeTable.put((byte) 0x53, "aastore");
            codeTable.put((byte) 0x54, "bastore");
            codeTable.put((byte) 0x55, "castore");
            codeTable.put((byte) 0x56, "sastore");
            codeTable.put((byte) 0x57, "pop");
            codeTable.put((byte) 0x58, "pop2");
            codeTable.put((byte) 0x59, "dup");
            codeTable.put((byte) 0x5A, "dup_x1");
            codeTable.put((byte) 0x5B, "dup_x2");
            codeTable.put((byte) 0x5C, "dup2");
            codeTable.put((byte) 0x5D, "dup2_x1");
            codeTable.put((byte) 0x5E, "dup2_x2");
            codeTable.put((byte) 0x5F, "swap");

            codeTable.put((byte) 0x60, "iadd");
            codeTable.put((byte) 0x61, "ladd");
            codeTable.put((byte) 0x62, "fadd");
            codeTable.put((byte) 0x63, "dadd");
            codeTable.put((byte) 0x64, "isub");
            codeTable.put((byte) 0x65, "lsub");
            codeTable.put((byte) 0x66, "fsub");
            codeTable.put((byte) 0x67, "dsub");
            codeTable.put((byte) 0x68, "imul");
            codeTable.put((byte) 0x69, "lmul");
            codeTable.put((byte) 0x6A, "fmul");
            codeTable.put((byte) 0x6B, "dmul");
            codeTable.put((byte) 0x6C, "idiv");
            codeTable.put((byte) 0x6D, "ldiv");
            codeTable.put((byte) 0x6E, "fdiv");
            codeTable.put((byte) 0x6F, "ddiv");

            codeTable.put((byte) 0x70, "irem");
            codeTable.put((byte) 0x71, "lrem");
            codeTable.put((byte) 0x72, "frem");
            codeTable.put((byte) 0x73, "drem");
            codeTable.put((byte) 0x74, "ineg");
            codeTable.put((byte) 0x75, "lneg");
            codeTable.put((byte) 0x76, "fneg");
            codeTable.put((byte) 0x77, "dneg");
            codeTable.put((byte) 0x78, "ishl");
            codeTable.put((byte) 0x79, "lshl");
            codeTable.put((byte) 0x7A, "ishr");
            codeTable.put((byte) 0x7B, "lshr");
            codeTable.put((byte) 0x7C, "iushr");
            codeTable.put((byte) 0x7D, "lushr");
            codeTable.put((byte) 0x7E, "iand");
            codeTable.put((byte) 0x7F, "land");

            codeTable.put((byte) 0x80, "ior");
            codeTable.put((byte) 0x81, "lor");
            codeTable.put((byte) 0x82, "ixor");
            codeTable.put((byte) 0x83, "lxor");
            codeTable.put((byte) 0x84, "iinc");
            codeTable.put((byte) 0x85, "i2l");
            codeTable.put((byte) 0x86, "i2f");
            codeTable.put((byte) 0x87, "i2d");
            codeTable.put((byte) 0x88, "l2i");
            codeTable.put((byte) 0x89, "l2f");
            codeTable.put((byte) 0x8A, "l2d");
            codeTable.put((byte) 0x8B, "f2i");
            codeTable.put((byte) 0x8C, "f2l");
            codeTable.put((byte) 0x8D, "f2d");
            codeTable.put((byte) 0x8E, "d2i");
            codeTable.put((byte) 0x8F, "d2l");

            codeTable.put((byte) 0x90, "d2f");
            codeTable.put((byte) 0x91, "i2b");
            codeTable.put((byte) 0x92, "i2c");
            codeTable.put((byte) 0x93, "i2s");
            codeTable.put((byte) 0x94, "lcmp");
            codeTable.put((byte) 0x95, "fcmpl");
            codeTable.put((byte) 0x96, "fcmpg");
            codeTable.put((byte) 0x97, "dcmpl");
            codeTable.put((byte) 0x98, "dcmpg");
            codeTable.put((byte) 0x99, "ifeq");
            codeTable.put((byte) 0x9A, "ifne");
            codeTable.put((byte) 0x9B, "iflt");
            codeTable.put((byte) 0x9C, "ifge");
            codeTable.put((byte) 0x9D, "ifgt");
            codeTable.put((byte) 0x9E, "ifle");
            codeTable.put((byte) 0x9F, "if_icmpeq");

            codeTable.put((byte) 0xA0, "if_icmpne");
            codeTable.put((byte) 0xA1, "if_icmplt");
            codeTable.put((byte) 0xA2, "if_icmpge");
            codeTable.put((byte) 0xA3, "if_icmpgt");
            codeTable.put((byte) 0xA4, "if_icmple");
            codeTable.put((byte) 0xA5, "if_aicmple");
            codeTable.put((byte) 0xA6, "if_aicmpne");
            codeTable.put((byte) 0xA7, "goto");
            codeTable.put((byte) 0xA8, "jsr");
            codeTable.put((byte) 0xA9, "ret");
            codeTable.put((byte) 0xAA, "tableswitch");
            codeTable.put((byte) 0xAB, "lookupswitch");
            codeTable.put((byte) 0xAC, "ireturn");
            codeTable.put((byte) 0xAD, "lreturn");
            codeTable.put((byte) 0xAE, "freturn");
            codeTable.put((byte) 0xAF, "dreturn");

            codeTable.put((byte) 0xB0, "areturn");
            codeTable.put((byte) 0xB1, "return");
            codeTable.put((byte) 0xB2, "getstatic");
            codeTable.put((byte) 0xB3, "putstatic");
            codeTable.put((byte) 0xB4, "getfield");
            codeTable.put((byte) 0xB5, "putfield");
            codeTable.put((byte) 0xB6, "invokevirtual");
            codeTable.put((byte) 0xB7, "invokespecial");
            codeTable.put((byte) 0xB8, "invokestatic");
            codeTable.put((byte) 0xB9, "invokeinterface");
            codeTable.put((byte) 0xBA, "invokedynamic");
            codeTable.put((byte) 0xBB, "new");
            codeTable.put((byte) 0xBC, "newarray");
            codeTable.put((byte) 0xBD, "anewarray");
            codeTable.put((byte) 0xBE, "arraylength");
            codeTable.put((byte) 0xBF, "athrow");

            codeTable.put((byte) 0xC0, "checkcast");
            codeTable.put((byte) 0xC1, "instanceof");
            codeTable.put((byte) 0xC2, "monitorenter");
            codeTable.put((byte) 0xC3, "monitorexit");
            codeTable.put((byte) 0xC4, "wide");
            codeTable.put((byte) 0xC5, "multianewarray");
            codeTable.put((byte) 0xC6, "ifnull");
            codeTable.put((byte) 0xC7, "ifnonnull");
            codeTable.put((byte) 0xC8, "goto_w");
            codeTable.put((byte) 0xC9, "jsr_w");
            codeTable.put((byte) 0xCA, "breakpoint");

            codeTable.put((byte) 0xFE, "impdep1");
            codeTable.put((byte) 0xFF, "impdep2");
        }
    }
}
