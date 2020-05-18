package kirito.calculator;

import java.util.Stack;

public class DaThuc {
    private String daThuc;
    private String postfix;
    private double result;
    public DaThuc(){
        this.daThuc = "";
        this.postfix = "";
        this.result = 0.0;
    }

    public void setDaThuc(String daThuc) {
        this.daThuc = daThuc;
    }

    public String getDaThuc() {
        return this.daThuc;
    }

    public String getPostfix() {
        return postfix;
    }

    public int addChar(char c){
        this.daThuc = this.daThuc + c;

        int err = this.calc();
        return  err;
    }

    public int delChar(){
        if(this.daThuc.equals("")) return this.calc();
        this.daThuc = this.daThuc.substring(0, this.daThuc.length() - 1);
        return this.calc();
    }

    public int clear(){
        this.daThuc = "";
        return this.calc();
    }

    public int calc(){
        this.postfix = "";

        if(this.daThuc.equals("")){
            this.result = 0.0;
            return 0;
        }

        int err = this.toPostfix();
        if(err > 0) return err;

        if(this.postfix.equals("")){
            this.result = 0.0;
            return 0;
        }

        Stack stack = new Stack<String>();
        stack.clear();

        String[] token = this.postfix.split("\\s");
        int n = token.length;
        for(int i =0; i < n; i++){
            if(token[i].equals("+") || token[i].equals("-") ||
                    token[i].equals("*") || token[i].equals("/")){
                int tmp = 2;
                double num1 = 0.0, num2 = 0.0;
                if(!stack.empty()){
                    tmp--;
                    num1 = Double.parseDouble((String) stack.pop());
                }
                if(!stack.empty()){
                    tmp--;
                    num2 = Double.parseDouble((String) stack.pop());
                }
                if(tmp > 0){
                    stack.clear();
                    return 1;
                }
                switch (token[i]){
                    case "+":{
                        num1 = num1 + num2;
                        break;
                    }
                    case "-":{
                        num1 = num2 - num1;
                        break;
                    }
                    case "*":{
                        num1 = num1 * num2;
                        break;
                    }
                    case "/":{
                        if(num1 == 0) return 2;
                        num1 = num2 / num1;
                        break;
                    }
                }
                stack.push(String.valueOf(num1));
            }
            else{
                stack.push(token[i]);
            }
        }
        if(stack.empty()) return 1;
        this.result = Double.parseDouble((String) stack.pop());

        return 0;
    }

    public String getResult() {
        long tmp = (long) this.result;
        if(tmp == this.result){
            return String.valueOf(tmp);
        }
        return String.valueOf(this.result);
    }

    public static int getPriority(String op){
        if(op.equals("*") || op.equals("/")) return 2;
        else if(op.equals("+") || op.equals("-")) return 1;
        else return 0;
    }

    public int toPostfix(){
        Stack stack = new Stack<String>();
        stack.clear();

        int err = 0;
        int n = this.daThuc.length();
        int numStart = -1, dau = 1;
        for(int i = 0; i < n; i++){
            char c = this.daThuc.charAt(i);
            if(c >= '0' && c <= '9'){
                if(numStart == -1) numStart = i;
            } else if(c!= '.'){
                if(numStart > -1){
                    if(dau == -1) this.postfix = this.postfix + "-" + this.daThuc.substring(numStart, i) + ' ';
                    else this.postfix = this.postfix + this.daThuc.substring(numStart, i) + ' ';
                    dau = 1;
                    numStart = -1;
                }

                if(c == '('){
                    char c1 = this.daThuc.charAt(i-1);
                    if(c1 >= '0' && c1 <= '9' || c1 =='.'){
                        int priority = DaThuc.getPriority(String.valueOf('*'));
                        while(!stack.empty()){
                            String tmp = (String)stack.pop();
                            if(tmp.equals("(")){
                                stack.push(tmp);
                                break;
                            } else{
                                if(DaThuc.getPriority(tmp) >= priority) this.postfix = this.postfix + tmp + ' ';
                                else {
                                    stack.push(tmp);
                                    break;
                                }
                            }
                        }
                        stack.push(String.valueOf('*'));
                    }
                    stack.push(String.valueOf(c));
                } else if(c == ')'){
                    err = 1;
                    while(!stack.empty()){
                        String tmp = (String)stack.pop();
                        if(tmp.equals("(")){
                            err = 0;
                            break;
                        } else{
                            this.postfix = this.postfix + tmp + ' ';
                        }
                    }
                    if(err == 1) {
                        stack.clear();
                        return err;
                    }
                } else{
                    if((c == '+' || c == '-') && !(i>0 && !DaThuc.checkDau(this.daThuc.charAt(i-1)))){
                        if(c == '-') dau *= -1;
                        continue;
                    }


                    int priority = DaThuc.getPriority(String.valueOf(c));
                    while(!stack.empty()){
                        String tmp = (String)stack.pop();
                        if(tmp.equals("(")){
                            stack.push(tmp);
                            break;
                        } else{
                            if(DaThuc.getPriority(tmp) >= priority) this.postfix = this.postfix + tmp + ' ';
                            else {
                                stack.push(tmp);
                                break;
                            }
                        }
                    }
                    stack.push(String.valueOf(c));
                }
            }
        }

        if(numStart > -1) {
            if(dau == -1) this.postfix = this.postfix + "-" + this.daThuc.substring(numStart, n) + ' ';
            else this.postfix = this.postfix + this.daThuc.substring(numStart, n) + ' ';
            numStart = -1;
        }

        while(!stack.empty()){
            String tmp = (String)stack.pop();
            if(!tmp.equals("(")){
                this.postfix = this.postfix + tmp + ' ';
            }
        }
        return err;
    }

    public static boolean checkDau(char c){
        return (c == '+' || c== '-' || c== '*' || c=='/');
    }
}

