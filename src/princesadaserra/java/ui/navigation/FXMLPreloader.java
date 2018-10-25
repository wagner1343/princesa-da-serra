package princesadaserra.java.ui.navigation;

import princesadaserra.java.ui.views.ScenesTypes;

import java.util.ArrayList;

public class FXMLPreloader {
    private Navigator navigator;
    private ArrayList<Thread> threads;

    public FXMLPreloader(Navigator navigator) {
        this.navigator = navigator;
        threads = new ArrayList<>();
    }

    public void load(ScenesTypes scene){
        FXMLPreloaderWorker worker = new FXMLPreloaderWorker(scene, navigator);

        Thread t = new Thread(worker);
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();

        threads.add(t);
    }
}

class FXMLPreloaderWorker implements Runnable{
    private ScenesTypes scene;
    private Navigator navigator;

    public FXMLPreloaderWorker(ScenesTypes scene, Navigator navigator) {
        this.scene = scene;
        this.navigator = navigator;
    }

    @Override
    public void run() {
        navigator.addToPool(scene, navigator.loadFxml(scene));
    }
}
