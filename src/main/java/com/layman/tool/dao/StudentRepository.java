package com.layman.tool.dao;

import com.layman.tool.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @InterfaceName StudentRepository
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/11 20:27
 * @Version 3.0
 **/
public interface StudentRepository extends CrudRepository<Student, String> {
    List<Student> findAllByClassNumOrderByNumber(String classNum);
}
