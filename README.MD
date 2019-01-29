# AMQP

This example uses AMQP in version 1.0. The code was built to connect to Queue in MS Azure.

This code is based on https://github.com/Azure/azure-service-bus. The 
code is only divided into two modules:
* **AMQP Receiver** - Receives messages from a queue.
* **AMQP Sender** - Sends messages to a queue.

## Build
For building is used **maven**. In a root of the project run:
`mvn clean install`

Boths modules will be builded.

## Usage
First the build have to be done.
### Run AMQP Receiver
In a folder amqp/amqp-receiver/target run command:
`java -jar amqp-receiver-1.0-jar-with-dependencies.jar -c <Endpoint>`
### Run AMQP Sender
In a folder amqp/amqp-receiver/target run command:
`java -jar amqp-sender-1.0-jar-with-dependencies.jar -c <Endpoint>`