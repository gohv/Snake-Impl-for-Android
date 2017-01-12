package georgyhristov.xyz.mrnom.Framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by gohv on 28.12.16.
 */

public interface FileIO {
    public InputStream readAsset(String fileName) throws IOException;
    public OutputStream writeFile(String fileName) throws IOException;
}
