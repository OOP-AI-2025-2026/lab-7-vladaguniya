package ua.opnu;

import java.util.Arrays;

public class Student {
    private String lastName;
    private String firstName;
    private String group;
    private int[] marks;

    public Student(String lastName, String firstName, String group, int[] marks) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.group = group;
        this.marks = marks;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGroup() {
        return group;
    }

    public int[] getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + lastName + " " + firstName + '\'' +
                ", group='" + group + '\'' +
                ", marks=" + Arrays.toString(marks) +
                '}';
    }

    public double getAverageMark() {
        if (marks == null || marks.length == 0) return 0.0;
        double sum = 0;
        for (int m : marks) sum += m;
        return sum / marks.length;
    }
}
