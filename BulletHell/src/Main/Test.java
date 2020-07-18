package Main;

import java.io.File;

import Misc.YAML;

public class Test {
	public static void main(String args[]) {
		YAML config = new YAML(new File("YAML Test.txt"));
		config.set("Key", "value");
		config.set("one.two", "value");
		config.set("one.four.five", "value");
		config.save();
		config.print();
	}
	public static int solution(String s) {
    //Your code here
    int re = 0;
    //Remove unused information
    s = s.substring(s.indexOf(">"));
    s = s.replace("-","");
    
    for(int x = 0;x < s.length();x++){
        if(s.substring(x,x+1).contains(">"))
            re+=countAfter(s.substring(x));
    }
    return re;
}
public static int countAfter(String s) {
    int re = 0;
    for(int x = 0;x < s.length();x++){
        if(s.substring(x,x+1).contains("<"))
            re+=2;
    }
    return re;
}
	/*
	public static String solution(int i) {
		String primeNumbers = "";
		for(int num = 2;primeNumbers.length() <= i+5;num++) {
			boolean prime = true;
        for(int x = 2;x <= num/2;x++)
            if(num % x == 0) {
                prime = false;
                break;
            }
        if(prime)
            primeNumbers += num;
            
    }
    
    return primeNumbers.substring(i,i+5);
	}
	*/
}

