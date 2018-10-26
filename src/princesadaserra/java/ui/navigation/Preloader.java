package princesadaserra.java.ui.navigation;

import javafx.scene.Parent;
import princesadaserra.java.ui.views.ScenesTypes;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

interface Loader<LoadArgument, LoadResult>{
    LoadResult load(LoadArgument loadArgument);
}

public class Preloader<LoadArgument, LoadResult> {
    private Map<LoadArgument, LoadResult> sceneMap;
    private Loader<LoadArgument, LoadResult> loader;
    private ArrayList<Thread> threads;

    public Preloader(Loader<LoadArgument, LoadResult> loader) {
        this.loader = loader;
        threads = new ArrayList<>();
        sceneMap = new HashMap<>(ScenesTypes.values().length, 1);
    }

    public void preload(LoadArgument loadArgument){
        PreloaderWorker worker = new PreloaderWorker(loadArgument, this);

        Thread t = new Thread(worker, loadArgument.getClass().getName() + " Worker - " + loadArgument);
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();

        threads.add(t);
    }

    public LoadResult load(LoadArgument argument) {
        return isPreloaded(argument)? pickFromPool(argument) : loadFxml(argument) ;
    }

    public boolean isPreloaded(LoadArgument argument) {
        return sceneMap.containsKey(argument);
    }

    public void addToPool(LoadArgument Argument, LoadResult p) {
        sceneMap.put(Argument, p);
    }

    private LoadResult pickFromPool(LoadArgument argument) {
        LoadResult r = sceneMap.remove(argument);
        preload(argument);
        return r;
    }

    public LoadResult loadFxml(LoadArgument argument) {
        return loader.load(argument);
    }

    class PreloaderWorker implements Runnable{
        private LoadArgument argument;
        private Preloader<LoadArgument, LoadResult> preloader;

    public PreloaderWorker(LoadArgument argument, Preloader<LoadArgument, LoadResult> preloader) {
            this.argument = argument;
            this.preloader = preloader;
        }

        @Override
        public void run() {
            preloader.addToPool(argument, preloader.loadFxml(argument));
        }
    }

}

