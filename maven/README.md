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
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <compilerArgs>
                        <arg>-Xlint:unchecked</arg>
                    </compilerArgs>
                    <showDeprecation>true</showDeprecation>
                    <showWarnings>true</showWarnings>
                </configuration>
            </plugin>
```
