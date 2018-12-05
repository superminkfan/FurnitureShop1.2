package grandPac.mainWork;

import grandPac.controller.Controller;
import grandPac.order.Furniture;

import java.util.*;

public class AssemblyShop {
    private List<Furniture> items = new ArrayList<>();
    private Controller controller;

    public AssemblyShop(Controller controller) {
        this.controller = controller;

    }

    public void setItems(List<Furniture> items) {
        this.items = items;
    }

    public void doService(Consumer c, Map<String, Integer> request, BilletShop billetShop){

      controller.getTA().appendText("");

        int total = 0;
        Iterator it = request.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            int n = (Integer)pair.getValue();
            Furniture i = items.stream().filter(item-> pair.getKey().equals(item.getName())).findFirst().orElse(null);
            if(i == null){
                controller.getTA().appendText("Такой мебели нет\n");

                System.out.println("Такой мебели нет");
            }
            total += i.getPrice() * n;
        }

        if(total > c.getMoney()){
            controller.getTA().appendText("У вас не хватает денег\n");

            System.out.println("У вас не хватает денег");
        }
        else {
            Map<String, Integer> RequestMaterials = new HashMap<>();

            Iterator iter = request.entrySet().iterator();
            while(iter.hasNext()){
                Map.Entry pair = (Map.Entry)iter.next();
                int n = (Integer)pair.getValue();
                Furniture mat = items.stream().
                        filter(i->i.getName().
                                equals(pair.getKey())).
                        findFirst().orElse(null);
                if(mat == null){
                    controller.getTA().appendText("Такой мебели нет\n");

                    System.out.println("Такой мебели нет");
                }


                Map<String, Integer> material = new HashMap<>();
                material = mat.getFurniture();
                for(Map.Entry<String, Integer> elem: material.entrySet()){
                    if(RequestMaterials.containsKey(elem.getKey())){
                        int value = RequestMaterials.get(elem.getKey());
                        RequestMaterials.put(elem.getKey(), value + elem.getValue() * n);
                    }
                    else{
                        RequestMaterials.put(elem.getKey(), elem.getValue());
                    }
                }
            }
            controller.getTA().appendText("Ваш заказ: " + total + " " + request.entrySet() + "\n");

            System.out.println("Ваш заказ: " + total + " " + request.entrySet());
            controller.getTA().appendText("Требуется такое количество деталей: " + RequestMaterials + "\n");

            System.out.println("Требуется такое количество деталей: " + RequestMaterials);


            Map<String, Integer> DoneMaterials = new HashMap<>();
            DoneMaterials = billetShop.doMaterials(RequestMaterials);
            if(DoneMaterials == RequestMaterials){
                c.setMoney(c.getMoney()-total);
                controller.getTA().appendText("Ваш заказ равен: " + total + "\n");

                System.out.println("Ваш заказ равен: " + total);
                controller.getTA().appendText("Со счета списано: " + total + " рублей" + "\n");

                System.out.println("Со счета списано: " + total + " рублей");
            }
            else{
                 Map<String, Integer> new_request = new HashMap<>();
                 total = 0;
                 for(Furniture item: items){
                     if(request.containsKey(item.getName())){
                         Map<String, Integer> mat = item.getFurniture();
                         boolean flag = true;
                         for(Map.Entry<String, Integer> elem: mat.entrySet()){
                             if(DoneMaterials.containsKey(elem.getKey()) && DoneMaterials.get(elem.getKey()) >= elem.getValue()){
                                 continue;
                             }
                             else
                                 flag = false;
                         }
                         if(flag){
                             new_request.put(item.getName(), request.get(item.getName()));
                             total += item.getPrice();
                         }
                     }
                 }
                if (total > 0) {
                    controller.getTA().appendText("Древесиы хватает на: " + new_request + "\n");

                    System.out.println("Древесиы хватает на: " + new_request);
                    controller.getTA().appendText("Со счета снято " + total + " рублей\n");

                    System.out.println("Со счета снято " + total + " рублей");
                    c.setMoney(c.getMoney() - total);
                } else {
                    controller.getTA().appendText("У нас не хватает материалов ни на какую мебель\n");

                    System.out.println("У нас не хватает материалов ни на какую мебель");
                }
            }
        }

    }

}

