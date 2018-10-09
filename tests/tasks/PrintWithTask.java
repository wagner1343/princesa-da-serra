package tasks;

import princesadaserra.java.tasks.Task;

import java.util.Scanner;

public  class PrintWithTask {

    public static void main(String[] args){

        Task task = new Task() {

            @Override
            public void execute() {
                while(isExecuting()){
                    System.out.println("Iam executing");
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        task.addOnExecutingCallback(() -> {
            System.out.println("Iniciando execução");
        });

        task.addOnFinishCallback(() -> {
            System.out.println("Finished");
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

        task.addOnStatusChangedCallback(() -> {
            System.out.println("Status changed");
        });

        task.start();

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
