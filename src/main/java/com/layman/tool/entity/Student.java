package com.layman.tool.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @ClassName Student
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/11 20:05
 * @Version 3.0
 **/
@Entity
@Table(name = "t_student")
@Data
public class Student {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "number", length = 50)
    private String number;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "class_num", length = 50)
    private String classNum;
}
