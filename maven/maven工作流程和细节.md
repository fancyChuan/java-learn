
| 执行过程                    | 执行内容                                                                                                    |
|-------------------------|---------------------------------------------------------------------------------------------------------|
| resources:resources     | Resources插件的resources目标绑定到了resources 阶段。这个目标复制src/main/resources下的所有资源和其它任何配置的资源目录，到输出目录。               |
| compiler:compile        | Compiler插件的compile目标绑定到了compile 阶段。这个目标编译src/main/java下的所有源代码和其他任何配置的资源目录，到输出目录。                        |
| resources:testResources | Resources插件的testResources目标绑定到了test-resources 阶段。这个目标复制src/test/resources下的所有资源和其它任何的配置的测试资源目录，到测试输出目录。 |
| compiler:testCompile    | Compiler插件的testCompile目标绑定到了test-compile 阶段。这个目标编译src/test/java下的测试用例和其它任何的配置的测试资源目录，到测试输出目录。           |
| surefire:test           | Surefire插件的test目标绑定到了test 阶段。这个目标运行所有的测试并且创建那些捕捉详细测试结果的输出文件。默认情况下，如果有测试失败，这个目标会终止。                      |
| jar:jarJar              | 插件的jar目标绑定到了package 阶段。这个目标把输出目录打包成JAR文件。                                                               |



1. Settings.xml的生效问题
   maven共有两个settings.xml:
   全局配置: ${M2_HOME}/conf/settings.xml
   用户配置: ${user.home}/.m2/settings.xml
   maven使用两者的合并信息，用户配置的优先级高于全局配置。其有mvn -s(–settings)可以修改用户配置.

2. 多个Profile的生效问题
   Profile根据环境条件被激活:
1) <activeByDefault>true</activeByDefault>
2) <activeProfile>profileName</activeProfile>
3) ..
   如果多个Profile同时激活，则Profile会合并，相同的配置则后定义的Profile优先级比较高。与active的顺序无关。

3. repository的搜索顺序
   repository分两类：本地仓库和远程仓库。远程仓库包括全局仓库，项目仓库和中央仓库。搜索顺序如下：
1) 本地仓库: 就是本地的缓存目录，一般~/m2/repository
2) 全局仓库: setting文件profile中配置的仓库。
   3）项目仓库: 项目pom.xml中profile配置的仓库。
   4）项目仓库: 项目pom.xml中配置的仓库。
5) 中央仓库：就是central仓库