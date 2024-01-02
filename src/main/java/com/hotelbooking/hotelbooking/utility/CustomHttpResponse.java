package com.hotelbooking.hotelbooking.utility;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpEntity;

@Data
@AllArgsConstructor
public class CustomHttpResponse extends HttpEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "mm:dd:yyyy hh:mm:ss" , timezone = "GMT+1")
    private String timeStamp;
    private String httpStatusCode; // 200,400...
    private String httpStatus;
    private String reason;
    private String message;

}
