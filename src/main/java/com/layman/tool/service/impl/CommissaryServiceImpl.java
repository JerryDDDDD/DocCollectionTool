package com.layman.tool.service.impl;

import com.layman.tool.dao.ClassRepository;
import com.layman.tool.dao.JobRepository;
import com.layman.tool.dao.RecordRepository;
import com.layman.tool.dao.StudentRepository;
import com.layman.tool.entity.Job;
import com.layman.tool.entity.Student;
import com.layman.tool.entity._Class;
import com.layman.tool.service.CommissaryService;
import com.layman.tool.utils.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


/**
 * @ClassName CommissaryServiceImpl
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/11 22:21
 * @Version 3.0
 **/
@Service
public class CommissaryServiceImpl implements CommissaryService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Value("${TOOL_FILE_ZIP_BASE_PATH}")
    private String TOOL_FILE_ZIP_BASE_PATH;

    @Override
    public ToolResult fileUpload(MultipartFile file) {
        try {
            String filePath = "D:" + "/" + file.getOriginalFilename();
            File desFile = new File(filePath);
            if (!desFile.getParentFile().exists()) {
                desFile.mkdirs();
            }
            try {
                file.transferTo(desFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }

            //将文件读入
            InputStream in = new FileInputStream("D:" + "/" + file.getOriginalFilename());
            //创建工作簿
            XSSFWorkbook wb = new XSSFWorkbook(in);
            //读取第一个sheet
            Sheet sheet = wb.getSheetAt(0);
            System.out.println(sheet.getLastRowNum());
            //获取所有行数据
            List<Student> studentList = new ArrayList<>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Student student = new Student();
                //循环读取科目
                for (Cell cell : row) {
                    CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                    //打印单元格的位置
                    System.out.print(cellRef.formatAsString());
                    System.out.print(" - ");
                    //转换数据类型
                    String rfidCode = ExcelUtil.getCellTypes(cell);
                    System.out.println(rfidCode);
                    switch (cell.getColumnIndex()) {
                        case 0: {
                            student.setNumber(rfidCode);
                            break;
                        }
                        case 1: {
                            student.setName(rfidCode);
                            break;
                        }
                    }
                    studentList.add(student);
                }
            }
            studentRepository.saveAll(studentList);
            return ToolResult.build(200, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "文件上传失败");
        }
    }

    @Override
    public ToolResult addClass(String classNumber, String className, MultipartFile file) {
        try {
            _Class _class = new _Class();
            _class.setClassNumber(classNumber);
            _class.setClassName(className);
            classRepository.save(_class);
            String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "/" + file.getOriginalFilename();

            File desFile = new File(filePath);
            if (!desFile.getParentFile().exists()) {
                desFile.mkdirs();
            }
            try {
                file.transferTo(desFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }


            //将文件读入
            InputStream in = new FileInputStream(filePath);
            //创建工作簿
            XSSFWorkbook wb = new XSSFWorkbook(in);
            //读取第一个sheet
            Sheet sheet = wb.getSheetAt(0);
            System.out.println(sheet.getLastRowNum());
            //获取所有行数据
            List<Student> studentList = new ArrayList<>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Student student = new Student();
                //循环读取科目
                for (Cell cell : row) {
                    CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                    //打印单元格的位置
                    System.out.print(cellRef.formatAsString());
                    System.out.print(" - ");
                    //转换数据类型
                    String rfidCode = ExcelUtil.getCellTypes(cell);
                    System.out.println(rfidCode);
                    switch (cell.getColumnIndex()) {
                        case 0: {
                            student.setNumber(rfidCode);
                            break;
                        }
                        case 1: {
                            student.setName(rfidCode);
                            break;
                        }
                    }
                    student.setClassNum(classNumber);
                    studentList.add(student);
                }
            }
            studentRepository.saveAll(studentList);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "添加失败");
        }
    }

    /**
     * @return java.util.List<com.layman.tool.entity._Class>
     * @Author 叶泽文
     * @Description 获取班级列表
     * @Date 22:01 2019/4/12
     * @Param []
     **/
    @Override
    public List<_Class> getClassList() {
        try {
            // 获取所有的班级
            return (List<_Class>) classRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ToolResult addJob(String jobName, String jobClass, String description) {
        try {
            String dirPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + jobName + "/";
            CreateFileUtil.createDir(dirPath);
            Job job = new Job();
            job.setJobName(jobName);
            job.setJobClass(jobClass);
            job.setDescription(description);
            Calendar calendar = Calendar.getInstance();
            job.setCreateTime(calendar.getTime());
            job.setIsFinished(0);
            job.setStorePath(dirPath);
            jobRepository.save(job);

            return ToolResult.build(200, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "获取数据失败");
        }
    }

    /**
     * @return com.layman.tool.utils.ToolResult
     * @Author 叶泽文
     * @Description 获取作业数据
     * @Date 7:55 2019/4/13
     * @Param []
     **/
    @Override
    public ToolResult getJobList() {
        try {
            List<Job> jobs = (List<Job>) jobRepository.findAll();
            ToolTablePojo toolTablePojo = new ToolTablePojo(0, "获取数据成功", (long) jobs.size(), jobs);
            return ToolResult.build(200, "获取数据成功", toolTablePojo);
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "获取数据失败");
        }
    }

    @Override
    public ToolResult getJobDetail(String jobId, String clazzNum) {
        try {
            List<Student> allStudent = studentRepository.findAllByClassNumOrderByNumber(clazzNum);
//            List<Record> recordList = recordRepository.findByJobId(jobId);
//            List<PostStudentPojo> postStudentPojoList = recordRepository.getPostStudent(jobId);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "获取数据失败");
        }
    }

    // 下载压缩包
    @Override
    public void download(String jobId, HttpServletRequest request, HttpServletResponse response) {
        Job job = jobRepository.findFirstById(jobId);
        IOtools.downloadZipFile(TOOL_FILE_ZIP_BASE_PATH, job.getJobName()+".zip", response);
    }

    // 打包文件
    @Override
    public ToolResult packageFile(String jobId) {
        try {
            Job job = jobRepository.findFirstById(jobId);
            List<File> fileList = IOtools.getFiles(job.getStorePath());
            String zipName = TOOL_FILE_ZIP_BASE_PATH + File.separator + job.getJobName() + ".zip";
            if (IOtools.zipListFile(fileList, zipName))
                return ToolResult.build(200, "打包成功");
            else
                return ToolResult.build(404, "打包失败");
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "打包失败");
        }
    }

    // 截止文件
    @Override
    public ToolResult deadLine(String jobId) {
        try {
            Job job = jobRepository.findFirstById(jobId);
            job.setIsFinished(1);
            jobRepository.save(job);
            return ToolResult.build(200, "成功截止");
        } catch (Exception e) {
            e.printStackTrace();
            return ToolResult.build(404, "截止失败");
        }
    }
}
