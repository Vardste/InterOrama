package com.example.mysql.application.spring.views.welcomepage;

import com.example.mysql.application.spring.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "welcomeview", layout = MainView.class)
@PageTitle("Welcome Page")
@CssImport("styles/views/welcomepage/welcome-page-view.css")
public class WelcomePageView extends Div{
    private Button getStarted = new Button("Getting Started");
    private Button aboutUsButton = new Button("More");
    private final H2 welcomeH2 = new H2("Welcome to Inside!");
    private final H2 aboutUsH2 = new H2("About Inside");
    private final H2 knowMore =  new H2("Know More");
    
    private final H3 xRayTitle=  new H3("X-RAY");
    private Div xRay = new Div();
    Label xrayDesc = new Label("X-rays are a form of electromagnetic radiation, similar to visible light. Unlike light, however, x-rays have higher energy and can pass through most objects, including the body. Medical x-rays are used to generate images of tissues and structures inside the body. If x-rays travelling through the body also pass through an x-ray detector on the other side of the patient, an image will be formed that represents the “shadows” formed by the objects inside the body.");
    Image xRayImage = new Image("../icons/x-ray.jpg", "");
        
    private final H3 mriTitle=  new H3("MRI");
    private Div mri = new Div();
    Label mriDesc = new Label("A procedure in which radio waves and a powerful magnet linked to a computer are used to create detailed pictures of areas inside the body. These pictures can show the difference between normal and diseased tissue.");
    Image mriImage = new Image("../icons/mri.jpg", "");

    private final H3 petctTitle=  new H3("PET-CT");
    private Div petct = new Div();
    Label petctDesc = new Label("A procedure that combines the pictures from a positron emission tomography (PET) scan and a computed tomography (CT) scan. The PET and CT scans are done at the same time with the same machine. The combined scans give more detailed pictures of areas inside the body than either scan gives by itself.");
    Image petctImage = new Image("../icons/petct.jpg", "");
    
    private final H3 ultrasoundTitle=  new H3("ULTRASOUND");
    private Div ultrasound = new Div();
    Label ultrasoundDesc = new Label("An ultrasound is an imaging test that uses sound waves to create a picture (also known as a sonogram) of organs, tissues, and other structures inside the body. Unlike x-rays, ultrasounds don’t use any radiation. An ultrasound can also show parts of the body in motion, such as a heart beating or blood flowing through blood vessels.\r\n"
    		+ "\r\n"
    		+ "There are two main categories of ultrasounds: pregnancy ultrasound and diagnostic ultrasound.");
    Image ultrasoundImage = new Image("../icons/ultrasound.jpg", "");

    private Text welcomeStr = new Text("Here you can keep track of your medical examinations.");
    private Text aboutUsStr = new Text("This is the Diploma Thesis of a Computer Science student based"
    									+ " on Computer Science Department of the University of Crete"
    									+ ". For more on the contributors of this project you can find "
    									+ "by clicking the button.");
    VerticalLayout knowMore1 = new VerticalLayout();
    VerticalLayout knowMore2 = new VerticalLayout();
    VerticalLayout veWelcome = new VerticalLayout();
    VerticalLayout veAboutUs = new VerticalLayout();
    HorizontalLayout knowMoreGeneral = new HorizontalLayout();
    SplitLayout welcomeSplit = new SplitLayout();

    public WelcomePageView() {
		setId("welcome-page-view");
		xRayTitle.setId("titles");
		xRayImage.setId("x-ray-image");
		xRay.setId("xray");
		xrayDesc.setId("labels");
		
		mriTitle.setId("titles");
		mriImage.setId("x-ray-image");
		mri.setId("xray");
		mriDesc.setId("labels");

		petctTitle.setId("titles");
		petctImage.setId("x-ray-image");
		petctDesc.setId("labels");
		petct.setId("xray");

		ultrasoundTitle.setId("titles");
		ultrasoundImage.setId("x-ray-image");
		ultrasoundDesc.setId("labels");
		ultrasound.setId("xray");

		getStarted.addClickListener(e -> UI.getCurrent().navigate("examinations"));
		aboutUsButton.addClickListener(e -> UI.getCurrent().navigate("aboutus"));

		xRay.add(xRayTitle, xRayImage, xrayDesc);
		mri.add(mriTitle, mriImage, mriDesc);
		petct.add(petctTitle, petctImage, petctDesc);
		ultrasound.add(ultrasoundTitle, ultrasoundImage, ultrasoundDesc);
		

		veWelcome.add(welcomeH2, welcomeStr, getStarted, knowMore, xRay, mri, petct, ultrasound);
		veAboutUs.add(aboutUsH2, aboutUsStr, aboutUsButton);
        veAboutUs.setWidth("35%");
        
        
		welcomeSplit.addToPrimary(veWelcome);
        welcomeSplit.addToSecondary(veAboutUs);
        welcomeSplit.setHeightFull();
        add(welcomeSplit);
	}
}
