package com.layman.tool.controller;

import ch.qos.logback.core.util.FileUtil;
import com.layman.tool.entity._Class;
import com.layman.tool.service.CommissaryService;
import com.layman.tool.utils.IOtools;
import com.layman.tool.utils.ToolResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName CommissaryController
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/11 22:20
 * @Version 3.0
 **/
@Controller
@RequestMapping("/commissary")
public class CommissaryController {

    @Autowired
    private CommissaryService commissaryService;

    /**
     * @return java.lang.String
     * @Author 叶泽文
     * @Description 首页
     * @Date 17:30 2019/4/12
     * @Param []
     **/
    @GetMapping("/index")
    public String index() {
        return "commissary/index";
    }

    /**
     * @return java.lang.String
     * @Author 叶泽文
     * @Description 跳转到添加班级
     * @Date 20:56 2019/4/12
     * @Param []
     **/
    @GetMapping("/toAddClass")
    public String toAddClass() {
        return "commissary/addClass";
    }


    /**
     * @return com.layman.tool.utils.ToolResult
     * @Author 叶泽文
     * @Description 添加班级
     * @Date 20:58 2019/4/12
     * @Param [claaNumber, className, file]
     **/
    @ResponseBody
    @PostMapping("/addClass")
    public ToolResult addClass(String classNumber, String className, MultipartFile file) {
        try {
            return commissaryService.addClass(classNumber, className, file);
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "添加失败");
        }
    }

    /**
     * @return java.lang.String
     * @Author 叶泽文
     * @Description 跳转到添加作业
     * @Date 22:14 2019/4/12
     * @Param [model]
     **/
    @GetMapping("/toAddJob")
    public String toAddJob(Model model) {
        List<_Class> classList = commissaryService.getClassList();
        model.addAttribute("classList", classList);
        return "commissary/addJob";
    }


    /**
     * @return com.layman.tool.utils.ToolResult
     * @Author 叶泽文
     * @Description 添加作业
     * @Date 22:19 2019/4/12
     * @Param [jobName, jobClass, description]
     **/
    @ResponseBody
    @PostMapping("/addJob")
    public ToolResult addJob(String jobName, String jobClass, String description) {
        try {
            return commissaryService.addJob(jobName, jobClass, description);
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "上传失败");
        }
    }

    /**
     * @return java.lang.String
     * @Author 叶泽文
     * @Description 跳转到作业列表页
     * @Date 7:44 2019/4/13
     * @Param []
     **/
    @GetMapping("/toJobList")
    public String toJobList() {
        return "commissary/jobList";
    }

    /**
     * @return com.layman.tool.utils.ToolResult
     * @Author 叶泽文
     * @Description 获取作业数据
     * @Date 7:54 2019/4/13
     * @Param []
     **/
    @ResponseBody
    @RequestMapping("/getJobList")
    public ToolResult getJobList() {
        try {
            return commissaryService.getJobList();
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "获取数据失败");
        }
    }

    @ResponseBody
    @RequestMapping("/getDetail")
    public ToolResult getJobDetail(String jobId, String clazzNum) {
        try {
            return commissaryService.getJobDetail(jobId, clazzNum);
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "获取数据失败");
        }
    }

    @ResponseBody
    @RequestMapping("/packageFile")
    public ToolResult packageFile(String jobId){
        try {
            return commissaryService.packageFile(jobId);
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "打包失败");
        }
    }

    @RequestMapping("/download")
    public void downloadZip(String jobId, HttpServletRequest request, HttpServletResponse response) {
        commissaryService.download(jobId, request, response);
    }



    @GetMapping("toUpload")
    public String toFileUpload() {
        return "upload";
    }

    @ResponseBody
    @PostMapping("/fileUpload")
    public ToolResult fileUpload(MultipartFile file) {
        try {
            return commissaryService.fileUpload(file);
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "文件上传失败");
        }
    }
}
