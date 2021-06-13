package com.lxw.main;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: ClassFileExplorer.java
 * @Date: 2021-06-10 23:29
 * @Version: V0.0
 */


public class ClassFileExplorer {
    private File classFile;
    private ClassBean classBean;

    public ClassFileExplorer(String path) {
        this(new File(path));
    }

    public ClassFileExplorer(File classFile) {
        if (classFile == null || (!classFile.isFile()) || (!classFile.exists())) return;
        this.classFile = classFile;
        if (classFile.getName().equals("MethodInfos.class"))
            System.out.println();
        decode(classFile);
    }

    private void decode(File file) {
        try {
//            if (file.getName().equals("Jz12Solution.class"))
//                System.out.println();
//            System.out.println(String.format("file: %s start to decode.", file.getName()));
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            classBean = new ClassBean();
            classBean.read(dis);
            if (!classBean.isValid())
                System.out.println(String.format("file: %s, result: %s", file.getName(), classBean.isValid()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("ClassFile %s\n%s", classFile.getAbsolutePath(), classBean);
    }

    public static void main(String[] args) {
        File path = new File("/home/lxw/IdeaProjects");
        decodeAll(path);
    }

    public static void decodeAll(File path) {
        File[] files = path.listFiles(
                (f)->f.getName().endsWith(".class") || f.isDirectory()
        );

        for (File file: files) {
            if (file.isDirectory()) decodeAll(file);
            else {
                System.out.println(new ClassFileExplorer(file));
            }
        }
    }
}
