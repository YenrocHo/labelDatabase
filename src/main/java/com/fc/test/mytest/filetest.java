package com.fc.test.mytest;

import com.fc.test.common.conf.V2Config;
import com.fc.test.util.FileUtils;

public class filetest {
    public static void main(String[] args) {
        String oldPath= V2Config.getProfile()+"test.jpg";
        System.out.println(oldPath);
        String newPath = V2Config.getProfile()+"image/"+"test.jpg";
        System.out.println(newPath);
       /* FileUtils.moveFile(oldPath,newPath);*/
    }
}
