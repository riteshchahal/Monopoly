import java.util.Random;

public class Card {
    String name;
    int costOfBuy;
    String color;
    int Houses = 0;
    int rent;
    int costOfhouse;
    String owner = "Bank";

    String type;

    Card(String name, int costOfBuy, int rent, String color) {
        this.name = name;
        this.costOfBuy = costOfBuy;
        this.rent = rent;
        this.color = color;
        type = typeOfCard(name);


    }


    public String toString() {
        String str = "";
        switch (name) {
            case "Chance":
                str = "Chance" + "\n" + "Press X to Choose a Chance Card \n";
                return str;
            case "Community Chest":
                str = "Community Chest" + "\n" + "Press X to Choose a Card from Community chest \n";
                return str;

            case "GO":
                str = "Congratulation for complete the round. \nHere is 200$ \n ";
                return str;
            case "Jail":
                str = "Weclome to the jail. Have a good visit \n ";
                return str;
            case "Go to jail":
                str = "You have been Caught, GO TO JAIL\n";
                return str;

            case "Free parking":
                str = "This is a free parking \n";
                return str;

            default:
                str = "Name of the card:" + this.name + " \n" + "Cost of the card:" + this.costOfBuy + " \n" + "Color of the card:" + this.color + " \n";
                return str;
        }


    }


    static String typeOfCard(String name) {
        switch (name) {
            case "Chance":
                return "chance";

            case "Community Chest":
                return "communityChest";

            case "GO":
                return "start";

            case "Jail":
                return "jail";

            case "Go to Jail":
                return "goToJail";

            case "Free parking":
                return "freeParking";

            default:
                return "countryCard";
        }


    }

    void chanceResult(Player p) {
        Random random = new Random();
        int x = random.nextInt(3);
        switch (x) {
            case 0:
                String[] whatHappened = {"Dentist Appointment: Pay 100$ to bank\n", "Won Lottery: Take 200$ from Bank\n"
                        , "Car Crash: Give bank 50$\n", "Car Crash: Take 50$ from bank\n"
                        , "You found 20$ on Road\n", "Bank Tax Return: 60$\n"
                        ,"Illegal Parking: Pay 20$","You have won a crossword competition. Collect $40\n"};


                int[] whatHappenedMoney = {-100, 200, -50, 50, 20, 60,20, 40};
                int y = random.nextInt(whatHappened.length);
                System.out.print(whatHappened[y]);
                if (whatHappenedMoney[y] > 0) {
                    p.increasePlayerMoney(whatHappenedMoney[y]);
                } else {
                    p.reducePlayerMoney(whatHappenedMoney[y]);
                }
            case 1:
                p.jailPass += 1;

            case 2:
                int z = random.nextInt(6);
                System.out.printf("Value of z is:%d",z);
                switch (z) {
                    case 0:
                        System.out.print("Go to the Starting Position\n");
                        p.position = 0;
                    case 1:
                        p.position = 9;
                        System.out.print("Go To Jail and Pay 40$\n");
                        p.reducePlayerMoney(40);
                    case 2:
                        System.out.print("Go 3 Step back\n");
                        p.position-=3;
                    case 3:
                        System.out.print("Go 4 Step forward\n");
                        p.position+=4;
                    case 4:
                        System.out.print("Go 2 Step forward\n");
                        p.position+=2;

                    case 5:
                        System.out.print("Go 5 Step back\n");
                        p.position-=5;

                }


        }
    }
}