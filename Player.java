import java.util.ArrayList;


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

    boolean fundCheck(int dec){
        if(this.playerMoney-dec>=0){
            return true;
        }else{
            return false;
        }
    }


    void reducePlayerMoney(int dec){
        boolean fundcheck = fundCheck(dec);
        if(fundcheck){
            this.playerMoney -=dec;
        }else{
            System.out.print("Player don't have sufficient Funds\n");
            bankcrupt = true;
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
            if((double) card.costOfBuy /card.rent<=1.8){
                return true;
            }return false;
        } else if (p.gameType==3) {
            boolean flag = true;
            if(this.playerMoney<750){
                flag=false;
            }return flag;
        }
        return false;

    }

    boolean bet(Card card){
       if (this.gameType==1){
           if(fundCheck(card.costOfBuy+card.costOfBuy/10)){
               this.reducePlayerMoney(card.costOfBuy+card.costOfBuy/10);
               System.out.printf("Bet from %s for buying %s:%d\n",this.name,card.name,card.costOfBuy+card.costOfBuy/10);
               card.owner=this.name;
               this.playerCardList.add(card);
               return true;
           }else{
               return false;
           }
       }else if(this.gameType==2){
           if(fundCheck(card.costOfBuy)&& (double)card.costOfBuy/card.rent<=1.8){
               this.reducePlayerMoney(card.costOfBuy);
               System.out.printf("Bet from %s for buying %s:%d\n",this.name,card.name,card.costOfBuy);
               card.owner=this.name;
               this.playerCardList.add(card);
               return true;
           }
           return false;
       }else{
           boolean flag = true;

           if(this.playerMoney<750){
               flag=false;
           }
           if(flag){
               System.out.printf("Bet from %s for buying %s:%d\n",this.name,card.name,card.costOfBuy);
           }return flag;
       }



    }


}
