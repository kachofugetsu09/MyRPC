package site.hnfy258.RPCVer2.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class SimpleRPCServer implements RPCServer{
    private ServiceProvider serviceProvide;
    public SimpleRPCServer(ServiceProvider serviceProvide){
        this.serviceProvide = serviceProvide;
    }
    @Override
    public void start(int port) {
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("SimpleRPCServer启动");
            while(true){
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket,serviceProvide)).start();

            }
        }catch(Exception e){
            System.out.println("SimpleRPCServer启动失败");
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}
