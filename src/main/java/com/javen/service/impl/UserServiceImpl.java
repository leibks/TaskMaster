package com.javen.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;  
  



import org.springframework.stereotype.Service;  

import com.javen.dao.IUserDao;
import com.javen.model.User;
import com.javen.model.UserInfor;
import com.javen.model.uploadFile;
import com.javen.service.IUserService;
import com.mysql.jdbc.StringUtils;
  
  
@Service("userService")  
public class UserServiceImpl implements IUserService {  
	// 为什么这里实现它不需要constructor 构造
	// java bean 默认了一个constructor, 注入了一个IUserDao
	
    @Resource  
    private IUserDao userDao;  
    
    public User getUserById(int userId) {  
        // TODO Auto-generated method stub  
        return this.userDao.selectByPrimaryKey(userId);  
    }

	@Override
	public void addInfo(User user) {
		// TODO Auto-generated method stub
		this.userDao.insertSelective(user);
		System.out.println(user.getUserName());
		int infor_id = this.userDao.getIdByuserName(user.getUserName());
		System.out.println(infor_id);
		int get = this.userDao.addPersonInfor(infor_id);
	}

	@Override
	public User service_login(String user_name, String password) {
//	Map<String, String> param = new HashMap<String, String>();
//	param.put("userName", user_name);
//	param.put("password", password);	
//	User user = this.userDao.login(param);
		return this.userDao.login(user_name, password);
	}

	@Override
	public UserInfor getUserInfor(int inforId) {
		return this.userDao.getUserInfor(inforId);
	}

	@Override
	public int editUserInfor(UserInfor userInfor) {
		// TODO Auto-generated method stub
		//int inforId = userInfor.getId();		
		return this.userDao.editUserInforById(userInfor);
	}

	@Override
	public List<uploadFile> findAllFiles(int User_id) {
		// TODO Auto-generated method stub
		return this.userDao.selectAllFiles(User_id);
	}

	@Override
	public void addFileInfor(uploadFile file_infor) {
		// TODO Auto-generated method stub
		this.userDao.addFileInfor_db(file_infor);
	}

	@Override
	public int deleteFile(int fileId) {
		// TODO Auto-generated method stub
		return this.userDao.deleteFileById(fileId);
	}

	@Override
	public uploadFile getFileById(int fileId) {
		// TODO Auto-generated method stub
		return this.userDao.getFileById(fileId);
	}


  
}  
