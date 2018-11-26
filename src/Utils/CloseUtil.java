package Utils;

public class CloseUtil {

    public static void close(AutoCloseable closeable)  {
        if (closeable!=null){
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
