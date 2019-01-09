package ru.vigovskiy.strike_team.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No data found")
public class IllegalRequestDataException extends RuntimeException {
    public IllegalRequestDataException(@NonNull String msg) {
        super(msg);
    }
}