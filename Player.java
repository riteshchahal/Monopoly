import java.util.Random;

public class Player {

    String name;
    int gameType;
    int playerMoney = 2000;
    int position = 0;
    Card[] playerCardList = new Card[20];
    int jailPass=0;


    Player(String name){
        this.name = name;
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
            return false;
        }

    }
    void increasePlayerMoney(int inc){
        this.playerMoney+=inc;
    }
    boolean buyOrNot(Player p,Card card){

    if(p.gameType==1){
        return true;
    }
    return false;
    }

}
