# Inside

This project is my Bachelor Thesis.


## Summary

The **Inside** project is a WebApp for **storing**, **searching** and **visualizing** personal user data and medical images. It was developed with Java using the Vaadin framework. The platform accepts DICOM image files and text data inserted by form. Data are stored in a MySQL database.
  
## Usage

To use Inside one should:

 1. Set up Apache Tomcat Server and make sure that the default port is set to **8080**.
 2. Create a database through MySQL Workbench named **dummydata**
 3. Create the tables contained in the database using the commands:	

    `CREATE TABLE IF NOT EXISTS users (firstname VARCHAR(1000), lastname VARCHAR(1000), email VARCHAR(1000), examination_type VARCHAR(1000), pathname VARCHAR(1000), uuid VARCHAR(1000)); "`
`CREATE TABLE IF NOT EXISTS image_data (email VARCHAR(1000), examination_type VARCHAR(1000), pathname VARCHAR(1000), filename VARCHAR(1000), header_file_data BLOB , png_file_data MEDIUMBLOB, dcm_file_data MEDIUMBLOB);`

 4. Run Inside:
		 i.  Open CommandPrompt and go to the Project File
		 ii. Use `mvn spring-boot:run` to compile 
		 iii. Open `http://localhost:8080/welcomeview` to use  Inside

## Future Work
The features added in future iterations should include:

 1. Storing multiple files at the same time
 2. LogIn and SignUp system
 3. Mobile App

		 

    

	
