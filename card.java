package sd2;
//Dallin Lyman
//cards with blackjack scoring
import java.io.File;
import java.io.IOException;//imports
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class card {
    static Scanner in = new Scanner(System.in);//user input
    static int sus = 0;//int for drawing cards
    static int betp1;//bet for each player
    static int betp2;
    static int betp3;
    static int p1m = 100;//money for each player
    static int p2m = 100;
    static int p3m = 100;
    static ArrayList<card> deck = new ArrayList<card>();
    static ArrayList<card> p1 = new ArrayList<card>();//makes players
    static ArrayList<card> p2 = new ArrayList<card>();
    static ArrayList<card> p3 = new ArrayList<card>();
    static ArrayList<card> house = new ArrayList<card>();
    static int stands;//stands
    static int players = 0;//players

    int type;
    int value;
    public card(int it, int iv){//value is the face value of the card, and type is the suite of the card
        type=it;
        value=iv;
    }
    public static void print(List<card> p, int pnum){//prints the cards the players had
        if(pnum<=3){
        System.out.println("player "+pnum +":");//player and their number
        }
        if(pnum==4){
            System.out.println("house:");//player and their number
            }
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
        if(p1s>houses){//prints the winner
            System.out.println("Player 1 wins!");
            p1m += betp1*2;
        }if(p1s==houses&&p1s!=0){
            System.out.println("Player 1 wins!");
            p1m += betp1;
        }
        betp1=0;
        if(p2s>houses){//prints the winner
            System.out.println("Player 2 wins!");
            p2m += betp2*2;
        }if(p2s==houses&&p2s!=0){
            System.out.println("Player 2 wins!");
            p2m += betp2;
        }
        betp2=0;
        if(p3s>houses){//prints the winner
            System.out.println("Player 3 wins!");
            p3m += betp3*2;
        }if(p2s==houses&p2s!=0){
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
    public static void save(){
        try {
            FileWriter Write = new FileWriter("BlackJack.txt"); //creates a writer object
            BufferedWriter write = new BufferedWriter(Write); //buffered writer
            write.write(p1m + "1m ");//writes the data, and it has markers so it doesn't get confused
            write.write(p2m + "2m ");
            write.write(p3m + "3m ");
            write.write(players + "p ");
            write.newLine();
          write.close();
          } catch (IOException e) {
            System.out.println("An error! :O");//bad message
            e.printStackTrace();
          }
    }
    public static void main(String[] args){
        for(int e=1;e<5;e++){//creating cards
        for(int n=1;n<14;n++){
            deck.add(new card(e, n));
            }}
            try {
        File File = new File("BlackJack.txt");//creates the file
        if (File.createNewFile()) {//tests if there is already a file
            System.out.println("File created: " + File.getName());
        } else {
            System.out.println("already have a file! :)");//if you have a file already
        }
            } catch (IOException e) {
            System.out.println("An error occurred.");//error message
            e.printStackTrace();
            }
            System.out.println("Use previous save? \"y\" for yes, and \"n\" for no (Don't use on first use)");
            String file = in.next();
            in.nextLine();
            if(file.equals("y")){
                try {
                File File0 = new File("BlackJack.txt");//new file read
                Scanner Read = new Scanner(File0);//new scanner
                while (Read.hasNextLine()) {//reads the next file line
                    String data = Read.nextLine();
                    StringTokenizer st = new StringTokenizer(data," ");//separates the data 
                    while (st.hasMoreTokens()) {//reads the tokens and prints them
                        String tokn = st.nextToken();
                        if(tokn.contains("1m")){//tests if the token has data
                            tokn=tokn.replace("1m", "");
                            p1m = (int) Float.parseFloat(tokn);
                        }
                        if(tokn.contains("2m")){
                            tokn=tokn.replace("2m", "");
                            p2m = (int) Float.parseFloat(tokn);
                        }
                        if(tokn.contains("3m")){
                            tokn=tokn.replace("3m", "");
                            p3m = (int) Float.parseFloat(tokn);
                        }
                        if(tokn.contains("p")){
                            tokn=tokn.replace("p", "");
                            players = (int) Float.parseFloat(tokn);
                        }
                    }
                }
                Read.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              } 
            }
            if(p1m==0&&p2m==0&&p3m==0){
                p1m=100;
                p2m=100;
                p3m=100;
                players=0;
                System.out.println("The last game was finished, restarted game");
                save();
            }
            System.out.println("Hit means that you want another card, and stand means that you don't want another card");
            System.out.println("How many players are playing?");
            if (players==0){
            players = in.nextInt();
            in.nextLine();
            }
            for(;p1m!=0||p2m!=0||p3m!=0;){
                DealCard(deck,house);
                DealCard(deck,house);
                if(players>=1&&p1m!=0){
                    DealCard(deck,p1);//deals the cards
                    DealCard(deck,p1);
                    bet(p1);
                    hitstand(p1);
                }
                if(players>=2&&p2m!=0){
                    DealCard(deck,p2);
                    DealCard(deck,p2);
                    bet(p2);
                    hitstand(p2);
                }
                if(players>=3&&p3m!=0){
                    DealCard(deck,p3);
                    DealCard(deck,p3);
                    bet(p3);
                    hitstand(p3);
                }
                househit();
                print(house,4);
                winner();//determines the winner
                System.out.println("Saved! ;)");
                System.out.println("");
                save();
            }
        in.close();//closes the scanner
    }
    public static void househit(){
        for(int i = score(house);i<=15;i=score(house)){
            if (i!=0){
                DealCard(deck, house);
            } else{
                break;
            }
        }
    }
    public static void ReturnCards(){
        for(;p1.isEmpty()==false;){
            deck.add(p1.remove(0));
        }
        for(;p2.isEmpty()==false;){
            deck.add(p2.remove(0));
        }
        for(;p3.isEmpty()==false;){
            deck.add(p3.remove(0));
        }
        for(;house.isEmpty()==false;){
            deck.add(house.remove(0));
        }
        sus=0;
    }
    public static void bet(List<card> player){
        int pnum = 1;
        int mon = 42;
        if (player==p1){ 
            mon = p1m;
            pnum = 1;
        }else if(player==p2){
            mon = p2m;
            pnum = 2;
        }else if(player==p3){
            mon = p3m;
            pnum = 3;
        }
        System.out.println("How much does player "+pnum+" want to bet?");
        System.out.println("You have $"+mon);
        int bet = in.nextInt();
        in.nextLine();
        if (player==p1){ 
            if (p1m>=bet) {
                p1m-=bet;
                betp1=bet;
            }else{
                System.out.println("that's too much money!!!");
                bet(player);
            }
        }else if(player==p2){
            if (p2m>=bet) {
                p2m-=bet;
                betp2=bet;
            }else{
                System.out.println("that's too much money!!!");
                bet(player);
            }
        }else if(player==p3){
            if (p3m>=bet) {
                p3m-=bet;
                betp3=bet;
            }else{
                System.out.println("that's too much money!!!");
                bet(player);
            }
        }
        
    }
    public static void hitstand(List<card> player){
        boolean hit = false;
        int pnum = 1;
        if(p2.isEmpty()==false){
            pnum = 2;
        }else if(p2.isEmpty()==false){
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
                    stands++; 
                    hit=true;//stops the loop
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
