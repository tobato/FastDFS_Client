FastDFS-Client 1.27.2(2020-2-11)
---

This is a java client lib for [FastDFS](https://github.com/happyfish100/fastdfs).

## 介绍

在原作者YuQing与yuqih发布的java客户端基础上进行了大量重构工作，便于Java工作者学习与阅读。

当前客户端单元测试全部通过，服务端版本是FastDFS_V5.07

主要特性

1. 对关键部分代码加入了单元测试，便于理解与服务端的接口交易，提高接口质量
2. 将以前对byte硬解析风格重构为使用 对象+注解 的形式，尽量增强了代码的可读性
3. 支持对服务端的连接池管理(commons-pool2)
4. 支持上传图片时候检查图片格式，并且自动生成缩略图
5. 在SpringBoot当中自动导入依赖

## 参考文档

想要学习掌握更多FastDFS的架构原理可以参考本[项目文档](https://github.com/tobato/FastDFS_Client/wiki).

## 修改日志

版本更新情况请查看[修改日志](/CHANGELOG.md)

## 运行环境要求

由于笔者主要工作环境是SpringBoot，因此目前客户端主要依赖于SpringBoot

    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.0.0.RELEASE</version>
    <relativePath />
    
* JDK环境要求  1.8
* FastDFS服务端 5.07 测试通过

## 单元测试

由于工作时间关系与解析原代码的复杂性，单元测试无法完全做到脱离FastDFS服务端，请见谅。

执行单元测试需要配置TestConstants文件当中参数

在Tracker与Storage都在一个机器的环境下

      private static String ip_home = "192.168.1.105";
      public static InetSocketAddress address = new InetSocketAddress(ip_home, FdfsMockSocketServer.PORT);
      public static InetSocketAddress store_address = new InetSocketAddress(ip_home, FdfsMockSocketServer.STORE_PORT);
      
      public static final String DEFAULT_STORAGE_IP = ip_home;
  
      
在Tracker与Storage不在一个机器的环境下      
     
    private static String ip_work = "192.168.174.47";
    private static String ip_work_store = "192.168.174.49";
    public static InetSocketAddress address = new InetSocketAddress(ip_work, FdfsMockSocketServer.PORT);
    public static InetSocketAddress store_address = new InetSocketAddress(ip_work_store, FdfsMockSocketServer.STORE_PORT);
    
    public static final String DEFAULT_STORAGE_IP = ip_work_store;
   

## FastDFS-Client使用方式

### 1.在项目Pom当中加入依赖

Maven依赖为

    <dependency>
        <groupId>com.github.tobato</groupId>
        <artifactId>fastdfs-client</artifactId>
        <version>1.27.2</version>
    </dependency>


### 2.将Fdfs配置引入项目

在Maven当中配置依赖以后，SpringBoot项目将会自动导入FastDFS依赖(感谢@Lzgabel)。

#### FastDFS-Client 1.26.4版本以前引入方式

将FastDFS-Client客户端引入本地化项目的方式非常简单，在SpringBoot项目`/src/[com.xxx.主目录]/conf`当中配置

    /**
     * 导入FastDFS-Client组件
     * 
     * @author tobato
     *
     */
    @Configuration
    @Import(FdfsClientConfig.class)
    // 解决jmx重复注册bean的问题
    @EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
    public class ComponetImport {
        // 导入依赖组件
    }
    
只需要一行注解 @Import(FdfsClientConfig.class)就可以拥有带有连接池的FastDFS Java客户端了。

>注意：`@EnableMBeanExport`解决问题JMX重复注册问题,[issue #8](../../issues/8) [issue #18](../../issues/8)，不要再配置 `spring.jmx.enabled=false`，以免影响SpringBoot默认的JMX监控。

### 3.在application.yml当中配置Fdfs相关参数
    # ===================================================================
    # 分布式文件系统FDFS配置
    # ===================================================================
    fdfs:
      so-timeout: 1501
      connect-timeout: 601 
      thumb-image:             #缩略图生成参数
        width: 150
        height: 150
      tracker-list:            #TrackerList参数,支持多个
        - 192.168.1.105:22122
        - 192.168.1.106:22122 

### 4.连接池的管理参数

应用启动后拥有两个连接池管理对象:

* Tracker连接池(`TrackerConnectionManager`)
* Storage连接池(`FdfsConnectionManager`)

必要的时候可以注入这两个对象，跟踪打印并分析连接池的情况

两个连接池的参数配置一致，可参考 ConnectionPoolConfig 与 apache.pool2 进行优化配置，默认配置为

    fdfs:
       ..其他配置信息..
      pool:
        #从池中借出的对象的最大数目（配置为-1表示不限制）
        max-total: -1
        #获取连接时的最大等待毫秒数(默认配置为5秒)
        max-wait-millis: 5*1000
        #每个key最大连接数
        max-total-per-key: 50
        #每个key对应的连接池最大空闲连接数
        max-idle-per-key: 10
        #每个key对应的连接池最小空闲连接数
        min-idle-per-key: 5

注意: key配置的是连接服务端的地址(IP+端口)连接情况，如果有连接不够用的情况可以调整以上参数

### 4.使用接口服务对Fdfs服务端进行操作

主要接口包括

1. TrackerClient - TrackerServer接口 
2. GenerateStorageClient - 一般文件存储接口 (StorageServer接口)
3. FastFileStorageClient - 为方便项目开发集成的简单接口(StorageServer接口)
4. AppendFileStorageClient - 支持文件续传操作的接口 (StorageServer接口)


## 常见问题

### 1.如何在没有spring-boot的情况下使用？

参考下面文章进行改造

https://blog.csdn.net/wzl19870309/article/details/74049204

### 2.高并发下测试出现上传的文件和得到的返回路径的文件不是同一个？

通过加大超时时间后解决

    soTimeout: 1500
    connectTimeout: 600

### 3.新手不会用

阅读单元测试，从学习test/java/com/github/tobato/fastdfs/service下的单元测试入手

### 4.生成的缩略图怎么访问？

缩略图为上传文件名+缩略图后缀(默认_150x150)

如:源图上传后路径为 xxx.jpg,缩略图为 xxx_150x150.jpg

    源图 http://localhost:8098/M00/00/17/rBEAAl33pQaAWNQNAAHYvQQn-YE374.jpg
    缩略图 http://localhost:8098/M00/00/17/rBEAAl33pQaAWNQNAAHYvQQn-YE374_150x150.jpg
    
### 5.返回的文件名看起来乱糟糟的，能自定义文件名吗？能指定上传路径吗？

上传以后的文件名是FastDfs服务端根据一定规则生成的，不可以修改文件名，因此也不可以由客户端指定上传的路径。
客户端只能控制把文件上传到哪一个分组(Group).

一般处理方法是把服务端返回的文件路径记录到数据库里，自己按需要在数据库里再记录一个便于识别的文件名。

### 6.请问当上传100M以上文件怎么才能获取进度?

FastDFS设计是用来存储小文件的，过大的文件处理方案是拆分为小文件，可跟踪小文件的上传情况。
如果应用场景都是处理大文件，可能选择其他分布式文件系统方案会更合适。

### 7.文件扩展名字数长度限制?

我需要上传文件扩展名很长的文件到fastdfs，但是发现扩展名总是会被截取，追了一下发现是Constants限制了FDFS_FILE_EXT_NAME_MAX_LEN = 6 ，请问这个参数可以配置吗？应该怎么修改呢？
在我将otherConstants类里的FDFS_FILE_EXT_NAME_MAX_LEN的值从6改到16时, 尝试传一张后缀长的文件时,会报错无效参数, 
原因是服务器上的fastDFS源码(c语言编写的那个)的commons包下fdfs_global.h中FDFS_FILE_EXT_NAME_MAX_LEN=6, 于是将6改为16, make.sh重新编译后, 
解决了传后缀长文件的问题（#157 感谢@787390869提供解答）


## 其他参考资料

对于FDFS服务端相关的问题可以在下面的论坛找到一些材料

[FastDFS论坛](http://bbs.chinaunix.net/forum-240-1.html)

[FastDFS常见问题](http://bbs.chinaunix.net/thread-1920470-1-1.html)
