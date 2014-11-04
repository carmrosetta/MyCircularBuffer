package ing.unipi.it.mycircularbuffer;

import android.util.Log;

/**
 * Created by carmen on 31/10/14.
 */
public class Consumer extends Thread {
    MyCircularBuffer buffer;

    public Consumer(MyCircularBuffer buf) {
        this.buffer = buf;
    }

    public void run() {
       while(true) {
           try {
               Thread.sleep(2000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
            int[] k = buffer.getNIntegers(1000);
        for(int i=0; i < k.length; i++) {
            Log.e("Consumer", System.currentTimeMillis()+": got "+k[i]);

        }

        Log.e("-->", "-----------------------");
           try {
               Thread.sleep(2000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }

         }
    }
}
