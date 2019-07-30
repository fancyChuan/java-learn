## Maven

核心用途：
- 项目自动构建
- 依赖管理
- (项目信息管理)

区分包的三要素：
- groupId: 域名反写
- artifactId: 项目中的模块名
- version: 版本号
- package: 打包方式（jar, war）


### 依赖管理
- 使用三要素坐标引用依赖项目
- 依赖的范围：由scope标签指定
    - compile: 默认值。编译范围有效：编译、测试、运行
    - test: 测试依赖范围。只对测试classpath有效
    - provided: 已提供以来范围。对于编译、测试的classpath有效，对运行无效。因为容器已提供

依赖范围 | 对主代码class有效 | 对测试classpath有效 | 被打包，对运行时classpath有效 | 例子
--- | --- | --- | --- | ---
compile | Y | Y | Y | log4j
test | - | Y | - | junit
provided | Y | Y | - | servlet-api
runtime | - | - | Y | JDBC Driver

- 依赖原则
    - 路径最近这优先
    - 路径相同最先声明者优先
    

### 仓库管理
本地、远程（私服、中央仓库、镜像）

### 生命周期
- 定义：
    - Maven生命周期是对所有构建过程的抽象和统一
    - 包括：项目清理、初始化、编译、打包、测试、部署等
- Maven的三套相互独立的生命周期
    - Clean Lifecycle 在正在构建前进行一些清理工作
    - Default Lifecycle 构建的核心部分，编译、测试、打包、安装、部署等
    - Site Lifecycle 生成项目报告，站点，发布站点
```
mvn clean 
mvn install
mvn site
```    

### 常用编译命令
命　令 | 含　义
--- | ---
mvn clean | 清理编译结果
mvn compile [-Pnative] | 编译源代码
mvn test [-Pnative] | 运行测试程序
mvn package | 创建JAR 包
mvn compile findbugs:findbugs | 运行findbugs
mvn compile checkstyle:checkstyle | 运行checkstyle（检查编程规范）
mvn install | 将JAR 包放到M2 缓存中
mvn deploy | 将JAR 部署到Maven 仓库中
mvn package [-Pdist][-Pdocs][-Psrc][-Pnative][-Dtar] | 构建发布版
mvn versions:set -DnewVersion=NEWVERSION | 修改版本

- 如果仅编译生成JAR 包而无须编译native code、测试用例和生成文档，使用以下命令：
```
mvn package -Pdist -DskipTests -Dtar
```  
- 如果编译JAR 包、native code 并生成文档，可使用以下命令：
```
mvn package -Pdist,native,docs -DskipTests -Dtar
```
- 如果仅编译Hadoop 的某一个子模块，需将该模块依赖的JAR 包作为它的第三方库引入。一种简单的实现方式是在Hadoop安装目录下输入以下命令编译所有源代码：
```mvn install -DskipTests``` 然后进入子模块目录，编译生成对应的JAR 包。

### Maven插件
- Maven的核心仅仅定义了抽象的生命周期，具体的任务都是交由插件完成的。
- 每个插件都能实现多个功能，每个功能就是一个插件目标
- Maven的生命周期与插件目标相互绑定，以完成某个具体的构建任务。
> compile就是插件maven-compiler-plugin的一个目标；


### 项目继承
- 父项目 parent
    - 修改pom.xml文件，<packaging>jar</packaging>改为<packaging>pom</packaging>
    - 把项目中的dependencies标签改为 dependencyManagement
    - 把子项目聚合，在modules标签下添加所有子项目
```
    <!-- 实现多个模块工程的聚合 -->
	<modules>
		<!-- pom文件的相对路径 -->
		<module>../Hello/pom.xml</module>
		<module>../HelloFriend/pom.xml</module>
		<module>../MakeFriends/pom.xml</module>
		<module>../HappyWeb/pom.xml</module>
	</modules>
```
- 子项目
    - 在pom.xml中，添加
```
<parent>
    <groupId>...</groupId>
	<artifactId>...</artifactId>
	<version>...</version>
	<relativePath>从当前目录到父项目的pom.xml文件的相对路径</relativePath>
</parent>
```

## pom文件常用元素
元素名 | 含义 | 示例
--- | --- | ---
classifier | 类似于关键词，识别特定的jar包 | json-lib-2.4-jdk15.jar json-lib-2.4-jdk13.jar 可以通过classifier=jdk15 识别


## Maven打包机制
#### src/main/java和src/test/java

这两个目录中的所有*.java文件会分别在comile和test-comiple阶段被编译，编译结果分别放到了target/classes和targe/test-classes目录中，但是这两个目录中的其他文件都会被忽略掉。

#### src/main/resouces和src/test/resources

这两个目录中的文件也会分别被复制到target/classes和target/test-classes目录中。

#### target/classes

打包插件默认会把这个目录中的所有内容打入到jar包或者war包中。

## 其他
- 打包指定JDK版本
```
<plugin>  
    <groupId>org.apache.maven.plugins</groupId>  
    <artifactId>maven-compiler-plugin</artifactId>  
    <version>3.1</version>  
    <configuration>  
        <verbose>true</verbose>  
        <fork>true</fork>  
        <executable>${JAVA_HOME}/bin/javac</executable>  
    </configuration>  
</plugin>  
```
另外一种方式
```
<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>
			</plugin>
		</plugins>
	</build>
```
配置的方式
```
<profiles>
        <profile>
            <id>JDK1.8</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <maven.compiler.source>1.8</maven.compiler.source>
                <maven.compiler.target>1.8</maven.compiler.target>
            </properties>
        </profile>
    </profiles>
```
一级配置的方式
```
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
</properties>
<dependencies>
...
</dependencies>
```