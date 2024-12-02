# Tanks 2D Game

Tanks 2D is a simple 2D tank shooting game where the player controls a tank, moves around the map, and shoots bullets to destroy cars. The game is built using Java and Swing.

## Table of Contents

-   [Installation](#installation)
-   [Usage](#usage)
-   [Controls](#controls)
-   [Game Mechanics](#game-mechanics)
-   [Contributing](#contributing)
-   [License](#license)

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/tanks2d.git
    ```
2. Navigate to the project directory:
    ```sh
    cd tanks2d
    ```
3. Build the project using Maven:
    ```sh
    mvn clean install
    ```

## Usage

To run the game, execute the following command:

```sh
mvn exec:java -Dexec.mainClass="com.tanks2d.MainFrame"
```

## Controls

-   `W`: Move forward
-   `S`: Move backward
-   `A`: Rotate left
-   `D`: Rotate right
-   `Space`: Shoot
-   `R`: Reset the game
-   `F`: Toggle debugging mode

## Game Mechanics

-   The player controls a tank and moves around the map.
-   The player can shoot bullets to destroy cars.
-   The player wins the game when all cars are destroyed.
-   The player loses the game if the tank is destroyed.
