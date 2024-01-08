package com.ljnewmap.common.exception;

import cn.hutool.core.map.MapUtil;
import com.ljnewmap.common.utils.HttpContextUtils;
import com.ljnewmap.common.utils.IpUtils;
import com.ljnewmap.common.utils.JsonUtils;
import com.ljnewmap.common.utils.RT;
import com.ljnewmap.modules.log.entity.SysLogErrorEntity;
import com.ljnewmap.modules.log.service.SysLogErrorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 异常处理器
 *
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class LogExceptionHandler {
    private final SysLogErrorService sysLogErrorService;

    /**
     * 没有权限联系管理员授权
     */
    @ExceptionHandler(AuthorizationException.class)
    public RT handleAuthorizationException(AuthorizationException ex){
        RT rt = new RT();
        rt.error(GlobalErrorCodeConstants.NO_PERMISSION);
        log.warn("[handleAuthorizationException]",rt.getMsg());
        return rt;
    }

    /**
     * 其他异常记录
     */
    @ExceptionHandler(Exception.class)
    public RT handleException(Exception ex) {
        log.error("[handleException]", ex);

        saveLog(ex);

        return new RT().error();
    }

    /**
     * 保存异常日志
     */
    private void saveLog(Exception ex) {
        SysLogErrorEntity log = new SysLogErrorEntity();

        //请求相关信息
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setRequestUri(request.getRequestURI());
        log.setRequestMethod(request.getMethod());
        Map<String, String> params = HttpContextUtils.getParameterMap(request);
        if (MapUtil.isNotEmpty(params)) {
            log.setRequestParams(JsonUtils.toJsonString(params));
        }

        //异常信息
        log.setErrorInfo(ExceptionUtils.getErrorStackTrace(ex));

        //保存
        sysLogErrorService.save(log);
    }
}