package game.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class ResourceFacade {
    private static volatile ResourceFacade instance;

    private ClassLoader classLoader;
    private CsvReader csvReader;

    private ResourceFacade() {
        classLoader = getClass().getClassLoader();
        csvReader = new CsvReader();
    }

    public static ResourceFacade getInstance(){
        if (instance == null) { // 1차 체크 (성능 최적화)
            synchronized (ResourceFacade.class) {
                if (instance == null) { // 2차 체크 (스레드 안전)
                    instance = new ResourceFacade();
                }
            }
        }
        return instance;
    }


    public URL getResource(String path){
        return classLoader.getResource(path);
    }

    public URI getResourceURI(String path) throws URISyntaxException {
        return getResource(path).toURI();
    }

    public Image getImage(String path) throws IOException {
        URL url = getResource(path);
        return ImageIO.read(url);
    }

    public List<List<String>> parseCSV(String path) throws URISyntaxException {
        URI uri = getResourceURI(path);
        return csvReader.parseCsv(uri);
    }
}
