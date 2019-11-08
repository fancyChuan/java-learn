package jaas.apps.demo;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.security.Principal;
import java.util.Iterator;
import java.util.Map;

public class DemoLoginModule implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;
    private boolean success = false;
    private String user;
    private String password;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> map, Map<String, ?> map1) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        NameCallback nameCallback = new NameCallback("请输入用户名");
        PasswordCallback passwordCallback = new PasswordCallback("请输入密码", false);
        Callback[] callbacks = {nameCallback, passwordCallback};
        try {
            callbackHandler.handle(callbacks);
            user = nameCallback.getName();
            password = new String(passwordCallback.getPassword());
        } catch (IOException | UnsupportedCallbackException e) {
            success = false;
            throw new FailedLoginException("用户名或密码获取失效");
        }
        if (user.length() > 3 && password.length() > 3) {
            success = true;
        }
        return true; // todo:这里合理不？
    }

    @Override
    public boolean commit() throws LoginException {
        if (success) {
            // 认证成功到subject添加一个Principal对象
            // 表示用户认证通过并且登录了该应用，也就标识了谁在执行该程序
            this.subject.getPrincipals().add(new DemoPrincipal(user));
            return true;
        }
        return false;
    }

    @Override
    public boolean abort() throws LoginException {
        logout();
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        // 登出是需要把想要的principal对象从subject中剔除
        Iterator<Principal> iterator = subject.getPrincipals().iterator();
        while (iterator.hasNext()) {
            Principal principal = iterator.next();
            if (principal instanceof DemoPrincipal) {
                if (principal.getName().equals(user)) {
                    iterator.remove();
                    break;
                }
            }
        }
        return true;
    }
}
