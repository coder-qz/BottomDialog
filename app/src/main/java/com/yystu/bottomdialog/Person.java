package com.yystu.bottomdialog;

import com.zqz.bottomdialog.IBottomData;

/**
 * @author : 章勤志
 * @date : 2019/9/2
 * 自定义对象,要实现IBottomViewData接口
 */
public class Person implements IBottomData {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
