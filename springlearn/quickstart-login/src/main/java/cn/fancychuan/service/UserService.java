package cn.fancychuan.service;

import cn.fancychuan.dao.LoginLogDao;
import cn.fancychuan.dao.UserDao;
import cn.fancychuan.domain.LoginLog;
import cn.fancychuan.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // 标注为一个服务层的Bean
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginLogDao loginLogDao;

    public boolean hasMatchUser(String userName, String password) {
        return userDao.getMatchCount(userName, password) > 0;
    }

    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    // 将两个DAO组织起来共同完成一个事务性的数据操作。也就是Spring声明式事务配置的具体使用方式
    public void loginSuccess(User user) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        loginLogDao.insertLoginLog(loginLog);
    }
}
