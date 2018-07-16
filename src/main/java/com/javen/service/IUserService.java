package com.javen.service;  

import java.util.List;

import com.javen.model.User;
import com.javen.model.UserInfor;
import com.javen.model.uploadFile;
  
//在service下创建UserService接口  （为控制层提供服务，接受控制层的参数，完成相应的功能，并返回给控制层。）
public interface IUserService {  
	
    public User getUserById(int userId);

	public void addInfo(User user);
	
	public User service_login(String user_name, String password);
	
    // Get User Information
    public UserInfor getUserInfor(int inforId);
    
    // Edit the User Information
    public int editUserInfor(UserInfor userInfor);

	public List<uploadFile> findAllFiles(int User_id);

	public void addFileInfor(uploadFile file_infor);

	public int deleteFile(int fileId);

	public uploadFile getFileById(int fileId);
}  