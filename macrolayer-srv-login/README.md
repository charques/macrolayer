# macrolayer-srv-login

## Building the Login Srv Docker image

    $ docker build --tag charques/macrolayer-srv-login .
    
The image will be tagged `charques/macrolayer-srv-login`.

## Using the Login Srv image

Using the Login Srv image we created, we can run one or more Login Srv instances.

    # First image
    # Usage: docker run --name <name for container> -d <user-name>/<repository>
    $ docker run -p 49160:8080 --name login_instance_001 --link mongo_instance_001 -d charques/macrolayer-srv-login

    # Checking out the logs of a MongoDB container
    # Usage: docker logs <name for container>
    $ docker logs login_instance_001

    # Basic test
    # curl -i -H "Accept: application/json" http://192.168.99.100:49160/macrolayer/api/login
    
    # Second image to build a two containers balanced system with nginx
    $ docker run -p 49161:8080 --name login_instance_002 --link mongo_instance_001 -d charques/macrolayer-srv-login