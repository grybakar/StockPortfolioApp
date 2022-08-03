package com.example.finalProject.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@Builder
public class ErrorMessage {

    //TODO Add a debug message.

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timeStamp;

    private HttpStatus httpStatus;
    private String message;
    private String path;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> subErrors;

    public ErrorMessage(LocalDateTime timeStamp,
                        HttpStatus httpStatus,
                        String message,
                        String path,
                        List<String> subErrors) {
        this.timeStamp = LocalDateTime.now();
        this.httpStatus = httpStatus;
        this.message = message;
        this.path = path;
        this.subErrors = subErrors;
    }
}
