# Emergency Disaster Simulator in Java
This project is a command line program written in Java ☕. This program is a implementation of a  system  to  simulate natural disasters, to  help test emergency service responses. State pattern and Dependency injection has been used to implement the program.

![Emergency Simulation Demo](https://i.imgur.com/xVSt6BY.png)

## Generating Emergencies
A file containing the emergencies should be given to the program using command line arguments. Each line consists of an integer (a time in seconds), then a space, then a type of emergency (“`fire`”, “`flood`” or “`chemical`”), then a space, then a location as free-form text. 
| Time | Disaster | Location |
|:-:|:-:|:-:|
| `240` | `fire` | `Midtown` |
| `60` | `fire` | `Midtown` |
| `120` | `fire` | `Hill Valley` |
| `240` | `flood` | `Hill Valley` |
| `240` | `chemical` | `Westtown` |

Each line expresses the fact that a particular type of emergency begins at a particular time in a particular location. The lines are in no particular order, but the parts of the line after the integer should be unique; e.g. there can only be one flood in Midtown in a given simulation. If the file is invalid the simulation will not proceed.

## Simulation Logic

 - Fire Emergency
	 - A fire emergency will at first be “low-intensity”. If a fire has been burning at low-intensity for `FIRE_LOW_TO_HIGH_TIME` (seconds), unattended, it will then become “high-intensity”. If attended by a fire-fighting team, a low-intensity fire will take `FIRE_LOW_CLEANP_TIME` to extinguish (ending the emergency), while a high-intensity fire will take `FIRE_HIGH_TO_LOW_TIME` to reduce to the level of low-intensity. If fire fighters are absent, the fire will continue indefinitely.
- Flood Emergency
	- A flood emergency will dissipate on its own in `FLOOD_END_TIME` time. Until it ends, for each passing second.
		- There is a `FLOOD_DAMAGE_PROB` probability of property destruction
	 - If flood rescuers are absent_, there is a `FLOOD_CASUALTY_PROB` probability of someone needing hospitalisation. (If flood rescuers are present, nobody will need hospitalisation.)
- Chemical Emergency
	- A chemical spill emergency will take a clean-up team `CHEM_CLEANUP_TIME` to clean it up, from the time they arrive. The spill will not go away otherwise.
	- For each passing second, a chemical spill has `CHEM_CASUALTY_PROB` probability of hospitalising someone, and `CHEM_CONTAM_PROB` probability of causing environmental contamination.

## Communicating with Responders
Since the objective is to test emergency responses, you must expect that there will be actual fire fighters, flood rescuers, and other responders participating in the exercise. They will be travelling to the locations of the emergencies with all their equipment, following their usual procedures, and pretending to handle the problems once there. Each different team of responders will communicate with the program to say who they are and where they are.

## How To Run The Program
 To run the program, open the directory in the terminal and run `./gradlew run --args="filename.txt"` for Linux environment *or* `gradle run --args="filename.txt"` for Windows environment. Make sure to place the text file in the root of the directory (this is already done, see `emg.txt`) You can edit this text file to your own data by following [Generating Emergencies](#generating-emergencies) Note : Gradle should be installed first. [Click here](https://gradle.org/install/) to see how to install gradle.

### Indexed in Turn-It In Global Referencing Scheme

***This project should not be used for any coursework related activity and all codes have been submitted to `Turn-It In global referencing platform`, where usage of this code may be caught for Plagiarism.***
