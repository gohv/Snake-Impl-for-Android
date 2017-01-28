package georgyhristov.xyz.mrnom.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by gohv on 28.12.16.
 */

public interface FileIO {
     InputStream readAsset(String fileName) throws IOException;
     InputStream readFile(String fileName) throws IOException;
     OutputStream writeFile(String fileName) throws IOException;
}
