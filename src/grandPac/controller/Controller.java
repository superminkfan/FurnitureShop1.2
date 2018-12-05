package grandPac.controller;

import grandPac.mainWork.AssemblyShop;
import grandPac.mainWork.BilletShop;
import grandPac.mainWork.Consumer;
import grandPac.materials.*;
import grandPac.order.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.TextArea;


public class Controller extends Inits{

    private AssemblyShop AsShop; // объект нашего класса

    public Controller(){
        this.AsShop = new AssemblyShop(this);
    }

    public TextArea getTA(){
        return textArea;
    }

    public void Click()
    {
        int money = 0;
        int wood = 0;
        int kitchen = 0;
        int tumba = 0;
        int shkaf = 0;
        int taburet = 0;
        int plohoy_taburet = 0;

        try {

            money = Integer.parseInt(money_inp.getText());
            wood = Integer.parseInt(wood_inp.getText());
            kitchen = Integer.parseInt(kitchen_inp.getText());
            tumba = Integer.parseInt(tumba_inp.getText());
            shkaf = Integer.parseInt(shkaf_inp.getText());
            taburet = Integer.parseInt(taburet_inp.getText());
            plohoy_taburet = Integer.parseInt(taburet_plohoi_inp.getText());

        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            money = 10000;
            wood = 20;

        }

        Map<String, Integer> request = new HashMap<>();
        AssemblyShop assemblyShop = new AssemblyShop(this);
        BilletShop billetShop = new BilletShop((double)wood);
        assemblyShop.setItems(Arrays.asList(
                new Kitchen(),
                new Dresser(),
                new Wardrobe(),
                new ChairGood(),
                new ChairBad()));

        billetShop.setMaterials(Arrays.asList(
                new Board(),
                new Beam(),
                new Plate(),
                new Dowel()
        ));

        if(kitchen != 0)
            request.put("Кухня", kitchen);
        if(tumba != 0)
            request.put("Тумба", tumba);
        if(shkaf != 0)
            request.put("Шкаф", shkaf);
        if(taburet != 0)
            request.put("Табурет хороший", taburet);
        if(plohoy_taburet != 0)
            request.put("Табурет плохой", plohoy_taburet);



        Consumer consumer = new Consumer(money);
       /* request.put("Кухня", 1);
        request.put("Шкаф", 1);
        request.put("Тумба", 1);
        request.put("Табурет хороший", 1);*/
        consumer.requestOrder(request, assemblyShop, billetShop);
    }

}
