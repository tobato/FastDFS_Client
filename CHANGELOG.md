## 1.26.5 (2018-12-30)

Features:

   - 重构代码路径，优化内部模块关系
   - 支持缩略图生成按尺寸(width，height)和按比例缩放（#87 @BigBroB ）
   - 支持上传图片选择Group (#53 @595979101)
   - 在上传文件时既可以选择组名又可以添加metadata (#91 @WildSaCk)

BugFixes:

   - 修复删除文件路径错误的bug(#90 @xfxf521 )

## 1.26.4 (2018-11-10)

Features:

   - 加入自动加载机制spring boot autoconfigure（#82 @Lzgabel）,无需再@Import(FdfsClientConfig.class)

BugFixes:

   - 拼写错误Mate错误拼写为Mata(#85 @Abigale-ztg)
   
## 1.26.3 (2018-09-30)

BugFixes:

   - 依赖注入改为@Autowired强制按类型注入(#58 #79)
   
## 1.26.2 (2018-04-26)

BugFixes:

   - 修正downloadFile分段下载bug (#40,@ligq10)
   - 修正MetaData拼写错误 (#44, @ThomasYangZi)

## 1.26.1 (2018-03-20)

update:

  - SpringBoot依赖升级为2.0.0.RELEASE版本 (#35,#18,@zer0Black,@zhanwenjie,@caokaizz)
  
     感谢zer0Black提交升级，同时与大余JAVA客户端同步更新版本号为1.26主版本


## 1.25.4 (2017-06-14)

BugFixes:

  - 解决在SpringBoot项目当中JMX重复注册的问题 (#8,#18,@flykarry,@SevenSecondsOfMemory)
  
    错误信息

        javax.management.InstanceAlreadyExistsException: MXBean already registered with name org.apache.commons.pool2:type=GenericKeyedObjectPool,name=pool

    解决办法配置

        @EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
    
  
  
Docs:

  - 增加文档目录，把相关文档完善提交一下

## 1.25.3 (2017-03-11)

Features:

  - 支持缓存连接池配置
      
        fdfs:
          soTimeout: 1501
          connectTimeout: 601 
          thumbImage:
            width: 150
            height: 150
          trackerList:
             - 192.168.1.105:22122
          pool:
            #从池中借出的对象的最大数目
            maxTotal: 153
            #获取连接时的最大等待毫秒数100
            maxWaitMillis: 102


## 1.25.2 (2016-09-01)

Features:

  - 优化无法连接后端服务器时候的错误输出，输出连接配置地址

BugFixes:

  - 修正打包时候将application.yml打包的问题 (#1,@wmz7year)
  - 修正设置MetaData错误 (#5, @yuck0419)
