package site.hnfy258.RPCVer3.service;

import site.hnfy258.RPCVer3.common.User;

public interface UserService {
    User getUserByUserId(Integer id);
    Integer insertUserId(User user);
}
