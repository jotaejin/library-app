interface FizzBuzz{
    void print(int from, int to);
}

class ConsoleBasedFizzBuzz implements FizzBuzz{

    @Override
    public void print(int from, int to) {
        for(int i=from; i<to; i++){
            if (i % 3 == 0 && i % 5 == 0 ) {
                System.out.println("FizzBuzz");
            }
              else if(i % 3 == 0){
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ConsoleBasedFizzBuzz consoleBasedFizzBuzz = new ConsoleBasedFizzBuzz();
        consoleBasedFizzBuzz.print(1,100);
    }
}
