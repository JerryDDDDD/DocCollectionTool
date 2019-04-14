package com.layman.tool.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Job
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/12 9:17
 * @Version 3.0
 **/
@Entity
@Table(name = "t_job")
@Data
public class Job {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "job_name", length = 255)
    private String jobName;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "job_class", length = 255)
    private String jobClass;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "is_finished")
    private Integer isFinished;

    @Column(name = "store_path")
    private String storePath;
}
