package com.example.mysql.application.spring.views.examinations;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import com.example.mysql.application.spring.ImageViewerTab;
import com.example.mysql.application.spring.MainView;
import com.example.mysql.application.spring.backend.FileUploader;
import com.example.mysql.application.spring.backend.ImageHandler;
import com.example.mysql.application.spring.backend.User;
import com.example.mysql.application.spring.backend.UserService;

@Route(value = "examinations", layout = MainView.class)
@PageTitle("Examinations")
@CssImport("styles/views/examinations/examinations-view.css")
public class ExaminationsView extends Div implements AfterNavigationObserver {
	private static final long serialVersionUID = 1L;

	private String BASE_PATH = "src/main/resources/META-INF/resources/data/";
    private String uuidText = UUID.randomUUID().toString();

    @Autowired
    private UserService userService;

//Basic Grids for images and Users    
    private Grid<User> users;
    private Grid<User> images;

//Form Fields
    private TextField firstname = new TextField("First Name");
    private TextField lastname = new TextField("Last Name");
    private TextField email = new TextField("Email");
    private FileUploader receiver = new FileUploader();
    Select<String> selectExaminationType = new Select<>();

    private TextField examination_type = new TextField();
    private String pathName = new String(BASE_PATH);
    private TextField pathname = new TextField();
    private TextField uuid = new TextField();

//Filter fields
    private TextField filterName = new TextField();
    private TextField filterEmail = new TextField();
    private Label filterLabel = new Label("Filters");
    private Button filtersOn = new Button(new Icon(VaadinIcon.ANGLE_DOWN));
    private Button filtersOff = new Button(new Icon(VaadinIcon.ANGLE_UP));
//Buttons
    Button openForm = new Button("Add Image");
    Button closeForm = new Button(new Icon(VaadinIcon.ARROW_RIGHT));
    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    
    private Upload uploadedFile = new Upload(receiver);

    private Binder<User> binder;

    public ExaminationsView() {
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();
        setId("examinations-view");
        openForm.setWidth("12%");
// Configure Users Grid
        users = new Grid<>();
        users.setId("users-grid");
        users.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS);

        users.addColumn(User::getFirstname).setComparator((user1, user2) ->
        	user1.getFirstname().compareToIgnoreCase(user2.getFirstname())).setHeader("First name");
        users.addColumn(User::getLastname).setComparator((user1, user2) ->
        	user1.getLastname().compareToIgnoreCase(user2.getLastname())).setHeader("Last name");
        users.addColumn(User::getEmail).setComparator((user1, user2) ->
        	user1.getEmail().compareToIgnoreCase(user2.getEmail())).setHeader("Email");

        users.setHeight("56%");
//Image grid
        images = new Grid<>();
        images.addColumn(User::getFilename).setHeader("Image");
        images.addColumn(User::getExaminationType).setHeader("Examination Type");
        images.addColumn(User::getPathname).setHeader("Pathname").setVisible(false);
        images.setVisible(false);
        images.setHeight("36%");      // Configure Form
        binder = new Binder<>(User.class);

        // Bind fields: matches form fields to class fields.
        binder.bindInstanceFields(this);

        binder.setBean(new User());
        
//Listeners
        //With the save button the data and image insertion is achieved. 
        save.addClickListener(e -> {
	    	User user = binder.getBean();											//Data collected from the form through the help of the binder
	    	receiver.setUser(user);													//Set the data of the new user for the image upload
	    	user.dicom = receiver.getDicomBytes();
	    	user.setFilename(receiver.getFilename());
	    	receiver.getHandler().save(user);
	    	if(firstname.getValue() == "" || lastname.getValue() == "" || email.getValue() == "") {
	    		Notification.show("Please fill the required fields", 4321, Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
	    	}else if ( userService.saveUser(user) > 0) {
				users.setItems(userService.findAll());
				users.asSingleSelect().clear();
				images.setVisible(false);
//				images.setItems(userService.findImageBytes(user.getEmail()));
		    	clearForm();
			} else {
				Notification.show("Save error");
			}				

	    	
        });
        
        save.addClickShortcut(Key.ENTER);    

        delete.addClickListener(e -> {
			User user = binder.getBean();											//delete the data shown in the populated form from the data base
			if ( userService.deketeUser(user) > 0) {								//show the updated grid or show fail message
				users.setItems(userService.findAll());
				images.setVisible(false);

			} else {
				Notification.show("Delete error");
			}				
        });

        delete.addClickShortcut(Key.DELETE);    

//============================
        uploadedFile.addSucceededListener(event -> {
        	String filename = event.getFileName();
        	User user = receiver.getUser();
        	receiver.setFilename(filename);
        	
        	ImageHandler handler = new ImageHandler(userService);				//handler to decode dicom file. argument is the user to be saved
        	if(user != null) {													//if the user isnt new then the handler gets his data from the user
        		user.setFilename(filename);
        		handler.pipeline(user.dicom);							
//        		System.out.println(filename);
        	}else {
        		handler.pipeline(receiver.getDicomBytes());						//else he decodes what is found in the receiver
        	}
        	receiver.setHandler(handler);										//add the handler instance to receiver in 
        	
        });
//============================        	

        
        filtering();

        createGridLayout(splitLayout);
        createFormLayout(splitLayout);

        add(splitLayout);
    }
    
    private void filtering() {
    	filterName.setVisible(true);
    	filterEmail.setVisible(true);

    	filterName.setValueChangeMode(ValueChangeMode.EAGER);
        filterName.addValueChangeListener(e -> {
        	users.setItems(userService.findByLastname(filterName.getValue()));
        	if(filterName.isEmpty()) {
				users.setItems(userService.findAll());
				
        	}
        });

        filterEmail.setValueChangeMode(ValueChangeMode.EAGER);
        filterEmail.addValueChangeListener(e -> {
        	users.setItems(userService.findByEmailFilter(filterEmail.getValue()));
        	if(filterEmail.isEmpty()) {
				users.setItems(userService.findAll());
        	}

        });
    	
    }

    private void clearForm() {
    	email.setValue("");
    	firstname.setValue("");
    	lastname.setValue("");
    	selectExaminationType.setValue("");
    	
    }

    private void createFormLayout(SplitLayout splitLayout) {
        Div userFormDiv = new Div();
        userFormDiv.setId("editor-layout");
        FormLayout formLayout = new FormLayout();
        
        addFormItem(userFormDiv, formLayout, firstname, "");
        firstname.setPlaceholder("John/Jane");
        lastname.setRequiredIndicatorVisible(true);
        firstname.setRequired(true);
        firstname.addBlurListener(event->{
	        if(firstname.getValue() == "") {
	        	Notification.show("Empty First Name", 1234, Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
	        }
        });

        addFormItem(userFormDiv, formLayout, lastname, "");
        lastname.setPlaceholder("Doe");
        lastname.setRequiredIndicatorVisible(true);
        lastname.setRequired(true);
        lastname.addBlurListener(event->{
	        if(lastname.getValue() == "") {
	        	Notification.show("Empty lastname", 1234, Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
	        }
        });

        addFormItem(userFormDiv, formLayout, email, "");
        email.setRequiredIndicatorVisible(true);
        email.setPlaceholder("example@eg.com");
        email.setRequired(true);
        email.addBlurListener(event->{
	        if(email.getValue() == "") {
	        	Notification.show("Empty email", 1234, Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
	        }
        });

        selectExaminationType.setLabel("Examination Type");
        selectExaminationType.setItems(" ", "MRI", "X-RAY", "Ultrasound", "PET-CT");
        selectExaminationType.setValue("");
        addFormItem(userFormDiv, formLayout, selectExaminationType, "");

        addFormItem(userFormDiv, formLayout, examination_type, "");
        examination_type.setPlaceholder("e.g X-RAY");
        examination_type.setVisible(false);
        selectExaminationType.addValueChangeListener(event ->{
        	examination_type.setValue(selectExaminationType.getValue());
        });

        addFormItem(userFormDiv, formLayout, pathname, " ");
        pathname.setValue(pathName);
        pathname.setPlaceholder(pathName);
        pathname.setVisible(false);
        addFormItem(userFormDiv, formLayout, uuid, " ");
        uuid.setVisible(false);

//Open and close the form buttons        
        userFormDiv.setVisible(false);
        openForm.addClickListener(e -> {
        	userFormDiv.setVisible(true);
        	openForm.setVisible(false);
        	closeForm.setVisible(true);
        	closeForm.addClickListener(event -> {
            	userFormDiv.setVisible(false);
            	openForm.setVisible(true);
            	closeForm.setVisible(false);
        	});
        });
        
//Open and close the form after selecting a User
//when a row is selected or deselected, populate form
        users.asSingleSelect().addValueChangeListener(event ->{
        	populateForm(event.getValue());
        	userFormDiv.setVisible(true);
        	openForm.setVisible(false);
        	closeForm.setVisible(true);
        	users.setHeight("45%");
            images.setVisible(true);
            
            images.setItems(userService.findImageBytes(email.getValue()));
            closeForm.addClickListener(e -> {
            	userFormDiv.setVisible(false);
            	users.asSingleSelect().clear();
            	openForm.setVisible(true);
                images.setVisible(false);
                images.asSingleSelect().clear();
                users.setHeight("56%");
            	closeForm.setVisible(false);
        	});
            
            // the grid valueChangeEvent will clear the form too
            cancel.addClickListener(e -> {
            	userFormDiv.setVisible(false);
            	openForm.setVisible(true);
                images.setVisible(false);
                users.setHeight("56%");
            	closeForm.setVisible(false);
            	users.asSingleSelect().clear();
                images.asSingleSelect().clear();
            });
            
            images.asSingleSelect().addValueChangeListener(event2 ->{
            	String pathname = "";
            	String filename = "";
            	String email = "";
            	byte[] png = null ;
            	byte[] dcm = null ;
            	byte[] header = null ;
            	for ( User obj : images.getSelectedItems()) {
            		email = obj.getEmail();
            		filename = obj.getFilename();
            		png = obj.getPngBytes();
            		header = obj.getHeaderBytes();
            		break;
            	}
            	ImageViewerTab imageToView = new ImageViewerTab(filename,png,header); 
            	imageToView.setWidth("35%");
            	if(png == null) {
            		imageToView.setVisible(false);
            		openForm.setVisible(true);
            		closeForm.setVisible(false);
            	}
            	

            	splitLayout.addToSecondary(imageToView);
            	openForm.setVisible(false);
            	closeForm.setVisible(true);
            	closeForm.addClickListener(event3 -> {
            		imageToView.setVisible(false);	
//            		splitLayout.remove(imageToView);
            		images.setVisible(false);
            		openForm.setVisible(true);
                	closeForm.setVisible(false);
                	splitLayout.addToSecondary(userFormDiv);
                	userFormDiv.setVisible(false);
                	users.asSingleSelect().clear();
                	images.asSingleSelect().clear();
            	});
            	
           		users.asSingleSelect().addValueChangeListener(event4 -> {
           			imageToView.setVisible(false);	
           			openForm.setVisible(false);
                	closeForm.setVisible(true);
                	splitLayout.addToSecondary(userFormDiv);
            		
            	});
                	            	
            });
            

        });
        cancel.addClickShortcut(Key.ESCAPE);    

//    	users.asSingleSelect().clear();
//    	images.asSingleSelect().clear();
//    	images.setVisible(false);

//==========
        email.addValueChangeListener(event -> {
        	if(userService.findByEmail(email.getValue()).size()>0) {
	    		receiver.setUser(userService.findByEmail(email.getValue()).get(0));
        	}
        });
//==========        

        createButtonLayout(userFormDiv);
        splitLayout.addToSecondary(userFormDiv);
    }

    private void createButtonLayout(Div editorDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        HorizontalLayout uploadLayout = new HorizontalLayout();
        VerticalLayout buttonsAndUploads = new VerticalLayout();
        
        buttonLayout.setId("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        buttonLayout.add(delete, cancel, save);


        uploadLayout.setId("upload-layout"); 
        uploadLayout.setWidthFull();
        uploadLayout.setSpacing(false);
        uploadLayout.add(uploadedFile);

        
        buttonsAndUploads.add(uploadLayout, buttonLayout);
        editorDiv.add(buttonsAndUploads);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        HorizontalLayout filterTextfieldsLayout = new HorizontalLayout();				//First filter Layout for all the textfields
        HorizontalLayout filtersLabelLayout = new HorizontalLayout();					//Second filter Layout just for the title label
        VerticalLayout filtersLayout = new VerticalLayout();							//Third filter Layout to vertically position the  filters and their Label
        HorizontalLayout filterAddUser = new HorizontalLayout();
        openForm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        openForm.setId("form-butt");
        closeForm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        closeForm.setId("form-butt");
        
//Filter appearance 
        filtersOn.setVisible(false);
        filtersOn.setHeight("75%");
        filtersOn.setWidth("75%");
        filtersOff.setHeight("75%");
        filtersOff.setWidth("75%");
        filtersOff.setVisible(true);
        filtersLabelLayout.add(filterLabel, filtersOn, filtersOff);
        filtersLabelLayout.setId("filter-label");
        
        
        filtersOff.addClickListener(event->{
        	filtersOn.setVisible(true);
        	filtersOff.setVisible(false);
        	filterName.setVisible(false);
        	filterEmail.setVisible(false);
        	
        });
        filtersOn.addClickListener(event ->{
        	filtersOff.setVisible(true);
        	filterName.setVisible(true);
        	filterEmail.setVisible(true);
        	filtersOn.setVisible(false);
        	
        });

        filterName.setPlaceholder("Lastname");
        filterName.setClearButtonVisible(true);
        filterEmail.setPlaceholder("Email");
        filterEmail.setClearButtonVisible(true);
        
        filterTextfieldsLayout.setId("filter-text-layout");
        filterTextfieldsLayout.add(filterName, filterEmail);
        filterTextfieldsLayout.setAlignSelf(null, filterLabel, filterName, filterEmail);
        filterTextfieldsLayout.setWidthFull();
        filterTextfieldsLayout.setPadding(false);
        
        filtersLayout.add(filtersLabelLayout, filterTextfieldsLayout);
        closeForm.setVisible(false);
        filterAddUser.add(filtersLayout, openForm, closeForm);
        filterAddUser.setWidthFull();
        filterAddUser.setAlignSelf(Alignment.END, openForm);
        
//==========
        
        wrapper.setId("wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        
        
        wrapper.add(filterAddUser, users, images);
    }

    private void addFormItem(Div wrapper, FormLayout formLayout, AbstractField field, String fieldName) {    	
        formLayout.addFormItem(field, fieldName);
        wrapper.add(formLayout);
        field.getElement().getClassList().add("full-width");
    }

    private void addFormItem(Div wrapper, FormLayout formLayout, Select<String> string, String fieldName) {    	
        formLayout.addFormItem(string, fieldName);
        wrapper.add(formLayout);
        string.getElement().getClassList().add("full-width");
    }
    
    @Override
    public void afterNavigation(AfterNavigationEvent event) {

        // Initialize grid Items
    	users.setItems(userService.findAll());
    }

    private void populateForm(User value) {
        // if the user is null, the new form is empty
        
    	if ( value == null ) {
    		value = new User();
    	}
    	binder.setBean(value);
    }

}