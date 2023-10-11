package com.example.mysql.application.spring.backend;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserService {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAll() {    	
    	try {
			return jdbcTemplate.query("SELECT firstname, lastname, examination_type, email, pathname, uuid FROM users",
			        (rs, rowNum) -> new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("examination_type"), rs.getString("email"), rs.getString("pathname"), rs.getString("uuid")));
		} catch (Exception e) {
			return new ArrayList<User>();	
		}		
    }
//===============================================
//return the list of images   
    public List<User> findImages(String email) {    	
    	try {

			return jdbcTemplate.query("SELECT filename, examination_type, pathname FROM image_data WHERE email = ?",
					new Object[]{email},
			        (rs, rowNum) -> new User( rs.getString("filename"), rs.getString("examination_type"), rs.getString("pathname")));
		} catch (Exception e) {
			return new ArrayList<User>();	
		}		
    }
//Filtering    
    public List<User> findByLastname(String lastname) {    	
    	try {
			return jdbcTemplate.query("SELECT firstname, lastname,  email, examination_type FROM users WHERE lastname LIKE '%"+lastname+"%'",
			        (rs, rowNum) -> new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("examination_type"), rs.getString("email")));
		} catch (Exception e) {
			return new ArrayList<User>();	
		}    	
    }

    public List<User> findByExaminationType(String examination_type) {    	
    	try {
			return jdbcTemplate.query("SELECT firstname, lastname,  email, examination_type FROM users WHERE examination_type = ?",
					new Object[]{examination_type},
			        (rs, rowNum) -> new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("examination_type"), rs.getString("email")));
		} catch (Exception e) {
			return new ArrayList<User>();	
		}  
    }
    public List<User> findByEmailFilter(String email) {    	
    	try {
			return jdbcTemplate.query("SELECT firstname, lastname, examination_type, email, pathname, uuid FROM users WHERE email LIKE '%"+email+"%'",
			        (rs, rowNum) -> new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("examination_type"), rs.getString("email"), rs.getString("pathname"), rs.getString("uuid")));
		} catch (Exception e) {
			return new ArrayList<User>();	
		}    	
    }

//===============================
    public List<User> findByEmail(String email) {    	
    	try {
			return jdbcTemplate.query("SELECT firstname, lastname, examination_type, email, pathname, uuid FROM users WHERE email = ?",
					new Object[]{email},
			        (rs, rowNum) -> new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("examination_type"), rs.getString("email"), rs.getString("pathname"), rs.getString("uuid")));
		} catch (Exception e) {
			return new ArrayList<User>();	
		}    	
    }
//Saves the people in both the basic and the image table.    
    public int saveUser(User user) {
    	List<User> users = this.findByEmail(user.getEmail());
    	if ( users.size() > 0 ) {
    		insertSecond(user);
    		return updateUser(user);
    	} else {
    		insertSecond(user);
    		return insertUser(user);
    	}
    	
    }
//Used to save the image pathname in a secondary table. This allows for multiple images to be stored for one person
//by keeping the pathnames to all images stored.
    private int insertSecond(User user) {
    	try {
			return jdbcTemplate.update("INSERT INTO image_data VALUES (?, ?, ?, ?)",
					user.getEmail(), user.getExaminationType(), user.getPathname(), FilenameUtils.getBaseName(user.getPathname()));
    		
    	} catch (Exception e) {
    		return 0;
    	}
    }
    
    public int insertSecondBytes(User user,byte[] dcm,byte[] png,byte[] header) {
    	try {
    		System.out.println(header.length);
			return jdbcTemplate.update("INSERT INTO image_data VALUES (?, ?, ?, ?, ?, ?, ?)",
					user.getEmail(), user.getExaminationType(), null, user.getFilename(),header,png,dcm);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		return 0;
    	}
    }
    public List<User> findImageBytes(String email) {    	
    	try {

			return jdbcTemplate.query("SELECT filename, examination_type, dcm_file_data, png_file_data, header_file_data FROM image_data WHERE email = ?",
					new Object[]{email},
			        (rs, rowNum) -> new User( rs.getString("filename"), rs.getString("examination_type"), rs.getBlob("dcm_file_data"),rs.getBlob("png_file_data"),rs.getBlob("header_file_data")));
		} catch (Exception e) {
			return new ArrayList<User>();	
		}		
    }
    private int updateUser(User user) {    	
    	try {
			return jdbcTemplate.update("UPDATE users SET lastname = ?, firstname = ?, examination_type = ?, pathname = ?, uuid = ? WHERE email = ?",
					user.getLastname(), user.getFirstname(), user.getExaminationType(), user.getPathname(), user.getUuid(), user.getEmail());
		} catch (Exception e) {
			return 0;
		}
    }
    
//Inserts people in the basic table.
    private int insertUser(User user) {
    	try {
			return jdbcTemplate.update("INSERT INTO users VALUES (?, ?, ?, ?, ?, ?)",
					user.getFirstname(), user.getLastname(), user.getEmail(), user.getExaminationType(), user.getPathname(), user.getUuid());
		} catch (Exception e) {
			return 0;
		}    	
    }
    
//Deletes people from both tables based on email.
    public int deketeUser(User user) {
    	try {
    		jdbcTemplate.update("DELETE FROM image_data WHERE email = ?", user.getEmail());
    		return jdbcTemplate.update("DELETE FROM users WHERE email = ?",
    				user.getEmail());
    	} catch (Exception e) {
			return 0;
		}
    }
}

