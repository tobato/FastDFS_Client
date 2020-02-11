## 1.27.2 (2020-2-11)

BugFixes:

   - 最新版1.27.1中央仓库没有jar包(#192 @cyping258)

说明:由于发布1.27.1版本时，中央仓库bug无法同步到Maven Repo，因此无变动升级版本号，重新发布。

## 1.27.1 (2020-1-27)

BugFixes:

   - 修复DEFAULT_RETRY_AFTER_SECOND拼写错误(#181 @liangxiong3403)
   - 修复metaDataSet default value logic bug(#182 @liangxiong3403)

Update:

   - JDK版本升级到1.8版本,做一个大的版本升级(#189 @HaoQiangJiang)
   - 重命名ConnectionManager ，解决与openfeign包冲突(#186 @summitxf)
   - 合并增加一种文件下载回调方法(#161 @xlb ),并在此基础上重构
   - 增加连接池控制参数：连接空闲的最小时间、创建时是否进行连接测试、借出时是否检测有效性
   - 调整连接池默认参数，关闭自动检查机制，如果需要自动检查，请手动开启
   
   感谢@HaoQiangJiang @summitxf 提交更新

Docs:

   - 常见问题:生成的缩略图该怎么访问 (#179 @msh01)
   - 常见问题:能自定义上传路径么，或者有没有类似的解决方案(#173 @yj348382870)
   - 常见问题:请问当上传100M以上文件怎么才能获取进度(#172 @lxge)
   
升级提示:

  - 没有接口改动，默认JDK升级到1.8版本

## 1.26.7 (2019-08-24)

BugFixes:

   - 修复StorePath.getGroupName中有额外的输出(#137 @jerry-yuan)
   - 修复DefaultGenerateStorageClient当中错误的LOGGER.error(#136 @Gsmsu @zhangmrit)
   
升级提示:

  - 没有接口改动，可以直接从1.26.6升级
   
## 1.26.6 (2019-06-08)

BugFixes:

   - 修复commons-beanutils版本安全漏洞(#94 @robby0321 )
   - 修复PNG背景颜色为透明时，生成缩略图背景颜色为黑色(#110 @lude008)
   - 修复当发生异常时，怎么把链接中对应的流数据一次性清空，处理方案：在连接池移除此连接(#121 @tdwu)
   - 修复连接池不能正常分配问题(#116 @JackHuang0801)
   - 修复FileInfe实体类ToString时间一直为1970年的问题(#109 @ENCHIGO)
   
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
