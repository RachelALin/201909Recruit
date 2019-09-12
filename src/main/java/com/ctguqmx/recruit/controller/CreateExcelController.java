package com.ctguqmx.recruit.controller;

import com.ctguqmx.recruit.pojo.Student;
import com.ctguqmx.recruit.service.StudentService;
import com.ctguqmx.recruit.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/download")
public class CreateExcelController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/exportGWDataManageList")
    public void exportGWDataManageList(HttpServletRequest request, HttpServletResponse response){

        try{
            List<Student> voList = studentService.FindAll();

            String[] headerName = { "序号","姓名", "学号", "学院","专业","QQ","电话","性别","组别","成绩"};
            String[] headerKey = { "id","studentName", "studentId", "academy","major","qq","tel","sex","group","grade"};

            HSSFWorkbook wb = ExcelUtil.createExcel(headerName, headerKey, "报名信息管理表", voList);
            if (wb == null) {
                return;
            }
            response.setContentType("application/vnd.ms-excel");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date();
            String str = sdf.format(date);
            String fileName = "学生信息管理" + str;
            response.setHeader("Content-disposition",
                    "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO-8859-1") + ".xls");
            OutputStream ouputStream = response.getOutputStream();
            ouputStream.flush();
            wb.write(ouputStream);
            ouputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/exportDevManageList")
    public void exportDevManageList(HttpServletRequest request, HttpServletResponse response){

        try{
            List<Student> voList = studentService.findByGroup("开发组");

            String[] headerName = { "序号","姓名", "学号", "学院","专业","QQ","电话","性别","组别","成绩"};
            String[] headerKey = { "id","studentName", "studentId", "academy","major","qq","tel","sex","group","grade"};

            HSSFWorkbook wb = ExcelUtil.createExcel(headerName, headerKey, "报名信息管理表", voList);
            if (wb == null) {
                return;
            }
            response.setContentType("application/vnd.ms-excel");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date();
            String str = sdf.format(date);
            String fileName = "学生信息管理" + str;
            response.setHeader("Content-disposition",
                    "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO-8859-1") + ".xls");
            OutputStream ouputStream = response.getOutputStream();
            ouputStream.flush();
            wb.write(ouputStream);
            ouputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/exportHardManageList")
    public void exportHardManageList(HttpServletRequest request, HttpServletResponse response){

        try{
            List<Student> voList = studentService.findByGroup("智能组");

            String[] headerName = { "序号","姓名", "学号", "学院","专业","QQ","电话","性别","组别","成绩"};
            String[] headerKey = { "id","studentName", "studentId", "academy","major","qq","tel","sex","group","grade"};

            HSSFWorkbook wb = ExcelUtil.createExcel(headerName, headerKey, "报名信息管理表", voList);
            if (wb == null) {
                return;
            }
            response.setContentType("application/vnd.ms-excel");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date();
            String str = sdf.format(date);
            String fileName = "学生信息管理" + str;
            response.setHeader("Content-disposition",
                    "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO-8859-1") + ".xls");
            OutputStream ouputStream = response.getOutputStream();
            ouputStream.flush();
            wb.write(ouputStream);
            ouputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}