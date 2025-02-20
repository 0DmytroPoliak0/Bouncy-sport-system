# Bouncy-sport-system
Application for the store to monitor number of people at the moment in the store.

## Bouncy Sports System

### 📌 Overview  
The **Bouncy Sports System** is a **store entry management simulation** designed to control customer flow, track store personnel access, and manage capacity limits. The system leverages **state and observer patterns** to simulate real-world store management scenarios.  

### 🔧 Features  
- **Customer Entry & Exit**: Tracks customers entering and leaving the store using **sensors and barriers**.  
- **Capacity Management**: Restricts store access if the **maximum capacity** is reached.  
- **Store Personnel Authentication**: Uses an **authentication strategy** to validate personnel entry.  
- **Manager Controls**: The **store manager** can:  
  - View current customer count  
  - Update maximum store capacity  
  - List registered personnel  
- **State Machine for Simulation**: Different **states** handle user input dynamically.  

### 🏢 System Components  
#### 🔹 Core Components  
- **SimulationManager**: Controls the entire simulation, initializes components, and manages state transitions.  
- **PeopleCounter**: Tracks customer count and triggers barrier updates.  
- **Sensor**: Detects customer movement (entry/exit) and updates the count.  
- **Barrier**: Opens or closes based on capacity rules.  

#### 🔹 Authentication  
- **AuthenticationStrategy**: Interface for different authentication methods.  
- **CardAuthentication**: Implements authentication via card IDs.  
- **StorePersonnel**: Represents store employees with unique IDs.  

#### 🔹 State Management  
- **SimulationState**: Interface for handling different states.  
- **DefaultState**: Main menu for selecting actions.  
- **CustomerEntryState & CustomerExitState**: Handles customer entry/exit.  
- **ManagerMenuState**: Provides management options.  

### 🚀 How to Run  
1. Clone the repository:  
   bash
   git clone https://github.com/0DmytroPoliak0/Bouncy-sport-system.git
     
2. Navigate to the project directory and run the `Main` class:  
   bash
   cd Bouncy-sport-system
   
3. Compile and run the program using a Java IDE or command line.  
4. Follow on-screen instructions to simulate store operations.  

### 🛠️ Testing  
JUnit tests are implemented for various core components:  
- `PeopleCounterTest`  
- `SensorTest`  
- `StoreManagerTest`  
- `BarrierTest`  

Run tests using your preferred IDE or command line with:  
bash
mvn test


### 📌 Notes  
- The program **does not use a database**—all data is managed in memory.  
- Uses **Observer Pattern** to notify barriers when store capacity changes.  
- Uses **State Pattern** to dynamically change the program’s behavior based on user input.  


