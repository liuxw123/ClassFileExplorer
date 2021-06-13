package com.lxw.main.attributes;

import com.lxw.main.ClassBean;
import com.lxw.main.common.Utils;
import com.lxw.main.common.inter.MultipleItemsInterface;
import com.lxw.main.common.inter.PostOperation;
import com.lxw.main.common.inter.ReaderInterface;
import com.lxw.main.common.number.U1;
import com.lxw.main.common.number.U2;
import com.lxw.main.cp.ConstantClassInfo;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: AttributeStackMapTable.java
 * @Date: 2021-06-12 12:24
 * @Version: V0.0
 */


public class AttributeStackMapTable extends AttributeInfo{
    private StackMapFrames stackMapFrameEntries;

    public AttributeStackMapTable(String tag, U2 attributeNameIndex, ClassBean classBean, int component) {
        super(tag, attributeNameIndex, classBean, component);
        stackMapFrameEntries = new StackMapFrames();
    }

    @Override
    public void read(DataInputStream dis) {
        super.read(dis);
        stackMapFrameEntries.readMultiple(dis);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s%s: number_of_entries: %d\n", getPrintLevelString(getLevel()+1), getTag(), stackMapFrameEntries.size()));
        stringBuilder.append(stackMapFrameEntries + "\n");
        return Utils.format(stringBuilder);
    }

    class StackMapFrames implements MultipleItemsInterface {

        private U2 numberOfEntries;
        private StackMapFrame[] stackMapFrames;

        public StackMapFrames() {
            numberOfEntries = new U2();
        }

        @Override
        public void readMultiple(DataInputStream dis) {
            numberOfEntries.read(dis);
            int count = (int) numberOfEntries.get();
            stackMapFrames = new StackMapFrame[count];

            for (int i = 0; i < count; i++) {
                stackMapFrames[i] = new StackMapFrame();
                stackMapFrames[i].read(dis);
            }
        }

        @Override
        public StackMapFrame get(int index) {
            if (index >= size()) return null;
            return stackMapFrames[index];
        }

        @Override
        public int size() {
            return (stackMapFrames == null)?-1:stackMapFrames.length;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < size(); i++) {
                stringBuilder.append(getPrintLevelString(getLevel()+2)+get(i)+"\n");
            }
            if (stringBuilder.length()>0) stringBuilder.deleteCharAt(stringBuilder.length()-1);
            return stringBuilder.toString();
        }
    }

    class StackMapFrame implements ReaderInterface {

        private Frame frame;

        private final static int ITEM_TOP = 0;
        private final static int ITEM_INTEGER = 1;
        private final static int ITEM_FLOAT = 2;
        private final static int ITEM_LONG = 4;
        private final static int ITEM_DOUBLE = 3;
        private final static int ITEM_NULL = 5;
        private final static int ITEM_UNINITIALIZED_THIS = 6;
        private final static int ITEM_OBJECT = 7;
        private final static int ITEM_UNINITIALIZED = 8;

        private final static int SAME = 0;
        private final static int SAME_LOCALS_1_STACK_ITEM = 1;
        private final static int SAME_LOCALS_1_STACK_ITEM_EXTENNDED = 2;
        private final static int CHOP = 3;
        private final static int SAME_FRAME_EXTENTDED = 4;
        private final static int APPEND = 5;
        private final static int FULL = 6;

        public static String getFrameString(int frameType) {
            switch (frameType) {
                case SAME: return "same";
                case SAME_LOCALS_1_STACK_ITEM: return "same_locals_1_stack_frame_item";
                case SAME_LOCALS_1_STACK_ITEM_EXTENNDED: return "same_locals_1_stack_item_frame_extended";
                case CHOP: return "chop";
                case SAME_FRAME_EXTENTDED: return "same_frame_extended";
                case APPEND: return "append";
                case FULL: return "full_frame";
                default:
                    throw new IllegalArgumentException();
            }
        }

        public static int getFrameType(int frameType) {
            if (frameType < 0 || frameType > 256) throw new IllegalArgumentException();
            if (frameType > 127 && frameType < 247) throw new IllegalArgumentException();

            if (frameType <= 63) return SAME;
            if (frameType <= 127) return SAME_LOCALS_1_STACK_ITEM;
            if (frameType <= 247) return SAME_LOCALS_1_STACK_ITEM_EXTENNDED;
            if (frameType <= 250) return CHOP;
            if (frameType <= 251) return SAME_FRAME_EXTENTDED;
            if (frameType <= 254) return APPEND;
            return FULL;
        }

        public static String getVariableString(int tag) {
            switch (tag) {
                case ITEM_TOP: return "top";
                case ITEM_INTEGER: return "int";
                case ITEM_FLOAT: return "float";
                case ITEM_DOUBLE: return "double";
                case ITEM_LONG: return "long";
                case ITEM_NULL:
                case ITEM_OBJECT:
                case ITEM_UNINITIALIZED:
                case ITEM_UNINITIALIZED_THIS:
                default: return "";
            }
        }

        @Override
        public String toString() {
            return ""+frame;
        }

        @Override
        public void read(DataInputStream dis) {
            U1 frameType = new U1();
            frameType.read(dis);
            int frameIndex = (int) frameType.get();

            switch (getFrameType(frameIndex)) {
                case SAME: frame = new Same(frameIndex); break;
                case SAME_LOCALS_1_STACK_ITEM: frame = new SameLocals1StackItem(frameIndex);break;
                case SAME_LOCALS_1_STACK_ITEM_EXTENNDED: frame = new SameLocals1StackItemExtended(frameIndex); break;
                case CHOP: frame = new Chop(frameIndex); break;
                case SAME_FRAME_EXTENTDED: frame = new SameFrameExtended(frameIndex); break;
                case APPEND: frame = new Append(frameIndex); break;
                case FULL: frame = new Full(frameIndex); break;
                default:
                    throw new IllegalArgumentException();
            }

            frame.read(dis);
        }

        abstract class Frame implements ReaderInterface{
            protected int frameType;
            protected int frame;

            public Frame(int frame) {
                this.frame = frame;
                this.frameType = getFrameType(frame);
            }

            @Override
            public void read(DataInputStream dis) {

            }

            public VariableInfo getVariableByTag(int tag) {
                VariableInfo stack;

                switch (tag) {
                    case ITEM_TOP: stack = new TopVariableInfo(); break;
                    case ITEM_INTEGER: stack = new IntegerVariableInfo(); break;
                    case ITEM_FLOAT: stack = new FloatVariableInfo(); break;
                    case ITEM_DOUBLE: stack = new DoubleVariableInfo(); break;
                    case ITEM_LONG: stack = new LongVariableInfo(); break;
                    case ITEM_NULL: stack = new NullVariableInfo(); break;
                    case ITEM_UNINITIALIZED_THIS: stack = new UninitializedThisVariableInfo(); break;
                    case ITEM_OBJECT: stack = new ObjectVariableInfo(); break;
                    case ITEM_UNINITIALIZED: stack = new UninitializedVariableInfo(); break;
                    default: throw new IllegalArgumentException();
                }
                return stack;
            }

            @Override
            public String toString() {
                return String.format("frame_type = %d /* %s */\n", frame, getFrameString(frameType));
            }
        }

        class Same extends Frame{
            public Same(int frame) {
                super(frame);
            }

            @Override
            public String toString() {
                String string = super.toString();
                return string.substring(0, string.length()-1);
            }
        }

        class SameLocals1StackItem extends Frame {
            VariableInfo stack;
            public SameLocals1StackItem(int frame) {
                super(frame);
            }

            @Override
            public void read(DataInputStream dis) {
                super.read(dis);
                U1 tag = new U1();
                tag.read(dis);
                stack = getVariableByTag((int) tag.get());
                stack.read(dis);
            }

            @Override
            public String toString() {
                StringBuilder stringBuilder = new StringBuilder(super.toString());
                stringBuilder.append(String.format("%sstack = [%s]", getPrintLevelString(getLevel()+3), stack));
                return stringBuilder.toString();
            }
        }

        class SameLocals1StackItemExtended extends Frame {
            U2 offsetDelta;
            VariableInfo stack;
            public SameLocals1StackItemExtended(int frame) {
                super(frame);
                offsetDelta = new U2();
            }

            @Override
            public void read(DataInputStream dis) {
                super.read(dis);
                offsetDelta.read(dis);
                U1 tag = new U1();
                tag.read(dis);
                stack = getVariableByTag((int) tag.get());
                stack.read(dis);
            }
        }

        class Chop extends Frame {
            U2 offsetDelta;
            public Chop(int frame) {
                super(frame);
                offsetDelta = new U2();
            }

            @Override
            public void read(DataInputStream dis) {
                super.read(dis);
                offsetDelta.read(dis);
            }

            @Override
            public String toString() {
                return String.format("%s%soffset_delta=%d",super.toString(), getPrintLevelString(getLevel()+3), offsetDelta.get());
            }
        }

        class SameFrameExtended extends Frame {
            U2 offsetDelta;
            public SameFrameExtended(int frame) {
                super(frame);
                offsetDelta = new U2();
            }

            @Override
            public void read(DataInputStream dis) {
                super.read(dis);
                offsetDelta.read(dis);
            }
        }

        class Append extends Frame {
            U2 offsetDelta;
            VariableInfo locals[];
            public Append(int frame) {
                super(frame);
                offsetDelta = new U2();
                locals = new VariableInfo[frame-251];
            }

            @Override
            public void read(DataInputStream dis) {
                super.read(dis);
                offsetDelta.read(dis);

                for (int i = 0; i < locals.length; i++) {
                    U1 tag = new U1();
                    tag.read(dis);
                    locals[i] = getVariableByTag((int) tag.get());
                    locals[i].read(dis);
                }
            }

            @Override
            public String toString() {
                StringBuilder stringBuilder = new StringBuilder(super.toString());
                stringBuilder.append(String.format("%soffset_delta = %d\n", getPrintLevelString(getLevel()+3), offsetDelta.get()));
                stringBuilder.append(String.format("%slocals = [ ", getPrintLevelString(getLevel()+3)));
                for (int i = 0; i < locals.length; i++) {
                    stringBuilder.append(locals[i]+" ");
                }
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
        }

        class Full extends Frame {
            U2 offsetDelta;
            VariableInfo locals[];
            VariableInfo stacks[];
            public Full(int frame) {
                super(frame);
                offsetDelta = new U2();
            }

            @Override
            public void read(DataInputStream dis) {
                super.read(dis);
                offsetDelta.read(dis);
                U2 numberOfLocals = new U2();
                U2 numberOfStackItems = new U2();
                numberOfLocals.read(dis);
                int count = (int) numberOfLocals.get();
                locals = new VariableInfo[count];
                for (int i = 0; i < count; i++) {
                    U1 tag = new U1();
                    tag.read(dis);
                    locals[i] = getVariableByTag((int) tag.get());
                    locals[i].read(dis);
                }

                numberOfStackItems.read(dis);
                count = (int) numberOfStackItems.get();
                stacks = new VariableInfo[count];
                for (int i = 0; i < count; i++) {
                    U1 tag = new U1();
                    tag.read(dis);
                    stacks[i] = getVariableByTag((int) tag.get());
                    stacks[i].read(dis);
                }
            }

            @Override
            public String toString() {
                String blank = getPrintLevelString(getLevel()+3);
                StringBuilder stringBuilder = new StringBuilder(super.toString());
                stringBuilder.append(String.format("%soffset_delta: %d\n", blank, offsetDelta.get()));
                stringBuilder.append(String.format("%slocals = [ ", blank));
                for (int i = 0; i < locals.length; i++) {
                    stringBuilder.append(locals[i]+", ");
                }
                if (locals.length>0) stringBuilder.deleteCharAt(stringBuilder.length()-2);
                stringBuilder.append("]\n");
                stringBuilder.append(String.format("%sstacks = [ ", blank));
                for (int i = 0; i < stacks.length; i++) {
                    stringBuilder.append(stacks[i]+", ");
                }
                if (stacks.length>0) stringBuilder.deleteCharAt(stringBuilder.length()-2);
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
        }

        abstract class VariableInfo implements ReaderInterface{
            protected int tag;

            public VariableInfo(int tag) {
                this.tag = tag;
            }

            @Override
            public void read(DataInputStream dis) {

            }

            @Override
            public String toString() {
                return getVariableString(tag);
            }
        }

        class TopVariableInfo extends VariableInfo {
            public TopVariableInfo() {
                super(ITEM_TOP);
            }
        }

        class IntegerVariableInfo extends VariableInfo {
            public IntegerVariableInfo() {
                super(ITEM_INTEGER);
            }
        }

        class LongVariableInfo extends VariableInfo {
            public LongVariableInfo () {
                super(ITEM_LONG);
            }
        }

        class FloatVariableInfo extends VariableInfo {
            public FloatVariableInfo() {
                super(ITEM_FLOAT);
            }
        }

        class DoubleVariableInfo extends VariableInfo {
            public DoubleVariableInfo() {
                super(ITEM_DOUBLE);
            }
        }

        class NullVariableInfo extends VariableInfo {
            public NullVariableInfo() {
                super(ITEM_NULL);
            }
        }

        class UninitializedVariableInfo extends VariableInfo {
            private U2 offset;
            public UninitializedVariableInfo() {
                super(ITEM_UNINITIALIZED);
                offset = new U2();
            }

            @Override
            public void read(DataInputStream dis) {
                super.read(dis);
                offset.read(dis);
            }
        }

        class UninitializedThisVariableInfo extends VariableInfo{
            public UninitializedThisVariableInfo() {
                super(ITEM_UNINITIALIZED_THIS);
            }
        }

        class ObjectVariableInfo extends VariableInfo implements PostOperation {
            private U2 cpoolIndex;
            private ConstantClassInfo cpool;
            public ObjectVariableInfo() {
                super(ITEM_OBJECT);
                cpoolIndex = new U2();
            }

            @Override
            public void read(DataInputStream dis) {
                super.read(dis);
                cpoolIndex.read(dis);
                post();
            }

            @Override
            public void post() {
                cpool = (ConstantClassInfo) get((int) (cpoolIndex.get()-1));
            }

            @Override
            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("class ");
                if (cpool.referenceString().contains("[") || cpool.referenceString().contains("]")) stringBuilder.append(String.format("\"%s\"", cpool.referenceString()));
                else stringBuilder.append(cpool.referenceString());
                return stringBuilder.toString();
            }
        }
    }
}
