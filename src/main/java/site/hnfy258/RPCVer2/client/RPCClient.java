package site.hnfy258.RPCVer2.client;

import site.hnfy258.RPCVer2.common.Blog;
import site.hnfy258.RPCVer2.common.User;
import site.hnfy258.RPCVer2.service.BlogService;
import site.hnfy258.RPCVer2.service.UserService;

public class RPCClient {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
        UserService proxy = clientProxy.getProxy(UserService.class);
        BlogService proxy2 = clientProxy.getProxy(BlogService.class);

        // 服务的方法1
        User userByUserId = proxy.getUserByUserId(10);
        System.out.println("从服务端得到的user为：" + userByUserId);
        // 服务的方法2
        User user = User.builder().userName("张三").id(100).sex(true).build();
        Integer integer = proxy.insertUserId(user);
        System.out.println("向服务端插入数据："+integer);

        Blog blogById = proxy2.getBlogById(1);
        System.out.println("从服务端得到的blog为：" + blogById);
    }
}
