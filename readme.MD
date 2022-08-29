1.使用maven pakage 打jar包，必须配置plugin，才能将依赖一起打包，还会自动生成启动信息
2.启动命令：java -Dfile.encoding=utf-8 -jar hsjc-1.0-SNAPSHOT-jar-with-dependencies.jar 参数1 参数2 参数3
3.详情请见https://blog.csdn.net/m0_37840862/article/details/119523526
4.javafx包，桌面启动手动输入参数，接收到参数可以继续运行程序
5.windows后台静默运行java，不打开命令行工具：
@echo off
start javaw -jar demo.jar --spring.config.location=application.properties
exit
