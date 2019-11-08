package jaas.apps.demo;

import javax.security.auth.callback.*;
import java.io.IOException;

public class DemoCallbackHandler implements CallbackHandler {

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        NameCallback nameCallback = (NameCallback) callbacks[0];
        PasswordCallback passwordCallback = (PasswordCallback) callbacks[1];
        // 设置用户名和密码
        nameCallback.setName(getUserName());
        passwordCallback.setPassword(getPassword().toCharArray());
    }

    public String getUserName() {
        return "fancy";
    }

    public String getPassword() {
        return "fancy";
    }
}
