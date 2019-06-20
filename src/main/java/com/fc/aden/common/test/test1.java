package com.fc.aden.common.test;

import java.io.File;

public class test1 {
    public static void main(String[] args) {
        createFileDir("E:/Aden_lable/profile/image/123456/1516565");
    }
    public static void createFileDir(String path) {
        File file = new File(path);
        //寻找父目录是否存在
        File parent = new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(File.separator)));
        //如果父目录不存在，则递归寻找更上一层目录
        if (!parent.exists()) {
            createFileDir(parent.getPath());
            //创建父目录
            parent.mkdirs();
        }
        try  {
            String  filePath  =  path;
            filePath  =  filePath.toString();
            java.io.File  myFilePath  =  new  java.io.File(filePath);
            if  (!myFilePath.exists())  {
                myFilePath.mkdir();
            }
        }
        catch  (Exception  e)  {
            System.out.println("新建目录操作出错");
            e.printStackTrace();
        }
    }
}
