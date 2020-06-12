## Project arcitecture

The order that processes are run in each iteration of the game loop will be determined by a priority system. (ie. networking then player movement then enviroment etc.)

Online architecture will be designed as Server - Client, with the server being a command-line executable. May be written in java or python.  

Java: Consistent with the game itself (everything being the same language)  
Python: alot easier  

A seperate launcher application may be created through Electron or CEF. The launcher would handle finding online lobbies and launching the libgdx project. 

## Gameplay design 
### Hitboxes 
Collisoins will be implemented through hit-boxes rather than pixel perfect sprite collisions. Each hittable entity will have its own Hitbox which will consist of smaller sub-hitboxes. The parent hitbox returns a hit if any of the sub-hitboxes are hit. 