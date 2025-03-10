package site.hnfy258.RPCVer3.client;

import lombok.AllArgsConstructor;
import site.hnfy258.RPCVer3.common.RPCRequest;
import site.hnfy258.RPCVer3.common.RPCResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
@AllArgsConstructor
public class ClientProxy implements InvocationHandler {
    private RPCClient client;


    // jdk 动态代理， 每一次代理对象调用方法，会经过此方法增强（反射获取request对象，socket发送至客户端）
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // request的构建，使用了lombok中的builder，代码简洁
        RPCRequest request = RPCRequest.builder().interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameters(args).parameterTypes(method.getParameterTypes()).build();
        // 数据传输
        RPCResponse response = client.sendRequest(request);
        if (response == null || response.getData() == null) {
            throw new RuntimeException("Response or data is null");
        }
        return response.getData();
    }
    <T>T getProxy(Class<T> clazz){
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T)o;
    }
}
