# docker-task-back-end

You can run the application in two ways

1)Normal application run from command prompt

2)Using docker container

The easiest way would be running using docker

# Steps for running from command prompt


1) Clone the code

2) Add the path in application.properties(src/main/resources) for archive file and destination folder to unzip archive.

            -app.archivePath = The path for zip file

            -app.destinationPath = Destination folder to unarchive

3) Apache maven installation and adding maven paths in environemnt variables. 

4) postgresql database server download

5) put all the postgresql credentials in application.properties

6) make sure to use host name as localhost while running using this method
      - spring.datasource.url=jdbc:postgresql://localhost:5432/test   ----test is database name
      - spring.datasource.username= your_username
      - spring.datasource.password= your_password
  
also use this credentials in application.properties.

7) go to project directroy from cmd and type mvn spring-boot:run

8) These are two REST apis for persisting data in database and deleting data from database by particular ID. Download Postman and hit the below urls.
    
    a)http://localhost:8082/patents -GET method
    
    b)http://localhost:8082/patents/{id-of-patent} - Delete method
    



  # Steps for running using docker

1) clone the code.

2) Add the path in application.properties((src/main/resources)) for archive and destination folder to unzip archive 
     app.archivePath = The path for zip file
     app.destinationPath = Destination folder to unarchive

3) Download docker.

4)  go to project directory and type mvn clean package -DskipTests(You need to install maven to run this command)

4) just go to -->your-project-directory/src/main/docker from cmd and type docker-compose up

5) Using credentials in docker-compose.yml login to postgres database.

      make sure to use this credentials when using docker-compose 
      - SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/compose-postgres
      - SPRING_DATASOURCE_USERNAME=compose-postgres
      - SPRING_DATASOURCE_PASSWORD=compose-postgres
      

6) These are two REST apis for persisting data in database and deleting data from database by particular ID. Download Postman  and hit the below urls.
    - http://localhost:8082/patents -GET method
    -  http://localhost:8082/patents/{id-of-patent} -Delete method
    
    
    # Description
    
 1)I have used java, spring boot and postgresql to create application. Since I had to persist data from XML in database I have used JaxB library from java which maps XML to Java Objects.
   
 2)I have used this design such that if in future any mapping filed we had to change we just need to write small amount of code without affecting the other code. also many developers can work at same time in different modules using this technique.
   
3)also using this methodology I can easily persist data in database.
   
   
   
   # Challenges
   1)one would be if XML format changes we would have to generate the java objects again using the new XML format.

   2)one challenge would be to deploy this on server with downloading and extracting all the files in the server container.
    
    
    
    
