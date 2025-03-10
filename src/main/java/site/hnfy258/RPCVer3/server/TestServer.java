package site.hnfy258.RPCVer3.server;

import site.hnfy258.RPCVer3.service.BlogService;
import site.hnfy258.RPCVer3.service.UserService;

public class TestServer {
    public static void main(String[] args) {
        UserService userservice = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
        ServiceProvider serviceProvide = new ServiceProvider();
        serviceProvide.provideServiceInterface(userservice);
        serviceProvide.provideServiceInterface(blogService);

        RPCServer RPCServer = new NettyRPCServer(serviceProvide);
        RPCServer.start(8899);
    }
}
