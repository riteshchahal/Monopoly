import java.util.Random;

public class Card {
    String name;
    int costOfBuy;
    String color;
    int numOfHouses = 0;
    int rent;
    int costOfhouse;
    String owner = "Bank";

    String type;

    Card(String name,int costOfBuy, int rent, String color){
        this.name = name;
        this.costOfBuy = costOfBuy;
        this.rent = rent;
        this.color = color;
        type = typeOfCard(name);


    }



    public String toString(){
        String str = "";
        switch(name) {
            case "Chance":
                str = "Chance" + "\n"+"Press X to Choose a Chance Card \n";
                return str;
            case "Community Chest":
                str = "Community Chest" + "\n"+"Press X to Choose a Card from Community chest \n";
                return str;

            case "GO":
                str = "Congratulation for complete the round. \nHere is 200$ \n ";
                return str;
            case "Just Vising Jail":
                str = "Weclome to the jail. Have a good visit \n ";
                return str;
            case "Go to jail":
                str = "You have been Caught, GO TO JAIL\n";
                return str;

            case "Free parking":
                str = "This is a free parking \n";
                return str;

            default:
                str = "Name of the card:"+this.name+" \n"+"Cost of the card:"+ this.costOfBuy+" \n"+"Color of the card:"+this.color+" \n";
                return str;
        }


    }


    static String typeOfCard(String name){
        switch(name) {
            case "Chance":
                return "chance";
            case "Community Chest":
                return "communityChest";
            case "GO":
                return "start";
            case "Jail":
                return "jail";
            case "Go to jail":
                return "goToJail";
            case "Free parking":
                return "freeParking";
            default:
                return "countryCard";
        }


    }



}
