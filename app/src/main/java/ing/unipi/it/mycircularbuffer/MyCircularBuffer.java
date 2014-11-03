package ing.unipi.it.mycircularbuffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by carmen on 31/10/14.
 */
public class MyCircularBuffer {

    private int[] buffer;
    private int head = 0;
    private int tail = 0;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    private Condition enoughData = lock.newCondition();//ci sono tanti dati quanti ne vuole il consumatore


    public MyCircularBuffer(int dim) {
        buffer = new int[dim];
    }

    public void putAInteger(int x) {
        lock.lock();
        try {
            while(count == buffer.length) notFull.await();
            buffer[tail] = x;
            count++;
            tail = (tail+1)%buffer.length;
            notEmpty.signal();

        } catch (InterruptedException e){}
        finally {
            lock.unlock();
        }
    }

    public int getAInteger() {
        int x=0;
        lock.lock();
        try {
            while (count == 0) notEmpty.await();
            x = buffer[head];
            head = (head+1)%buffer.length;
            count--;
            notFull.signal();
        }catch (InterruptedException e){}
        finally {
            lock.unlock();
        }

        return x;
    }



    public int[] getNIntegers(int num) {
        int[] values = new int[num];
        lock.lock();
        try {
            while(count < num) /*enoughData.await();*/notEmpty.await();
            System.arraycopy(buffer, head, values, 0, num);
            count -=values.length;
            head = (head+num)%buffer.length;
            for(int i = 0; i < num; i++){
                notFull.signal();

            }

        } catch (InterruptedException ex) {}
        finally {
            lock.unlock();
        }

        return values;
    }
}
