Dawn Fitch
Student ID:  001346922 
Application Version: 1.0 
Date: 06/16/2023 
---------------------------------------------------------------------
Scheduling Application for C195 Software II: Final Project

	The purpose of the application is to pull data from a database 
and populate the applications scheduling and customer tables.  It was 
organized into a schedule table page, customer table page, add/modify 
customer page, add/modify appointment page, menu pages and the report pages.  
It was designed to create an application that a user can log-in to and 
change the appointment and customer data as needed.  The user's changes should 
seamlessly load from and save to the database, without modifying its structure. 
-------------------------------------------------------------------------------
Development Environment

IntelliJ Community 2021.1.3 
Java version 17.0.1 
JavaFX-SDK-17.0.1 
Mysql-connector-java-8.0.25 
-------------------------------------------------------------------------------
How to run the program

When running the program it opens the login form. 
On a successful login the application takes users to the main menu: 
	Move application to the upper left of screen. 
	Schedule button will take user to the schedule. 
	Customers will take user to the customers table. 
	Add Customer will take the user to the add customer form. 
	Reports will take the user to the reports menu. 
	Exit will exit the program. 
On the Schedule page:
	Add will take user to the Add appointment form.
	Modify will take user to the modify appointment form. 
	Delete will delete an appointment. 
	Back takes the user to the main menu. 
On the Customer page: 
	Add will take user to the add customer form. 
	Modify will take user to the modify appointment form. 
	Delete will delete customer and associated appointments. 
	Back takes user to the main menu.
On the Add and modify forms for the customers and appointments: 
	Save button will save the Appointment/Customer. 
	Cancel button will cancel. 
	Add and modify customer forms will take user back to the customer table. 
	Add and modify Appointment forms will take user back to the schedule table. 
On the Reports menu: 
	Type/Month Totals will take the user to the report that counts appointments 
		by the type or by the month. 
	Contact schedule will take the user to the report that filters the appointments 
		by the contact. 
	Country totals will take the user to the report that counts appointments 
		by country. 
-------------------------------------------------------------------------------
Additional Report Information

	The report I did was getting the total number of appointments by Country.  
It joins multiple tables together, including appointments and customers, to associate 
appointments with the customers' country.  This report will be very useful for 
department heads and other higher level employees, letting them easier make decisions 
about different branches of the business.  This will help determine how many employees 
are needed in a given location or whether a new location is needed. 
