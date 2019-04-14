package com.layman.tool.dao;

import com.layman.tool.entity.Record;
import com.layman.tool.pojo.PostStudentPojo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @ClassName RecordRepository
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/12 21:20
 * @Version 3.0
 **/
public interface RecordRepository  extends CrudRepository<Record, String> {

    List<Record> findByJobId(String jobId);

//    @Query(value = "select new com.layman.tool.pojo.PostStudentPojo(student_id, student_name) from t_record where job_id = ?1")
//    List<PostStudentPojo> getPostStudent(String jobId);
}
