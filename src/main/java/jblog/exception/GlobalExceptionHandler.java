package jblog.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception e) {
        //1.로깅
        System.out.println("=================");
        System.out.println("ControllerAdvice에 의한 예외 처리");

        ModelAndView mav = new ModelAndView();
        mav.addObject("name",e.getClass().getSimpleName());
        mav.addObject("message", e.getMessage());
        mav.setViewName("exception/exception");

        return mav;
    }

}
