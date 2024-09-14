package com.scaler.lld.designPatterns.builder;

public class Student {
    private static StudentBuilder sb;
    public int age;

    private Student() {
        this.age = sb.age1;
    }

    public static StudentBuilder getBuilder(){
        sb = new StudentBuilder();
        return sb;
    }
    public static class StudentBuilder{
        private int age1;

        public StudentBuilder setAge1(int age1) {
            this.age1 = age1;
            return this;
        }
        public Student build() {
            return new Student();
        }
    }
}
