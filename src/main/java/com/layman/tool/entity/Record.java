package com.layman.tool.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.layman.tool.pojo.PostStudentPojo;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Record
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/12 21:09
 * @Version 3.0
 **/
@Entity
//@NamedNativeQueries(value={
//        @NamedNativeQuery(
//                name = "getPostStudent",
//                query = "select student_id, student_name from t_record where job_id =",
//                resultClass = PostStudentPojo.class
//        )
//})
@Table(name = "t_record")
@Data
public class Record {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "jobId", length = 255)
    private String jobId;

    @Column(name = "studentId", length = 255)
    private String studentId;

    @Column(name = "studentName", length = 255)
    private String studentName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @Column(name = "postTime")
    private Date postTime;

    @Column(name = "store_path")
    private String storePath;
}
