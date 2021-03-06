package cn.fancychuan.springboot.chap3.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 使用ConfigurationProperties来从配置文件中读取配置项，避免使用@Value
 */
@Component
@ConfigurationProperties("database")
public class DataBasePropertiesUsingConfigurationProperties {

    private String driverName = null;
    
    private String url = null;

    private String username = null;

    private String password = null;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        System.out.println("[=====]" + driverName);
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        System.out.println("[=====]" + url);
        this.url = url;
    }

    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        System.out.println("[=====]" + username);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        System.out.println("[=====]" + password);
        this.password = password;
    }
}
