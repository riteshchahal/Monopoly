import java.util.*;


public class Play {
    void continuePlay(){
        Card[] board = makeBoard();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Number of players:");
        int numOfplayer = scan.nextInt();
        Player[] playersList = new Player[numOfplayer];



        for(int i=0;i<numOfplayer;i++){
            System.out.printf("Enter Player %d name:",i+1);
            System.out.println();
            String name = scan.next();
            Player p = new Player(name);
            playersList[i]=p;

        }

        while(true){
            for(int j = 0;j<numOfplayer;j++){
                Player currentPlayer = playersList[j];

                System.out.printf("%s Turn. Do you want to take the turn? y/n:",currentPlayer.name);
                System.out.println();

                String con = scan.next();
                if(Objects.equals(con, "n")){
                    System.out.print("What do you want \n1.Build a house  -Press 1\n2.Sell a property  -Press 2\n3.Quit  -Press 3\n4.Continue  -Press 4\n");
                    int an =scan.nextInt();


                    if(an == 1){
                        System.out.print("BUILD FUNCTION REQUIRED ");
                    }else if(an ==2){
                        System.out.print("SELL FUNCTION REQUIRED");
                    } else if (an ==3) {
                        break;
                    }else if(an ==4){
                        break;
                    }
                    else{
                        System.out.print("Continuing the game. \n");
                    }

                }

                else{

                    turn(currentPlayer, board,playersList);

                    System.out.printf("Current Player-%s\nPosition:%d\nMoney:%d\n",currentPlayer.name,currentPlayer.position,currentPlayer.playerMoney);
                }
            }
            System.out.print("Next round or stop?\n");
            String con1 = scan.next();

            if(Objects.equals(con1, "stop")){
                System.out.print("Game over");
                break;
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
                System.out.print("Do you want to buy it? y/n:");
                String con2 = scan.next();
                if (con2.equals("y")) {

                    boolean flag =p.reducePlayerMoney(board[p.position].costOfBuy);
                    if(flag){
                        board[p.position].owner = p.name;
                    }

                }else if(con2.equals("n")){
                    Player buyer = auction(p,playersList);
                    System.out.printf("%s is new owner \n",buyer.name);
                    board[p.position].owner=buyer.name;
                }
            }else if(!board[p.position].equals("Bank")) {
                System.out.printf("%s is the owner, therefore %s has to pay rent \n",board[p.position].owner,p.name);
                p.reducePlayerMoney(board[p.position].rent);
            }
        }else if (Objects.equals(board[p.position].type, "chance")) {
            System.out.print("CHANCE FUNCTION REQUIRED\n");


        }else if (Objects.equals(board[p.position].type, "communityChest")) {
            System.out.print("COMMUNITY CHEST FUNCTION REQUIRED\n");


        }else if (Objects.equals(board[p.position].type, "freeParking")) {
            System.out.print("FREE PARKING FUNCTION REQUIRED\n");


        }else if (Objects.equals(board[p.position].type, "goToJail")) {
            System.out.print("GO TO JAIL FUNCTION REQUIRED\n");


        }else if (Objects.equals(board[p.position].type, "jail")) {
            System.out.print("VISITING JAIL FUNCTION REQUIRED\n");


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
