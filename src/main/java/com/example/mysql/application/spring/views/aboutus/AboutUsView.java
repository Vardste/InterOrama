package com.example.mysql.application.spring.views.aboutus;

import com.example.mysql.application.spring.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "aboutus", layout = MainView.class)
@PageTitle("About Us")
@CssImport("styles/views/aboutus/about-us-view.css")
public class AboutUsView extends Div{
	private Text aboutMain = new Text("This is the Diploma Thesis of a Computer Science student, Stella Vardaki"
			+ " based on Computer Science Department of the University of Crete. "
			+ "The project is a web application addressed to both independent individuals"
			+ " and doctors. The webapp communicates with a Database and stores information"
			+ "on the Medical Images included in certain types of Medical Examinations."
			+ "\n"
			+ "The Project was held under the supervision of Marios Pitikaks.");
	private final H2 aboutTitle = new H2("About Us");

	
	public AboutUsView() {
		setId("about-us-view");
		VerticalLayout aboutLayout = new VerticalLayout();
		aboutLayout.setId("about-layout");
		
		aboutLayout.add(aboutTitle, aboutMain);
		add(aboutLayout);
	}
}
