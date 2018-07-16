package com.javen.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;   
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.javen.model.User;
import com.javen.model.UserInfor;
import com.javen.model.uploadFile;
import com.javen.service.IUserService;
  
  
@Controller  
@RequestMapping("/user")  
// /user/**
public class UserController {  
    private static Logger log=LoggerFactory.getLogger(UserController.class);
    
    //在spring-mvc 中那个部分的表明了用注解进行注入？
     @Resource
     private IUserService userService;     
     
     @RequestMapping(value="/home")
     public String home( HttpServletRequest request) { 
    	 return "home";
     }
     
     @RequestMapping(value="/SignupPage")
     public String loginPage( HttpServletRequest request) { 
    	 return "signup";
     }
     
     @RequestMapping(value="/loginPage")
     public String SignupPage( HttpServletRequest request) { 
    	 return "login";
     }
     
     
     @RequestMapping(value="/doLogout")
     public String doLogout( HttpServletRequest request) { 
		request.getSession().setAttribute("userId", null);
		request.getSession().setAttribute("userName", null);
    	 return "home";
     }
     
     @RequestMapping(value="/loginFailed")
     public String loginFailed( HttpServletRequest request) { 
    	 return "loginFailed";
     }
          
     
     
 	@RequestMapping(value="/addInfo")
 	public void addUser(User user, HttpServletRequest request, HttpServletResponse response) throws IOException{
 		try {			
 			//System.out.println("Name: " + user.getUserName() + " Password: " + user.getPassword() + " Age: " + user.getAge());
 			userService.addInfo(user);
 		} catch (Exception e) {
 			e.printStackTrace();
 			request.setAttribute("InfoMessage", "添加信息失败！具体异常信息：" + e.getMessage());
 		} finally {
 			String path = request.getContextPath() ;
 			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
 			response.sendRedirect(basePath+"user/home");	
 		}
 	}

    
    @RequestMapping(value="/login") 
    public void login(@RequestParam(value="userName") String userName,
    		@RequestParam(value="password") String pass, HttpServletRequest request,
    		HttpServletResponse response) {
		String path = request.getContextPath() ;
		String basePath = request.getScheme()
				+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		//System.out.println("Name: " + userName + " Password: " + pass);
		try {
			User user = userService.service_login(userName, pass);
    		if (user != null) {
    			System.out.println("Successfully");
    			request.getSession().setAttribute("userId", user.getId());
    			request.getSession().setAttribute("userName", userName);
    			response.sendRedirect(basePath+"user/home");
    		}
    		else {
    			response.sendRedirect(basePath+"user/loginFailed");
    		}  		
    	} catch (Exception e) {
 			e.printStackTrace();
 			request.setAttribute("InfoMessage", "登陆失败！具体异常信息：" + e.getMessage());
 		}	
    	//return "home";
    }
    
    
    //提取数据库中用户信息用于显示
    @RequestMapping(value="/showPersonalInfor")
    public String showPersonalInfor(HttpServletRequest request) {
   	 
   	 int userId = (Integer) request.getSession().getAttribute("userId");
   	 System.out.println("show personal information user id" + userId);
   	 UserInfor userInfor = userService.getUserInfor(userId);
   	 String firstName = userInfor.getFirstName();
   	 String lastName = userInfor.getLastName();
   	 String gender = userInfor.getGender();
   	 String street = userInfor.getStreet();
   	 String email = userInfor.getEmail();
   	 Integer  phone_num = userInfor.getPnoneNum();
   	 
   	 if (firstName == null) {
   		 request.setAttribute("first_name", "undefined");
   	 } else {
   		 request.setAttribute("first_name", firstName);
   	 }
   	 
    	 if (lastName == null) {
   		 request.setAttribute("last_name", "undefined");
   	 } else {
   		 request.setAttribute("last_name", lastName);
   	 }
    	 
    	 if (gender == null) {
   		 request.setAttribute("gender", "undefined");
   	 } else {
   		 request.setAttribute("gender", gender);
   	 }
    	 
    	 if (street == null) {
   		 request.setAttribute("street", "undefined");
   	 } else {
   		 request.setAttribute("street", street);
   	 }
    	 
    	 if (email == null) {
   		 request.setAttribute("email", "undefined");
   	 } else {
   		 request.setAttribute("email", email);
   	 }
    	 
    	 // 数字怎么处理啊
    if (phone_num == null) {
   		 request.setAttribute("phone_num", 0);
   	 } else {
   		 request.setAttribute("phone_num", phone_num);
   	 }
    	 
   	 return "editPersonalInfor";
    }
    
    //submit the new User Information 
    @RequestMapping(value="/editInfor")
    public void editInfor(HttpServletRequest request, HttpServletResponse response) throws IOException { 
    	
    	String path = request.getContextPath() ;
		String basePath = request.getScheme()
				+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		UserInfor userInfor = new UserInfor();
		int userId = (Integer) request.getSession().getAttribute("userId");
    	userInfor.setId(userId);
    	String firstName = request.getParameter("first_name");
    	userInfor.setFirstName(firstName);
    	String last_name = request.getParameter("last_name");
    	userInfor.setLastName(last_name);
    	String personal_gender = request.getParameter("personal_gender");
    	userInfor.setGender(personal_gender);
    	String street = request.getParameter("street");
    	userInfor.setStreet(street);
    	String email = request.getParameter("email");
    	userInfor.setEmail(email);
    	Integer phone_num = Integer.parseInt(request.getParameter("phone_num"));
    	userInfor.setPnoneNum(phone_num);

    	System.out.println("first name: " + userInfor.getFirstName());
    	try {
    		int result = this.userService.editUserInfor(userInfor);
    		if (result == 1) {
    			System.out.println("Edit User Information successfully");
    			response.sendRedirect(basePath+"user/showPersonalInfor");
    		} else {
    			response.sendRedirect(basePath+"user/showPersonalInfor");
    		}
    	} catch (Exception e) {
 			e.printStackTrace();
 			request.setAttribute("InfoMessage", "修改信息失败：" + e.getMessage());
 		}
    }
    
    
    // upload all files that belongs to this users
    @RequestMapping(value="/uploadFilePage")
    public ModelAndView uploadFilePage( HttpServletRequest request) {
    	//调用Service层进行数据查找
    	int userId = (Integer) request.getSession().getAttribute("userId");
		List<uploadFile> fileLists = userService.findAllFiles(userId);
		ModelAndView modelAndView = new ModelAndView();

		//将数据放到request中
		modelAndView.addObject("fileLists", fileLists);
		modelAndView.addObject("file_numer", fileLists.size());
		//System.out.println(fileLists.get(0).getFile_name());
		//指定视图
		modelAndView.setViewName("filePage");
		System.out.println("numbers of files: "  + fileLists.size());
		return modelAndView;
    }
    
	/*
     	*采用Spring提供的上传文件的方法
     */
    @RequestMapping(value="/UploadFile", method=RequestMethod.POST)
    public void fileUpload(HttpServletRequest request, 
    		@RequestParam("file") CommonsMultipartFile file, HttpServletResponse response) throws IOException {    

    	int userId = (Integer) request.getSession().getAttribute("userId");
    	String save = "E:\\Internship\\Wuhan_Huawei_Boss\\SpringFramework"
    			+ "\\Projects\\demo\\WebContent\\UploadFile";
    	// create one new directory if not exist
    	File dir = new File(save + "\\" + userId);
    	if (!dir.isDirectory()) {
    		dir.mkdir();
    	} 
        try {
            //获取输出流  
        	String fileInfor = file.getOriginalFilename();
        	String savePath = save + "\\"  + userId  + "\\" + fileInfor;
            OutputStream os=new FileOutputStream(savePath);
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is = file.getInputStream();
            int temp;
            //一个一个字节的读取并写入
            while((temp=is.read())!=(-1))
            {
                os.write(temp);
            }
           os.flush();
           os.close();
           is.close();
           // save the file information in the database for this user_id
           uploadFile file_infor = new uploadFile();
           //System.out.println("fileInfor: " + fileInfor);
           String[] splits = fileInfor.split("\\.");
           String file_name = splits[0];
           String file_type = splits[1];
           file_infor.setUser_id(userId);
           file_infor.setFile_name(file_name);
           file_infor.setFile_type(file_type);
           file_infor.setFile_path(savePath);
           System.out.println(file_name +  file_type);
       	   List<uploadFile> fileLists = userService.findAllFiles(userId);
       	   boolean check = true;
       	   for (int i = 0; i < fileLists.size(); i++) {
       		   String get_name = fileLists.get(i).getFile_name();
       		   String get_type = fileLists.get(i).getFile_type();
       		   if (get_name.equals(file_name) && get_type.equals(file_type)) {
       			   check = false;
       		   }
       	   }
       	   if (check) {
       		   this.userService.addFileInfor(file_infor);
       	   } 
           
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		String path = request.getContextPath() ;
		String basePath = request.getScheme()
				+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        response.sendRedirect(basePath+"user/uploadFilePage");
    }
    
    
    @RequestMapping("/fileDownLoad")
    public ResponseEntity<byte[]> fileDownLoad(@RequestParam("fileId")int fileId,
    HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String path = request.getContextPath() ;
		String basePath = request.getScheme()
				+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    	uploadFile _file = userService.getFileById(fileId);
    	String save = _file.getFile_path();
    	String fileName = _file.getFile_name();
    	
    	//将该文件加入到输入流之中
		InputStream in = new FileInputStream(save);
		
		// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
    	byte[] body = new byte[in.available()];
        in.read(body);//读入到输入流里面
        
        fileName = new String(fileName.getBytes("gbk"),"iso8859-1");//防止中文乱码
        HttpHeaders headers = new HttpHeaders();//设置响应头
        headers.add("Content-Disposition", "attachment;filename=" + fileName + "." + _file.getFile_type());
        HttpStatus statusCode = HttpStatus.OK;//设置响应吗
        ResponseEntity<byte[]> _response = new ResponseEntity<byte[]>(body, headers, statusCode);
        return _response;

        //public ResponseEntity（T  body，
        //                       MultiValueMap < String，String > headers，
        //                       HttpStatus  statusCode）
        //HttpEntity使用给定的正文，标题和状态代码创建一个新的。
        //参数：
        //body - 实体机构
        //headers - 实体头
        //statusCode - 状态码
    }
     
    @RequestMapping(value="/deleteFile", method=RequestMethod.GET)
    public void deleteFile(HttpServletRequest request, HttpServletResponse response) throws IOException { 
    	int fileId = Integer.parseInt(request.getParameter("file_id"));
    	uploadFile _file = userService.getFileById(fileId);
    	System.out.println("file_id: " + fileId);
    	String path = request.getContextPath() ;
		String basePath = request.getScheme()
				+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		// delete this file by using given file id according to this user id
		File deleteFile = new File(_file.getFile_path());
		if (deleteFile.isFile() && deleteFile.exists()) {
			deleteFile.delete();
			int check = userService.deleteFile(fileId);
			if (check == 1) {
				System.out.println("delete file successfully");
			} else {
				System.out.println("delete file failed");
			}
		}
        response.sendRedirect(basePath+"user/uploadFilePage");
    }
    
//    // /user/test?id=1
//    @RequestMapping(value="/test",method=RequestMethod.GET)  
//    public String test(HttpServletRequest request,Model model){  
//        int userId = Integer.parseInt(request.getParameter("id"));  
//        System.out.println("userId:"+userId);
//        User user=null;
//        if (userId==1) {
//             user = new User();  
//             user.setAge(11);
//             user.setId(1);
//             user.setPassword("123");
//             user.setUserName("javen");
//        }
//       
//        log.debug(user.toString());
//        model.addAttribute("user", user);  
//        return "index";  
//    }  
//    
//    
//    // /user/showUser?id=1
//    @RequestMapping(value="/showUser",method=RequestMethod.GET)  
//    public String toIndex(HttpServletRequest request,Model model){  
//        int userId = Integer.parseInt(request.getParameter("id"));  
//        System.out.println("userId:"+userId);
//        User user = this.userService.getUserById(userId);  
//        log.debug(user.toString());
//        model.addAttribute("user", user);
//        model.addAttribute("testid", "123123123");
//        return "showUser";  
//    }  
//    
// // /user/showUser2?id=1
//    @RequestMapping(value="/showUser2",method=RequestMethod.GET)  
//    public String toIndex2(@RequestParam("id") String id,Model model){  
//        int userId = Integer.parseInt(id);  
//        System.out.println("userId:"+userId);
//        User user = this.userService.getUserById(userId);  
//        log.debug(user.toString());
//        model.addAttribute("user", user);  
//        return "showUser";  
//    }  
//    
//    
//    // /user/showUser3/{id}
//    @RequestMapping(value="/showUser3/{id}",method=RequestMethod.GET)  
//    public String toIndex3(@PathVariable("id")String id,Map<String, Object> model){  
//        int userId = Integer.parseInt(id);  
//        System.out.println("userId:"+userId);
//        User user = this.userService.getUserById(userId);  
//        log.debug(user.toString());
//        model.put("user", user);  
//        return "showUser";  
//    }  
//    
// // /user/{id}
//    @RequestMapping(value="/{id}",method=RequestMethod.GET)  
//    public @ResponseBody User getUserInJson(@PathVariable String id,Map<String, Object> model){  
//        int userId = Integer.parseInt(id);  
//        System.out.println("userId:"+userId);
//        User user = this.userService.getUserById(userId);  
//        log.info(user.toString());
//        return user;  
//    }  
//    
//    // /user/{id}
//    @RequestMapping(value="/jsontype/{id}",method=RequestMethod.GET)  
//    public ResponseEntity<User>  getUserInJson2(@PathVariable String id,Map<String, Object> model){  
//        int userId = Integer.parseInt(id);  
//        System.out.println("userId:"+userId);
//        User user = this.userService.getUserById(userId);  
//        log.info(user.toString());
//        return new ResponseEntity<User>(user,HttpStatus.OK);  
//    } 
//    
//    //文件上传、
//    @RequestMapping(value="/upload")
//    public String showUploadPage(){
//        return "user_admin/file";
//    }
//    
//    @RequestMapping(value="/doUpload",method=RequestMethod.POST)
//    public String doUploadFile(@RequestParam("file")MultipartFile file) throws IOException{
//        if (!file.isEmpty()) {
//            log.info("Process file:{}",file.getOriginalFilename());
//        }
//        FileUtils.copyInputStreamToFile(file.getInputStream(), new File("E:\\",System.currentTimeMillis()+file.getOriginalFilename()));
//        return "succes";
//    }
}  