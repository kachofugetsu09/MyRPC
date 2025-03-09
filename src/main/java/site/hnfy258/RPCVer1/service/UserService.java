package site.hnfy258.RPCVer1.service;

import site.hnfy258.RPCVer1.common.User;

public interface UserService {
    User getUserByUserId(Integer id);
    Integer insertUserId(User user);
}
