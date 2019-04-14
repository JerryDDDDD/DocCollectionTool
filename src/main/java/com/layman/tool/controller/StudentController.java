package com.layman.tool.controller;

import com.layman.tool.dao.JobRepository;
import com.layman.tool.entity._Class;
import com.layman.tool.service.CommissaryService;
import com.layman.tool.service.StudentService;

import com.layman.tool.utils.ToolResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.tools.Tool;
import java.util.List;


/**
 * @ClassName StudentController
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/11 19:59
 * @Version 3.0
 **/
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CommissaryService commissaryService;

    // 学生页面
    @RequestMapping("/index")
    public String index(Model model) {
        List<_Class> classList = commissaryService.getClassList();
        model.addAttribute("classList", classList);
        return "student/index";
    }

    @ResponseBody
    @RequestMapping("/getStudentNumByClass")
    public ToolResult getStudentNumByClass(String _class) {
        try {
            return studentService.getStudentNumByClass(_class);
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "获取数据失败");
        }
    }

    @ResponseBody
    @RequestMapping("/getJobByClass")
    public ToolResult getJobByClass(String _class) {
        try {
            return studentService.getJobByClass(_class);
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "获取数据失败");
        }
    }

    @ResponseBody
    @RequestMapping("/uploadJob")
    public ToolResult uploadJob(String jobId, String studentName, MultipartFile jobFile) {
        try {
            return studentService.uploadJob(jobId, studentName, jobFile);
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "添加失败");
        }
    }
}
