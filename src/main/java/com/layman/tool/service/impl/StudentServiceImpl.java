package com.layman.tool.service.impl;

import com.layman.tool.dao.JobRepository;
import com.layman.tool.dao.RecordRepository;
import com.layman.tool.dao.StudentRepository;
import com.layman.tool.entity.Job;
import com.layman.tool.entity.Record;
import com.layman.tool.entity.Student;
import com.layman.tool.service.StudentService;
import com.layman.tool.utils.ToolResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/11 22:19
 * @Version 3.0
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public ToolResult fileUpload(MultipartFile file) {
        return null;
    }

    // 通过班号获取所在班级学生
    @Override
    public ToolResult getStudentNumByClass(String aClass) {
        try {
            List<Student> studentList = studentRepository.findAllByClassNumOrderByNumber(aClass);
            String res = "<option value=''> -- 请选择 -- </option>";
            for (Student student : studentList) {
                res = res + "<option value='" + student.getNumber() + ":" + student.getName() + "'>" + student.getNumber() + "</option>";
            }
            return ToolResult.build(200, "获取数据成功", res);
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "获取数据失败");
        }
    }

    // 通过班号获取班级作业
    @Override
    public ToolResult getJobByClass(String aClass) {
        try {
            List<Job> jobList = jobRepository.findAllByJobClassAndAndIsFinished(aClass, 0);
            String res = "<option value=''>--请选择--</option>";
            for (Job job : jobList) {
                res = res + "<option value='" + job.getId() + "'>" + job.getJobName() + "</option>";
            }
            return ToolResult.build(200, "获取数据成功", res);
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "获取数据失败");
        }
    }

    @Override
    public ToolResult uploadJob(String jobId, String studentName, MultipartFile jobFile) {
        try {
            Job job = jobRepository.findFirstById(jobId);

            String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "/" + job.getJobName() + "/" + jobFile.getOriginalFilename();
            File desFile = new File(filePath);
            if (!desFile.getParentFile().exists()) {
                desFile.mkdirs();
            }
            // 保存文档
            jobFile.transferTo(desFile);
            Record record = new Record();
            record.setJobId(jobId);
            record.setStudentId(studentName.split(":")[0]);
            record.setStudentName(studentName.split(":")[1]);
            Calendar calendar = Calendar.getInstance();
            record.setPostTime(calendar.getTime());
            record.setStorePath(filePath);
            recordRepository.save(record);
            return ToolResult.build(200, "上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "上传作业失败,请重新上传");
        }
    }
}
