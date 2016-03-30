# EvenNewerBuildings
Springboot implementation of Building manager. <br />
target/newerbuildings-1.0.war is an execuatable war file.<br />
Use java -jar newerbuildings-1.0.war to run embedded Tomcat.<br />
Visit localhost:8080 to browse.<br />

Database has two tables, Buildings & Rooms.<br />
Buildings have a name, address, number of floors, and list of rooms.
Rooms have a their floor's and building's id numbers as reference
Buildings can be created with name, address, number of floors, and number of rooms.
The rooms are assigned to each floor of the building evenly.
Buildings can be created, deleted, and viewed.
Rooms can only be created or destroyed with their building.
