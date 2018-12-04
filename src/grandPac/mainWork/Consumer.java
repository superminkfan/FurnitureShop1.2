package grandPac.mainWork;


import java.util.Map;

public class Consumer {
    private int money;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Consumer(int money){this.money = money;}

    public void requestOrder(Map<String, Integer> request, AssemblyShop assemblyShop, BilletShop billetShop){
        assemblyShop.doService(this, request, billetShop);
    }

}
