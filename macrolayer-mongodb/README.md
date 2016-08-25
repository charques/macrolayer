# macrolayer-mongodb

## Building the MongoDB Docker image

    $ docker build --tag charques/macrolayer-mongodb .

The image will be tagged `charques/macrolayer-mongodb`.

## Pushing the MongoDB image to Docker Hub

All Docker image repositories can be hosted and shared on
[Docker Hub](https://hub.docker.com) with the `docker push` command. For this,
you need to be logged-in.

    # Log-in
    $ docker login
    Username:
    ..

    # Push the image
    # Format: docker push <user-name>/<repository>
    $ docker push charques/macrolayer-mongodb
    The push refers to a repository [charques/macrolayer-mongodb] (len: 1)
    Sending image list
    Pushing repository charques/macrolayer-mongodb (1 tags)
    ..

## Using the MongoDB image

Using the MongoDB image we created, we can run one or more MongoDB instances
as daemon process(es).

    # Basic way
    # Usage: docker run --name <name for container> -d <user-name>/<repository>
    $ docker run -p 27017:27017 --name mongo_instance_001 -d charques/macrolayer-mongodb

    # Dockerized MongoDB, lean and mean!
    # Usage: docker run --name <name for container> -d <user-name>/<repository> --noprealloc --smallfiles
    $ docker run -p 27017:27017 --name mongo_instance_001 -d charques/macrolayer-mongodb --smallfiles

    # Checking out the logs of a MongoDB container
    # Usage: docker logs <name for container>
    $ docker logs mongo_instance_001

    # Playing with MongoDB
    # Usage: mongo --port <port you get from `docker ps`>
    $ mongo --port 27017

    # If using docker-machine
    # Usage: mongo --port <port you get from `docker ps`>  --host <ip address from `docker-machine ip VM_NAME`>
    $ mongo --port 27017 --host 192.168.99.100

> **Tip:**
If you want to run two containers on the same engine, then you will need to map
the exposed port to two different ports on the host

    # Start two containers and map the ports
    $ docker run -p 28001:27017 --name mongo_instance_001 -d charques/macrolayer-mongodb
    $ docker run -p 28002:27017 --name mongo_instance_002 -d charques/macrolayer-mongodb

    # Now you can connect to each MongoDB instance on the two ports
    $ mongo --port 28001
    $ mongo --port 28002

 - [Linking containers](../userguide/networking/default_network/dockerlinks.md)
 - [Cross-host linking containers](../admin/ambassador_pattern_linking.md)
 - [Creating an Automated Build](https://docs.docker.com/docker-hub/builds/)