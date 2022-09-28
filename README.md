# UDP socket programming exercise

Exercise on UDP socket programming, used in the
course [IDATA2304 Computer networks](https://www.ntnu.edu/studies/courses/IDATA2304),
[NTNU](https://www.ntnu.edu/), Campus Ålesund.

## Intention

The intention of this exercise is to get a bit of experience programming UDP sockets, and combining them with a bit of
logic. Seriously speaking - the main challenge of programming is typically not typing the letters and using the right
functions. The challenge is understanding the real-life problem and translating it to proper logic. This is what you
will do here, using UDP sockets as a tool for solving a (un)real problem.

## Background story

The greatest minds of several European countries are going to work on a huge research project. The goal is to build a
secret Enigma machine, version 72. It will be a deep-learning artificial intelligence (AI) able to answer many difficult
questions, such as "Should there be pineapples on pizzas?", "Which side of toilet paper should face the wall?", "Why do
cats sneeze?" etc.
However, every research project must start small (and then grow exponentially). The first step of the project is to
prepare clean training data for the AI. Researchers have scraped the web and prepared 3 petabytes of text, split into
individual sentences. The sentences are of two types: statements and questions. Researchers want to classify the
sentences into the right categories. In addition, they want to sort the sentences by how long they are. Together with
british scientists (in a previously well-funded Horizon 2020 project) our researchers have found that sentences start
to lose meaning when they get too long (this was especially clear in sentences collected on websites of
political parties, the results are clearly documented in a 56-page report). Therefore, researchers want to filter out
long sentences. However, in another research council-funded project they found that simply looking at the number of
letters is not a good estimate of the length of the sentence. Counting the number of words is a much better estimate.

Long story short - the team needs to create a program which can process sentences and find out two things:

1. Whether a sentence is a question or a statement?
2. How many words does it contain?

However, the only computer-science master-student they had in the team has recently graduated. Therefore, researchers
don't have a programmer who could create the program for them. Some of them tried to learn programming, but got stuck
into long theoretical discussions on where to place curly braces to maximized aesthetic value. This led nowhere, as you
might guess.

This is where the Enigma-72 research team needs your help!

So you thought that you would need to implement a function which counts the words? It's not that easy. ;)
The researchers want to first make sure that your algorithm is correct. Therefore, they have prepared some test examples
of input data and expected output data. They have also somehow managed (through an improbability drive) to generate a
UDP-server application which contains some predefined sentences. This UDP-server (let's call it a "Task server") is
waiting for clients to ask for a task. Then the task server sends a random sentence to the client. The client must
detect whether this is a statement or a question, and how many words are in the sentence. The answer must be sent to the
task server, which will use a novel neural network with back-propagation to decide whether the answer of the client is
correct or not. (Well, to be honest, the task server will use a simple if-then-else comparison with a predefined list of
static answers, just don't tell that to anyone!) Then the server sends an `ok` to the client if the answer was correct,
or `error` if the answer was not correct (or if the client did something wrong). This can be repeated indefinitely, but
you should probably not waste server resources by asking too many tasks. (You have probably
seen [this site](https://sdgs.un.org/goals), right?)

# The task and the protocol (the serious part)

Your task is to write a UDP-client application which communicates with the task server according to the following
protocol:

1. The client is the first one sending a message (a datagram in the terms of UDP) to the server. The first message must
   always be the lowercase string `"task"`. This message means that the client is asking for a (new) task.
2. Then your client must listen for a response datagram from the task server. The server will respond with a simple
   sentence.
3. Then your client needs to process the sentence and find out:
    * Whether this is a statement or a question. This can be decided by looking at the terminating character of the
      sentence: sentences ending with the period (".") are considered `statements`, while sentences ending with a
      question mark (?) are considered `questions`.
        * Example question: `What is the meaning of life?`
        * Example statement: `Forty two.`
    * How many words does the sentence contain? For simplicity, the words will consist only of lowercase or uppercase
      letters. There will be one space between each two words. Examples:
        * 6 words in `What is the meaning of life?`
        * 2 words in `Forty two.`
        * A word can consist of a single letter, for example, the sentence `I see a tree.` contains 4 words
        * P.S. A sentence can contain zero words, for example: `?` (the researchers find such questions especially
          fascinating and deep, despite the governments warning to cancel the funding if they will
          continue to research the zero-word sentences)
4. Then your client needs to send the answer to the task server in the following format: `<type> <wordCount>`, where
    * `<type>` is the type of sentence and can have one of two values: `statement` or `question`
    * `<wordCount>` is the number of words in the sentence, as a string representation of an integer number
    * Examples:
        * The answer for the sentence `What is the meaning of life?` would be `question 6`
        * The answer for the sentence `Forty two.` would be `statement 2`
        * The answer for the sentence `?` would be `question 0`
5. The task server will check your answer (and probably integrate it into Ethereum blockchain). If your answer is
   correct, the task server will respond with a datagram containing a single word `ok`. If the answer is incorrect, the
   server will respond with `error`.

To make things a bit more interesting - make your application ask for three tasks, and solve them. Note, that the task
server's memory is limited - it remembers only the last task it has sent to each client. Which means you can't ask for
three tasks at once and then send three answers. You need to alternate: ask for a task, send answer, wait for servers
approval; then repeat.

## Testing

To test your solution (your UDP client), you can send the datagrams to the task server running on a host with IP
address `129.241.152.12`. It is listening on the UDP port number `1234`. Unless a hacker (or a peer student of yours)
has used some buffer overflow attacks, the server should be up and running 24/7.

In case you find some problems with the task server, let the course teachers know about it ASAP!

## (No) Limitations

You can choose programming language freely. Remember - sockets are language-independent and operating-system-independent
concept. While we suggest using Java (for consistency with other courses) and the examples we show are in Java, you can
use Python, C#, C++, Rust or any other language you like, no lim[i](https://www.youtube.com/watch?v=r6FVk2k4qsM)tations.
The concepts should be the same no matter the language.

The only thing we expect - that the source code of your solution is stored in a Git repository.

## Hand-in and approval

Because there is no template code (and no unit tests), this exercise is not performed as a GitHub classroom assignment.
You can simply read these instructions and create your own repo.

When you are done, hand in the link to your Git repo in the assignment submission page on Blackboard.

You will be able to show a demo of your solution during the next checkpoint with the course teachers.

## Optional part (Bonus!)

For especially interested students - we challenge you to implement the UDP-server part on your own as well. This could
be significantly more challenging than the client part. Why? Try on your own, and you will find out! ;)

This is an optional part of the assignment!

P.S. If you start implementing the server and get stuck, need some help or hints, there are several ways how to proceed:

* Talk with others - teachers, other students.
* Check out the source code of the server part made by the course teachers. It is a ready-to-use server, which contains
  all the necessary server code. You can learn a bit by looking at it, especially the structure - the separation of
  responsibilities into different classes. The `Logic` class is totally separate from any socket-code. In fact, `Logic`
  does not know anything about sockets! All it knows is the protocol, the agreement on how the tasks should be performed
    - what the client should ask for in each step and what the server should respond with. The separation of concern is
      not 100% perfect though. You can notice that some logic is actually inside the server code. Can you find a
      better way to make all the logic inside the `Logic` class? ;)

