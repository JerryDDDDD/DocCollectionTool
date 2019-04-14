package com.layman.tool.service;

import com.layman.tool.utils.ToolResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName StudentService
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/11 22:18
 * @Version 3.0
 **/
public interface StudentService {

    ToolResult fileUpload(MultipartFile file);

    ToolResult getStudentNumByClass(String aClass);

    ToolResult getJobByClass(String aClass);

    ToolResult uploadJob(String jobId, String studentName, MultipartFile jobFile);
}
