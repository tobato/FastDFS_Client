package com.github.tobato.fastdfs.domain.fdfs;

import java.util.ArrayList;

/**
 * 基于ArrayList的循环链表类<br>
 * 第一次调用next()将返回第一个元素，调用previous()将返回最后一个元素
 *
 * @author yuqih
 */
class CircularList<E> extends ArrayList<E> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private int index = -1;

    /**
     * 重置，之后第一次调用next()将返回第一个元素，调用previous()将返回最后一个元素
     */
    public void reset() {
        synchronized (this) {
            index = -1;
        }
    }

    /**
     * 下一个元素
     *
     * @return
     */
    public E next() {
        check();

        synchronized (this) {
            index++;
            if (index >= this.size()) {
                index = 0;
            }
            return this.get(index);
        }

    }

    public E current() {
        check();

        synchronized (this) {
            if (index < 0) {
                index = 0;
            }
            return this.get(index);
        }
    }

    /**
     * 上一个元素
     *
     * @return
     */
    public E previous() {
        check();

        synchronized (this) {
            index--;
            if (index < 0) {
                index = this.size() - 1;
            }
            return this.get(index);
        }
    }

    private void check() {
        if (this.size() == 0) {
            throw new IndexOutOfBoundsException("空的列表，无法获取元素");
        }
    }

}
