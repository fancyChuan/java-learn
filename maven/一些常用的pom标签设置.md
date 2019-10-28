## 常用的一些pom配置
- 修改工程默认的JDK版本
```
<profile>
  <id>jdk-1.8</id>
  <activation>
	<activeByDefault>true</activeByDefault>
	<jdk>1.8</jdk>
  </activation>
  <properties>
	  <maven.compiler.source>1.8</maven.compiler.source>
	  <maven.compiler.target>1.8</maven.compiler.target>
	  <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
  </properties>
</profile>
```

- 解决项目测试乱码问题
```
<build>
	<plugins>
		<!-- 解决maven test命令时console出现中文乱码乱码 -->
		<plugin>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-surefire-plugin</artifactId>
			<version>2.7.2</version>
			<configuration>
				<forkMode>once</forkMode><!--在一个进程中进行所有测试 ; 默认值:once -->
				<argLine>-Dfile.encoding=UTF-8</argLine>
			</configuration>
		</plugin>
	</plugins>
</build>

```
- 将非resources下的xml等资源文件打包进classes目录
```
    <packaging>jar</packaging> <!-- 这个地方不能少！如果没有这一行，加了下面的内容还是不生效的 -->
    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            </resource>
        </resources>
    </build>
```
