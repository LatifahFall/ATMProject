package packagee;

class TopUp extends Transaction{
    enum Operator {INWI,ORANGE,IAM};
    private Operator operator;
    private String phone;
    public TopUp(int account,double amount,String op,String phone){
        super(account,amount);
        switch (op){
            case "INWI": this.operator=Operator.INWI; break;
            case "IAM": this.operator=Operator.IAM; break;
            case "ORANGE": this.operator=Operator.ORANGE; break;
        }
        this.phone=phone;
    }
    public TopUp(int id,int account,double amount,long ttimestamp,String op,String phone){
        super(id,account,amount,ttimestamp);
        switch (op){
            case "INWI": this.operator=Operator.INWI; break;
            case "IAM": this.operator=Operator.IAM; break;
            case "ORANGE": this.operator=Operator.ORANGE; break;
        }
        this.phone=phone;
    }
    public String getType(){
        return "TOPUP";
    }
    public String getOperator(){
        String str="";
        switch (operator){
            case INWI: return str+"INWI";
            case IAM: return str+"IAM";
            case ORANGE: return str+"ORANGE";
        }
        return str;
    }
    public String getPhone(){
        return phone;
    }
    public String toString(){
        return super.toString()+"Type: Top-Up Operator : "+getOperator()+" Phone: "+getPhone()+".";
    }
    public static void main (String args[]){
        TopUp tp=new TopUp(1,2200,"INWI","0629294134");
        System.out.println(tp);
    }
}
