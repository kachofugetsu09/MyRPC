package site.hnfy258.RPCVer2.service;

import site.hnfy258.RPCVer2.common.User;

public interface UserService {
    User getUserByUserId(Integer id);
    Integer insertUserId(User user);
}
