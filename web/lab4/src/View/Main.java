package View;

import Model.entity.*;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static class MyThead extends Thread {
        @Override
        public void run() {
            Burse burse = new Burse(1, "New York burse", 1.0);
            List<Action> actionBillList = new ArrayList<>();
            List<Action> actionSmithList = new ArrayList<>();
            actionBillList.add(new Action(1, "Microsoft", 512, 200.00));
            actionSmithList.add(new Action(1, "Oracle", 1024, 100.00));
            Brocker bill = new Brocker(1, "Bill", actionBillList);
            Brocker smith = new Brocker(2, "Smith", actionSmithList);
            Lot lot1 = new Lot(1, "Microsoft", new Action(3, "Microsoft", 512, 200.00), 250.0);
            Lot lot2 = new Lot(2, "Oracle", new Action(4, "Oracle", 256, 100.00), 150.0);
            System.out.println("Hello, Auction!");

            Auction auction1 = new Auction(1, lot1, 0);
            Auction auction2 = new Auction(2, lot2, 0);
            System.out.println(bill);
            System.out.println(smith);

            System.out.println(burse);
            System.out.println(auction1);
            System.out.println(auction2);
            System.out.println("Start, Auction!");
            auction1.setWinnerBrockerId(2);
            auction1.setWinnerBrockerId(1);
            System.out.println(auction1);
            System.out.println(auction2);
            List<Action> actionList1;
            actionList1 = bill.getActionList();
            actionList1.add(lot2.getAction());
            bill.setActionList(actionList1);

            List<Action> actionList2;
            actionList2 = smith.getActionList();
            actionList2.add(lot1.getAction());
            smith.setActionList(actionList2);

            System.out.println(bill);
            System.out.println(smith);
            burse.setIndex(0.8);
            System.out.println(burse);
            System.out.println("Stop auction");

        }
    }

    public static void main(String[] args) {
        Thread thread = new MyThead();
        thread.start();
    }
}
