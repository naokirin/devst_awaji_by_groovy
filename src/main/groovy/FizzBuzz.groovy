package main.groovy

import main.java.*

class FizzBuzz implements features.FizzBuzz {
    String fizzBuzz(int number) {
        if(number < 1) throw new RuntimeException()
        else if (number%15 == 0) "FizzBuzz"
        else if (number%3 == 0) "Fizz"
        else if (number%5 == 0) "Buzz"
        else number.toString()
    }
}