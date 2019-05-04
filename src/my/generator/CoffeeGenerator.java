package my.generator;

import java.util.Iterator;

/**
 * @Package: my.generator<br>
 * @ClassName: CoffeeGenerator.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class CoffeeGenerator implements Generator<Coffee>,Iterable<Coffee> {
    private int size = 0;

    public CoffeeGenerator(int size) {
        this.size = size;
    }

    public Coffee next() {
        try {
            return Mocha.class.newInstance();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    class CoffeeIterator implements Iterator<Coffee>{
        int count = size; // *
        public boolean hasNext() {
            return count > 0;
        }

        public Coffee next() {
            count--;
            return CoffeeGenerator.this.next();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args){
        for(Coffee coffee : new CoffeeGenerator(6)){ // *
            System.out.println(coffee);
        }
    }

}
