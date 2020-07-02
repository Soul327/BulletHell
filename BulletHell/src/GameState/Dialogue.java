package GameState;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Misc.Graphics;

public class Dialogue {
	Dia onDia;
	//ArrayList<Dia> dia = new ArrayList<Dia>();
	
	public Dialogue() {
		/*
		Dia d = new Dia("Welcome traveler! Look at this fancy texts!");
		d.dia.add(new Dia("I don't see this as very fancy", "Well then you won't appreciate what these can do for you."));
		d.dia.add(new Dia("Wowie is this a test or something","Yes this is a test, or something"));
		dia.add(d);
		
		for(Dia dd:dia) {
			System.out.println(dd.toString());
			System.out.println();
		}
		onDia = dia.get(0).dia.get(0);
		*/
	}
	
	public void getDio() throws FileNotFoundException {
		Scanner scan = new Scanner(new File("res/NPC_chat"));
		System.out.println( scan.next() );
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawString(onDia.prompt, 5, 0);
		g.setColor(Color.white);
		int x = 1;
		for(Dia d:onDia.dia) {
			g.drawString(d.response, 15, 15*x++);
		}
	}
	
	public static void main(String args[]) { new Dialogue(); }
}

class Dia {//Prompts
	String prompt = "Prompt";
	String response = "Prompt";
	
	ArrayList<Dia> dia = new ArrayList<Dia>();
	
	public Dia(String s) {
		prompt = s;
	}
	
	public Dia(String r, String s) {
		prompt = s;
		response = r;
	}
	
	public String mes(int off) {
		String s = prompt + "\n";
		for(Dia d:dia) {
			s += " " + d.response + "\n";
			for(int x=0;x<off;x++)
				s+=" ";
			s += d.mes(off + 1);
		}
		return s;
	}
	
	public String toString() {
		return mes(0);
	}
}