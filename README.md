# ClassFileExplorer
# 简介：

仿javap命令，解析class文件Demo

# 使用： 

```java
classFileExplorer = new ClassFileExplorer("path_to_class_file");
System.out.println(classFileExplorer);
```

# 效果：

```java
ClassFile /home/lxw/IdeaProjects/ClassFileExplorer/out/production/ClassFileExplorer/com/lxw/main/ClassFileExplorer.class
  magic: 0xCAFEBABE
  version: major: 0x003C, minor: 0x0000 (jdk-16.0)
  flags: (0x0021)  ACC_PUBLIC ACC_SUPER
  this class: #8 // com/lxw/main/ClassFileExplorer
  super class: #13 // java/lang/Object
  interfaces: 0, fields: 2, methods: 7, attributes: 3
Constant Pool:
   #1 = Class #2 // java/io/File
   #2 = Utf8 java/io/File
   #3 = Methodref #1.#4 // java/io/File.<init>:(Ljava/lang/String;)V
   #4 = NameAndType #5:#6 // <init>:(Ljava/lang/String;)V
   #5 = Utf8 <init>
   #6 = Utf8 (Ljava/lang/String;)V
   #7 = Methodref #8.#9 // com/lxw/main/ClassFileExplorer.<init>:(Ljava/io/File;)V
   #8 = Class #10 // com/lxw/main/ClassFileExplorer
   #9 = NameAndType #5:#11 // <init>:(Ljava/io/File;)V
  #10 = Utf8 com/lxw/main/ClassFileExplorer
  #11 = Utf8 (Ljava/io/File;)V
  #12 = Methodref #13.#14 // java/lang/Object.<init>:()V
  #13 = Class #15 // java/lang/Object
  #14 = NameAndType #5:#16 // <init>:()V
  #15 = Utf8 java/lang/Object
  #16 = Utf8 ()V
  #17 = Methodref #1.#18 // java/io/File.isFile:()Z
  #18 = NameAndType #19:#20 // isFile:()Z
  #19 = Utf8 isFile
  #20 = Utf8 ()Z
  #21 = Methodref #1.#22 // java/io/File.exists:()Z
  #22 = NameAndType #23:#20 // exists:()Z
  #23 = Utf8 exists
  #24 = Fieldref #8.#25 // com/lxw/main/ClassFileExplorer.classFile:Ljava/io/File;
  #25 = NameAndType #26:#27 // classFile:Ljava/io/File;
  #26 = Utf8 classFile
  #27 = Utf8 Ljava/io/File;
  #28 = Methodref #1.#29 // java/io/File.getName:()Ljava/lang/String;
  #29 = NameAndType #30:#31 // getName:()Ljava/lang/String;
  #30 = Utf8 getName
  #31 = Utf8 ()Ljava/lang/String;
  #32 = String #33 // MethodInfos.class
  #33 = Utf8 MethodInfos.class
  #34 = Methodref #35.#36 // java/lang/String.equals:(Ljava/lang/Object;)Z
  #35 = Class #37 // java/lang/String
  #36 = NameAndType #38:#39 // equals:(Ljava/lang/Object;)Z
  #37 = Utf8 java/lang/String
  #38 = Utf8 equals
  #39 = Utf8 (Ljava/lang/Object;)Z
  #40 = Fieldref #41.#42 // java/lang/System.out:Ljava/io/PrintStream;
  #41 = Class #43 // java/lang/System
  #42 = NameAndType #44:#45 // out:Ljava/io/PrintStream;
  #43 = Utf8 java/lang/System
  #44 = Utf8 out
  #45 = Utf8 Ljava/io/PrintStream;
  #46 = Methodref #47.#48 // java/io/PrintStream.println:()V
  #47 = Class #49 // java/io/PrintStream
  #48 = NameAndType #50:#16 // println:()V
  #49 = Utf8 java/io/PrintStream
  #50 = Utf8 println
  #51 = Methodref #8.#52 // com/lxw/main/ClassFileExplorer.decode:(Ljava/io/File;)V
  #52 = NameAndType #53:#11 // decode:(Ljava/io/File;)V
  #53 = Utf8 decode
  #54 = Class #55 // java/io/DataInputStream
  #55 = Utf8 java/io/DataInputStream
  #56 = Class #57 // java/io/FileInputStream
  #57 = Utf8 java/io/FileInputStream
  #58 = Methodref #56.#9 // java/io/FileInputStream.<init>:(Ljava/io/File;)V
  #59 = Methodref #54.#60 // java/io/DataInputStream.<init>:(Ljava/io/InputStream;)V
  #60 = NameAndType #5:#61 // <init>:(Ljava/io/InputStream;)V
  #61 = Utf8 (Ljava/io/InputStream;)V
  #62 = Class #63 // com/lxw/main/ClassBean
  #63 = Utf8 com/lxw/main/ClassBean
  #64 = Methodref #62.#14 // com/lxw/main/ClassBean.<init>:()V
  #65 = Fieldref #8.#66 // com/lxw/main/ClassFileExplorer.classBean:Lcom/lxw/main/ClassBean;
  #66 = NameAndType #67:#68 // classBean:Lcom/lxw/main/ClassBean;
  #67 = Utf8 classBean
  #68 = Utf8 Lcom/lxw/main/ClassBean;
  #69 = Methodref #62.#70 // com/lxw/main/ClassBean.read:(Ljava/io/DataInputStream;)V
  #70 = NameAndType #71:#72 // read:(Ljava/io/DataInputStream;)V
  #71 = Utf8 read
  #72 = Utf8 (Ljava/io/DataInputStream;)V
  #73 = Methodref #62.#74 // com/lxw/main/ClassBean.isValid:()Z
  #74 = NameAndType #75:#20 // isValid:()Z
  #75 = Utf8 isValid
  #76 = String #77 // file: %s, result: %s
  #77 = Utf8 file: %s, result: %s
  #78 = Methodref #79.#80 // java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
  #79 = Class #81 // java/lang/Boolean
  #80 = NameAndType #82:#83 // valueOf:(Z)Ljava/lang/Boolean;
  #81 = Utf8 java/lang/Boolean
  #82 = Utf8 valueOf
  #83 = Utf8 (Z)Ljava/lang/Boolean;
  #84 = Methodref #35.#85 // java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
  #85 = NameAndType #86:#87 // format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
  #86 = Utf8 format
  #87 = Utf8 (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
  #88 = Methodref #47.#89 // java/io/PrintStream.println:(Ljava/lang/String;)V
  #89 = NameAndType #50:#6 // println:(Ljava/lang/String;)V
  #90 = Class #91 // java/io/FileNotFoundException
  #91 = Utf8 java/io/FileNotFoundException
  #92 = Methodref #90.#93 // java/io/FileNotFoundException.printStackTrace:()V
  #93 = NameAndType #94:#16 // printStackTrace:()V
  #94 = Utf8 printStackTrace
  #95 = String #96 // ClassFile %s
%s
  #96 = Utf8 ClassFile %s
%s
  #97 = Methodref #1.#98 // java/io/File.getAbsolutePath:()Ljava/lang/String;
  #98 = NameAndType #99:#31 // getAbsolutePath:()Ljava/lang/String;
  #99 = Utf8 getAbsolutePath
 #100 = String #101 // out/production/ClassFileExplorer/com/lxw/main/ClassFileExplorer.class
 #101 = Utf8 out/production/ClassFileExplorer/com/lxw/main/ClassFileExplorer.class
 #102 = Methodref #8.#4 // com/lxw/main/ClassFileExplorer.<init>:(Ljava/lang/String;)V
 #103 = Methodref #47.#104 // java/io/PrintStream.println:(Ljava/lang/Object;)V
 #104 = NameAndType #50:#105 // println:(Ljava/lang/Object;)V
 #105 = Utf8 (Ljava/lang/Object;)V
 #106 = InvokeDynamic #0.#107
 #107 = NameAndType #108:#109 // accept:()Ljava/io/FileFilter;
 #108 = Utf8 accept
 #109 = Utf8 ()Ljava/io/FileFilter;
 #110 = Methodref #1.#111 // java/io/File.listFiles:(Ljava/io/FileFilter;)[Ljava/io/File;
 #111 = NameAndType #112:#113 // listFiles:(Ljava/io/FileFilter;)[Ljava/io/File;
 #112 = Utf8 listFiles
 #113 = Utf8 (Ljava/io/FileFilter;)[Ljava/io/File;
 #114 = Methodref #1.#115 // java/io/File.isDirectory:()Z
 #115 = NameAndType #116:#20 // isDirectory:()Z
 #116 = Utf8 isDirectory
 #117 = Methodref #8.#118 // com/lxw/main/ClassFileExplorer.decodeAll:(Ljava/io/File;)V
 #118 = NameAndType #119:#11 // decodeAll:(Ljava/io/File;)V
 #119 = Utf8 decodeAll
 #120 = String #121 // .class
 #121 = Utf8 .class
 #122 = Methodref #35.#123 // java/lang/String.endsWith:(Ljava/lang/String;)Z
 #123 = NameAndType #124:#125 // endsWith:(Ljava/lang/String;)Z
 #124 = Utf8 endsWith
 #125 = Utf8 (Ljava/lang/String;)Z
 #126 = Utf8 Code
 #127 = Utf8 LineNumberTable
 #128 = Utf8 LocalVariableTable
 #129 = Utf8 this
 #130 = Utf8 Lcom/lxw/main/ClassFileExplorer;
 #131 = Utf8 path
 #132 = Utf8 Ljava/lang/String;
 #133 = Utf8 StackMapTable
 #134 = Utf8 dis
 #135 = Utf8 Ljava/io/DataInputStream;
 #136 = Utf8 e
 #137 = Utf8 Ljava/io/FileNotFoundException;
 #138 = Utf8 file
 #139 = Utf8 toString
 #140 = Utf8 main
 #141 = Utf8 ([Ljava/lang/String;)V
 #142 = Utf8 args
 #143 = Utf8 [Ljava/lang/String;
 #144 = Utf8 files
 #145 = Utf8 [Ljava/io/File;
 #146 = Class #145 // [Ljava/io/File;
 #147 = Utf8 lambda$decodeAll$0
 #148 = Utf8 (Ljava/io/File;)Z
 #149 = Utf8 f
 #150 = Utf8 SourceFile
 #151 = Utf8 ClassFileExplorer.java
 #152 = Utf8 BootstrapMethods
 #153 = MethodHandle #6.#154 // REF_invokeStatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
 #154 = Methodref #155.#156 // java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
 #155 = Class #157 // java/lang/invoke/LambdaMetafactory
 #156 = NameAndType #158:#159 // metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
 #157 = Utf8 java/lang/invoke/LambdaMetafactory
 #158 = Utf8 metafactory
 #159 = Utf8 (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
 #160 = MethodType #148 // (Ljava/io/File;)Z
 #161 = MethodHandle #6.#162 // REF_invokeStatic com/lxw/main/ClassFileExplorer.lambda$decodeAll$0:(Ljava/io/File;)Z
 #162 = Methodref #8.#163 // com/lxw/main/ClassFileExplorer.lambda$decodeAll$0:(Ljava/io/File;)Z
 #163 = NameAndType #147:#148 // lambda$decodeAll$0:(Ljava/io/File;)Z
 #164 = Utf8 InnerClasses
 #165 = Class #166 // java/lang/invoke/MethodHandles$Lookup
 #166 = Utf8 java/lang/invoke/MethodHandles$Lookup
 #167 = Class #168 // java/lang/invoke/MethodHandles
 #168 = Utf8 java/lang/invoke/MethodHandles
 #169 = Utf8 Lookup
{
  private Ljava/io/File; classFile
    descriptor: Ljava/io/File;
    flags: (0x0002)  ACC_PRIVATE

  private Lcom/lxw/main/ClassBean; classBean
    descriptor: Lcom/lxw/main/ClassBean;
    flags: (0x0002)  ACC_PRIVATE


  public (Ljava/lang/String;)V <init>()
    descriptor: (Ljava/lang/String;)V
    flags: (0x0001)  ACC_PUBLIC
    Code:
      stack=4, locals=2, args_size=2
         0: aload_0
         1: new
         2: nop
         3: aconst_null
         4: dup
         5: aload_1
         6: invokespecial
         7: nop
         8: iconst_0
         9: invokespecial
        10: nop
        11: iconst_4
        12: return
      LineNumberTable
        line 22: 0
        line 23: 12
      LocalVariableTable:
         Start Length   Slot Name Signature
             0     13      0 this   Lcom/lxw/main/ClassFileExplorer;
             0     13      1 path   Ljava/lang/String;

  public (Ljava/io/File;)V <init>()
    descriptor: (Ljava/io/File;)V
    flags: (0x0001)  ACC_PUBLIC
    Code:
      stack=2, locals=2, args_size=2
         0: aload_0
         1: invokespecial
         2: nop
         3: fconst_1
         4: aload_1
         5: ifnull
         6: nop
         7: sipush
         8: aload_1
         9: invokevirtual
        10: nop
        11: sipush
        12: ifeq
        13: nop
        14: lconst_1
        15: aload_1
        16: invokevirtual
        17: nop
        18: iload
        19: ifne
        20: nop
        21: iconst_1
        22: return
        23: aload_0
        24: aload_1
        25: putfield
        26: nop
        27: dload
        28: aload_1
        29: invokevirtual
        30: nop
        31: iload_2
        32: ldc
        33: lload_2
        34: invokevirtual
        35: nop
        36: fload_0
        37: ifeq
        38: nop
        39: lconst_0
        40: getstatic
        41: nop
        42: dload_2
        43: invokevirtual
        44: nop
        45: iaload
        46: aload_0
        47: aload_1
        48: invokevirtual
        49: nop
        50: baload
        51: return
      LineNumberTable
        line 25: 0
        line 26: 4
        line 27: 23
        line 28: 28
        line 29: 40
        line 30: 46
        line 31: 51
      LocalVariableTable:
         Start Length   Slot Name Signature
             0     52      0 this   Lcom/lxw/main/ClassFileExplorer;
             0     52      1 classFile   Ljava/io/File;
      StackMapTable: number_of_entries: 3
        frame_type = 255 /* full_frame */
          offset_delta: 22
          locals = [ class com/lxw/main/ClassFileExplorer, class java/io/File ]
          stacks = [ ]
        frame_type = 0 /* same */
        frame_type = 22 /* same */

  private (Ljava/io/File;)V decode()
    descriptor: (Ljava/io/File;)V
    flags: (0x0002)  ACC_PRIVATE
    Code:
      stack=6, locals=3, args_size=4
         0: new
         1: nop
         2: istore
         3: dup
         4: new
         5: nop
         6: fstore
         7: dup
         8: aload_1
         9: invokespecial
        10: nop
        11: astore
        12: invokespecial
        13: nop
        14: istore_0
        15: astore_2
        16: aload_0
        17: new
        18: nop
        19: istore_3
        20: dup
        21: invokespecial
        22: nop
        23: lstore_1
        24: putfield
        25: nop
        26: lstore_2
        27: aload_0
        28: getfield
        29: nop
        30: lstore_2
        31: aload_2
        32: invokevirtual
        33: nop
        34: fstore_2
        35: aload_0
        36: getfield
        37: nop
        38: lstore_2
        39: invokevirtual
        40: nop
        41: dstore_2
        42: ifne
        43: nop
        44: dload_0
        45: getstatic
        46: nop
        47: dload_2
        48: ldc
        49: astore_1
        50: iconst_2
        51: anewarray
        52: nop
        53: fconst_2
        54: dup
        55: iconst_0
        56: aload_1
        57: invokevirtual
        58: nop
        59: iload_2
        60: aastore
        61: dup
        62: iconst_1
        63: aload_0
        64: getfield
        65: nop
        66: lstore_2
        67: invokevirtual
        68: nop
        69: dstore_2
        70: invokestatic
        71: nop
        72: astore_3
        73: aastore
        74: invokestatic
        75: nop
        76: bastore
        77: invokevirtual
        78: nop
        79: pop2
        80: goto
        81: nop
        82: iconst_5
        83: astore_2
        84: aload_2
        85: invokevirtual
        86: nop
        87: dup2
        88: return
      Exception table:
         from    to target type
            0    80    83 java/io/FileNotFoundException
      LineNumberTable
        line 38: 0
        line 39: 16
        line 40: 27
        line 41: 35
        line 42: 45
        line 45: 80
        line 43: 83
        line 44: 84
        line 46: 88
      LocalVariableTable:
         Start Length   Slot Name Signature
            16     64      2 dis   Ljava/io/DataInputStream;
            84      4      2 e   Ljava/io/FileNotFoundException;
             0     89      0 this   Lcom/lxw/main/ClassFileExplorer;
             0     89      1 file   Ljava/io/File;
      StackMapTable: number_of_entries: 3
        frame_type = 251 /* same_frame_extended */
        frame_type = 66 /* same_locals_1_stack_frame_item */
          stack = [class java/io/FileNotFoundException]
        frame_type = 4 /* same */

  public ()Ljava/lang/String; toString()
    descriptor: ()Ljava/lang/String;
    flags: (0x0001)  ACC_PUBLIC
    Code:
      stack=5, locals=1, args_size=1
         0: ldc
         1: swap
         2: iconst_2
         3: anewarray
         4: nop
         5: fconst_2
         6: dup
         7: iconst_0
         8: aload_0
         9: getfield
        10: nop
        11: dload
        12: invokevirtual
        13: nop
        14: ladd
        15: aastore
        16: dup
        17: iconst_1
        18: aload_0
        19: getfield
        20: nop
        21: lstore_2
        22: aastore
        23: invokestatic
        24: nop
        25: bastore
        26: areturn
      LineNumberTable
        line 50: 0
      LocalVariableTable:
         Start Length   Slot Name Signature
             0     27      0 this   Lcom/lxw/main/ClassFileExplorer;

  public static ([Ljava/lang/String;)V main()
    descriptor: ([Ljava/lang/String;)V
    flags: (0x0009)  ACC_PUBLIC ACC_STATIC
    Code:
      stack=4, locals=1, args_size=1
         0: getstatic
         1: nop
         2: dload_2
         3: new
         4: nop
         5: iconst_5
         6: dup
         7: ldc
         8: isub
         9: invokespecial
        10: nop
        11: fsub
        12: invokevirtual
        13: nop
        14: dsub
        15: return
      LineNumberTable
        line 54: 0
        line 55: 15
      LocalVariableTable:
         Start Length   Slot Name Signature
             0     16      0 args   [Ljava/lang/String;

  public static (Ljava/io/File;)V decodeAll()
    descriptor: (Ljava/io/File;)V
    flags: (0x0009)  ACC_PUBLIC ACC_STATIC
    Code:
      stack=4, locals=6, args_size=3
         0: aload_0
         1: invokedynamic
         2: nop
         3: fmul
         4: nop
         5: nop
         6: invokevirtual
         7: nop
         8: fdiv
         9: astore_1
        10: aload_1
        11: astore_2
        12: aload_2
        13: arraylength
        14: istore_3
        15: iconst_0
        16: istore
        17: iconst_1
        18: iload
        19: iconst_1
        20: iload_3
        21: if_icmpge
        22: nop
        23: iaload
        24: aload_2
        25: iload
        26: iconst_1
        27: aaload
        28: astore
        29: iconst_2
        30: aload
        31: iconst_2
        32: invokevirtual
        33: nop
        34: frem
        35: ifeq
        36: nop
        37: fconst_0
        38: aload
        39: iconst_2
        40: invokestatic
        41: nop
        42: lneg
        43: goto
        44: nop
        45: ldc
        46: getstatic
        47: nop
        48: dload_2
        49: new
        50: nop
        51: iconst_5
        52: dup
        53: aload
        54: iconst_2
        55: invokespecial
        56: nop
        57: iconst_4
        58: invokevirtual
        59: nop
        60: dsub
        61: iinc
        62: iconst_1
        63: aconst_null
        64: goto
        65: impdep2
        66: not match code.
        67: return
      LineNumberTable
        line 58: 0
        line 62: 10
        line 63: 30
        line 65: 46
        line 62: 61
        line 68: 67
      LocalVariableTable:
         Start Length   Slot Name Signature
            30     31      5 file   Ljava/io/File;
             0     68      0 path   Ljava/io/File;
            10     58      1 files   [Ljava/io/File;
      StackMapTable: number_of_entries: 4
        frame_type = 255 /* full_frame */
          offset_delta: 18
          locals = [ class java/io/File, class "[Ljava/io/File;", class "[Ljava/io/File;", int, int ]
          stacks = [ ]
        frame_type = 252 /* append */
          offset_delta = 27
          locals = [ class java/io/File ]
        frame_type = 250 /* chop */
          offset_delta=14
        frame_type = 248 /* chop */
          offset_delta=5

  private static (Ljava/io/File;)Z lambda$decodeAll$0()
    descriptor: (Ljava/io/File;)Z
    flags: (0x100A)  ACC_PRIVATE ACC_STATIC ACC_SYNTHETIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: invokevirtual
         2: nop
         3: iload_2
         4: ldc
         5: ishl
         6: invokevirtual
         7: nop
         8: ishr
         9: ifne
        10: nop
        11: lconst_1
        12: aload_0
        13: invokevirtual
        14: nop
        15: frem
        16: ifeq
        17: nop
        18: iconst_4
        19: iconst_1
        20: goto
        21: nop
        22: iconst_1
        23: iconst_0
        24: ireturn
      LineNumberTable
        line 59: 0
      LocalVariableTable:
         Start Length   Slot Name Signature
             0     25      0 f   Ljava/io/File;
      StackMapTable: number_of_entries: 3
        frame_type = 19 /* same */
        frame_type = 3 /* same */
        frame_type = 64 /* same_locals_1_stack_frame_item */
          stack = [int]


}
SourceFile: "ClassFileExplorer.java"
BootstrapMethods:
  0: #153 REF_invokeStatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #160 (Ljava/io/File;)Z
      #161 REF_invokeStatic com/lxw/main/ClassFileExplorer.lambda$decodeAll$0:(Ljava/io/File;)Z
      #160 (Ljava/io/File;)Z

InnerClasses:
  public static final #169= #165 of #167 // Lookup= class java/lang/invoke/MethodHandles$Lookup of class java/lang/invoke/MethodHandles
```

