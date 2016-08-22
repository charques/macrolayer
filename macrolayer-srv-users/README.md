# macrolayer-srv-users

* Build the application with : `mvn clean package`
* Init discovery service : project `macrolayer-srv-discovery`
* Init user service (port 2222) : `java -jar target/macrolayer-users-1.0.0-SNAPSHOT.jar users`
* If you want to init other instance of the service, set another port : `java -jar target/macrolayer-users-1.0.0-SNAPSHOT.jar users 2223`
