Welcome file
Welcome file
# Inside

This project is my Bachelor Thesis.

 The **Inside** project is a comprehensive solution for the secure storage, efficient search, and interactive visualization of medical images and associated personal data. It leverages the power of the Vaadin framework for building the user interface and MySQL as the database for efficient data management. This web application is destined for medical professionals and individuals to ensure that medical data are available and up-to-date at all times.
 
It was developed with Java using the Vaadin framework. The platform accepts DICOM image files and text data inserted by form. Data are stored in a MySQL database.

## Features
-   **Medical Image Storage:** Securely store and manage medical images, ensuring data integrity and confidentiality.
    
-   **Powerful Search:** Effortlessly search for medical images using various criteria, including patient information, date, and diagnostic data.
    
-   **Interactive Visualization:** Visualize medical images with an intuitive and user-friendly interface, allowing for in-depth examination.
    
-   **Patient Data Management:** Store and manage patient data alongside medical images, ensuring a holistic view of patient history.  

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
 2. Authentication system
 3. Storing entire Medical History
 4. Mobile App
		 

    

	
Inside
This project is my Bachelor Thesis.

The Inside project is a comprehensive solution for the secure storage, efficient search, and interactive visualization of medical images and associated personal data. It leverages the power of the Vaadin framework for building the user interface and MySQL as the database for efficient data management. This web application is destined for medical professionals and individuals to ensure that medical data are available and up-to-date at all times.

It was developed with Java using the Vaadin framework. The platform accepts DICOM image files and text data inserted by form. Data are stored in a MySQL database.

Features
Medical Image Storage: Securely store and manage medical images, ensuring data integrity and confidentiality.

Powerful Search: Effortlessly search for medical images using various criteria, including patient information, date, and diagnostic data.

Interactive Visualization: Visualize medical images with an intuitive and user-friendly interface, allowing for in-depth examination.

Patient Data Management: Store and manage patient data alongside medical images, ensuring a holistic view of patient history.

Usage
To use Inside one should:

Set up Apache Tomcat Server and make sure that the default port is set to 8080.

Create a database through MySQL Workbench named dummydata

Create the tables contained in the database using the commands:

CREATE TABLE IF NOT EXISTS users (firstname VARCHAR(1000), lastname VARCHAR(1000), email VARCHAR(1000), examination_type VARCHAR(1000), pathname VARCHAR(1000), uuid VARCHAR(1000)); "
CREATE TABLE IF NOT EXISTS image_data (email VARCHAR(1000), examination_type VARCHAR(1000), pathname VARCHAR(1000), filename VARCHAR(1000), header_file_data BLOB , png_file_data MEDIUMBLOB, dcm_file_data MEDIUMBLOB);

Run Inside:
i. Open CommandPrompt and go to the Project File
ii. Use mvn spring-boot:run to compile
iii. Open http://localhost:8080/welcomeview to use Inside

Future Work
The features added in future iterations should include:

Storing multiple files at the same time
Authentication system
Storing entire Medical History
Mobile App
Markdown 2291 bytes 311 words 45 lines Ln 40, Col 2HTML 1841 characters 295 words 26 paragraphs
