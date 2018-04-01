package com.zzz.ioc;

import java.util.List;
import java.util.Set;

/**
 * Created by sunqiongzai on 2018/4/1.
 */
public interface Ioc {
    void addBean(Object bean);

    void addBean(String name, Object bean);

    void addBean(Class<?> type);

    void addBean(String name, Class<?> type);

    <T> T getBean(Class<T> type);

    Object getBean(String name, Class<?> type);


    Set<String> getNames();

    List<Object> getBeans();


    void inject();
}
