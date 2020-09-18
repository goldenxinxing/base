package my.base;

import java.util.Collections;
import java.util.HashMap;

public class PlaceHolderTest {
    public static void main(String[] args) {
        // 这种不支持
        String str = "i'm {0},who are you!{1}";
        String newStr = String.format(str, "gaoxinxing", "hei");
        byte[][] environ = ProcessEnvironment.environ();
        theEnvironment = new HashMap<>(environ.length/2 + 3);
        // Read environment variables back to front,
        // so that earlier variables override later ones.
        for (int i = environ.length-1; i > 0; i-=2)
            theEnvironment.put(ProcessEnvironment.Variable.valueOf(environ[i-1]),
                    ProcessEnvironment.Value.valueOf(environ[i]));

        theUnmodifiableEnvironment
                = Collections.unmodifiableMap
                (new ProcessEnvironment.StringEnvironment(theEnvironment));
        System.out.println(newStr);
    }
}
