//package com.kodark.news.controller;
//
//import java.sql.SQLException;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.dao.DataAccessException;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.ModelAndView;
//
//
//@ControllerAdvice
//@Controller
//public class ExceptionHandlingController {
//
//  // @RequestHandler methods
//
//  
//  // Exception handling methods
//  
//  // Convert a predefined exception to an HTTP Status code
//
//  @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "bad request")  
//  public String badRequest() {
//	  return "401";
//  }
//  @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "forbidden")  
//  public String forbidden() {
//	  return "403";
//  }
//  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "not found")  
//  public String notFound() {
//	  return "404";
//  }
//  @ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT, reason = "time out")  
//  public String requestTimeOut() {
//	  return "408";
//  }
//  @ResponseStatus(value=HttpStatus.CONFLICT,reason="Data integrity violation")  // 409
//  @ExceptionHandler(DataIntegrityViolationException.class)
//  public String conflict() {
//	  return "409";
//  }
//  @ExceptionHandler(ClassNotFoundException.class)
//  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "500")
//  public String internalServerError() {
//	  return "500";
//  }
//  // Specify name of a specific view that will be used to display the error:
//  @ExceptionHandler({SQLException.class,DataAccessException.class})
//  public String databaseError() {
//    // Nothing to do.  Returns the logical view name of an error page, passed
//    // to the view-resolver(s) in usual way.
//    // Note that the exception is NOT available to this view (it is not added
//    // to the model) but see "Extending ExceptionHandlerExceptionResolver"
//    // below.
//    return "databaseError";
//  }
//
//  // Total control - setup a model and return the view name yourself. Or
//  // consider subclassing ExceptionHandlerExceptionResolver (see below).
//  @ExceptionHandler(Exception.class)
//  public ModelAndView handleError(HttpServletRequest req, Exception ex) {
//    ModelAndView mav = new ModelAndView();
//    mav.addObject("exception", ex);
//    mav.addObject("url", req.getRequestURL());
//    mav.setViewName("error");
//    return mav;
//  }
//}