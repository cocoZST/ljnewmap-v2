package com.ljnewmap.common.validator;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * hibernate-validator校验工具类
 * 参考文档：http://docs.jboss.org/hibernate/validator/6.0/reference/en-US/html_single/
 *
 */
public class ValidatorUtils {

    private static final Pattern PATTERN_MOBILE = Pattern.compile("^(?:(?:\\+|00)86)?1(?:(?:3[\\d])|(?:4[0,1,4-9])|(?:5[0-3,5-9])|(?:6[2,5-7])|(?:7[0-8])|(?:8[\\d])|(?:9[0-3,5-9]))\\d{8}$");

    private static final Pattern PATTERN_URL = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

    private static final Pattern PATTERN_XML_NCNAME = Pattern.compile("[a-zA-Z_][\\-_.0-9_a-zA-Z$]*");

    public static boolean isMobile(String mobile) {
        return StringUtils.hasText(mobile)
                && PATTERN_MOBILE.matcher(mobile).matches();
    }

    public static boolean isURL(String url) {
        return StringUtils.hasText(url)
                && PATTERN_URL.matcher(url).matches();
    }

    public static boolean isXmlNCName(String str) {
        return StringUtils.hasText(str)
                && PATTERN_XML_NCNAME.matcher(str).matches();
    }

    /**
     * 验证
     * @param object    待验证对象
     * @param groups    验证分组
     */
    public static void validateEntity(Object object, Class<?>... groups) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Assert.notNull(validator);
        validate(validator, object, groups);
    }

    public static void validate(Validator validator, Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (CollUtil.isNotEmpty(constraintViolations)) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}