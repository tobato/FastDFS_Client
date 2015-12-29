package com.github.tobato.fastdfs.proto.handler;

/**
 * 将与服务器的交易抽象为命令处理接口
 * 
 * @author tobato
 *
 * @param <T>
 */
public interface ICmdProtoHandler<T> {

    /**
     * 处理
     * 
     */
    T handle();

}
