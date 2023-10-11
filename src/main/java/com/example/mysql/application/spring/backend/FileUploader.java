package com.example.mysql.application.spring.backend;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystem;

import javax.imageio.ImageIO;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.upload.Receiver;

import ij.ImagePlus;
import ij.io.Opener;
import io.swagger.models.Path;

public class FileUploader implements Receiver{
    private User user;
    private ByteArrayOutputStream bytes;
    private String filename;
    private ImageHandler handler;
    @Override
	public OutputStream receiveUpload(String filename, String mimeType) {
        // Create upload stream
    	if(user == null) {
    		bytes = new ByteArrayOutputStream();
    		return bytes;
    	}else {
    		user.dicom = new ByteArrayOutputStream();
    		return user.dicom;
    	}
    	 // Return the output stream to write to
    }
    
    
    public ByteArrayOutputStream getDicomBytes() {
    	return this.bytes;
    }
    
    public void setFilename(String filename) {
    	this.filename = filename;
    }
    public String getFilename() {
    	return this.filename;
    }
    public void setUser(User user) {
    	this.user = user;
    }
    public User getUser() {
    	return this.user;
    }
    public ImageHandler getHandler() {
    	return this.handler;
    }
    public void setHandler(ImageHandler handler) {
    	this.handler = handler;
    }
    
}


