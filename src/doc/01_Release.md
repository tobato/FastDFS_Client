项目发布
----
   
  记录项目发布的过程，免得每次发布都重新想一遍。

## Docker环境准备

   启动Docker以后需要把FDFS服务打开

    bash
    /usr/bin/fdfs_trackerd /etc/fdfs/tracker.conf
    /usr/bin/fdfs_storaged /etc/fdfs/storage.conf
   
## 发布流程

### 1.修改文档

发布前先调整Readme.md 和 Changelog.md

### 2.项目发布准备

    mvn release:prepare -P release

>回滚失败的发布
>
>     mvn release:rollback 

### 3.正式发布提交

    mvn release:perform -P release
    
### 4.中央仓库地址

    构件发布地址 https://oss.sonatype.org/    
    
### 5.发布问题处理

* 如果需要跳过单元测试，可以加入参数 -Darguments="-DskipTests"
* 在执行mvn release:perform时默认会生成api文档，如果默写注释不符合规范的话会造成构建失败，可以加参数-DuseReleaseProfile=false取消构建api文档
  
      mvn release:prepare -Darguments="-DskipTests"
      mvn release:perform  -DuseReleaseProfile=false

## 参考资料

Maven Release Plugin that is used to automate release management. Releasing a project is done in two steps: prepare and perform. 

* Preparing a release goes through the following release phases:

  * Check that there are no uncommitted changes in the sources
  * Check that there are no SNAPSHOT dependencies
  * Change the version in the POMs from x-SNAPSHOT to a new version (you will be prompted for the versions to use)
  * Transform the SCM information in the POM to include the final destination of the tag
  * Run the project tests against the modified POMs to confirm everything is in working order
  * Commit the modified POMs
  * Tag the code in the SCM with a version name (this will be prompted for)
  * Bump the version in the POMs to a new value y-SNAPSHOT (these values will also be prompted for)
  * Commit the modified POMs

* Performing a release runs the following release phases:

  * Checkout from an SCM URL with optional tag
  * Run the predefined Maven goals to release the project (by default, deploy site-deploy)


   
   
