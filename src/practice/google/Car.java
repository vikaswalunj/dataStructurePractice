/*
package practice.google;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

// formula 1 car problem to support multiple functionalities of race described in link given below
// https://careercup.com/question?id=5644349121495040

public class Car{
    public static final double HANDLING_FACTOR= .8d;

    public int team;
    public double speed;
    public double nextSpeed;
    public double topSpeed;
    public double accel;
    public boolean hasNitro;
    public double positionFromEnd;
    public double nextPositionFromEnd;

    public Car(int i, double trackLength){
        this.team = i;
        //meters per 2 sec
        this.topSpeed = (150d + 10d * (double)i)/1.8d;
        // meters per 2 sec2
        this.accel = 2 * (double)i;
        this.accel *= this.accel;
        this.hasNitro = true;
        this.positionFromEnd = trackLength + 200d * (double)i;
    }

    public void update(){
        this.speed = this.nextSpeed;
        this.positionFromEnd = this.nextPositionFromEnd;
    }


 
public static Collection<FinishInformation> getCompletionData(int numTeams, double trackLength){
    //initialize the teams
    ArrayList<Car> cars = new ArrayList<Car>(numTeams);
    for(int i = 0; i < numTeams; i++){
        cars.add(new Car(i, trackLength));
    }

    //initialize the output
    ArrayList<FinishInformation> results = new ArrayList<FinishInformation>();

    Comparator<Car> carSorter = new Comparator<Car>(){
        @Override
        public int compare(Car c1, Car c2){
            return (int) (c1.positionFromEnd - c2.positionFromEnd);
        }
    }

    //while there are cars still racing
    int iterations = 0;
    while(cars.size() > 0){
        //sort the list based on the positions.
        Collections.sort(cars, carSorter);
        
        //now, nearby cars are used for the Handling checks and the last car will always use nitro
        double lastNearbyCar;
        for(int i = 0; i < cars.size(); i++){
            Car car = cars.get(i);
            //check for nearby-ness
            boolean notNearby = true;
            if(i > 0){
                if(Math.abs(cars.get(i-1).positionFromEnd - car.positionFromEnd) <= 10d){
                    notNearby = false;
                }
            }
            if(i < cars.size() -1){
                if(Math.abs(cars.get(i+1).positionFromEnd - car.positionFromEnd) <= 10d){
                    notNearby = false;
                }
            }

            //compute next speed
            if(notNearby){
                car.nextSpeed = car.speed + car.accel;
                if(car.nextSpeed > car.topSpeed){
                    car.nextSpeed = car.topSpeed;
                }
            }
            else{
                car.nextSpeed = car.speed * Car.HANDLING_FACTOR;
            }
             
            //compute next position
            car.nextPositionFromEnd -= car.nextSpeed;
        }

        //compute usage of nitro
        Car lastCar = cars.get(cars.size() -1);
        if(lastCar.hasNitro){
            lastCar.hasNitro = false;
            lastCar.nextSpeed = lastCar.speed * 2d;
            if(lastCar.nextSpeed > lastCar.topSpeed){
                lastCar.nextSpeed = lastCar.topSpeed;
            }
        }

        //complete the movements
        iterations++;
        for(int i = 0; i < cars.size(); i++){
            cars.get(i).update();
        }

        //process finishers
        Car car = cars.get(0);
        while(car.positionFromEnd <= 0d){
            removeIndex++;
            results.add(new FinishInformation(car.team, iterations<<1, car.speed));
            cars.remove(0);
            car = cars.get(0);
        }
    }
    return results;
}

class FinishInformation{
    public int team;
    public int finishTime;
    public double finalSpeed;

    public FinishInformation(int team, int finishTime, double finalSpeed){
        this.team = team;
        this.finishTime = finishTime;
        this.finalSpeed = finalSpeed;
    }
}

*/
