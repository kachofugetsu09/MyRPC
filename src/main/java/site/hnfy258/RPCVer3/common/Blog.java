package site.hnfy258.RPCVer3.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Blog implements Serializable {
    private Integer id;
    private Integer useId;
    private String title;


}
