package com.lxw.main.common.inter;

import com.lxw.main.common.abs.MultipleItemsDefault;

import java.io.DataInputStream;

/**
 * @Description: TODO
 * @Author: lxw
 * @File: MultipleReaderInterface.java
 * @Date: 2021-06-11 19:26
 * @Version: V0.0
 */


public interface MultipleReaderInterface{
    void read(DataInputStream dis, int count, MultipleItemsDefault[] es);
}
