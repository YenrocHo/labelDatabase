package com.fc.test.mytest;

public class split {
    public static void main(String[] args) {
        String s1 = "D:/profile/c716be74ddaa0b74212c7b5fe1ae9500.jpg";
        String[] strings = s1.split("/");
        for (int i=0;i<strings.length;i++){
            System.out.println(i);
            System.out.println(strings[i]);
        }
    }

}


