package tasks;

import princesadaserra.java.util.threading.Task;

import java.util.Scanner;

public  class PrintWithTask {

    public static void main(String[] args){

        Task<String, Integer, Integer> task = new Task<String, Integer, Integer>() {

            @Override
            public Integer execute(String argument) {
                return Integer.parseInt(argument);
            }
        };

        task.addOnExecutingCallback(() -> {
            System.out.println("Iniciando execução");
        });

        task.addOnFinishCallback((result) -> {
            System.out.println("Result is " + result);
        });

        task.addOnCanceledCallback(() -> {
            System.out.println("Canceled");
        });

        task.addOnSuccessCallback(() -> {
            System.out.println("Success");
        });

        task.addOnFailedCallback(() -> {
            System.out.println("Failed");
        });

        task.addOnStatusChangedCallback((status) -> {
            System.out.println("Status changed to " + status.toString());
        });

        task.start("123");

        while(true) {
            Scanner s = new Scanner(System.in);

            String in = s.nextLine();
            System.out.println("in: " + in);

            switch (in){
                case "cancel":
                    task.setCanceled();
                    break;
                case "finish":
                    task.setSuccess();
                    break;
                case "failed":
                    task.setFailed();
                    break;
            }
        }
    }
}
