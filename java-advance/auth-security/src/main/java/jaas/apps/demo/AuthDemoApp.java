package jaas.apps.demo;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.PrivilegedAction;

public class AuthDemoApp {
    public static void main(String[] args) {
        String classPath = AuthDemoApp.class.getResource("").getPath();
        System.out.println(classPath);
        System.setProperty("java.security.auth.login.config", classPath + "demo.config");
        System.setProperty("java.security.policy", classPath + "demo.policy");
        System.setSecurityManager(new SecurityManager());

        try {
            LoginContext context = new LoginContext("demo", new DemoCallbackHandler());
            context.login();
            Subject subject = context.getSubject();
            Subject.doAsPrivileged(subject, (PrivilegedAction<Object>) () -> {
                System.out.println(System.getProperty("java.home"));
                return null;
            }, null);

        } catch (LoginException e) {
            System.err.println("Cannot create LoginContext. " + e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        } catch (SecurityException se) {
            System.err.println("Cannot create LoginContext. " + se.getMessage());
            System.exit(-1);
        }
    }




}
