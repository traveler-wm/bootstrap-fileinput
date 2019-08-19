package cn.datacharm.springboot_fileinputdemo.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class FileController {
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping("/uploadImg")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file,Model model, HttpServletRequest request) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();

        //获取附加的数据
        System.out.println(request.getParameter("seriesId"));
        System.out.println(request.getParameter("seriesName"));


        File dest = null;

        //上传路径设置
        String path = "D:/";
        dest= new File(path + fileName);

        model.addAttribute("src","img/"+fileName);


        try {

            //上传到本机
            file.transferTo(dest);



//            //WebService方式中的Axis上传到远程服务器
//            Service service = new Service();
//            Call call;
//            try {
//                call = (Call) service.createCall();
//                String address = "YourIp" + "YourPorts";
//                String method = "xxx";
//                String nameSpace = "xxx";
//                String localPart = "xxx";
//                String param1 = "xxx";     //上传参数1
//
//                QName qName = new QName("http://tempuri.org/", "UloadJgwpicts"); //设置命名空间和需要调用的方法名
//                call.setTargetEndpointAddress("http://" + address + "/" + method + "?wsdl"); //设置请求路径
//                call.setOperationName(qName); //调用的方法名
//                call.setTimeout(new Integer(60000));//注意设置超时时间
//
//                //上传接口参数设置
//                call.addParameter(new QName(nameSpace,"YourParam1"), XMLType.XSD_STRING, ParameterMode.IN);
//                call.setEncodingStyle("UTF-8");
//                call.setSOAPActionURI(nameSpace + localPart);
//                call.setReturnType(XMLType.XSD_INT);   //设置返回类型(如：成功返回1,失败返回0)
//
//                Object result = call.invoke(new Object[]{param1});    //执行上传
//                System.out.println(result.toString());
//                if (result.toString().equals("1"))
//                    System.out.println("success!");
//                else System.out.println("error!");
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }


            return JSON.toJSONString("上传成功！");
        } catch (Exception e) {
            return JSON.toJSONString("上传失败！");
        }
    }

}
