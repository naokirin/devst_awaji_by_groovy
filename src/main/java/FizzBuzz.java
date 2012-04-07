package main.java;

public class FizzBuzz {
    public String says(int number) {
        if(number < 1) throw new RuntimeException();
        else if (number%15 == 0) return "FizzBuzz";
        else if (number%3 == 0) return "Fizz";
        else if (number%5 == 0) return "Buzz";
        else return Integer.toString(number);
    }
}
