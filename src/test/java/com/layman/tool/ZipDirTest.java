package com.layman.tool;

import com.layman.tool.utils.IOtools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName ZipDirTest
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/14 14:12
 * @Version 3.0
 **/
public class ZipDirTest {

    public static void main(String[] args) throws Exception {

        byte[] buffer = new byte[1024];
        //生成的ZIP文件名为Demo.zip
        String strZipName = "D:/作业.zip";
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipName));
        //需要同时下载的两个文件result.txt ，source.txt
        List<File> file1 = IOtools.getFiles("G:\\Tool\\tool\\target\\classes\\作业1");
        for (int i = 0; i < file1.size(); i++) {
            FileInputStream fis = new FileInputStream(file1.get(i));
            out.putNextEntry(new ZipEntry(file1.get(i).getName()));
            int len;
            //读入需要下载的文件的内容，打包到zip文件
            while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.closeEntry();
            fis.close();
        }
        out.close();
        System.out.println("生成Demo.zip成功");
    }
}
