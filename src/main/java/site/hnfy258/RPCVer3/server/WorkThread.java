package site.hnfy258.RPCVer3.server;

import lombok.AllArgsConstructor;
import site.hnfy258.RPCVer3.common.RPCRequest;
import site.hnfy258.RPCVer3.common.RPCResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

@AllArgsConstructor
public class WorkThread implements Runnable {
    private Socket socket;
    private ServiceProvider serviceProvide;
    @Override
    public void run() {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            RPCRequest request = (RPCRequest) ois.readObject();
            RPCResponse response = getResponse(request);

            oos.writeObject(response);
            oos.flush();

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("服务端处理请求失败");
        }
    }

    private RPCResponse getResponse(RPCRequest request) {
        String interfaceName = request.getInterfaceName();
        Object service = serviceProvide.getService(interfaceName);
        Method method = null;
        try{
            method = service.getClass().getMethod(request.getMethodName(),request.getParameterTypes());
            Object invoke = method.invoke(service,request.getParameters());
            return RPCResponse.success(invoke);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("服务端处理请求失败");
            return RPCResponse.fail();
        }

    }
}
