from controller import Robot, DistanceSensor, Motor

thresh=200
max_speed=6.28
TIME_STEP=64
robot = Robot()

#init devices
ps=[]
psNames=['ps0','ps1','ps2','ps3','ps4','ps5','ps6','ps7']
for i in range(8):
    ps.append(robot.getDevice(psNames[i]))
    ps[i].enable(TIME_STEP)
    
    
#initialize the motors
leftMotor = robot.getDevice('left wheel motor')
rightMotor = robot.getDevice('right wheel motor')
leftMotor.setPosition(float('inf'))
rightMotor.setPosition(float('inf'))
leftMotor.setVelocity(0.0)
rightMotor.setVelocity(0.0)


a=0
# feedback loop:
while robot.step(TIME_STEP) != -1:
    a+=1
    # Reading the sensors: create function for later use
    psValues=[]
    for i in range(8):
        psValues.append(ps[i].getValue())

    #detect obstacles by using an arbitrary value, of say 80
    right_obstacles=psValues[0] > thresh or psValues[1] > thresh or psValues[2] > thresh
    left_obstacles=psValues[5] >thresh or psValues[6] > thresh or psValues[7] >thresh

    #actuator commands
    leftSpeed=0.5*max_speed
    rightSpeed=0.5*max_speed
    if left_obstacles:
       print("Obstacle on the left detected")
       leftSpeed=0.5*max_speed
       rightSpeed=-0.5*max_speed #turn right
       
    elif right_obstacles:
       print("Obstacle on the right detected")
       leftSpeed=-0.5*max_speed #turn left
       rightSpeed=0.5*max_speed
       
    leftMotor.setVelocity(leftSpeed)
    rightMotor.setVelocity(rightSpeed)
