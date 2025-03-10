package site.hnfy258.RPCVer3.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Builder
@Data

public class RPCRequest implements Serializable {
    private String interfaceName;
    private String methodName;
    private Class<?> [] parameterTypes;
    private Object [] parameters;
}
