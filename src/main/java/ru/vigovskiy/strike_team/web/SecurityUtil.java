package ru.vigovskiy.strike_team.web;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.vigovskiy.strike_team.AuthorizedUser;

import static java.util.Objects.requireNonNull;

public class SecurityUtil {

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int authUserId() {
        return get().getUserDto().getId();
    }

    public static Integer getAuthUserIdOrNull() {
        AuthorizedUser user = safeGet();
        if (user == null) {
            return null;
        }
        return user.getUserDto().getId();
    }

}