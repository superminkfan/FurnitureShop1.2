package grandPac.mainWork;

import grandPac.materials.Item;

import java.util.*;

public class BilletShop {
    private double wood;

    private List<Item> materials = new ArrayList<>();


    public BilletShop(double wood){
        this.wood = wood;
    }

    public void setMaterials(List<Item> materials){this.materials = materials;}

    public Map<String, Integer> doMaterials(Map<String, Integer> RequestMaterials){
        double count_of_wood = 0;
        Iterator it = RequestMaterials.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry) it.next();
            int n = (Integer)pair.getValue();
            Item material = materials.stream().
                    filter(item -> pair.getKey().
                    equals(item.getName())).
                    findFirst().orElse(null);
            if(material == null){
                System.out.println("Такого материала нет");
            }
            count_of_wood += material.getWeidth() * n;
        }

        if(this.wood > count_of_wood){
            this.wood -= count_of_wood;
            return RequestMaterials;
        }
        else {
            Map<String, Integer> new_RequestMaterials = new HashMap<>();
            for (Item elem : materials) {
                double elem_wood = elem.getWeidth() * RequestMaterials.get(elem.getName());
                if (RequestMaterials.containsKey(elem.getName()) && (elem_wood <= this.wood)) {
                    for (int i = 1; i <= RequestMaterials.get(elem.getName()); i++)
                        if (elem_wood <= this.wood) {
                            this.wood -= elem.getWeidth();
                            new_RequestMaterials.put(elem.getName(), i);
                        }
                    else
                            break;
                }
            }
            return new_RequestMaterials;
        }
    }
}
