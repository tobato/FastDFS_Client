##版本发布指南

此文档描述版本发布构建指南。

版本定义管理参照 sipub 项目版本定义进行管理

版本发布过程参照 sipub 项目发布过程
 
在CC上加入视图 Tutorials_Release 路径为

    F:\yardman_release 

在CC上删除视图 Tutorials_Release

更新cc上构建视图Tutorials\_Dev_Yardman

进入构建路径执行发布操作
  
    cd F:\cc\Tutorials_Dev_Yardman\Tutorials\Yardman
    mvn validate release:prepare -DautoVersionSubmodules=true     

版本发布的命令为
 
    mvn release:perform -DworkingDirectory=F:\yardman_release -DconnectionUrl="scm:clearcase|Tutorials_Release|load \Tutorials\Yardman|\testvob1_pvob|Tutorials_Int" -Dgoals=install deploy

最后需要部署到Nexus服务器上

    cd F:\yardman_release\Tutorials\Yardman
    mvn deploy



