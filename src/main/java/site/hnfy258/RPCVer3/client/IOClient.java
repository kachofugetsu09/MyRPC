package site.hnfy258.RPCVer3.client;

import site.hnfy258.RPCVer3.common.RPCRequest;
import site.hnfy258.RPCVer3.common.RPCResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IOClient {
    //负责底层和服务端的通信
    //客户端发起调用请求，这里建立连接发起request得到response
    //这里的request是封装好的。不同的service进行不同的封装，客户端只知道接口
    public static RPCResponse sendRequest(String host, int port, RPCRequest request){
        try{
            Socket socket = new Socket(host,port);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("客户端发送请求"+request);
            oos.writeObject(request);
            oos.flush();

            RPCResponse response = (RPCResponse)ois.readObject();
            return response;
        }catch(Exception e){
            System.out.println("客户端捕获异常");
            return null;
            
        }
    }
}
