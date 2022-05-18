package tokyo.boblennon.bcnjob22.core.execptions;

public class HttpException extends RuntimeException {
    private Integer code;

    public HttpException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}