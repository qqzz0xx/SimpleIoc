package com.zzz.ioc;

import java.lang.annotation.*;

/**
 * Created by sunqiongzai on 2018/4/1.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
    String value() default "";
}
