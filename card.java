package sd2;

//Dallin Lyman
//cards with blackjack scoring
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class card {
    static Scanner in = new Scanner(System.in);//user input
    static int sus = 0;//int for drawing cards
    static int betp1;//bet for each player
    static int betp2;
    static int betp3;
    static int p1m;
    static int p2m;
    static int p3m;
    static ArrayList<card> deck = new ArrayList<card>();
    static ArrayList<card> p1 = new ArrayList<card>();//makes players
    static ArrayList<card> p2 = new ArrayList<card>();
    static ArrayList<card> p3 = new ArrayList<card>();
    static ArrayList<card> house = new ArrayList<card>();
    static int stands;

    int type;
    int value;
    public card(int it, int iv){//value is the face value of the card, and type is the suite of the card
        type=it;
        value=iv;
    }
    public static void print(List<card> p, int pnum){//prints the cards the players had
        System.out.println("player "+pnum +":");//player and their number
        for(card ii:p){
            if(ii.type==4){
                if(ii.value==1){
                    System.out.println(" Spade "+ "Ace");
                }if(ii.value==11){
                    System.out.println(" Spade "+ "Jack");
                }if(ii.value==12){
                    System.out.println(" Spade "+ "Queen");
                }if(ii.value==13){
                    System.out.println(" Spade "+ "King");
                }if(ii.value<=10 && ii.value!=1)
                    System.out.println(" Spade "+ ii.value);
                }
            if (ii.type==3) {
                if(ii.value==1){
                    System.out.println(" Club "+ "Ace");
                }if(ii.value==11){
                    System.out.println(" Club "+ "Jack");
                }if(ii.value==12){
                    System.out.println(" Club "+ "Queen");
                }if(ii.value==13){
                    System.out.println(" Club "+ "King");
                }if(ii.value<=10 && ii.value!=1)
                    System.out.println(" Club "+ ii.value);
            } if(ii.type==2) {
                if(ii.value==1){
                    System.out.println(" Diamond "+ "Ace");
                }if(ii.value==11){
                    System.out.println(" Diamond "+ "Jack");
                }if(ii.value==12){
                    System.out.println(" Diamond "+ "Queen");
                }if(ii.value==13){
                    System.out.println(" Diamond "+ "King");
                }if(ii.value<=10 && ii.value!=1)
                    System.out.println(" Diamond "+ ii.value);
            } if(ii.type==1){
                if(ii.value==1){
                    System.out.println(" Heart "+ "Ace");
                }if(ii.value==11){
                    System.out.println(" Heart "+ "Jack");
                }if(ii.value==12){
                    System.out.println(" Heart "+ "Queen");
                }if(ii.value==13){
                    System.out.println(" Heart "+ "King");
                }if(ii.value<=10 && ii.value!=1)
                    System.out.println(" Heart "+ ii.value);
            }
        }
    }
    
    public static void winner(){//determines who wins
        int p1s = score(p1);
        int p2s = score(p2);
        int p3s = score(p3);
        int houses = score(house);
        if(p1s==houses){//prints the winner
            System.out.println("Player 1 wins!");
            p1m += betp1*2;
        }if(p1s>houses){
            System.out.println("Player 1 wins!");
            p1m += betp1;
        }
        betp1=0;
        if(p2s==houses){//prints the winner
            System.out.println("Player 2 wins!");
            p2m += betp2*2;
        }if(p2s>houses){
            System.out.println("Player 2 wins!");
            p2m += betp2;
        }
        betp2=0;
        if(p3s==houses){//prints the winner
            System.out.println("Player 3 wins!");
            p3m += betp3*2;
        }if(p2s>houses){
            System.out.println("Player 3 wins!");
            p3m += betp3;
        }
        betp3=0;
        ReturnCards();
    }
    public static int score(List<card> p){
        int pa=0;//determines how many aces a player has
        int ps=0;//overall score
        for(card ii:p){//for every card, it tests for if it is an ace or a face card
            if(ii.value==1){
                pa+=1;
                ps+=10;
            }if(ii.value>=10){
                ps+=10;
            } else{
                ps+=ii.value;
            }
        }
        if(ps>=22){//uses the number of aces to determine the score
            if(pa==1){
                ps-=10;
            }
            if(pa==2 && ps == 22){//if the player has two aces at 22
                ps-=1;
            }
            if(ps>=22){
                ps=0;
            }
        }
        return ps;//returns the score
    }
    public static void main(String[] args){
        for(int e=1;e<5;e++){//creating cards
        for(int n=1;n<14;n++){
            deck.add(new card(e, n));
            }}
        DealCard(deck,p1);//deals the cards
        DealCard(deck,p1);
        DealCard(deck,p2);
        DealCard(deck,p2);
        DealCard(deck,p3);
        DealCard(deck,p3);
        DealCard(deck,house);
        DealCard(deck,house);
        System.out.println("Hit means that you want another card, and stand means that you don't want another card");
        System.out.println("How many players are playing?");
        int players = in.nextInt();
        if(players>=1){
            hitstand(p1);
        }
        if(players>=2){
            hitstand(p2);
        }
        if(players>=3){
            hitstand(p3);
        }
        print(p2,2);//prints the cards the players had
        print(p3,3);
        print(house,4);
        winner();//determines the winner

        in.close();//closes the scanner
    }
    public static void ReturnCards(){
        if(p1.isEmpty()==false){
            
        }
        sus=0;
    }
    public static void hitstand(List<card> player){
        boolean hit = false;
        int pnum = 1;
        if (player==p1){ 
            pnum = 1;
        }else if(player==p2){
            pnum = 2;
        }else if(player==p3){
            pnum = 3;
        }
        for(;hit==false;){//loops if you keep hitting
            print(player, pnum);//prints your current cards
            System.out.println("score: "+score(player));//prints your current score
            System.out.println("Hit or Stand? \"h\" or \"s\"");
            String hitstand = in.next();
            if (hitstand.contains("h")) {//tests if you hit or not
                DealCard(deck, player);
            } else if(hitstand.contains("s")){
                if(stands>=pnum){
                    for(int i=score(house);i>=15;i=score(house)){
                        if(i!=0){
                            DealCard(deck, house);
                        }
                        if(i==0){
                            break;
                        }
                        winner();
                    }
                    hit=true;//stops the loop
                }
            } else {
                System.out.println("put in your hits/stands right!!");//error message
                hitstand(player);
        }
    }
    }
    public static void DealCard(List<card> source,List<card> destination){//this deals the card by a random number generator
    Random rand = new Random();
    destination.add(source.remove(rand.nextInt(52-sus)));
    sus++;//counts how many cards are taken
    }
}