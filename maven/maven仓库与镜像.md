## maven仓库与镜像


阿里云效-仓库服务：[https://maven.aliyun.com/mvn/guide](https://maven.aliyun.com/mvn/guide)

仓库名称 | 阿里云仓库地址 | 阿里云仓库地址(老版) | 源地址
--- | --- | --- | --- 
central | https://maven.aliyun.com/repository/central | https://maven.aliyun.com/nexus/content/repositories/central | https://repo1.maven.org/maven2/
jcenter | https://maven.aliyun.com/repository/public | https://maven.aliyun.com/nexus/content/repositories/jcenter | http://jcenter.bintray.com/
public | https://maven.aliyun.com/repository/public | https://maven.aliyun.com/nexus/content/groups/public | central仓和jcenter仓的聚合仓
google | https://maven.aliyun.com/repository/google | https://maven.aliyun.com/nexus/content/repositories/google | https://maven.google.com/
gradle-plugin | https://maven.aliyun.com/repository/gradle-plugin | https://maven.aliyun.com/nexus/content/repositories/gradle-plugin | https://plugins.gradle.org/m2/
spring | https://maven.aliyun.com/repository/spring | https://maven.aliyun.com/nexus/content/repositories/spring | http://repo.spring.io/libs-milestone/
spring-plugin | https://maven.aliyun.com/repository/spring-plugin | https://maven.aliyun.com/nexus/content/repositories/spring-plugin | http://repo.spring.io/plugins-release/
grails-core | https://maven.aliyun.com/repository/grails-core | https://maven.aliyun.com/nexus/content/repositories/grails-core | https://repo.grails.org/grails/core
apache snapshots | https://maven.aliyun.com/repository/apache-snapshots | https://maven.aliyun.com/nexus/content/repositories/apache-snapshots | https://repository.apache.org/snapshots/


镜像配置
```
<mirror>
      <id>tz-mirror</id>
      <mirrorOf>external:*,!mmkj</mirrorOf>
      <name>tz test nexus repository</name>
      <url>http://xxxxx:30003/repository/maven-proxy</url>
</mirror>
```
- id 唯一标识
- mirrorOf 指定镜像的规则。就是什么情况会从镜像仓库拉取，而不是从原本的仓库拉取
```
可选项参考链接：
* 匹配所有
external:* 除了本地缓存之后的所有仓库
repo,repo1 repo 或者 repo1。 这里repo指的是仓库的id，下文会提到
*,!repo1 除了repo1的所有仓库
```
- name 名称描述
- url 地址

#### 几种仓库的设置
**pom文件中**
```
<repositories>
    <repository>
        <id>dsdf</id>
        <releases>
            <enabled>true</enabled>
        </releases>
        <url>http://222.197.XXXXXX/nexus/content/groups/public/</url>
    </repository>
</repositories>
```

**profile中的仓库是在maven的设置文件（maven安装目录/conf/settings.xml）**
```
<profile>
    <id>nexus</id>
    <repositories>
        <repository>
            <id>sonatype-forge</id>
            <url>http://repository.sonatype.org/content/groups/forge/</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
    </repositories>
    <pluginRepositories>
        <pluginRepository>
            <id>sonatype-forge</id>
            <url>http://repository.sonatype.org/content/groups/forge/</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </pluginRepository>
    </pluginRepositories>
</profile>

<!-- 使用下面代码来激活profile–>
<activeProfiles>
    <activeProfile>nexus</activeProfile>
</activeProfiles>
```
maven profile也是有优先级别
1. 在settings.xml中的profile优先级高于pom中的
2. 同在settings.xml的properties，如果都激活了，根据profile定义的先后顺序来进行覆盖取值，后面定义的会覆盖前面，其properties为同名properties中最终有效。并不是根据activeProfile定义的顺序 。
3. 如果有user setting和globel settings，则两者合并，其中重复的配置，以user settings为准。


参考资料：
- [maven - mirrorOf 的坑、多镜像切换（避免一切无厘头报错）](https://blog.nowcoder.net/n/1166815f837244caa74bf9f62c43d04c)
- [Maven配置多仓库无效？来看看这篇文章](https://cloud.tencent.com/developer/article/1677690)
