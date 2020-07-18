package Misc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.processing.FilerException;

public class YAML {
	File file;
	public ArrayList<Dir> keys = new ArrayList<Dir>();
	
	public YAML(File file) {
		this.file = file;
		try {
			this.file.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void set(String location,String value) {
		set(location,value,keys);
	}
	public void set(String location,String value,ArrayList<Dir> list) {
		//Split by dot
		String names[] = location.split("\\.");
		if(names.length==1) {
			Dir tempDir = getDirByName(list,names[0]);
			if(tempDir==null)
				list.add(new Dir(names[0],value));
			else
				tempDir.value = value;
		}
		if(names.length>1) {
			Dir tempDir = getDirByName(list,names[0]);
			if(tempDir==null)
				list.add(new Dir(names[1],null));
			else
				set(location.substring(location.indexOf(".")+1),value, tempDir.linked);
		}
		//getDirByName(keys, names[0]).linked.add(new Dir(names[names.length-1], value));
		
	}
	public Dir getDirByName(ArrayList<Dir> list,String name) {
		for(Dir d:list)
			if(d.name.equals(name))
				return d;
		return null;
	}
	//Sav File
	public void save() {
		try {
			FileWriter writer = new FileWriter(file);
			for(Dir d:keys) {
				writer.write(saveRe(d,0));
			}
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public String saveRe(Dir d,int lay) {
		String re = "";
		for(int x=0;x<lay;x++) re+=" ";
		re += d.name+": "+d.value+"\n";
		for(Dir dd:d.linked)
			re += saveRe(dd,lay+1);
		return re;
	}
	//Debug
	public void print() {
		for(Dir d:keys)
			d.print();
	}
}

class Dir {
	String name = "";
	String value = "";
	ArrayList<Dir> linked;
	
	public Dir(String name,String value) {
		linked = new ArrayList<Dir>();
		this.name = name;
		this.value = value;
	}
	public void print() {
		System.out.println(name+": "+value+" "+linked.size());
		for(Dir d:linked)
			d.print();
	}
}