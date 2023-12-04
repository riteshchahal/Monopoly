import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.*;

public class Play {

    void game(){
        int numOfplayer = 2;
        String[] winRateArray= new String[3];
        String winRate ="";
        int a=1;
        int b=2;
        for(int j=0;j<3;j++) {
            int win1=0;
            int win2=0;
            if(j==1){
                b=3;
            }else if (j==2) {
                a=2;
            }
            for (int i = 0; i < 20; i++) {
                System.out.printf("Game %d\n", i + 1);
                Player[] playersList = new Player[numOfplayer];
                Player p1 = new Player("Ritesh", a);
                playersList[0] = p1;
                Player p2 = new Player("Aryan", b);
                playersList[1] = p2;
                System.out.println("Player 1: Ritesh");
                System.out.println("Player 2: Aryan\n");
                Player winner = continuePlay(p1, p2, playersList);
                System.out.println("Winner:"+winner.name+"\n\n");
                if (winner == p1) {
                    win1++;
                } else {
                    win2++;
                }
            }
            winRate= String.valueOf(win1)+"/"+String.valueOf(win2);

            if(win2==0){
                winRate="20/0";
            } else if (win1==0) {
                winRate="0/20";
            }
            winRateArray[j]=winRate;
        }
        System.out.printf("Win rate of Players with strategy 1 vs strategy 2 in 20 games: %s\nWin rate of Players with strategy 1 vs strategy 3 in 20 games: %s\nWin rate of Players with strategy 2 vs strategy 3 in 20 games: %s\n",winRateArray[0],winRateArray[1],winRateArray[2]);
    }


    Player continuePlay(Player p1,Player p2, Player[] playersList){
        Card[] board = makeBoard();
        int numOfplayer = playersList.length;



        while(p1.playerMoney!=0 && p2.playerMoney!=0&&p1.playerMoney<10000 && p2.playerMoney<10000){
            for(int j = 0;j<numOfplayer;j++){
                Player currentPlayer = playersList[j];
                int j2 = j;
                if(j==0){
                    j2=1;
                }else{
                    j2=0;
                }
                Player otherPlayer = playersList[j2];

                System.out.printf("%s Turn.\n",currentPlayer.name);

                turn(currentPlayer, board,otherPlayer);


                System.out.printf("Current Player-%s\nPosition:%d\nMoney:%d\n",currentPlayer.name,currentPlayer.position,currentPlayer.playerMoney);
                System.out.println();
                currentPlayer.houseYesNO();
                if(p1.bankcrupt && p1.playerCardList.size()>0){
                    System.out.printf("%s don't have sufficient fund, Therefore %s has to sell his property\n",p1.name,p1.name);
                    for(Card c: p1.playerCardList){
                        System.out.println(c);
                    }Random random = new Random();
                    int rand = random.nextInt(p1.playerCardList.size());
                    System.out.println(rand);
                    Card randCard = p1.playerCardList.get(rand);
                    p1.playerCardList.remove(randCard);
                    randCard.owner = "Bank";
                    p1.increasePlayerMoney(randCard.rent/2);
                    System.out.printf("%s have been sold to the bank for half price",randCard.name);
                } else if (p1.bankcrupt) {
                    System.out.printf("%s don't have sufficient fund, also don't have any card property to sell",p1.name);
                    break;
                }

            }

        }

        System.out.println("Game Over");
        if(p1.playerMoney>10000 ||p2.playerMoney<=0){
            return p1;
        }
        else{
            return p2;
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
    void turn(Player p1, Card[] board,Player p2){


        System.out.printf("Type of Strategy:%s\n",p1.gameType);

        int diceNumber = rollDice();
        int newP = p1.position+diceNumber;

        if(newP>=35){
            p1.position= newP-35;
            p1.increasePlayerMoney(200);
            System.out.printf("\nCongratulation %s for completing the round, here is 200$\n\n",p1.name);
        }else{
            p1.position+=diceNumber;
        }
        if(Objects.equals(board[p1.position].type, "countryCard")){
            if(Objects.equals(board[p1.position].owner, "Bank")) {
                System.out.print(board[p1.position]);

                boolean buy = p1.buyOrNot(p1,board[p1.position]);
                boolean flag =p1.fundCheck(board[p1.position].costOfBuy);
                if(flag&&buy){
                    board[p1.position].owner = p1.name;
                    p1.reducePlayerMoney(board[p1.position].costOfBuy);
                    p1.playerCardList.add(board[p1.position]);
                    System.out.printf("Congratulation for buying %s\n",board[p1.position].name);
                }else if(!buy){
                    boolean betCheck= p2.bet(board[p1.position]);
                    if(betCheck){
                        System.out.printf("%s is new owner \n",p2.name);
                    }
                    else{
                        System.out.printf("%s is still unsold\n",board[p1.position].name);
                    }
                }else if(!flag){
                    boolean betCheck= p2.bet(board[p1.position]);
                    if(betCheck){
                        System.out.printf("%s is new owner \n",p2.name);

                    }
                    else{
                        System.out.printf("%s is still unsold\n",board[p1.position].name);
                    }
                }


            }else if(!(Objects.equals(board[p1.position].owner, p1.name))){
                System.out.printf("Name of the Property:%s\n",board[p1.position].name);
                System.out.printf("%s is the owner, therefore %s has to pay rent \n",board[p1.position].owner,p1.name);
                System.out.printf("Rent Payed:%d\n",board[p1.position].rent);
                p1.reducePlayerMoney(board[p1.position].rent);
                p2.increasePlayerMoney(board[p1.position].rent);

            }else{
                System.out.printf("This is your property, Enjoy your stay.\n");
            }
        }else if (Objects.equals(board[p1.position].type, "chance")) {
            System.out.print("Welcome to chance\n");
            board[p1.position].chanceResult(p1);


        }else if (Objects.equals(board[p1.position].type, "communityChest")) {
            System.out.print("Welcome To Community Chest\n");
            board[p1.position].chanceResult(p1);


        }else if (Objects.equals(board[p1.position].type, "freeParking")) {
            System.out.println("It's a FREE PARKING");


        }else if (Objects.equals(board[p1.position].type, "goToJail")) {
            System.out.print(board[p1.position]);
            p1.position=10;
            if(p1.jailPass>0){
                System.out.print("you have a Jail Pass, therefore no charges\n");
                p1.jailPass-=1;
            }else{

                System.out.println("Pay 40$ to get of Jail");
                p1.reducePlayerMoney(40);
            }


        }else if (Objects.equals(board[p1.position].type, "jail")) {
            System.out.print(board[p1.position]);

        }else if(Objects.equals(board[p1.position].type, "start")){
            System.out.print(board[p1.position]);
        }

    }


    Card[] makeBoard(){
        Board game = new Board();

        Board.board = game.fillBoard();
        return Board.board;
    }






}
