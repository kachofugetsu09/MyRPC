package site.hnfy258.RPCVer0.server;

import site.hnfy258.RPCVer0.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

                        int id = ois.readInt();
                        User user = userService.getUserByUserId(id);
                        oos.writeObject(user);
                        oos.flush();
                    } catch (IOException e) {
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
