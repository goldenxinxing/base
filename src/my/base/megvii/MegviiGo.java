package my.base.megvii;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Package: my.base.megvii<br>
 * @ClassName: MegviiGo.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class MegviiGo {

  public static void main(String[] args) {
    IntStream.iterate(15, i -> i - 1).
        limit(30).
        mapToObj(y ->
            String.join("",
                IntStream.range(-30, 30)
                    .mapToObj(x ->
                        Math.pow(Math.pow(x * 0.05, 2) + Math.pow(y * 0.1, 2) - 1, 3)
                            - Math.pow(x * 0.05, 2) * Math.pow(y * 0.1, 3) <= 0
                            ? "ILoveUMegvii".split("")[Math.floorMod(x - y, 12)] : " "
                    )
                    .collect(Collectors.toList())
            )
        ).forEach(System.out::println);
  }
}
