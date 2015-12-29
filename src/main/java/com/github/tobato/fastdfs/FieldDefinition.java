package com.github.tobato.fastdfs;

/**
 * 单元定义
 * 
 * @author wuyf
 *
 */
class FieldDefinition {
    /** 偏移量 */
    private int offset;
    /** 单元长度 */
    private int size;

    FieldDefinition(int offset, int size) {
        this.offset = offset;
        this.size = size;
    }

    /**
     * @return the offset
     */
    int getOffset() {
        return offset;
    }

    /**
     * @return the size
     */
    int getSize() {
        return size;
    }
}
