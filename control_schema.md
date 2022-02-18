# Controls Layout / Interface

## Types of Inputs
- [button] - button, active high
- [analog] - analog value

## Driver
- [analog] (Left Joystick U-D): Drivetrain Speed (forward/backward)
- [analog] (Right Joystick L-R): Drivetrain Turn (left/right)
- [button] (Right Bumper): Drivetrain Crawl Enabled *{whenHeld}*
- [button] (Left Bumper): Drive Train Turbo Enabled *{whenHeld}*~~
- [button] (Back Button) Switch Drive Direction
- [analog] (Left Trigger): Intake In
- [analog] (Right Trigger): Intake Out

## Operator
### Fire
- [button] (Green #1): Fire Pose *{whenHeld}*
  - if shooter is not revved up, rev up shooter
  - run intake and hopper
  - advance balls via ballevator into shooter
### Vision
- [button] (Right Bumper #6): Enable vision targetting *{whenHeld}*
  - enforce turret rotation with limits
### Shooter
- [button] (Red #2): Rev Shooter *{whenHeld}*
  - use PID and selected set_point
  - else idle shooter at constant preset in code
- [button] (POV Up 0): Shooter RPM fine adjustment off of baseline
- [button] (POV Down 180): Shooter RPM fine adjustment off of baseline
  - +/- range **~50** RPM
  - funky start enable processing
### Turret
- [button] (Start #8): Home Turret *{whenPressed}*
  - hold control until homed?
- [button] (POV Left 270): Turret fine angle adjustment off of baseline
- [button] (POV Right 90): Turret fine angle adjustment off of baseline
  - +/- range **~3** degrees
  - funky start enable processing
- [analog] (Left Trigger #2): Turret jog *{whenHeld}*
  - counter-clockwise at constant preset in code
  - additive to final turret rotation value
  - stays from one shot sequence to next
- [analog] (Right Trigger #3): Turret jog *{whenHeld}*
  - clockwise at constant preset in code
  - additive to final turret rotation value
  - stays from one shot sequence to next
### Elevator
- [analog] (Left Joystick U-D): Elevator Speed (up/down)
  - cancels Fire Pose (but leaves shooter running)



- [button] (??????): Extend Climber *{whenPressed}*
  - limit by encoder or limit switch
  - speed is constant preset in code
- [button] (??????): Retract Climber *{whenHeld}*
   - speed is constant preset in code


## Special
- When some special combo of buttons is pressed, the climber reverses
- Final turret position is based on:
  1. turret robot orientation (buttons 9, 10, 11)
  1. field position (buttons 3, 4, 5)
  1. jogs (buttons 11, 12)
