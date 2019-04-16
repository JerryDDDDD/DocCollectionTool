package com.layman.tool.service;

import com.layman.tool.entity.Record;
import com.layman.tool.entity._Class;
import com.layman.tool.utils.ToolResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @InterfaceName CommissaryService
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/11 22:21
 * @Version 3.0
 **/
public interface CommissaryService {
    ToolResult fileUpload(MultipartFile file);

    ToolResult addClass(String classNumber, String className, MultipartFile file);

    List<_Class> getClassList();

    ToolResult addJob(String jobName, String jobClass, String description);

    ToolResult getJobList();

    List<Record> getJobDetail(String jobId, String clazzNum);

    void download(String jobId, HttpServletRequest request, HttpServletResponse response);

    ToolResult packageFile(String jobId);

    ToolResult deadLine(String jobId);

    Object getSubmittedStudentByJobId(String jobId);

    Object findByNumberNotIn(List<String> ids);
}
