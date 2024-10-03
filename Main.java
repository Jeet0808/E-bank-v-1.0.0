package ebank;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        System.out.println("You are lucky that you are going to see");
        System.out.println("World's first E-Bank by JLSS & company");
        System.out.print("Just a moment.");

        CountDownLatch latch = new CountDownLatch(1);
        // here 1 shows that the timer fn  runs only ones
        Interviewwait.startInterviewwaitTimer(latch); // Note -> after user enters all ans this loding or timer fn sart to print a loading baar
        //and till the loding bar is printing we should stop our other tasks so that we use countdown and timer

        try {
            latch.await();//it stops all the threds till this timer runs


        } catch (InterruptedException e) {
            System.out.println("Interview process interrupted.");
        }


        Bank bank = new Bank();
        bank.displayapp();

    }


    static class Interviewwait {
        public static void startInterviewwaitTimer(CountDownLatch latch) {


            Timer timer = new Timer();
            int delay = 0;
            int period = 100;
            int[] counter = {21};

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (counter[0] > 0) {
                        System.out.print(".");
                        counter[0]--;
                    } else {
                        System.out.println(" ");
                        timer.cancel();
                        latch.countDown();
                        // notes -> countdoun() means releas threads
                    }
                }
            };

            timer.scheduleAtFixedRate(task, delay, period);
        }

    }
}