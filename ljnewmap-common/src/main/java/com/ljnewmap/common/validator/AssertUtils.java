package com.ljnewmap.common.validator;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import com.ljnewmap.common.exception.ErrorCode;
import com.ljnewmap.common.exception.GlobalErrorCodeConstants;
import com.ljnewmap.common.exception.RRException;
import cn.hutool.core.util.StrUtil;

import java.util.List;
import java.util.Map;

/**
 * 校验工具类
 *
 */
public class AssertUtils {

    public static void isBlank(String str, String... params) {
        isBlank(str, GlobalErrorCodeConstants.NOT_NULL, params);
    }

    public static void isBlank(String str, ErrorCode errorCode, String... params) {
        if(errorCode == null){
            throw new RRException(GlobalErrorCodeConstants.NOT_NULL, "code");
        }

        if (StrUtil.isBlank(str)) {
            throw new RRException(errorCode, params);
        }
    }

    public static void isNull(Object object, String... params) {
        isNull(object, GlobalErrorCodeConstants.NOT_NULL, params);
    }

    public static void isNull(Object object, ErrorCode errorCode, String... params) {
        if(errorCode == null){
            throw new RRException(GlobalErrorCodeConstants.NOT_NULL, "code");
        }

        if (object == null) {
            throw new RRException(errorCode, params);
        }
    }

    public static void isArrayEmpty(Object[] array, String... params) {
        isArrayEmpty(array, GlobalErrorCodeConstants.NOT_NULL, params);
    }

    public static void isArrayEmpty(Object[] array, ErrorCode code, String... params) {
        if(code == null){
            throw new RRException(GlobalErrorCodeConstants.NOT_NULL, "code");
        }

        if(ArrayUtil.isEmpty(array)){
            throw new RRException(code, params);
        }
    }

    public static void isListEmpty(List<?> list, String... params) {
        isListEmpty(list, GlobalErrorCodeConstants.NOT_NULL, params);
    }

    public static void isListEmpty(List<?> list, ErrorCode errorCode, String... params) {
        if(errorCode == null){
            throw new RRException(GlobalErrorCodeConstants.NOT_NULL, "code");
        }

        if(CollUtil.isEmpty(list)){
            throw new RRException(errorCode, params);
        }
    }

    public static void isMapEmpty(Map map, String... params) {
        isMapEmpty(map, GlobalErrorCodeConstants.NOT_NULL, params);
    }

    public static void isMapEmpty(Map map, ErrorCode errorCode, String... params) {
        if(errorCode == null){
            throw new RRException(GlobalErrorCodeConstants.NOT_NULL, "code");
        }

        if(MapUtil.isEmpty(map)){
            throw new RRException(errorCode, params);
        }
    }
}