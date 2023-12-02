import java.util.Random;

public class Card {
    String name;
    int costOfBuy;
    String color;
    boolean house = false;
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
                str = "Chance" + "\n" + "You will get a random chance card\n";
                return str;
            case "Community Chest":
                str = "Community Chest" + "\n" + "You will get a random Card from Community chest \n";
                return str;

            case "GO":
                str = "You are at the start\n";
                return str;
            case "Jail":
                str = "Weclome to the jail. Have a good visit \n";
                return str;
            case "Go to Jail":
                str = "You have been Caught, GO TO JAIL\n";
                return str;

            case "Free parking":
                str = "This is a free parking\n";
                return str;

            default:
                str = "Name of the card:" + this.name + "\n" + "Cost of the card:" + this.costOfBuy + "\n" +"Rent from this property:"+this.rent+"\n"+ "Color of the card:" + this.color + "\n";
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
        int x = random.nextInt(3)+1;
        switch (x) {
            case 1:
                String[] whatHappened = {"Dentist Appointment: Pay 100$ to bank\n", "Won Lottery: Take 200$ from Bank\n"
                        , "Car Crash: Give bank 50$\n", "Car Crash: Take 50$ from bank\n"
                        , "You found 20$ on Road\n", "Bank Tax Return: 60$\n"
                        ,"Illegal Parking: Pay 20$\n","You have won a crossword competition. Collect $40\n"};

                int[] whatHappenedMoney = {-100, 200, -50, 50, 20, 60,-20, 40};
                int y = random.nextInt(whatHappened.length);
                System.out.print(whatHappened[y]);
                if (whatHappenedMoney[y] > 0) {
                    p.increasePlayerMoney(whatHappenedMoney[y]);
                } else {
                    p.reducePlayerMoney(whatHappenedMoney[y]);
                }
                break;
            case 2:
                int z2 = random.nextInt(2)+1;
                switch (z2) {
                    case 1:
                        System.out.println("Congratulation! You got a free Get out Jail pass");
                        p.jailPass += 1;
                        break;
                    case 2:
                        System.out.print("Go to the Starting Position\n");
                        p.position = 0;
                        break;
                }

            case 3:

                if(p.position>10) {
                    int z = random.nextInt(3)+1;
                    switch (z){

                        case 2:
                            System.out.print("Go 3 Step back\n");
                            p.position-=3;
                            break;
                        case 3:
                            System.out.print("Go 4 Step back\n");
                            p.position-=4;
                            break;
                        case 1:
                            System.out.print("Go 2 Step back\n");
                            p.position-=2;
                            break;


                    }

                } else if (p.position<10) {
                    int z = random.nextInt(4)+1;
                    switch (z) {
                        case 1:
                            p.position = 9;
                            System.out.print("Go To Jail and Pay 40$\n");
                            p.reducePlayerMoney(40);
                            break;
                        case 2:
                            System.out.print("Go 3 Step forward\n");
                            p.position += 3;
                            break;
                        case 3:
                            System.out.print("Go 4 Step forward\n");
                            p.position += 4;
                            break;
                        case 4:
                            System.out.print("Go 2 Step forward\n");
                            p.position += 2;
                            break;

                    }
                }
        }
    }
    void buildHouse(Player p){
        p.reducePlayerMoney(this.costOfhouse);
        this.house=true;
        this.rent+=this.rent/2;
        System.out.println("House has been build");
    }
}