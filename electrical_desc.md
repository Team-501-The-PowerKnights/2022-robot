## Motors

| Mechanism        | Controller Type | CAN ID | Position    | PDB ID |
| ---------------- | --------------- | ------ | ----------- | ------ |
| Elevator         | Talon SRX       | 50     | Lower       | 5      |
| Incrementer      | Talon SRX       | 51     | Upper       | 7      |
| Climber          | Spark Max       | 55     | Right       | 12     |
| Climber          | Spark Max       | 56     | Left        | 13     |
| Drive            | Spark Max       | 11     | Left Front  | 15     |
| Drive            | Spark Max       | 12     | Left Rear   | 14     |
| Drive            | Spark Max       | 13     | Right Front | 0      |
| Drive            | Spark Max       | 14     | Right Rear  | 1      |
| Intake           | Talon SRX       | 41     | N/A         | 11     |
| Intake Retractor | Talon SRX       | 42     | N/A         | 9      |
| Shooter          | Spark Max       | 21     | Left        | 2      |
| Shooter          | Spark Max       | 22     | Right       | 3      |
| Turret           | Spark Max       | 20     | N/A         | 6      |
|                  |                 |        |             |        |
| Pneumatics       | PCM             | 0      |             | 8      |
|                  |                 |        |             |        | 
| Vision           | Limelight       | N/A    | N/A         | 10     |

## Sensors

| Mechanism   | Sensor Type  | Port      | Position |
| ----------- | ------------ | --------- |----------|
| Elevator    | Ultrasonic-y | 0         | Lower    |
| Incrementer | Ultrasonic-y | 1         | Upper    |
| Climber     | ???          | ???       | N/A      |
| Climber     | ???          | ???       | N/A      |
| Drive       | IMU          | MXP       | N/A      |
| Turret      | Proximity    | Digital 8 | N/A      |

| Drive       | Relative Encoder | Left  | Left Front (Talon SRX)  |
| Drive       | Relative Encoder | Right | Right Front (Talon SRX) |

