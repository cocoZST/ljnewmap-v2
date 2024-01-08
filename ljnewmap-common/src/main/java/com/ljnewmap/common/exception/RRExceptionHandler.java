package com.ljnewmap.common.exception;

import com.ljnewmap.common.utils.RT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class RRExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(RRException.class)
    public RT handleRRException(RRException ex) {
        log.warn("[handleRRException]",ex.getMsg());
        RT rt = new RT();
        rt.error(ex.getCode(), ex.getMsg());

        return rt;
    }

    /**
     * 处理 SpringMVC 参数绑定不正确，本质上也是通过 Validator 校验
     */
    @ExceptionHandler(BindException.class)
    public RT bindExceptionHandler(BindException ex) {
        log.info("[handleBindException]", ex);
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null; // 断言，避免告警
        return new RT().ExRrror(GlobalErrorCodeConstants.BAD_REQUEST, String.format("请求参数不正确:%s", fieldError.getDefaultMessage()));
    }

    /**
     * 处理 SpringMVC 请求参数缺失
     *
     * 例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public RT handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.info("[handleMissingServletRequestParameterException]",ex);
        return new RT().ExRrror(GlobalErrorCodeConstants.BAD_REQUEST, String.format("请求参数缺失:%s", ex.getParameterName()));
    }

    /**
     * 处理 SpringMVC 请求参数类型错误
     *
     * 例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public RT handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.info("[handleMethodArgumentTypeMismatchException]", ex);
        return new RT().ExRrror(GlobalErrorCodeConstants.BAD_REQUEST, String.format("请求参数类型错误:%s", ex.getMessage()));
    }

    /**
     * 处理 SpringMVC 参数校验不正确
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RT handleArgumentNotValidExceptionException(MethodArgumentNotValidException ex) {
        log.info("[handleArgumentNotValidExceptionException]", ex);
        FieldError fieldError = ex.getBindingResult().getFieldError();
        assert fieldError != null; // 断言，避免告警
        return new RT().ExRrror(GlobalErrorCodeConstants.BAD_REQUEST, String.format("请求参数不正确:%s", fieldError.getDefaultMessage()));
    }

    /**
     * 处理 SpringMVC 请求地址不存在
     *
     * 注意，它需要设置如下两个配置项：
     * 1. spring.mvc.throw-exception-if-no-handler-found 为 true
     * 2. spring.mvc.static-path-pattern 为 /statics/**
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public RT handleNoHandlerFoundException(HttpServletRequest req, NoHandlerFoundException ex) {
        log.warn("[handleNoHandlerFoundException]", ex);
        return new RT().ExRrror(GlobalErrorCodeConstants.NOT_FOUND, String.format("请求地址不存在:%s", ex.getRequestURL()));
    }

    /**
     * 数据库不存在异常
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public RT handleDuplicateKeyException(DuplicateKeyException ex) {
        log.warn("[handleDuplicateKeyException]",ex);
        RT rt = new RT();
        rt.error(GlobalErrorCodeConstants.DB_RECORD_EXISTS);

        return rt;
    }
}
