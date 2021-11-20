package de.mcimpact.missilewars.util;

import de.mcimpact.missilewars.MissileWars;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Set;


public class Timeout<E> {

    int timeoutTicks = 0;

    public Timeout(int ticks) {
        timeoutTicks = ticks;
    }

    private Set<E> eSet;

    public boolean isFree(E e) {
        return eSet.contains(e);
    }

    public void timeout(E e){
        eSet.add(e);
        TimeoutRunable timeoutRunable = new TimeoutRunable(e);
        Bukkit.getScheduler().runTaskAsynchronously(MissileWars.getMissileWars(), timeoutRunable);
    }

    private class TimeoutRunable extends BukkitRunnable {

        private  E e;
        private TimeoutRunable(E e) {
            this.e = e;
        }

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
            try {
                Thread.sleep(timeoutTicks);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

            eSet.remove(e);

        }
    }
}
