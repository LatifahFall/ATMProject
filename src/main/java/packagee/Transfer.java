package packagee;
import java.util.*;
import java.text.SimpleDateFormat;

class Transfer extends Transaction{
    private String iden,pincode,phone;
    private final long killtime;
    public Transfer(int account,double amount,String phone,String iden, String pincode){
        super(account,amount);
        this.phone=phone;
        this.iden=iden;
        this.pincode=pincode;
        this.killtime=getTimestamp()+604800000;
    }
    public Transfer(int id,int account,double amount,long ttimestamp,String phone,String iden,String pincode,long killtime){
        super(id,account,amount,ttimestamp);
        this.phone=phone;
        this.iden=iden;
        this.pincode=pincode;
        this.killtime=killtime;
    }
    public String getType(){
        return "TRANSFER";
    }
    public String getPincode(){
        return pincode;
    }
    public String getIden(){
        return iden;
    }
    public long getKilltime(){
        return killtime;
    }
    public String getPhone(){
        return phone;
    }
    public String getDateKilltime() {
        SimpleDateFormat date =new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        return date.format(new Date(killtime));
    }
    public String getTimeKilltime() {
        SimpleDateFormat time =new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
        return time.format(new Date(killtime));
    }
    public String getSQLKilltime(){
        return getDateKilltime()+" "+getTimeKilltime();
    }
    public String toString(){
        return super.toString()+"Type: Transfer of Iden : "+getIden()+" Pincode: "+getPincode()+" Killtime :  "+getSQLKilltime()+".";
    }
    public static void main(String args[]){
        Transfer tf=new Transfer(2,34,"fatimaelksakass@gmail.com","7664545","6555");
        System.out.println(tf);
    }

}

