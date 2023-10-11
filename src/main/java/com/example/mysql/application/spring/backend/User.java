package com.example.mysql.application.spring.backend;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public class User {
	
    private String firstname;
    private String lastname;
    private String examination_type;
    private String email;
    private String pathname;
    private String uuid;
    private String filename;
    public ByteArrayOutputStream dicom;
    private byte[] png_file_data;
    private byte[] dcm_file_data;
    private byte[] header_file_data;
    public User(String filename, String pathname) {
    	this.filename = filename;
    	this.pathname = pathname;
    	

    }
    
    public User(String filename, String examination_type, String pathname) {
    	this.filename = filename;
    	this.examination_type = examination_type;
    	this.pathname = pathname;
    	

    }
    public User(String filename, String examination_type, Blob dcm_file_data,Blob png_file_data,Blob header_file_data) {
    	this.filename = filename;
    	this.examination_type = examination_type;
    	try {
			this.png_file_data = png_file_data.getBinaryStream().readAllBytes();
			this.dcm_file_data = dcm_file_data.getBinaryStream().readAllBytes();
			this.header_file_data = header_file_data.getBinaryStream().readAllBytes();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public User(String firstname, String lastname, String examination_type, String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.examination_type = examination_type;
		this.email = email;
	}

    public User(String firstname, String lastname, String examination_type, String email, String pathname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.examination_type = examination_type;
		this.email = email;
		this.pathname = pathname;
    }

    public User(String firstname, String lastname, String examination_type, String email, String pathname, String uuid) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.examination_type = examination_type;
		this.email = email;
		this.pathname = pathname;
		this.uuid = uuid;
    }

    public User() {

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public byte[] getPngBytes() {
        return this.png_file_data;
    }
    public byte[] getDCMBytes() {
        return this.dcm_file_data;
    }
    public byte[] getHeaderBytes() {
        return this.header_file_data;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getExaminationType() {
    	return examination_type;
    }
    public void setExaminationType(String examination_type) {
    	this.examination_type = examination_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPathname() {
        return pathname;
    }


    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public String getFilename() {
        return filename;
    }


    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUuid() {
    	return uuid;
    }
    public void setUuid(String uuid) {
    	this.uuid = uuid;
    }


    @Override
    public String toString() {
        return firstname + " " + lastname + "(" + email + ")";
    }
}
