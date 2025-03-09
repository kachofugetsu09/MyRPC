package site.hnfy258.RPCVer1.server;

import site.hnfy258.RPCVer1.server.UserServiceImpl;
import site.hnfy258.RPCVer1.common.RPCRequest;
import site.hnfy258.RPCVer1.common.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RPCServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        try (ServerSocket serverSocket = new ServerSocket(8899)) {
            System.out.println("服务端启动成功");

            ExecutorService pool = Executors.newCachedThreadPool();

            while (true) {
                Socket socket = serverSocket.accept();
                pool.submit(() -> {
                    try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                         ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
                        RPCRequest request = (RPCRequest) ois.readObject();
                        Method method = userService.getClass().getMethod(request.getMethodName(),request.getParameterTypes());
                        Object invoke = method.invoke(userService,request.getParameters());

                        oos.writeObject(RPCResponse.success(invoke));
                        oos.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务端启动失败");
        }
    }
}
