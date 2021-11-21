package de.mcimpact.missilewars.util;

import org.bukkit.scheduler.BukkitRunnable;

public abstract class Timer implements Runnable {

    public Timer(int seconds) {
        value = seconds;
    }

    public void start() {
        running = true;
        run();
    }

    public void pause() {
        running = false;
    }

    public void complete() {
        running = false;
        finish();
    }

    public void abort(){
        running = false;
        abort(value);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    private boolean running;
    private int value = 0;


    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        if(running)
        try {
            if(value < 1) {
                finish();
                return;
            }
            Thread.sleep(1000);
            value--;
            update(value);
            run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract void update(int value);
    protected void finish() {};
    protected void abort(int finalvalue){}
}
