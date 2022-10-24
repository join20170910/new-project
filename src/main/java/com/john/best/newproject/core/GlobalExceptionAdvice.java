package com.john.best.newproject.core;

import com.john.best.newproject.core.configuration.ExceptionCodeConfiguration;
import com.john.best.newproject.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author john
 * @date 2022/10/23
 */
@ControllerAdvice
public class GlobalExceptionAdvice {

    @Autowired
    private ExceptionCodeConfiguration codeConfiguration;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(HttpServletRequest request,Exception e){
        final String requestURI = request.getRequestURI();
        final String method = request.getMethod();
        System.out.println();
        return new UnifyResponse(9999,e.getMessage(),method+ " "+requestURI);

    }

    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity<UnifyResponse> handleHttpException(HttpServletRequest request,HttpException e){
        final String requestURI = request.getRequestURI();
        final String method = request.getMethod();
        final UnifyResponse message = new UnifyResponse(e.getCode(), codeConfiguration.getMessage(e.getCode()), method + " " + requestURI);
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        final HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());
        final ResponseEntity responseEntity = new ResponseEntity(message,httpStatus);
        return responseEntity;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public UnifyResponse handleBeanValidation(HttpServletRequest request,MethodArgumentNotValidException e){

        final String requestURI = request.getRequestURI();
        final String method = request.getMethod();
        final List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        final String message = formatAllErrorMessages(allErrors);
        final UnifyResponse unifyResponse = new UnifyResponse(10001, message, method + " " + requestURI);
        return unifyResponse;
    }

  @ExceptionHandler(value = ConstraintViolationException.class)
  @ResponseBody
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public UnifyResponse handleConstraintException(
      HttpServletRequest request, ConstraintViolationException e) {

    final String requestURI = request.getRequestURI();
    final String method = request.getMethod();
    final String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
    final UnifyResponse unifyResponse =
        new UnifyResponse(10001, message, method + " " + requestURI);
    return unifyResponse;
        }

    private String formatAllErrorMessages(List<ObjectError> allErrors ){
        final StringBuilder stringBuilder = new StringBuilder();
        allErrors.forEach(
                error ->{
                    stringBuilder.append(error.getDefaultMessage()).append(";");
                }
        );
        return stringBuilder.toString();

    }
}
