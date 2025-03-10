package site.hnfy258.RPCVer3.client;

import lombok.AllArgsConstructor;
import site.hnfy258.RPCVer3.common.RPCRequest;
import site.hnfy258.RPCVer3.common.RPCResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@AllArgsConstructor

public class SimpleRPCClient implements RPCClient{
    private String host;
    private int port;
    @Override
    public RPCResponse sendRequest(RPCRequest request) {
        try{
            Socket socket = new Socket(host,port);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("客户端发送请求"+request);
            oos.writeObject(request);
            oos.flush();

            RPCResponse reseponse = (RPCResponse)ois.readObject();
            return reseponse;

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("客户端发送请求失败");
            return null;
        }
    }
}
