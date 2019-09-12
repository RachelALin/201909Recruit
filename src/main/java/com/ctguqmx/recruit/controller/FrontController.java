package com.ctguqmx.recruit.controller;

import com.aliyuncs.exceptions.ClientException;
import com.ctguqmx.recruit.pojo.Content;
import com.ctguqmx.recruit.pojo.MessageBoard;
import com.ctguqmx.recruit.pojo.Student;
import com.ctguqmx.recruit.service.ContentService;
import com.ctguqmx.recruit.service.MessageBoardService;
import com.ctguqmx.recruit.service.StudentService;
import com.ctguqmx.recruit.util.AliDayunSms;
import com.ctguqmx.recruit.util.GetRandom;
import com.ctguqmx.recruit.util.Qiniu1;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class FrontController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private MessageBoardService messageBoardService;

    @RequestMapping("/signup")
    public String signup(){
        return "signup";
    }

    @RequestMapping("/checkstudentId")
    @ResponseBody
    public String checkstudentId(@RequestBody Student student){
        String result;
        if (studentService.selectBySid(student)!=null){
            result = "0";
        }else {
            result="1";
        }
        return result;
    }

    @RequestMapping("/checkTel")
    @ResponseBody
    public String checkTel(@RequestBody Student student){
        String result;
        System.out.println("ssssssssss"+student);
        if (studentService.selectByTel(student)!=null){
            result = "0";
        }else {
            result="1";
        }
        return result;
    }

    @RequestMapping("/dosignup")
    @ResponseBody
    public String dosignup(@RequestBody Student student){
        String result;
        Date nowTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTimeString = sdf.format(nowTime);
        System.out.println(nowTimeString);
        String beginTime = "2019-09-01 00:00:00";
        String endTime = "2019-09-20 23:59:59";
        int res1 = nowTimeString.compareTo(beginTime);
        int res2 = nowTimeString.compareTo(endTime);
        if( res1 >=0 && res2 <= 0 ){
            if (studentService.AddStudent(student)){
                result = "1";
            }else {
                result="0";
            }
        }
        else{
            result="2";
        }
        System.out.println(result);
        return result;
    }

    @RequestMapping("/")
    public String index(Model model){
        List<Content> list=contentService.findAll();
        if (list.get(0).getPropaganda()==null){
            list.get(0).setPropaganda("暂无信息");
        }
        if (list.get(0).getSignUp()==null){
            list.get(0).setSignUp("暂无信息");
        }
        if (list.get(0).getSeminar()==null){
            list.get(0).setSeminar("暂无信息");
        }
        if (list.get(0).getExam()==null){
            list.get(0).setExam("暂无信息");
        }
        model.addAttribute("cont1",list.get(0).getPropaganda());
        model.addAttribute("cont2",list.get(0).getSignUp());
        model.addAttribute("cont3",list.get(0).getSeminar());
        model.addAttribute("cont4",list.get(0).getExam());

        List<MessageBoard> list1 = messageBoardService.FindAll();
        for (MessageBoard messageBoard: list1){
            model.addAttribute("message",list1);
        }
            return "index";
    }

    @RequestMapping("/grade")
    public String grade(){
            return "grade";
    }

    @RequestMapping("/getcode")
    @ResponseBody
    public String getcode(@RequestBody Student student, HttpSession session) throws ClientException {
        String result;
        String code = GetRandom.getMsgCode();
        if(studentService.findStudentByNameAndIdAndTel(student.getStudentName(),student.getStudentId(),student.getTel())!=null){
            studentService.updateCodeByTel(code,student.getTel());

            Qiniu1 qiniu = new Qiniu1();
            if (qiniu.SendMessage(student.getTel(),code)){

                result=code;
            }else {
                result="0";
            }

        }else{
            result="2";
        }
        return result;
    }


    @RequestMapping("/checkgrade")
    @ResponseBody
    public String checkgrade(@RequestBody Student student){
        String result="0";
        try{
            if (studentService.FindByNameAndSIdAndCode(student.getStudentName(),student.getStudentId(),student.getCode())!=null){
                result="1";
            }else{
                System.out.println("让我看看");
                result="0";
            }
            return result;
        }catch (Exception e){
            return result;
        }

    }

    @RequestMapping("/dograde")
    public String dograde(Student student, Model model){
        System.out.println("wsm500   "+student);
        Student student1=studentService.FindByNameAndSIdAndCode(student.getStudentName(),student.getStudentId(),student.getCode());
        if (student1.getGrade()!=null&&student1.getStatus()!=0){
            model.addAttribute("stu",student1);
            if (student1.getStatus()==2){
                model.addAttribute("status","笔试通过");
            }else {
                model.addAttribute("status","未通过");
            }
            return "showgrade";
        }else {
            model.addAttribute("stu",student1);
            model.addAttribute("status","待定");
            return "showgrade";
        }

    }

    @RequestMapping("/success")
    public String success(){
        return "success";
    }
}
