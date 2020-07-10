# Challenge Brasilprev 
A REST API to simulate an online store.

This store must have a record of its customers, products and orders.

How Api deployed on azure:

Generated image from the docker file.

in the directory project:

-build image-
0. mvn package

1. $ docker build -t brasilprev .

- tag image need repository local in docker -
2. $ docker tag brasilprev brasilprevi/latest

------------ test deploy azure --------------------

-After I created a webapp and instantiated container on azure-
-command push azure- (brasilprev is instance image docker azure create to test)
3.  $ az acr login -n brasilprev && mvn compile jib:build

-now just test-
4. http://191.235.200.251:8080/swagger-ui.html

-----------------------------------------------------

------------ test deploy local with docker  ---------

3 $ docker run -d -p 8080:8080 brasilprevi/latest

-now just test-
4 $ http://localhost:8080/swagger-ui.html

-----------------------------------------------------

# HOW GET TOKEN AUTHENTICATION

1 Send request post to /authenticate  with user userPrevi and senha any.

2 Return token its valid.

3 To acess api add before token ex: Bearer axfgT1wT3a....




