package com.zzz.ioc;

import java.lang.annotation.*;

/**
 * Created by sunqiongzai on 2018/4/1.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {
    String value() default "";

    boolean isSingle() default true;
}
