package system.vo;

import lombok.Data;

@Data
public class ResponseVO {

    public String response;
    public Object result;

    public ResponseVO(){}

    public ResponseVO (Object response , Object result){
        this.response = String.valueOf(response);
        this.result = String.valueOf(result);
    }
}
