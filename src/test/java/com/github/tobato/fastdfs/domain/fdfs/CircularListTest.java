package com.github.tobato.fastdfs.domain.fdfs;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * 循环列表测试-支持列表轮询算法
 *
 * @author tobato
 */
public class CircularListTest {

    private CircularList<String> list;
    private String[] strs = {"1", "2", "3", "4"};

    @Before
    public void init() {
        list = new CircularList<String>();
        list.addAll(Arrays.asList(strs));
    }

    @Test
    public void testReset() {
        assertEquals(list.next(), strs[0]);
        assertEquals(list.next(), strs[1]);
        // 重置以后将从第一个数据获取
        list.reset();
        assertEquals(list.next(), strs[0]);
        assertEquals(list.next(), strs[1]);
    }

    @Test
    public void testNext() {
        assertEquals(list.next(), strs[0]);
        assertEquals(list.next(), strs[1]);
        assertEquals(list.next(), strs[2]);
        assertEquals(list.next(), strs[3]);
        assertEquals(list.next(), strs[0]);
        assertEquals(list.next(), strs[1]);
    }

    @Test
    public void testCurrent() {
        assertEquals(list.next(), strs[0]);
        assertEquals(list.next(), strs[1]);
        assertEquals(list.current(), strs[1]);
        assertEquals(list.current(), strs[1]);
    }

    @Test
    public void testPrevious() {
        assertEquals(list.next(), strs[0]);
        assertEquals(list.next(), strs[1]);
        assertEquals(list.previous(), strs[0]);
        assertEquals(list.previous(), strs[3]);
    }

}
