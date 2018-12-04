package grandPac.order;

import java.util.HashMap;
import java.util.Map;

public class Furniture {
    protected int price;
    protected String name;
    protected Map<String, Integer> furniture = new HashMap<>();

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getFurniture(){
        return this.furniture;
    }


}
