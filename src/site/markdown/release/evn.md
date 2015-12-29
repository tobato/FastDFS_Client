##构建环境搭建

由于加入了Markdown项目文档，因此构建项目站点时候，构建环境当中需要安装文档生成工具环境。

##安装gitbook

执行下面的命令安装gitbook

    npm install gitbook -g

##安装pdf生成环境

gitbook生成pdf文件需要使用[calibre](http://calibre-ebook.com/)工具集合，根据操作系统到[download下载](http://calibre-ebook.com/download)下载安装文件，进行安装。

安装完成以后，需要将calibre本地安装的路径如`C:\Program Files (x86)\Calibre2`
加入到系统path环境变量当中，以便gitbook编译时候可以顺利找到calibre组件。



