# TwoodeeLegacy
Public codebase for Twoodee, an abandoned tile based engine in LibGDX and Java.
![java_skvUHQW44f](https://user-images.githubusercontent.com/108619637/191604056-4b17359f-5168-4352-b870-b8a15eb43a44.png)

# Context
I orignallly made a project called "Terraria Clone" in order to learn LibGDX more and to learn how to handle a terraria based lighting.
I ended up by making an dedicated algorithm for it and it ended up like this:
![image](https://user-images.githubusercontent.com/108619637/191603574-0089b5b8-6ed8-49b7-9d8a-7027561b8754.png)

The code was good enough but was just a simple experiment. This project, called Twoodee (because of a lack of imagination) was an upgraded version but with bigger plans like networking, chunk based rendering, and other stuff.
I did make a lot of it, and it has interesting features too (which I discuss below).

I created this github page in order to share the source code to some people that asked to see the code on the LibGDX discord server.

# Features

* Chunk based rendering
* Basic UI elements with event listeners and much more
* Box2d based lighting
* _Heavy CPU usage_
* Tile types and enums
* Terraria based lighting with light influence
* Autotiling
* Multithreading (but still low fps)
* Low memory usage

# Abandon

I've abandoned this project and left it out here as an archive for those who need it, I do not see myself working on the code anymore as I struggled with optimization too much and ended up by giving up.
I am looking forward to use another framework in C#, but with a better knowledge of tiled based games and even more features (that I promised myself of doing in this project that I ended up not doing)

# Using this project

This project was written with IntelliJ Idea, I don't know about Eclipse, but I know that if you open this project in intellij, it should run just fine.
There are some features that you can try like FUTURE lighting (edit it in WorldRenderer.java) or some other stuff here and there.

I cannot provide documentation of this "engine", but most of the code is written so I could understand myself and so you could (I hope).

You are completely free of using some of the code out here if you need (good luck with reading the code with my desperate optimization attempts) or redistribute, I don't really care.
**This project will stay archived.**
