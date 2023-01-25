1. Unzip the folder 
2. Open the folder in terminal write cd backend
3. In case of change in JDK change the java version from /count-visit-users/backend/pom.xml ( if not skip to step 4)
4. Change DB name if you want from ount-visit-users/backend/src/main/resources/templates/application.properties with the name you want. Make sure you have created that database in your MSYQL server, also the cridentials for the database username and password.
3. Write mvn spring-boot:run ( make sure maven is installed and configured on your system)
4. Open a new terminal and write cd front
5. Run npm install ( make sure node is installed on your system)
6. Run npm start
7. Go to http://localhost:3000/ in your browser and ulpload the csv file and you will get the output
