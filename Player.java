import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class Player {

    String name;
    int gameType;
    int playerMoney = 2000;
    int position = 0;
    ArrayList<Card> playerCardList = new ArrayList<Card>();
    boolean bankcrupt=false;
    int jailPass=0;


    Player(String name,int gameType){
        this.name = name;
        this.gameType = gameType;

    }
    String getName(){
        return this.name;
    }


    int getGameType(){
        Random random = new Random();
        int x = random.nextInt(3);
        return x;
    }
    boolean reducePlayerMoney(int dec){
        if(this.playerMoney-dec>=0){
            this.playerMoney -=dec;
            return true;
        }else{
            System.out.print("Player don't have sufficient Funds\n");
            bankcrupt = true;
            return false;
        }

    }
    void increasePlayerMoney(int inc){
        if(bankcrupt){
            bankcrupt=!bankcrupt;
        }
        this.playerMoney+=inc;
    }

    boolean buyOrNot(Player p,Card card){

        if(p.gameType==1){
            return true;
        } else if (p.gameType==2) {
            if((double) card.costOfBuy /card.rent<=1.5){
                return true;
            }return false;
        } else if (p.gameType==3) {
            boolean flag = true;
            for(Card x: p.playerCardList){
                if(!x.house){
                    flag = false;
                }
            }return flag;
        }
        return false;
        }

}
