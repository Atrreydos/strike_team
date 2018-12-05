package ru.vigovskiy.strike_team.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.StringJoiner;

public class ValidationUtil {

    public static ResponseEntity<String> getErrorResponse(BindingResult result) {
        StringJoiner joiner = new StringJoiner("<br>");
        result.getFieldErrors().forEach(
                fe -> {
                    String msg = fe.getDefaultMessage();
                    if (!msg.startsWith(fe.getField())) {
                        msg = fe.getField() + ' ' + msg;
                    }
                    joiner.add(msg);
                });
        return new ResponseEntity<>(joiner.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    //  http://stackoverflow.com/a/28565320/548473
    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }
}
