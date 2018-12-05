package ru.vigovskiy.strike_team.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

//    @ExceptionHandler(Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        log.error("Exception at request " + req.getRequestURL(), e);
//        ModelAndView mav = new ModelAndView("exception/exception");
//        Throwable rootCause = ValidationUtil.getRootCause(e);
//        mav.addObject("exception", rootCause);
//        mav.addObject("message", rootCause.toString());
//
//        // Interceptor is not invoked, put userTo
//        AuthorizedUser authorizedUser = SecurityUtil.safeGet();
//        if (authorizedUser != null) {
//            mav.addObject("userTo", authorizedUser.getUserDto());
//        }
//        return mav;
//    }
}
