# quarkus-aws-lambda Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-aws-lambda-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- AWS Lambda HTTP ([guide](https://quarkus.io/guides/amazon-lambda-http)): Allow applications written for a servlet container to run in AWS Lambda
- RESTEasy JAX-RS ([guide](https://quarkus.io/guides/rest-json)): REST endpoint framework implementing JAX-RS and more

## Setting up propeties based on active profile
- [Ref 1](https://access.redhat.com/documentation/en-us/red_hat_build_of_quarkus/1.3/html/configuring_your_quarkus_applications/proc-using-configuration-profiles_quarkus-configuration-guide) 

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)

## AWS Deployment
- [Setup your environment - Install AWS SAM CLI MacBook](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli-install-mac.html)
- Install AWS
```shell script
curl "https://awscli.amazonaws.com/AWSCLIV2.pkg" -o "AWSCLIV2.pkg"
sudo installer -pkg AWSCLIV2.pkg -target /
which aws
aws --version

# CHECK WHO iS LOGGED IN
aws sts get-caller-identity

# Configure new creditials
# https://www.youtube.com/watch?v=BNH4i7CQ4Oc
aws configure

# Deploy
./mvn clean install
sam deploy -t target/sam.jvm.yaml -g #Quick note on this one, when you updating don't forgot to use the same StackName that you used when you were deploying e.g. Stack Name [sam-app]: quarkus-aws-lambda
```
- [AWS Lamba Deploy Reference](https://www.youtube.com/watch?v=BOvxdY8cSHw)

## H2 Database setup
- [H2 console - http://localhost:8080/h2](http://localhost:8080/h2)
- [H2 DB Console setup](https://stackoverflow.com/questions/61853691/how-to-set-h2-database-console-url-in-the-quarkus-application)

## Quarkus Testing
- [Youtube](https://www.youtube.com/watch?v=2ZC-f83GNfI)
- [Junit5 & Mockito](https://www.infoq.com/articles/testing-quarkus-integration/)
- [Http Integration Test](https://www.baeldung.com/java-quarkus-testing)
- [From RedHat](https://access.redhat.com/documentation/en-us/red_hat_build_of_quarkus/1.3/html/getting_started_with_quarkus/proc-quarkus-junit-testing_quarkus-getting-started)