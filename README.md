## 1. Group Information

**Name of Project:** ***Good Impression***

**Link of Project:** [Good Impression](https://github.com/Hecker-Chuoi/BTCK2/tree/main)

**Group Member:**
- [Nguyen Huu Tien](https://github.com/Hecker-Chuoi)
- [Pham Quang Minh](https://github.com/PhQuangMinh)
- [Phan Thi Hong Tham](https://github.com/thm1911)

**Mentor:**
- [Nguyen Quoc Hung](https://github.com/quochung-cyou)
- [Vu Dinh Thiet](https://github.com/thiet003)

### Working model

The team operates according to the Scrum model, using Linear to manage work. Jobs are kept fully tracked on Linear.
- Link linear: [Linear of Good Impression](https://linear.app/bdtproptit/team/NHOM6/all)

Each week, the team will sit down to review the work they have done, solve problems together and propose solutions for the following week. There will then be a demo session for the mentor to receive feedback and guidance.

### Version Control Strategy


The team works according to Gitflow to manage code. Each member will create a branch from `develop` to work, branches are placed according to format `feature/ten-chuc-nang`. Once completed, a Pull Request will be created to review the code and merge it into develop
- Main branches:
  - `master/main`: Contains stable, thoroughly tested and tested code
  - `develop`: Contains the latest code, reviewed and tested
  - `feature/`: Branches contain code under development, short-live, and will be merged into develop `develop`. 

<figure>
  <img src="./Demo images/image.png" alt="Git flow" style="width:500px">
  <figcaption>Git flow</figcaption>
</figure>

After each week, the team will merge `develop` into `master` to release the new version.



## 2. Project introduction

**Describe:** **Good Impression** is set in the context of you are a very nomal, lazy and FA student living in a small apartment. Your room are all messed up because you're always throwing things around and you 're too lazy to tidy up.

To day, just like another day, you woke up and checked your phone. There's a message from your mom, she said she's on the way to your apartment.

Urgent, you need to clean your apartment immidiately and as fast as possible or you'll be scolded.
- Trash must be thrown in the trash can, wet floors must be wiped away, dirty dishes must be washed, clothes must be stored in a flat cupboard, in general everything must be neat.
- When doing an action, press the button according to the game's instructions
- The guy can ask his friend to help (2-player mode), the two of them clean the house together, and can throw objects back and forth to each other.

## 3. Main fuction

- ***Function 1***: Pick up, throw trash, food, clothes,... into containers.
- ***Function 2***: Mopping the floor using clothes or tissue, washing dishes.
- ***Function 3***: 1 or 2 player mode.
- ***Function 4***: Local leaderboard for each play mode.
- ***Function 5***: With 2-player mode, 2 people can clean the house together and throw things back and forth to each other.
## 4. Technology

### 4.1. Technology Used
- Java language
- LibGDX framework

### 4.2. Project structure
```
├── ProGameCup
│   ├── assets
│   │   ├── alert
│   │   ├── animations
│   │   │   ├── main-char1
│   │   │   ├── main-char2
│   │   │   ├── mom
│   │   ├── button
│   │   │   ├── game
│   │   │   ├── menu
│   │   ├── endgame
│   │   │   ├── score
│   │   ├── files
│   │   ├── fonts
│   │   ├── instruction
│   │   ├── items
│   │   │   ├── dynamic-items
│   │   │   │   ├── items
│   │   │   │   ├── pick-items
│   │   │   ├── static-items
│   │   │   │   ├── items
│   │   │   │   ├── pick-items
│   │   │   │   ├── storage
│   │   ├── leaderboard
│   │   ├── maps
│   │   ├── music
│   │   ├── otherImage
│   │   ├── sounds
│   │   ├── story
│   │   ├── ...
│   ├── core
│   │   ├── src
│   │   │   ├── com.mygdx.game
│   │   │   │   ├── common.constant
│   │   │   │   ├── controller
│   │   │   │   │   ├── collision
│   │   │   │   │   ├── discover
│   │   │   │   │   │   ├── discovermulti
│   │   │   │   │   │   ├── discoversingle
│   │   │   │   │   ├── filter
│   │   │   │   │   │   ├── filterendgame
│   │   │   │   │   │   ├── filtermulti
│   │   │   │   │   │   ├── filtersingle
│   │   │   │   │   ├── handleinput
│   │   │   │   │   │   ├── firstplayer
│   │   │   │   │   │   ├── secondplayer
│   │   │   │   │   ├── item
│   │   │   │   │   │   ├── activity
│   │   │   │   │   │   │   ├── throwitem
│   │   │   │   │   │   ├── setup
│   │   │   │   │   ├── player
│   │   │   │   ├── model
│   │   │   │   │   ├── item
│   │   │   │   ├── view
│   │   │   │   │   ├── draw
│   │   │   │   │   │   ├── item
│   │   │   │   │   │   ├── map
│   │   │   │   │   │   ├── player
│   │   │   │   │   │   ├── screengame
│   │   │   │   │   │   ├── text 
│   │   │   │   │   │   ├── ui
│   │   │   │   │   ├── effect
│   │   │   │   │   ├── screens
│   │   │   │   │   │   ├── endgame
│   │   │   │   │   │   │   ├── DrawMom
│   │   │   │   │   │   │   ├── DrawResult
│   │   │   │   │   │   ├── maingame
│   │   │   │   │   │   │   ├── multiplayer
│   │   │   │   │   │   │   ├── singleplayer
│   │   │   │   │   │   ├── mainmenu
│   │   │   │   │   │   ├── mainstory
│   │   │   │   │   │   ├── optionplayer
│   │   │   └── ...
│   ├── desktop
│   │   ├── src
│   │   │   ├── com.mygdx.game
│   │   ├──...
│   ├── README.md
│   ├──...
```

Explain:
- **assets:** Contains resources such as images, sounds, files,...
- **core:** Contains the main packages of the game such as model, view, controller
- **desktop:** Contains classes to run on desktop platforms

## 5. Demo Photos and Demo Videos

**Photos Demo:**

<figure>
  <img src="./Demo images/home_screen.png" alt="Home game screen" style="width:400px">
  <figcaption>Home game screen</figcaption>
</figure>

<figure>
  <img src="./Demo images/howToPlay_and_select_mode.png" alt="How to play and select game mode" style="width:400px">
  <figcaption>Show how to play and wait for game mode selection</figcaption>
</figure>

<figure>
  <img src="./Demo images/1_player_mode.jpg" alt="One player mode" style="width:400px">
  <figcaption>One player mode</figcaption>
</figure>

<figure>
  <img src="./Demo images/two_player_mode.png" alt="Two player mode" style="width:400px">
  <figcaption>Two player mode</figcaption>
</figure>

<figure>
  <img src="./Demo images/game_pause.png" alt="Game pause" style="width:400px">
  <figcaption>Game pause screen</figcaption>
</figure>

<figure>
  <img src="./Demo images/final_score.jpg" alt="Show final score" style="width:400px">
  <figcaption>Final score screen</figcaption>
</figure>

<figure>
  <img src="./Demo images/input_name.png" alt="Input name" style="width:400px">
  <figcaption>If a new record is discovered, a name will be requested</figcaption>
</figure>

**Video Demo:**
[![Good Impression](https://markdown-videos-api.jorgenkh.no/url?url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3DSlAu0WRmLnw)](https://www.youtube.com/watch?v=SlAu0WRmLnw)

## 6. Problems Encountered

### Issue 1: Ram memory overflow
The amount of RAM used by the game continuously increases after each frame, consuming a lot of system RAM.
When the amount of RAM used is too large, the game begins to lag, become unstable and crash when the system RAM is not enough.

#### Action to Resolve
**Solution:** 
- Due to creating too many objects, it leads to RAM overflow and reduced performance
- Use Design Pattern Object Pool to reuse objects. When the object is no longer in use, it will be put into the pool for reuse.

#### Result
- After using `Object pool`, the amount of RAM used by the game has become stable (700 - 1400 MB of RAM), the amount of RAM used depends on each screen.
- The amount of RAM used for each screen varies by no more than 50 MB.

### Issue 2: Objects overlap each other
When objects stand next to each other, one object stands in front and partially or completely obscures another object.  
Ex: The character stands in front of the bed or the character stands behind the bed.

An object can be behind one object but at the same time can be in front of another object.  
Ex: The character stands between the table and the sofa, then the character stands behind the table and in front of the sofa.

#### Action to Resolve
**Solution:** Based on the player's standing position, divide the objects into 2 sets standing behind and in front of the player. The set of objects behind will be drawn first, then the player and finally the objects in front.

#### Result
- Solve the problem, clearly showing the distance relationship between objects.

## 7. Conclude

**Results achieved:** The group's game project has been greatly improved in terms of performance, game logic,... 

**Next development direction:** In the coming time, the team will research to develop some more parts such as: Expanding characters (Speed ​​up characters), Expanding levels (Creating some more levels for the game).

After this project, our team got a chance to gain Gitflow related knowledge, teamwork skills and learn how to run a project in real life, which greatly improved skills program. Hope everyone will love our game. All pull requests are welcome. For major changes, please open an issue first to discuss what you want to change.