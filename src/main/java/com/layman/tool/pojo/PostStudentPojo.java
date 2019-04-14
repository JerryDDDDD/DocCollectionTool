package com.layman.tool.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @ClassName PostStudentPojo
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/13 19:56
 * @Version 3.0
 **/
@Data
public class PostStudentPojo {

    String studentId;

    String StudentName;

    public PostStudentPojo(String studentId, String studentName) {
        this.studentId = studentId;
        StudentName = studentName;
    }
}
