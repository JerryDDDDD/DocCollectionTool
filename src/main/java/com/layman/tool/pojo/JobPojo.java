package com.layman.tool.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.layman.tool.entity.Student;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

/**
 * @ClassName JobPojo
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/13 9:33
 * @Version 3.0
 **/

@Data
public class JobPojo {

   List<Student> postStudent;

   List<Student> unPostStudent;
}
