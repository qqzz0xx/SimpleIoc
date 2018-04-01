package com.zzz.ioc;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by sunqiongzai on 2018/4/1.
 */
public class SimpleIoc implements Ioc {
    private Map<String, BeanDefine> beanDefineMap = new ConcurrentHashMap();

    private void addBeanDefine(BeanDefine beanDefine) {
        beanDefineMap.put(beanDefine.getName(), beanDefine);
    }


    @Override
    public void addBean(Object bean) {
        Bean annotation = bean.getClass().getAnnotation(Bean.class);
        String beanName = annotation.value();
        beanName = beanName.equals("") ? bean.getClass().getName() : beanName;

        addBean(beanName, bean);
    }

    @Override
    public void addBean(String name, Object bean) {
        addBeanDefine(new BeanDefine(name, bean));
    }

    @Override
    public void addBean(Class<?> type) {
        Bean annotation = type.getAnnotation(Bean.class);
        String beanName = annotation.value();
        beanName = beanName.equals("") ? type.getName() : beanName;

        addBean(beanName, type);

    }

    @Override
    public void addBean(String name, Class<?> type) {
        addBeanDefine(new BeanDefine(name, type));
    }

    @Override
    public <T> T getBean(Class<T> type) {
        List<BeanDefine> collect = this.beanDefineMap.values().stream()
                .filter(p -> type.isAssignableFrom(p.getType()))
                .collect(Collectors.toList());
        if (collect.isEmpty()) {
            throw new IllegalStateException("not find class");
        }
        if (collect.size() == 1)
            return type.cast(collect.get(0).getObject());
        throw new IllegalStateException("more than one class");
    }

    @Override
    public Object getBean(String name, Class<?> type) {
        BeanDefine beanDefine = this.beanDefineMap.get(name);
        if (beanDefine == null)
            throw new IllegalStateException("name is error");
        if (!type.isAssignableFrom(beanDefine.getType()))
            throw new IllegalStateException("type is error");
        return beanDefine.getObject();
    }

    @Override
    public Set<String> getNames() {
        return this.beanDefineMap.keySet();
    }

    @Override
    public List<Object> getBeans() {
        return this.beanDefineMap.values().stream()
                .map(BeanDefine::getObject)
                .collect(Collectors.toList());
    }

    @Override
    public void inject() {
        beanDefineMap.values().stream().forEach(p -> p.inject(this));
    }
}
