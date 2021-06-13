package com.lxw.main.version;

import com.lxw.main.ClassFileComponent;
import com.lxw.main.common.inter.ReaderInterface;
import com.lxw.main.common.number.U2;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: Version.java
 * @Date: 2021-06-12 23:38
 * @Version: V0.0
 */


public class Version extends ClassFileComponent implements ReaderInterface {
    private U2 minorVersion;
    private U2 majorVersion;

    public Version(int printLevel) {
        super(printLevel);
        minorVersion = new U2();
        majorVersion = new U2();

    }

    @Override
    public void read(DataInputStream dis) {
        minorVersion.read(dis);
        majorVersion.read(dis);
    }

    @Override
    public String toString() {
        return String.format("major: %s, minor: %s (jdk-%d.%d)", majorVersion, minorVersion, majorVersion.get()-44, minorVersion.get()-0);
    }
}
