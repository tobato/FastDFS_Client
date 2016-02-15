package com.github.tobato.fastdfs.proto.storage.enums;

/**
 * 元数据设置方式
 * 
 * @author tobato
 *
 */
public enum StorageMetdataSetType {

    /** 覆盖 */
    STORAGE_SET_METADATA_FLAG_OVERWRITE((byte) 'O'),
    /** 没有的条目增加，有则条目覆盖 */
    STORAGE_SET_METADATA_FLAG_MERGE((byte) 'M');

    private byte type;

    private StorageMetdataSetType(byte type) {
        this.type = type;
    }

    public byte getType() {
        return type;
    }

}
