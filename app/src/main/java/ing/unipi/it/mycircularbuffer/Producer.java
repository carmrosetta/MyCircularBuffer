package ing.unipi.it.mycircularbuffer;

import android.util.Log;

/**
 * Created by carmen on 31/10/14.
 */
public class Producer extends Thread {
    MyCircularBuffer buffer;

    public Producer(MyCircularBuffer buf) {
        this.buffer = buf;
    }

    public void run() {
        //int[] k = {1,2,3,4,5,6,7,8};


               //for(int i = 0; i < k.length; i++)
        int i = 1;
        while (i<10001) {

            buffer.putAInteger(i);
            i++;
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }


        }
    }
