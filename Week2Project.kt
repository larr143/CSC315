
import kotlin.math.ln

// The class where I create the data for the proccesses 
data class Process( val timeRequested:Double, val timeTillNextProccess:Double )

fun main() {

    // this is where I store waiting times 
    var timeWaitingInQueue = DoubleArray(10) {0.0}

    var timeInQueue = 0.0

    val specifiedMean = 3.0

    var index = 0

    var sum = 0.0



// these next lines until line 28 is where I make the random exponential number with the mean of 3 then store it in a list called queue
    val rng = kotlin.random.Random(System.nanoTime())

    val makeExpRNG =
    { mean:Double -> { n:Int -> -mean * ln(rng.nextDouble()) }}

    val expRNG: (Int) -> Double = makeExpRNG( specifiedMean )

    val elementInitializer: (Int) -> Process =
        {_:Int -> Process(expRNG(0), expRNG(0))}

    val queue = List<Process>(10, elementInitializer)


// to print the list of processes if needed for debuging 
//    for( p in queue ) {
//        println( p )
//    } // for


// this for loop does all of the calculations for the queue and uses the index variable to keep the index 
    for(i in queue){
        

        // this if else checks if the time requested is lower than the time untill the next proccess arives if it is less then it sets the queue to zero 
        // if not it calculates the time thats going to be in the queue for the next proccess 
        if(queue[index].timeRequested + timeInQueue < queue[index].timeTillNextProccess ) {

            timeWaitingInQueue[index] = 0.0 

            timeInQueue = 0.0

        }else{

            timeWaitingInQueue[index] = queue[index].timeRequested - queue[index].timeTillNextProccess + timeInQueue            

            timeInQueue = timeInQueue + queue[index].timeRequested - queue[index].timeTillNextProccess 

        }

        index++

    }
    
// to print the list of time waiting for debugging  if needed 
//    for(p in timeWaitingInQueue){
//        println(p)
//    }


// here is where I add up the array 
    for (num in timeWaitingInQueue) {
        sum += num
    }
// this is where i find the mean of the array of time waiting in queue 
    var mean = sum / 10

// these next three print lines are where it finds the max and the min then prints them out as well as the mean 
    println("The mean waiting time is: " + mean )

    println("The minimum waiting time is: " + timeWaitingInQueue.minOrNull())
    println("The maximum waiting time is: " + timeWaitingInQueue.maxOrNull())

} // main() 