# macrolayer-srv-login

## Building the Login Srv Docker image

    $ docker build --tag charques/macrolayer-srv-login .
    
The image will be tagged `charques/macrolayer-srv-login`.

## Using the Login Srv image

Using the Login Srv image we created, we can run one or more Login Srv instances
as daemon process(es).

    # Basic way
    # Usage: docker run --name <name for container> -d <user-name>/<repository>
    $ docker run -p 49160:8080 --name login_instance_001 --link mongo_instance_001 -d charques/macrolayer-srv-login

    # Dockerized NodeJs Srv, lean and mean!
    # Usage: docker run --name <name for container> -d <user-name>/<repository> --noprealloc --smallfiles
    $ docker run -p 49160:8080 --name login_instance_001 --link mongo_instance_001 -d charques/macrolayer-srv-login --smallfiles

    # Checking out the logs of a MongoDB container
    # Usage: docker logs <name for container>
    $ docker logs login_instance_001

    # Basic test
    # curl -i -H "Accept: application/json" http://192.168.99.100:49160/macrolayer/api/login
    
> **Tip:**
If you want to run two containers on the same engine, then you will need to map
the exposed port to two different ports on the host

    # Start two containers and map the ports
    $ docker run -p 49160:8080 --name login_instance_001 --link mongo_instance_001 -d charques/macrolayer-srv-login
    $ docker run -p 49161:8080 --name login_instance_002 --link mongo_instance_001 -d charques/macrolayer-srv-login

    # Now you can connect to each Login Srv instance on the two ports
    $ curl -i -H "Accept: application/json" http://192.168.99.100:49160/macrolayer/api/login
    $ curl -i -H "Accept: application/json" http://192.168.99.100:49161/macrolayer/api/login

    