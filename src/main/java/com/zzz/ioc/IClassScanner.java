package com.zzz.ioc;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

/**
 * Created by sunqiongzai on 2018/4/1.
 */
public interface IClassScanner {
    Stream<Class<?>> parse(String packageName, Class<?> superClass, Class<? extends Annotation> annotation);

    default Stream<Class<?>> parseByAnnotation(String packageName, Class<? extends Annotation> annotation) {
        return parse(packageName, null, annotation);
    }

    default Stream<Class<?>> parseBySuper(String packageName, Class<? extends Annotation> superClass) {
        return parse(packageName, superClass, null);
    }
}
