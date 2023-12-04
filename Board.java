public class Board {


    static Card[] board = new Card[36];

    String[] cardNamesList = {"GO","Mediterranean Avenue","Community Chest","Baltic Avenue", "Station1","Oriental Avenue","Chance","Vermont Avenue","Connecticut Avenue", "Jail",
            "St. Charles Place","States Avenue","Station2","Virginia Avenue", "St James Place","Community Chest","Tennessee Avenue","New York Avenue","Free parking",
            "Kentucky Avenue","Indiana Avenue","Chance","Illinois Avenue","Atlantic Avenue", "Station3","Ventnor Aenue Street","Marvin Gardens", "Go to Jail",
            "Pacific Avenue", "North Carolina Avenue","Community Chest","Pennsylvania Avenue","Station4","Chance","Park Place","Boardwalk"};


    int[] cardPriceList = {0,60,0,60,100,100,0,100,120,0,
                           140,120,100,160,180,0,180,200,0,
                           220,220,0,240,130,100,260,280,0,
                           300,300,0,150,100,0,350,250};

    int[] houseList = {0,50,0,50,70,70,0,70,80,0,
            100,70,60,100,110,0,110,140,0,
            160,160,0,170,70,60,180,180,0,
            190,190,0,80,60,0,250,160};


    int[] cardrentList = {0,35,0,40,55,50,0,65,70,0,
                          70,70,80,90,115,0,90,130,0,
                          125,150,0,120,75,80,130,180,0,
                          190,150,0,90,80,0,180,145};





    Card[] fillBoard(){
        for(int i=0;i<36;i++){

            Card card = new Card(cardNamesList[i],cardPriceList[i],cardrentList[i], houseList[i]);
            board[i]=card;
        }
        return board;
    }







}
