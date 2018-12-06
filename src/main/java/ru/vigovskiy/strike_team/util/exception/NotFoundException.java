package ru.vigovskiy.strike_team.util.exception;

import org.springframework.lang.NonNull;

//  http://stackoverflow.com/a/22358422/548473
//@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "No data found")  // 422
public class NotFoundException extends RuntimeException {
    public NotFoundException(@NonNull String message) {
        super(message);
    }
}