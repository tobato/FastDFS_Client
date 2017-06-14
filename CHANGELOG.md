## 1.25.4 (2017-06-14)

Bugfixes:

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

Bugfixes:

  - 修正打包时候将application.yml打包的问题 (#1,@wmz7year)
  - 修正设置MetaData错误 (#5, @yuck0419)
