# SynthCorp – A Smart and Automated Manufacturing System

## Project Overview

**SynthCorp** is a futuristic automated manufacturing system designed to restore and modernize a malfunctioning factory using robust Object-Oriented Programming (OOP) principles and proven design patterns. The system simulates a smart factory where robotics, AI-driven workflows, and real-time monitoring work together to optimize production, ensure safety, and streamline resource management in Java.

---

## Problem Statement

In 2045, SynthCorp's advanced automation revolutionized manufacturing. However, after a critical software update, catastrophic failures led to:
- Malfunctioning robots (incorrect assembly, halts)
- Erratic raw material orders (overstocking/shortages)
- Machines ignoring safety protocols
- Unpredictable scheduling and shipment delays

Your OOP engineering team is tasked to restore and upgrade this control system using OOP and design patterns to ensure efficiency, scalability, and safety.

---

## Key Features & Requirements

### 1. Core OOP Concepts
- **Encapsulation:** Securely manage machine data, inventory logs, and production schedules.
- **Inheritance:** Hierarchy of machines (e.g., `AssemblyRobot`, `PackagingRobot`, `QualityControlBot` from base `Machine` class).
- **Polymorphism:** Multiple manufacturing strategies (Mass Production, Custom Batch, On-Demand).
- **Abstraction:** Simple manager-facing API for plant control & monitoring.

### 2. Creational Design Patterns
- **Factory Pattern:** Dynamically create machine types (`CuttingMachine`, `DrillingMachine`, `WeldingMachine`).
- **Singleton Pattern:** Central Control System as a single orchestrator.
- **Builder Pattern:** Custom production setups (assembly lines, quality rules).

### 3. Structural Design Patterns
- **Facade Pattern:** Unified dashboard for monitoring and control.
- **Adapter Pattern:** Integrate legacy hardware with new software.
- **Decorator Pattern:** Add features like error detection or energy-saving.

### 4. Behavioral Design Patterns
- **Observer Pattern:** Engineer notifications on faults/maintenance.
- **Strategy Pattern:** Switch production modes based on market/resource state.
- **Command Pattern:** Undoable production actions (pause/cancel orders).
- **State Pattern:** Machine status tracking (`Idle`, `Active`, `Error`, `Maintenance`).

### 5. Additional Features
- **Inventory & Resource Management:** Auto-track/reorder raw materials.
- **Safety Protocols:** Emergency shutdown automation.
- **Production Optimization:** Dynamic scheduling to reduce downtime.

---

## Project Structure

```
Synthcorp/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── synthcorp/
│                   ├── Main.java                      # Entry point
│                   ├── controllers/
│                   │   ├── CentralControlSystem.java      # Singleton orchestrator
│                   │   ├── DashboardFacade.java           # Facade for manager interface
│                   │   ├── ProductionStrategy.java        # Strategy pattern interface
│                   │   ├── CommandManager.java            # Command pattern for production orders
│                   ├── machines/
│                   │   ├── Machine.java                   # Base class
│                   │   ├── AssemblyRobot.java
│                   │   ├── PackagingRobot.java
│                   │   ├── QualityControlBot.java
│                   │   ├── CuttingMachine.java
│                   │   ├── DrillingMachine.java
│                   │   ├── WeldingMachine.java
│                   │   ├── LegacyMachineAdapter.java      # Adapter pattern
│                   │   ├── MachineDecorator.java          # Decorator pattern
│                   ├── observer/
│                   │   ├── Observer.java                  # Observer interface
│                   │   ├── EngineerNotifier.java          # Observer concrete class
│                   ├── builder/
│                   │   ├── AssemblyLineBuilder.java       # Builder pattern for setups
│                   ├── state/
│                   │   ├── MachineState.java              # State pattern interface
│                   │   ├── IdleState.java
│                   │   ├── ActiveState.java
│                   │   ├── MaintenanceState.java
│                   │   ├── ErrorState.java
│                   ├── inventory/
│                   │   ├── InventoryManager.java          # Inventory & resource control
│                   ├── safety/
│                   │   ├── SafetyProtocol.java            # Safety systems
│                   └── utils/
│                       └── Logger.java                    # Logging utilities
│
├── src/
│   └── test/
│       └── java/
│           └── com/
│               └── synthcorp/
│                   └── ...                                # Unit & integration tests
│
├── docs/
│   └── design.md                          # Architecture and design patterns used
│
├── pom.xml                                # Maven build config
├── README.md
└── LICENSE
```

---

## Getting Started

1. **Clone the repository:**
    ```bash
    git clone https://github.com/jenilsoni04/Synthcorp.git
    cd Synthcorp
    ```

2. **Build the project using Maven:**
    ```bash
    mvn clean install
    ```

3. **Run the application:**
    ```bash
    mvn exec:java -Dexec.mainClass="com.synthcorp.Main"
    ```

4. **Run tests:**
    ```bash
    mvn test
    ```

---

## Documentation

- See `docs/design.md` for pattern explanations and architectural decisions.
- In-code comments and JavaDoc provide further guidance.

---

## Contributing

Pull requests welcome! Please see [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

---

## License

[MIT License](LICENSE)

---

## Credits

SynthCorp project by [jenilsoni04](https://github.com/jenilsoni04) and OOP engineering team.
