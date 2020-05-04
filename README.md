## Online Course Management system :

Course demand was predicted by analyzing trends and patterns using Weka Machine Learning software (Market Basket Analysis & Apriori) on large-scale course data.

WekaImplementation folder has the machine learning part implemented using Weka software.

Project Presentation.mp4 has the video demonstration of the project

#### Demonstration video
https://www.youtube.com/watch?v=05NVlE2A_M4&feature=youtu.be 

Credentials added below. [This repository is private and hence please contact me to get access]

#### VM Image
virtual_machine_image folder

>> VM credentials:

User : student

Password : cs6310

>> DB credentials:

User : root

Password : cs6310

>> Source code

The source code can be viewed on clicking the IntelliJ icon on the desktop of course VM.

>> JAR file 

The JAR file is placed inside the CourseManagementSystem folder on the desktop of the Virtual Machine along with test case. On command prompt, going to that folder on Desktop and executing the following command opens the application, as shown in the video demonstration:
java -jar CourseManagementTeam45-1.0-jar-with-dependencies.jar

#### User Reference Manual:

Steps for navigating on UI: 

The application begins with the Home Screen and other tabs are disabled. 
On the home screen you need to select the mode for the application (Initial or Resume). 

For the very first run (term1), select the Initial mode and click on Start Simulation button. 

When the simulation starts, initial data will be loaded. All the other tabs will be enabled on the screen and Process Request button will be enabled. 

As per the instructions on the home page, the assignment of instructors to courses should be performed before clicking on Process Requests button and one optional instructor reassignment is allowed before clicking on Validate Requests button. 

Run Weka analysis (produce meaningful output only if there is sufficient input data from academic records) and use the results to assign instructors to courses. 

Go to Instructors tab (can use the link under instructions in home page). On the Instructors tab, the list of instructors will be displayed. When you click on any instructor name, the course taught by the selected instructor will be displayed on the side pane. 

Since academic records data is not sufficient for meaningful output from weka analysis, we assigned courses with no prerequisites to the instructors in the first term.

If no course is assigned, you can select the course from the dropdown and click on Assign button. Otherwise, newly assigned course will replace the previously assigned course. An instructor can be assigned to maximum one course. 

On clicking the Assign button, a new course offering will be created and the newly assigned course will be displayed in the details (side) pane. 

Next, click on the Process Request button to start loading the requests data. The Process Request button will be disabled and Validate Request button will be enabled. 

Perform the one optional instructor reassignment, if required. 

Click on Validate Request button to validate the student's course requests. After validating the requests, Validate Request button will be disabled and Generate Grade button will be enabled. 

Next click on Generate Grade button to generate the grades for granted requests. After this the Generate Grades button will be disabled and Start Next Term button will be enabled. 

On clicking Start Next Term button, Process Requests button will be enabled. 

After Weka analysis and course assignment to instructors, Process Request button is clicked. On clicking this button, requests file for next term will be uploaded. 

If the request file for that term does not exist, an error dialog box will pop-up stating the same.

#### REFERENCES
weka - How can I use transactional data in Weka? (2017). Retrieved from WEKA The University of Waikato: https://weka.wikispaces.com/How+can+I+use+transactional+data+in+Weka%3F 
GC: weka.filters.unsupervised.instance.Denormalize. (n.d.). Retrieved from GC: http://grepcode.com/file/repo1.maven.org/maven2/nz.ac.waikato.cms.weka/denormalize/1.0.2/weka/filters/unsupervised/instance/Denormalize.java/#Denormalize
Java Code Examples for weka.associations.Apriori. (n.d.). Retrieved from Program Creek: https://www.programcreek.com/java-api-examples/index.php?api=weka.associations.Apriori
Denormalize. (n.d.). Retrieved from Weka 3: Data Mining Software in Java: http://weka.sourceforge.net/doc.packages/denormalize/weka/filters/unsupervised/instance/Denormalize.html 
Association Rule Mining with WEKA. (2010). Retrieved from Depaul University: http://facweb.cs.depaul.edu/mobasher/classes/ect584/WEKA/associate.html 
Java Database Connector Software (JDBC) Retrieved from Oracle.com website: http://www.oracle.com/technetwork/database/features/jdbc/index-091264.html
Example of how to use JDBC and design a data access layer. Retrieved from dzone.com website: https://dzone.com/articles/building-simple-data-access-layer-using-jdbc
MySQL database server software. Retrieved from mysql.com website:  https://dev.mysql.com/downloads/mysql/
MySQL workbench software. Retrieved from mysql.com website:  https://dev.mysql.com/downloads/workbench/
Weka 3: Data Mining Software in Java. (n.d.). Retrieved from WEKA The University of Waikato: http://www.cs.waikato.ac.nz/ml/weka/

#### NOTES 
Denormalize filter available with weka (open-source code[2]) was downloaded and customized as DenormalizedCustom.java and used in our code.
Performed frequent itemset analysis and fed transactional data into weka after researching using Internet resources[1].
Used Apriori algorithm for the Association Rule mining[3].
The various options for denormalize were researched[4].
Used internet resources to learn more on metric types like lift and leverage[5].
Downloaded Java Database Connection Software [6].
Researched internet to understand the examples related to use of JDBC and designing data access layer [7].
Downloaded MySQL database server software [8].
Downloaded MySQL Workbench software [9].
Downloaded Weka software[10].

