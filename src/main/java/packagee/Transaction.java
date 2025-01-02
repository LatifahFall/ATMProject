package packagee;
import java.util.*;
import java.text.SimpleDateFormat;
 class Transaction{
    private static int incrementId=0;
    private int id,account;
    private double amount;
    private final long ttimestamp;
    public Transaction(int account,double amount){
        this.id=incrementId++;
        this.account=account;
        this.amount=amount;
        this.ttimestamp = new Date().getTime();
    }
    public Transaction(int id,int account,double amount,long ttimestamp){
        this.id=id;
        this.account=account;
        this.amount=amount;
        this.ttimestamp=ttimestamp;
    }
    public int getId(){
        return id;
    }
    public int getAccount(){
        return account;
    }
    public double getAmount(){
        return amount;
    }
    public long getTimestamp(){
        return ttimestamp;
    }
    public String getDate() {
        SimpleDateFormat date =new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        return date.format(new Date(ttimestamp));
    }
    public String getTime() {
        SimpleDateFormat time =new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
        return time.format(new Date(ttimestamp));
    }
    public String getSQLTimestamp() {
        return getDate()+" "+getTime();
    }
    public String getType(){return "TRANSACTION";}
    public String toString(){
        return "Transaction of id "+getId()+" account "+getAccount()+" amount "+getAmount()+" done on "+getDate()+" at "+getTime()+" .";
    }
    /*public static void main(String args[]){
        Transaction tr=new Transaction(1,12332.45);
        System.out.println(tr);
    }*/
}
