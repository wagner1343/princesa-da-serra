package tasks;

import princesadaserra.java.util.threading.CallbackWithArgument;
import princesadaserra.java.util.threading.Task;

public class TestCountClass {
    static  String valor = "";
    public static void main(String[] args) {


        Task<Integer,String,Integer> task1 = new Task<Integer, String, Integer>() {
            @Override
            protected String execute(Integer argument) {
                while (true) {
                    System.out.println(valor);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        task1.start();
        Task<Integer, Integer, Integer> task = new Task<Integer, Integer, Integer>() {
            @Override
            protected Integer execute(Integer argument) {

                int count = 0;
                while(count < argument){

                    count++;
                    setProgress(count * 100 / argument);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };

        task.setOnProgressChangedCallback((integer) -> {
            System.out.println(integer + "%");
            valor = String.valueOf(integer);
        });

        task.start(10);

    }
}
