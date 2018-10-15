package tasks;

import princesadaserra.java.util.threading.Task;

public class CountTask extends Task<Integer, Integer, Integer> {
    @Override
    protected Integer execute(Integer argument) {
        int count = 0;
        while(count < argument){
            count++;
            setProgress(count * 100/argument);
        }
        return count;
    }
}
