package _02_Pixel_Art;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GridPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int windowWidth;
	private int windowHeight;
	private int pixelWidth;
	private int pixelHeight;
	private int rows;
	private int cols;
	private JButton button = new JButton();
	// 1. Create a 2D array of pixels. Do not initialize it yet.
	Pixel[][] p;
	private Color color;

	public GridPanel(int w, int h, int r, int c) {
		this.windowWidth = w;
		this.windowHeight = h;
		this.rows = r;
		this.cols = c;
		button.setText("Save");
		this.add(button);
		this.pixelWidth = windowWidth / cols;
		this.pixelHeight = windowHeight / rows;

		color = Color.BLACK;

		setPreferredSize(new Dimension(windowWidth, windowHeight));

		// 2. Initialize the pixel array using the rows and cols variables.
		p = new Pixel[rows][cols];

		// 3. Iterate through the array and initialize each element to a new pixel.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				p[i][j] = new Pixel(pixelWidth, pixelHeight);
			}
		}
		Data data = new Data(windowWidth, windowHeight);
		button.addActionListener((e) ->{
			if(e.getSource() == button) {
				save(data);
				Data load = load();
				System.out.println(load.height);
			}
		});
		
	}

	public void setColor(Color c) {
		color = c;
	}

	public void clickPixel(int mouseX, int mouseY) {
		// 5. Use the mouseX and mouseY variables to change the color
		// of the pixel that was clicked. *HINT* Use the pixel's dimensions.
		p[mouseX / pixelWidth][mouseY / pixelHeight].color = color;
	}

	@Override
	public void paintComponent(Graphics g) {
		// 4. Iterate through the array.
		// For every pixel in the list, fill in a rectangle using the pixel's color.
		// Then, use drawRect to add a grid pattern to your display.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				g.setColor(p[i][j].color);
				g.fillRect(pixelWidth * i, pixelWidth * j, pixelWidth, pixelHeight);
				g.setColor(Color.black);
				g.drawRect(pixelWidth * i, pixelWidth * j, pixelWidth, pixelHeight);
			}
		}

	}

	private static void save(Data data) {
		final String DATA_FILE = "src/_02_Pixel_Art/saved.dat";
		try (FileOutputStream fos = new FileOutputStream(new File(DATA_FILE));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static Data load() {
		final String DATA_FILE = "src/_02_Pixel_Art/saved.dat";
		try (FileInputStream fis = new FileInputStream(new File(DATA_FILE)); ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (Data) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// This can occur if the object we read from the file is not
			// an instance of any recognized class
			e.printStackTrace();
			return null;
		}
	}
}
