# RUN IT USING DOCKER FILE

NOTE: The -t (tags) reference to the tag name that will be generate for the Docker, the s4 means that we are into the section 4 for this course.

docker build . -t eazybytes/accounts/s4

! You can run the commands 

docker images --> LIST ALL OF THE IMAGES

docker inspect <IMAGE_ID> --> CHECK THE DETAILS FOR THE DOCKER IMAGE

docker run -d -p 8080:8080 eazybytes/accounts/s4 --> Start the docker using the port 8080, to expose the docker outside the docker network.
-d --> detached mode
-p --> port mapping

- First 8080 --> Custom port
- Second 8080 --> Network port

