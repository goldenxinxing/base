package my.generator.collection;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Package: my.generator.collection<br>
 * @ClassName: SetTest.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class SetTest {
    public static void main(String[] args){
        Set<Entity> entities = new HashSet<>();
        Entity entity1 = new Entity("gaoxinxing");
        entities.add(entity1);
        Set<Entity> newEntities = new HashSet<>();
        newEntities.addAll(entities);
        for (Entity newEntity : newEntities) {
            newEntity.setName("gxx");
            System.out.println(newEntity.getName());
        }
        System.out.println("======");
        for (Entity entity : entities) {
            System.out.println(entity.getName());
        }
        System.out.println("======");
        System.out.println(entity1.getName());

       /*gxx
                ======
        gxx
                ======
        gxx*/
    }
}
class Entity{
    public Entity(String name){
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}