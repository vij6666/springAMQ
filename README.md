# springAMQ

Spring project with a simple restful service which triggers an AMQ message on GET /product/{id} which is received by a JMS Listener
which then updates the Product "messageReceived" and "messageCount" fields.

Runs on Port:9099
Registers with Consul on //localhost:8500

Also implemented JPL Query and Native Query for GET /product/jpql/{id} and GET /product/native/{id} respectively.

JPL Query has LockModeType.PESSIMISTIC_FORCE_INCREMENT and will hence update the Product "version" field on call.
