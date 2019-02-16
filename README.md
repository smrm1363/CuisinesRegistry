# Cuisines Registry

## The story:

This is my simple solution to technical tasks. In this project, I tried to make the codes cleaner and refactor them. Dou to you did not mention about using frameworks, I did not use any framework and everything is implemented via pure Java. I used a dynamic solution for adding a Cuisine using a Property file, if you want to add a cuisine, use the application.properties file. 

## Short answer to task 4:
For keeping a large amount of data in memory instead of using a database, I have two suggestions.

 First, we should increase the -Xmx as large as possible.
 
 Second, we could use techniques like Swapping instead of using Heap. As far as I know, we cloud implement this policy programatically or using a tool like BigMemory. The idea is to use direct ByteBuffers to store your object's data. Because direct byte buffers' contents are stored in native process memory (as opposed to heap) you can rely on OS swap mechanism to swap memory out for you.
 
 ## Conclusion:
 I am thankful for these technical tasks, I hope you like my solutions.
  
  Yours sincerely,
  
  Mohammadreza Mirali