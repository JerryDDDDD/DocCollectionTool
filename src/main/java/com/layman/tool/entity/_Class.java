package com.layman.tool.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @ClassName _Class
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/12 21:01
 * @Version 3.0
 **/
@Entity
@Table(name = "t_class")
@Data
public class _Class {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "class_number", length = 50)
    private String classNumber;

    @Column(name = "class_name", length = 50)
    private String className;
}
