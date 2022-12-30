from controller import Robot


def follow_wall(robot):
    kp=0.01
    r=110
    timestep = 64
    max_hiz=6.28 
    
    l_motor=robot.getDevice("left wheel motor")
    r_motor=robot.getDevice("right wheel motor")
    l_motor.setPosition(float("inf"))
    r_motor.setPosition(float("inf")) 
    l_motor.setVelocity(0.0)
    r_motor.setVelocity(0.0)
    yakinlik_sensorleri=[]

    for i in range (8):
        sensor_adi = "ps"+str(i)
        yakinlik_sensorleri.append(robot.getDistanceSensor(sensor_adi))
        yakinlik_sensorleri[i].enable(timestep)

    while robot.step(timestep) != -1:
        s1 = yakinlik_sensorleri[1].getValue()
        s2 = yakinlik_sensorleri[2].getValue()
        s3 = yakinlik_sensorleri[3].getValue()
        s_ort= (s1+s2+s3)/3
        
        ps_on_1 = yakinlik_sensorleri[7].getValue()
        ps_on_2 = yakinlik_sensorleri[0].getValue()
        ps_on_ort = (ps_on_1 + ps_on_2)/2
        
        e = r-s_ort
        P = kp*e
        c = 0.6
        
        print(ps_on_1)
        
        if e>40:
            print("правильно")
            r_motor.setVelocity(c*max_hiz*P)
            l_motor.setVelocity(0.8*max_hiz)
        
        if e<20:
            print("оставил")
            r_motor.setVelocity(0.8*max_hiz)
            l_motor.setVelocity(c*max_hiz*P)
        
        if e>20 and e<=40:
            print("прямой")
            r_motor.setVelocity(max_hiz)
            l_motor.setVelocity(max_hiz)
        
        if ps_on_ort>110:
            print("десять стен налево")
            r_motor.setVelocity(max_hiz)
            l_motor.setVelocity(-max_hiz)


if __name__=="__main__":
    robot=Robot()
    follow_wall(robot)
