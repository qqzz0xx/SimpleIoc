package com.zzz.ioc;

/**
 * Created by sunqiongzai on 2018/4/1.
 */
@Bean
public class TestInject2 {

    @Inject
    private TestType testType;

    @Override
    public String toString() {
        return "TestInject2{}";
    }
}
