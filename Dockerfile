################### Stage 2: A minimal docker image with command to run the app 
FROM openjdk:8-jre-alpine
ENV APP_FILE ms-notify-1.0.0.jar

ENV APP_HOME /usr/apps

ENV APP_SOURCE ms-parameter/target

ARG JAR_FILE=target/ms-notify-1.0.0.jar
COPY $JAR_FILE $APP_HOME/
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
EXPOSE 8084
CMD ["exec java -Djava.security.egd=file:/dev/./urandom -jar $APP_FILE"]

# mvn clean install
# docker build  -t us.gcr.io/hondu-pf/ms-notify:1.1.7 -f Dockerfile .
# gcloud docker -- push us.gcr.io/hondu-pf/ms-notify:1.1.7
# git commit -am 'fix'
# git push  

