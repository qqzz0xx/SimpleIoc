package com.zzz.ioc;

import java.util.List;

/**
 * Created by sunqiongzai on 2018/4/1.
 */
public class IocFactory {
    public static Ioc buildIoc(List<String> packageNames) {
        Ioc ioc = new SimpleIoc();
        ClassScanner scanner = new ClassScanner();
        packageNames.stream()
                .flatMap(p->scanner.parseByAnnotation(p, Bean.class))
                .forEach(c->ioc.addBean(c));
        ioc.inject();
        return ioc;
    }
}
