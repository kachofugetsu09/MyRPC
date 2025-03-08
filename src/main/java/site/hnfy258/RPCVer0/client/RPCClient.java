package site.hnfy258.RPCVer0.client;

import site.hnfy258.RPCVer0.common.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class RPCClient {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("127.0.0.1", 8899);
            System.out.println("客户端启动成功");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            oos.writeInt(new Random().nextInt());
            oos.flush();

            User user = (User)ois.readObject();
            System.out.println("返回的User"+user);
        }
        catch(Exception e){
            System.out.println("客户端捕获异常");
        }
    }
}
