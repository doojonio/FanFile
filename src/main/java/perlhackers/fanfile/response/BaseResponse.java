package perlhackers.fanfile.response;

import lombok.Data;

@Data
public class BaseResponse {
    private final String status;
    private final int code;

    public BaseResponse(String status, int code) {
        this.status = status;
        this.code = code;
    }
}
