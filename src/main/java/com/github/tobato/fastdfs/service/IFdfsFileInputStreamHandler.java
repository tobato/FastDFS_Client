package com.github.tobato.fastdfs.service;

import java.io.IOException;
import java.io.InputStream;

public interface IFdfsFileInputStreamHandler<T> {

    /**
     * 处理完毕后根据ins的实际情况进行关闭socket操作，注意不能直接返回入参的InputStream，因为此方法返回后将关闭原输入流
     * 
     * @param ins
     * @throws IOException
     * @return
     */
    T deal(InputStream ins) throws IOException;

}
