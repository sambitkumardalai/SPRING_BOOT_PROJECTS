package com.ecom.exception; 

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.profiles.active:prod}")
    private String activeProfile;

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("error"); // loads error.html
        mav.addObject("url", request.getRequestURL());
        mav.addObject("profile", activeProfile);

        if ("dev".equalsIgnoreCase(activeProfile)) {
            mav.addObject("exception", ex);
            mav.addObject("message", ex.getMessage());
            mav.addObject("stackTrace", ex.getStackTrace());
        } else {
            mav.addObject("message", "Something went wrong. Please try again later.");
        }

        return mav;
    }
}

