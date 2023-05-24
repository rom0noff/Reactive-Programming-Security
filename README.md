# Reactive-Programming-Security

Reactive Programming.

It’s important to understand the meaning of reactive in order to understand the reactive programming paradigm. React means a form of response, but for what do we react? We react to events, this means reactive is a response to an event. In this way, we can define reactive programming as an event-driven method of programming.

Reactive programming is a programming paradigm where the focus is on developing asynchronous and non-blocking applications in an event-driven form

Asynchronous and non-blocking
Asynchronous execution is a way of executing code without the top-down flow of the code. In a synchronous way, if there is a blocking call, like calling a database or calling a 3rd party API, the execution flow will be blocked. But in a non-blocking and asynchronous way, the execution flow will not be blocked. Rather than that, futures and callbacks will be used in asynchronous code execution.
Event/Message Driven stream data flow
In reactive programming, data will flow like a stream and because it is reactive, there will be an event and a response message to that event. In Java, it is similar to java streams which was introduced in Java 1.8. In the traditional way, when we get data from a data source (Eg: database, API), all the data will be fetched at once. In an event-driven stream, the data will be fetched one by one and it will be fetched as an event to the consumer.

   ![image](https://github.com/rom0noff/Reactive-Programming-Security/assets/99829336/ed7a2259-2c96-43e5-93d2-a0e763de6f1b)


* Functional style code.

In Java, we write lambda expressions for Functional Programming. Lambda expressions are functional style codes. In reactive programming, we mostly use this lambda expression style.

* Back Pressure.

In reactive streams when a reactive application (Consumer) is consuming data from the Producer, the producer will publish data to the application continuously as a stream. Sometimes the application cannot process the data at the speed of the producer. In this case, the consumer can notify the producer to slow down the data publishing.

![image](https://github.com/rom0noff/Reactive-Programming-Security/assets/99829336/8a986935-e075-4a13-9f0b-f304d40edccb)
