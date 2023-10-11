package com.example.mysql.application.spring.backend;

import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

import ij.ImagePlus;
import ij.io.FileOpener;
import ij.io.Opener;
import ij.plugin.DICOM;
import ij.process.ByteProcessor;

public class ImageHandler{
	private byte[] dcm_image_bytes;
	private byte[] png_image_bytes;
	private byte[] header_image_bytes;
	private UserService svc;
	public ImageHandler(UserService user_svc) {
		svc = user_svc;
	}
//The pipeline decodes the dicom bytes in png image and text file	
	public void pipeline(ByteArrayOutputStream image_buffer) {
		try {
			dcm_image_bytes = image_buffer.toByteArray();										//get the whole file 
			ByteArrayOutputStream png_byte_stream = new ByteArrayOutputStream();
			System.out.println(dcm_image_bytes.length);
			InputStream is = new ByteArrayInputStream(image_buffer.toByteArray());
			DICOM dcm = new DICOM(is);

			dcm.run("Name");
			ImagePlus imp = dcm.createImagePlus();
			String info = (String) imp.getProperty("Info");										//header bytes
//			System.out.println(info);
			ImageIO.write((RenderedImage) dcm.getBufferedImage(), "png", png_byte_stream);		//open and write a png file with the image bytes
			png_byte_stream.close();
			png_image_bytes = png_byte_stream.toByteArray();									//store png data as bytes
			
			System.out.println("Parsed");
	        header_image_bytes = info.getBytes();												//store header bytes
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void save(User user) {
		svc.insertSecondBytes(user,dcm_image_bytes,png_image_bytes,header_image_bytes);			//insert to database. called with the save button
	}
}











