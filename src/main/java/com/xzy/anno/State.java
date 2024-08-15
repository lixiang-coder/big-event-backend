package com.xzy.anno;

import com.xzy.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented // 元注解
@Target({ElementType.FIELD})    // 元注解
@Retention(RetentionPolicy.RUNTIME) // 元注解
@Constraint(validatedBy = {StateValidation.class})  // 指定提供校验规则的类
public @interface State {
    // 提供校验失败后的逻辑
    String message() default "{state参数的值只能是已发布或者草稿}";

    // 指定分组
    Class<?>[] groups() default {};

    // 负载 获取到State注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
