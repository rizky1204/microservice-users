package system.requesthandle;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class RestUtil {

    private static final String CONTENT_TYPE = "Content-Type";

    /**
     * get JSON response from Object
     *
     * @param src
     * @param <T> source class
     * @return @ResponseEntity
     */
    public static <T> ResponseEntity<T> getJsonResponse(T src) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<T>(src, headers, HttpStatus.OK);
    }
}
