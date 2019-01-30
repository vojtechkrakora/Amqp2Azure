# AMQP

This example uses AMQP in version 1.0. The code was built to connect to Queue in MS Azure.

This code is based on https://github.com/Azure/azure-service-bus. The 
code is only divided into three modules:
* **AMQP Receiver** - Receives messages from a queue.
* **AMQP Sender** - Sends messages to a queue.
* **AMQP Common** - Shared parts between the modules.

## Build
For building is used **maven**. In a root of the project run:
`mvn clean install`

Both modules will be built.

## Usage
First the build have to be done.
### Run AMQP Receiver
In a folder amqp/amqp-receiver/target run command:
`java -jar amqp-receiver-1.1-jar-with-dependencies.jar -c <Endpoint> -q <QueueName>`
### Run AMQP Sender
In a folder amqp/amqp-receiver/target run command:
`java -jar amqp-sender-1.1-jar-with-dependencies.jar -c <Endpoint> -q <QueueName>`

## Versions

### Version 1.2
* Code refactoring.
* Adds dependencies to Log4j.

### Version 1.1
* Adds new shared module AMQP Common.
* Queue is required as an input parameter (q).
* Received message info is always written to a console.