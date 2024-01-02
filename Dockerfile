FROM tomcat:latest
COPY target/areeba_customer_management-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
COPY src/main/resources/application.properties application.properties
EXPOSE 8081:8081
CMD ["catalina.sh", "run"]
