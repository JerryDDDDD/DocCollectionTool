package com.layman.tool.dao;

import com.layman.tool.entity.Job;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * @InterfaceName JobRepository
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/12 21:20
 * @Version 3.0
 **/
public interface JobRepository extends CrudRepository<Job, String> {

    Job findFirstById(String jobId);

    List<Job> findAllByJobClassAndAndIsFinished(String aClass, int isFinished);

//    List<Job> findAllOrderByCreateTime();

//    @Query(value = "SELECT new com.layman.tool.pojo.JobPojo (a.*, b.class_name) FROM t_job a JOIN t_class b ON a.job_class = b.id", nativeQuery = true)
//    List<JobPojo> getAllJob();
//    @Query(value = "select '*' from t_job")
//    List<Job> findByCondition(JobQueryCondition jobQueryCondition);
}
