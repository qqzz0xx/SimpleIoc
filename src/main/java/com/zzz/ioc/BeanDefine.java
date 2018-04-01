package com.zzz.ioc;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

/**
 * Created by sunqiongzai on 2018/4/1.
 */
public class BeanDefine {
    private String name;
    private Class<?> type;
    private Object object;
    private boolean single;
    List<IInjectField> injects = new Vector<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public boolean isSingle() {
        return single;
    }

    public void setSingle(boolean single) {
        this.single = single;
    }


    public BeanDefine(Class<?> type) {
        init(type.getName(), type, null, true);
    }

    public BeanDefine(String name, Class<?> type) {
        init(name, type, null, true);
    }

    public BeanDefine(String name, Object object) {
        init(name, object.getClass(), object, true);
    }

    public BeanDefine(String name, Class<?> type, Object object, boolean single) {
        init(name, type, object, single);
    }

    public void init(String name, Class<?> type, Object object, boolean single) {
        this.name = name;
        this.type = type;
        this.object = object;
        this.single = single;

        initField();
    }

    private void initField() {
        Field[] declaredFields = this.type.getDeclaredFields();

        for (Field field : declaredFields) {
            if (field.getAnnotation(Inject.class) != null) {
                this.injects.add(new InjectField(field));
            }
        }

        if (isSingle() && object == null) {
            try {
                this.object = this.type.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    void inject(Ioc ioc) {
        injects.stream().forEach(p -> p.inject(ioc, object));
    }
}
