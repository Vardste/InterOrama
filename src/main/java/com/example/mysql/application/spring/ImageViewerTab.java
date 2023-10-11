package com.example.mysql.application.spring;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.github.jknack.handlebars.internal.Files;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.server.FileResource;

import com.vaadin.ui.Link;


//Gets the decoded bytes for the image and the header and makes them vidible/invisible
@Route(value = "imageviewertab", layout = MainView.class)
@PageTitle("Inside")
@CssImport("styles/views/welcomepage/welcome-page-view.css")
public class ImageViewerTab extends Div{

	String imagePathname = new String();
	VerticalLayout imageLayout = new VerticalLayout();
	
	public ImageViewerTab(String filename, byte[] img_bytes, byte[] header_bytes) {
		if(img_bytes == null || img_bytes.length ==0){														//wroks on deselection
			imageLayout.setVisible(false);
			return;
		}
		StreamResource resource = new StreamResource(filename,() -> new ByteArrayInputStream(img_bytes));
		Image image = new Image(resource, filename);
		imageLayout.setWidthFull();

		TextArea header = new TextArea();
		header.setId("text-area");
		header.setValue(new String(header_bytes));
		header.setWidthFull();
		header.setReadOnly(true);

		imageLayout.add(image, header);

		add(imageLayout);
	
	}

	public void setImagepathname(String imagePathname) {
    	this.imagePathname = imagePathname;
    }

    public String getImagepathname() {
    	return imagePathname;
    }
    

}
