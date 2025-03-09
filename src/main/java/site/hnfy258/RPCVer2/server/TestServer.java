package site.hnfy258.RPCVer2.server;

import site.hnfy258.RPCVer2.service.BlogService;
import site.hnfy258.RPCVer2.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class TestServer {
    public static void main(String[] args) {
        UserService userservice = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
        ServiceProvider serviceProvide = new ServiceProvider();
        serviceProvide.provideServiceInterface(userservice);
        serviceProvide.provideServiceInterface(blogService);

        RPCServer RPCServer = new SimpleRPCServer(serviceProvide);
        RPCServer.start(8899);
    }
}
