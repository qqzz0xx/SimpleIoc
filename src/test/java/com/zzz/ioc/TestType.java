package com.zzz.ioc;

/**
 * Created by sunqiongzai on 2018/4/1.
 */
@Bean
public class TestType {

    @Inject
    private TestInject1 field1;
    @Inject
    private TestInject2 field2;

    @Override
    public String toString() {
        return "TestType{" +
                "field1=" + field1 +
                ", field2=" + field2 +
                '}';
    }
}
