package com.example.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

// https://mp.weixin.qq.com/s/zNMOeX6AOmQSlqYFoTjHCw
// spring 反射工具类
public class ReflectionUtilsDemo {
    static Person person = new Person("gugu", 29);

    public static void main(String[] args) {
//        shallowCopyFieldState();
//        handleReflectionException();
//        accessibleConstructor();
//        findMethod();
//        invokeMethod();
//        declaresException();
//        doWithLocalMethods();
//        doWithMethods();
//        doWithMethods2();
//        getAllDeclaredMethods();
//        getUniqueDeclaredMethods();
//        isEqualsMethod();
//        isObjectMethod();
//        findField();
//        setField();
//        getField();
//        doWithLocalFields();
//        doWithFields();
//        doWithFields2();
        isPublicStaticFinal();
    }

    // 用于判断一个字段是否是public、static和final修饰的。
    @SneakyThrows
    static void isPublicStaticFinal() {
        System.out.println(ReflectionUtils.isPublicStaticFinal(Person.class.getDeclaredField("name")));
    }

    static void doWithFields2() {
        ReflectionUtils.doWithFields(Person.class, new ReflectionUtils.FieldCallback() {

            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                field.setAccessible(true); // 设置字段可访问，因为字段是私有的
                Object value = field.get(person); // 获取字段的值
                System.out.println("Field name: " + field.getName());
                System.out.println("Field type: " + field.getType());
                System.out.println("Field value: " + value);
            }
        }, new ReflectionUtils.FieldFilter() {
            @Override
            public boolean matches(Field field) {
                return field.getType() == String.class; // 只处理类型为String的字段
            }
        });
    }

    // 所有类方法
    static void doWithFields() {
        ReflectionUtils.doWithFields(Person.class, new MyFieldCallback());
    }

    // 本类方法
    static void doWithLocalFields() {
        ReflectionUtils.doWithLocalFields(Person.class, new MyFieldCallback());
    }

    @SneakyThrows
    static void getField() {
        Field field = Person.class.getDeclaredField("name");
        field.setAccessible(true);
        Object value = ReflectionUtils.getField(field, person);
        System.out.println("Field value: " + value);

    }

    @SneakyThrows
    static void setField() {
        Field field = Person.class.getDeclaredField("name");
        field.setAccessible(true);
        ReflectionUtils.setField(field, person, "Hello, World!");
        System.out.println(person.getName());
    }

    static void findField() {
        Field field = ReflectionUtils.findField(Person.class, "name");
        System.out.println(field.getName());
    }

    @SneakyThrows
    static void isObjectMethod() {
        // 用于判断给定的方法是否是Object类中的方法。类似的 isCglibRenamedMethod
        Method method = Person.class.getMethod("getName");
        boolean objectMethod = ReflectionUtils.isObjectMethod(method);
        System.out.println(objectMethod);
    }

    @SneakyThrows
    static void isEqualsMethod() {
        Method method = Person.class.getMethod("equals", Object.class);
        // 判断是否是equal方法 还有 isHashCodeMethod  isToStringMethod
        boolean equals = ReflectionUtils.isEqualsMethod(method);
        System.out.println(equals);
    }

    // 用于获取指定类及其所有父类中声明的所有唯一方法。
    static void getUniqueDeclaredMethods() {
        Method[] methods = ReflectionUtils.getUniqueDeclaredMethods(Person.class);
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }

    // 用于获取指定类及其所有父类中声明的所有方法。
    static void getAllDeclaredMethods() {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(Person.class);
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }

    static void doWithMethods2() {
        //PersonMethodFilter 实现 MethodFilter
        ReflectionUtils.doWithMethods(Person.class, new PersonCallback(), new PersonMethodFilter());
    }

    // 用于迭代指定类的所有方法，并对每个方法执行回调操作。
    static void doWithMethods() {
        ReflectionUtils.doWithMethods(Person.class, new PersonCallback());
    }

    // 用于迭代指定类的所有本地方法，并对每个方法执行回调操作。
    static void doWithLocalMethods() {
        //PersonCallback实现 MethodCallback接口
        ReflectionUtils.doWithLocalMethods(Person.class, new PersonCallback());
    }

    static void declaresException() {
        Method method = ReflectionUtils.findMethod(Person.class, "setName", String.class);
        // 用于检查指定方法是否声明了指定的异常类型。
        boolean declaresIOException = ReflectionUtils.declaresException(method, IOException.class);
        System.out.println("Declares IOException: " + declaresIOException);

    }

    static void invokeMethod() {
        Method method = ReflectionUtils.findMethod(Person.class, "setName", String.class);
        method.setAccessible(true);
        ReflectionUtils.invokeMethod(method, person, "gxl");
        System.out.println(person.toString());
    }

    static void findMethod() {
        Method method = ReflectionUtils.findMethod(Person.class, "getName");
        Method method2 = ReflectionUtils.findMethod(Person.class, "setName", String.class);
        System.out.println(method);
        System.out.println(method2);
    }

    public static void accessibleConstructor() {
        // 获取可以访问的构造器
        try {
            Constructor<Person> personConstructor = ReflectionUtils.accessibleConstructor(Person.class, String.class, int.class);
            Person person1 = personConstructor.newInstance("gugu", 25);
            System.out.println(person1.toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void handleReflectionException() {
        try {
            Method method = Person.class.getMethod("nonExistingMethod");
        } catch (Exception e) {
            // 处理反射操作中的异常
            ReflectionUtils.handleReflectionException(e);
        }
    }

    public static void shallowCopyFieldState() {
        Person target = new Person();
        // 浅拷贝
        ReflectionUtils.shallowCopyFieldState(person, target);
        System.out.println(target.toString());
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class Person {
        private String name;
        private int age;
    }

    static class PersonCallback implements ReflectionUtils.MethodCallback {

        @Override
        public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
            System.out.println("Method name: " + method.getName());
        }
    }

    static class PersonMethodFilter implements ReflectionUtils.MethodFilter {

        @Override
        public boolean matches(Method method) {
            return Modifier.isPublic(method.getModifiers());
        }
    }

    static class MyFieldCallback implements ReflectionUtils.FieldCallback {

        @Override
        public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
            System.out.println("Field name: " + field.getName());
            System.out.println("Field type: " + field.getType());
        }
    }
}
