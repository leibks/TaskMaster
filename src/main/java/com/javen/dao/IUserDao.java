package com.javen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.javen.model.User;
import com.javen.model.UserInfor;
import com.javen.model.uploadFile;

// 所有能对mysql数据库直接执行操作的function, 在userMapper中配置使用
// ??在UserMapper中进行映射， 可是返回的值在哪里定义呢， mapper中只有sql语句
public interface IUserDao {

    // using by Sign Up :
    int insert(User record);
    
	int getIdByuserName(@Param("userName")String userName);

	int addPersonInfor(@Param("idByuserName")int idByuserName);
	
    //  Using for log in :
    //  User login(Map<String, String> /*String userName, String password*/ param);
    User login(@Param("userName")String userName, @Param("password")String password);

    // Get User Information
    UserInfor getUserInfor(@Param("inforId")int inforId);
    
    // Edit the User Information
    int editUserInforById(UserInfor userInfor);
    
    // Get file information for given user id
	List<uploadFile> selectAllFiles(@Param("userId")int user_id);
	
	// store the file information
	void addFileInfor_db(uploadFile file_infor);
	
	// delete the file by given id
	int deleteFileById(@Param("fileId") int fileId);
	
	// get file by file id
	uploadFile getFileById(@Param("fileId") int fileId);
	
	
    
    int deleteByPrimaryKey(Integer id);
    
    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	
}