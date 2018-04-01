package com.zzz.ioc;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by sunqiongzai on 2018/4/1.
 */
public class ClassScanner implements IClassScanner {
    @Override
    public Stream<Class<?>> parse(String packageName, Class<?> superClass, Class<? extends Annotation> annotation) {
        Set<Class<?>> classHashSet = new HashSet<>();
        String packagePaht = packageName.replace('.', '/');
        try {
            Enumeration<URL> resources = this.getClass().getClassLoader().getResources(packagePaht);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();

                Set<Class<?>> allClassByPath = getAllClassByPath(packageName, url.getPath(), superClass, annotation);
                classHashSet.addAll(allClassByPath);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return classHashSet.stream();
    }

    private Set<Class<?>> getAllClassByPath(String packageName, String path, Class<?> superClass, Class<? extends Annotation> annotation) {
        Set<Class<?>> classSet = new HashSet<>();
        File file = new File(path);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("path is not directory");
        }
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.isFile() && file1.getName().endsWith(".class")) {
                String className = file1.getName().substring(0, file1.getName().indexOf(".class"));

                try {
                    Class<?> aClass = Class.forName(packageName + "." + className);
                    if (superClass != null && !superClass.isAssignableFrom(aClass))
                        continue;
                    if (annotation != null && aClass.getAnnotation(annotation) == null)
                        continue;
                    classSet.add(aClass);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return classSet;
    }
}
