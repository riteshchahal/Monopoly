import java.util.*;

public class Simulation {
    void continuePlay(){
        Card[] board = makeBoard();
        int numOfplayer = 2;
        Player[] playersList = new Player[numOfplayer];


        Player p1 = new Player("Ritesh",1);
        playersList[0]=p1;
        Player p2 = new Player("Aryan",2);
        playersList[1]=p2;


        while(p1.playerMoney!=0 && p2.playerMoney!=0){
            for(int j = 0;j<numOfplayer;j++){
                Player currentPlayer = playersList[j];

                System.out.printf("%s Turn.\n",currentPlayer.name);

                turn(currentPlayer, board,playersList);

                System.out.printf("Current Player-%s\nPosition:%d\nMoney:%d\n",currentPlayer.name,currentPlayer.position,currentPlayer.playerMoney);
                System.out.println();

            }
            if(p1.bankcrupt){
                System.out.printf("%s don't have sufficient fund, Therefore he has to sell his property\n",p1.name);
                for(Card c: p1.playerCardList){
                    System.out.println(c);
                }Random random = new Random();
                int rand = random.nextInt(p1.playerCardList.size());



            }
        }


    }






    int rollDice(){
        Random random = new Random();
        int x = random.nextInt(6)+1;
        int y = random.nextInt(6)+1;
        System.out.printf("Dice one is %d\n",x);
        System.out.printf("Dice two is %d\n",y);
        return x+y;
    }
    void turn(Player p, Card[] board,Player[] playersList){
        Scanner scan = new Scanner(System.in);
        System.out.printf("Type of Strategy:%s\n",p.gameType);
        int diceNumber = rollDice();


        int newP = p.position+diceNumber;

        if(newP>=36){
            p.position= newP-36;
            p.increasePlayerMoney(200);
        }else{
            p.position+=diceNumber;
        }
        if(Objects.equals(board[p.position].type, "countryCard")){
            System.out.print(board[p.position]);
            if(Objects.equals(board[p.position].owner, "Bank")) {

                boolean buy = p.buyOrNot(p,board[p.position]);
                boolean flag =p.reducePlayerMoney(board[p.position].costOfBuy);
                if(flag&&buy){
                    board[p.position].owner = p.name;
                    p.playerCardList.add(board[p.position]);
                    System.out.printf("Congratulation for buying %s\n",board[p.position].name);
                }else if(!buy){
                    Player buyer = auction(p,playersList);
                    System.out.printf("%s is new owner \n",buyer.name);
                    board[p.position].owner=buyer.name;
                }else if(!flag){
                    Player buyer = auction(p,playersList);
                    System.out.printf("%s is new owner \n",buyer.name);
                    board[p.position].owner=buyer.name;
                }


            }else if(!board[p.position].equals("Bank") && !board[p.position].equals(board[p.position].owner)){
                System.out.printf("%s is the owner, therefore %s has to pay rent \n",board[p.position].owner,p.name);
                System.out.printf("Rent Payed:%d\n",board[p.position].rent);
                p.reducePlayerMoney(board[p.position].rent);

            }else if(!board[p.position].equals("Bank") && board[p.position].equals(board[p.position].owner)){
                System.out.printf("This is your property, Enjoy your stay.\n");
            }
        }else if (Objects.equals(board[p.position].type, "chance")) {
            System.out.print("Welcome to chance\n");
            board[p.position].chanceResult(p);


        }else if (Objects.equals(board[p.position].type, "communityChest")) {
            System.out.print("Welcome To Community Chest\n");
            board[p.position].chanceResult(p);


        }else if (Objects.equals(board[p.position].type, "freeParking")) {
            System.out.println("It's a FREE PARKING");


        }else if (Objects.equals(board[p.position].type, "goToJail")) {
            p.position=10;
            if(p.jailPass>0){
                System.out.println("you have a Jail Pass, therefore no charges");
                p.jailPass-=1;
            }else{
                System.out.println(board[p.position]);
                System.out.println("Pay 40$ to get of Jail");
                p.reducePlayerMoney(40);
                p.position=10;
            }


        }else if (Objects.equals(board[p.position].type, "jail")) {
            System.out.println(board[p.position]);


        }

    }


    Card[] makeBoard(){
        Board game = new Board();

        Board.board = game.fillBoard();
        return Board.board;
    }



    Player auction(Player x, Player[] p){
        Scanner scan = new Scanner(System.in);

        int num = p.length;
        ArrayList<Player> playerForBet = new ArrayList<Player>();
        ArrayList<Integer> bet = new ArrayList<Integer>();

        for(int i=0;i<num;i++){
            if(!Objects.equals(p[i].name, x.name)){

                playerForBet.add(p[i]);
                System.out.printf("How much %s want to bet:",p[i].name);
                int ans1 = scan.nextInt();
                bet.add(ans1);

            }
        }int y = Collections.max(bet);
        Player buyer = playerForBet.get(bet.indexOf(y));
        buyer.reducePlayerMoney(y);
        return buyer;

    }


}
