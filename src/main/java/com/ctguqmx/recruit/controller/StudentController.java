package com.ctguqmx.recruit.controller;

import com.aliyuncs.exceptions.ClientException;
import com.ctguqmx.recruit.pojo.Student;
import com.ctguqmx.recruit.pojo.User;
import com.ctguqmx.recruit.service.ContentService;
import com.ctguqmx.recruit.service.StudentService;
import com.ctguqmx.recruit.util.AliDayunSms;
import com.ctguqmx.recruit.util.Qiniu;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static com.ctguqmx.recruit.util.AliDayunSms.sendmessge;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/dashboard")
    public String dashboard(Model model){
        int DevNum=findNumByGroup("开发组");
        int HardNum=findNumByGroup("智能组");
        model.addAttribute("DevNum",DevNum);
        model.addAttribute("HardNum",HardNum);
        model.addAttribute("AllNum",DevNum+HardNum);
        return "dashboard";
    }

    @RequestMapping("/liststudent")
    public String liststudent(Model model){
        List<Student> list1 = studentService.FindAll();
        int DevNum=findNumByGroup("开发组");
        int HardNum=findNumByGroup("智能组");
        model.addAttribute("DevNum",DevNum);
        model.addAttribute("HardNum",HardNum);
        model.addAttribute("AllNum",DevNum+HardNum);
        model.addAttribute("list",list1);
        return "tables";
    }

    @RequestMapping("findstudent")
    public String findstudent(Model model,Student student){
        List<Student> student1= studentService.FindByName(student.getStudentName());
        model.addAttribute("list",student1);
        return "tables";
    }

    @RequestMapping("/deletestudent")
    public String deletestudent(Student student){
        studentService.DeleteById(student.getId());
        return "redirect:/student/liststudent";
    }

    @RequestMapping("/editstudent")
    @ResponseBody
    public String editstudent(@RequestBody Integer id){
        Object obj = studentService.FindById(id);
        String obstr = new Gson().toJson(obj);
        return obstr;
    }

    @RequestMapping("/updatestudent")
    public String updatestudent(Student student){
        System.out.println("yidiyaocgya"+student);
        studentService.Update(student);
        return "redirect:/student/liststudent";
    }


    @RequestMapping("/doaddstudent")
    public String doaddstudent(Student student){
        studentService.AddStudent(student);
        return "redirect:/student/liststudent";
    }



    @RequestMapping("/sendmessage")
    public String sendmessage(){
        List<Student> list=studentService.FindAll();
        for (int i=0;i<list.size();i++){
            Qiniu qiniu = new Qiniu();
            qiniu.SendMessage(list.get(i).getTel(),list.get(i).getStudentName(),"9月1号","一机房");
        }
        return "redirect:/student/liststudent";
    }

    @RequestMapping("/sendonemessage")
    public String sendonemessage(Integer id){
        Student student=studentService.FindById(id);
        Qiniu qiniu = new Qiniu();
        qiniu.SendMessage(student.getTel(),student.getStudentName(),"9月1号","一机房");
        return "redirect:/student/liststudent";
    }

    @RequestMapping("/development_chart")
    public String development_chart(){
        return "development_chart";
    }
    @RequestMapping("/hardware_chart")
    public String hardware_chart(){
        return "hardware_chart";
    }

    @ResponseBody
    @RequestMapping("/getDevlist")
    public List<Integer> getDevlist(){
        String gro="开发组";
        List<Integer> list = new ArrayList<>();
        int a,b,c,d,e,f,g;
        list.add(a=findNumByMajor("计算机科学与技术",gro));
        list.add(b=findNumByMajor("数字媒体技术",gro));
        list.add(c=findNumByMajor("电子信息类",gro));
        list.add(d=findNumByMajor("物联网工程",gro));
        list.add(e=findNumByMajor("信息管理与信息系统",gro));
        list.add(f=findNumByMajor("数据科学与大数据技术",gro));
        g=findNumByGroup(gro);
        list.add(g-a-b-c-d-e-f);
        return list;
    }

    @ResponseBody
    @RequestMapping("/getHardlist")
    public List<Integer> getHardlist(){
        String gro="智能组";
        List<Integer> list = new ArrayList<>();
        int a,b,c,d,e,f,g;
        list.add(a=findNumByMajor("计算机科学与技术",gro));
        list.add(b=findNumByMajor("数字媒体技术",gro));
        list.add(c=findNumByMajor("电子信息类",gro));
        list.add(d=findNumByMajor("物联网工程",gro));
        list.add(e=findNumByMajor("信息管理与信息系统",gro));
        list.add(f=findNumByMajor("数据科学与大数据技术",gro));
        g=findNumByGroup(gro);
        list.add(g-a-b-c-d-e-f);

        return list;
    }

    @ResponseBody
    @RequestMapping("/getHardSexlist")
    public List<Integer> getHardSexlist(){
        String gro="智能组";
        List<Integer> list = new ArrayList<>();
        list.add(findNumBySex("男",gro));
        list.add(findNumBySex("女",gro));
        return list;
    }

    @ResponseBody
    @RequestMapping("/getDevSexlist")
    public List<Integer> getDevSexlist(){
        String gro="开发组";
        List<Integer> list = new ArrayList<>();
        list.add(findNumBySex("男",gro));
        list.add(findNumBySex("女",gro));
        System.out.println(findNumBySex("女",gro));
        return list;
    }


//    方法
    public int findNumByGroup(String group){
        return studentService.findNum(group);
    }

    public int findNumByMajor(String major,String group){
        return studentService.findNUmByMajor(major,group);
    }

    public int findNumBySex(String sex,String group){
        return studentService.findNumBySex(sex,group);
    }


}
