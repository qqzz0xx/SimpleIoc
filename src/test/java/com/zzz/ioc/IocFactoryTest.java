package com.zzz.ioc;


import org.junit.Test;

import java.util.Arrays;

/**
 * Created by sunqiongzai on 2018/4/1.
 */
public class IocFactoryTest {
    @Test
    public void Test() {
        Ioc ioc = IocFactory.buildIoc(Arrays.asList("com.zzz.ioc"));
        ioc.getNames().forEach(System.out::println);
        ioc.getBeans().forEach(System.out::println);
    }

}