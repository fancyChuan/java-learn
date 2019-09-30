
执行过程 | 执行内容
--- | --- 
resources:resources | Resources插件的resources目标绑定到了resources 阶段。这个目标复制src/main/resources下的所有资源和其它任何配置的资源目录，到输出目录。
compiler:compile | Compiler插件的compile目标绑定到了compile 阶段。这个目标编译src/main/java下的所有源代码和其他任何配置的资源目录，到输出目录。
resources:testResources | Resources插件的testResources目标绑定到了test-resources 阶段。这个目标复制src/test/resources下的所有资源和其它任何的配置的测试资源目录，到测试输出目录。
compiler:testCompile | Compiler插件的testCompile目标绑定到了test-compile 阶段。这个目标编译src/test/java下的测试用例和其它任何的配置的测试资源目录，到测试输出目录。
surefire:test | Surefire插件的test目标绑定到了test 阶段。这个目标运行所有的测试并且创建那些捕捉详细测试结果的输出文件。默认情况下，如果有测试失败，这个目标会终止。
jar:jarJar | 插件的jar目标绑定到了package 阶段。这个目标把输出目录打包成JAR文件。


