# Geo-Location Tracking Based Emergency Rescue System
## Implementation Details
The development phase describes the coding part of the system to be developed. This phase is where we start to write program code for the development of the system and show outputs. We follow the requirements specifications from the design stage and start to create a new system. 

### User session 
At the beginning of the app screen we developed a user registration session where the users can register themselves providing proper information and authenticity. We save all the information about the users in the database for statistical analysis. A user must be registered before going into guest login.

![](https://github.com/smilesifat/SafeGuard/blob/master/images/register.png)

(a) Register as user	                   (d) Profile section in the app

### Home Screen
When the user ends his/her registration process, the app will intent them into Home Screen. Here we developed a Home Screen for the user interaction. In Home Screen, we will integrate many different segments like Fire Station, Feed Back, Police Station, Emergency Medical, Sexual Harassment, Traffic Accidents and Round Help Button.
 
Home Screen
### Menus
We developed three kinds of menus like Navigation drawer menu, Bottom menu, and Settings menu. All these menus are helping the users to operate themselves in different functional perspective. In Navigation drawer, we created emergency services section like ambulances, hospitals and emergency contacts so that the victims find nearby help center and relatives contact numbers. 

(a) Navigation Drawer Menu 		 
(b) Bottom Menu	                  
(c) Settings Menu

### Development of Emergency Help Activity
This is the core activity in our project. It lets victims to send current location with live geo tracking and notification to the feedback section. A victims can type messages or left as blank in emergency situation. Then the messages are forwarded to the One Signal server. The server then broadcasts the data to all the subscriber. Others can click on the notification and track the victim with the distance shown easily on the feedback section.
  				 
(a) Press the Help button in emergency 			               
(b) Request for emergency help
(c) Messages hit in the database 	                            
(d) Notification sent to the users
(e) Press the Feed Back	           
(f) Victim’s Location	(g) Distance from the help center/rescuer

we also developed categories Emergency Medical Help,  Sexual Harassment Help Activity , Traffic Accidents Help Activity

### Fire Station Activity
We implemented a recycler view data list of fire stations in this segment. It is containing both the list view and maps view of the current locations nearby services. Anyone may get help from the nearby fire stations by clicking on the list or map. At the time of clicking on the list or map it will intent to phone book or email.

(a) Fire Station Button 	           
(b) Fire Station List view 	             
(c) Fire Stations Map view

We also developed same way to Police Station Activity

### Organizations
This segment is developed for different kinds of volunteer organizations like Human Rights Organizations, and Blood Donation etc. 

### Get Current Location
We use Google maps API for getting current location and users nearby places through tracking for helping and identifying.
 
### Emergency Capture Pictures, Videos and Audios
We used Camera, Video and Audio options for this segment for sending data to the server and saving the data to identify the evil person. We will implement video and audio functionality in future.
(a) Take a Criminal Picture 	         
(b) Capture Image by Camera    	      
(c) Picture Uploaded
(c) Image stored in the database
(e) Upload from the gallery                     
(f) Record Video 	                      
(g) Record Audio

### Emergency Services 
We implemented emergency Hospitals, Ambulances and Emergency Contacts list for getting help from nearby places through this every segment. This activities are actually on the Navbar section.

(a) Ambulances Button 				                              
(b) Ambulances List
(c) Hospitals Button 				                                
(d) Hospitals List 
(e) Emergency Contacts 			                                       
(f) Emergency Contacts List

### Implementation of Admin Panel
We have developed an admin panel for maintenance and user security management for many purposes. In this segment we have designed six different segments like Add Organizations, Add Ambulances, Add Hospitals, Add Police Stations and Add Fire Stations etc.
  					 
(a) Admin Panel Login 				 	             
(b) Admin Panel Activity

