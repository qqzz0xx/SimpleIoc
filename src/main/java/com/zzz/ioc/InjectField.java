package com.zzz.ioc;

import java.lang.reflect.Field;

/**
 * Created by sunqiongzai on 2018/4/1.
 */
public class InjectField  implements IInjectField{
    private Field field;

    public InjectField(Field field) {
        this.field = field;
    }

    @Override
    public void inject(Ioc ioc, Object object) {
        String name = this.field.getAnnotation(Inject.class).value();
        Class<?> type = this.field.getType();
        Object value = null;
        if (name == null || name.isEmpty()) {
            value = ioc.getBean(type);
        }
        else {
            value = ioc.getBean(name, type);
        }
        this.field.setAccessible(true);
        try {
            this.field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
