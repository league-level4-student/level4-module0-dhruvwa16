package _02_Pixel_Art;

import java.io.Serializable;

public class Data implements Serializable {
	//Pixel[][] pixel;
	int width;
	int height;
	public Data(int width, int height ){
		//this.pixel = pixel;
		this.width = width;
		this.height = height;
	}
}
