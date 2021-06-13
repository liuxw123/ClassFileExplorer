package com.lxw.main.common.inter;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: MultipleItemsInterface.java
 * @Date: 2021-06-11 19:43
 * @Version: V0.0
 */


public interface MultipleItemsInterface {
    void readMultiple(DataInputStream dis);
    Object get(int index);
    int size();
}
