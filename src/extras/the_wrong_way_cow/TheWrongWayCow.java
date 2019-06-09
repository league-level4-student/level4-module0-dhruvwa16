//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

public class TheWrongWayCow {

    public static int[] findWrongWayCow(final char[][] field) {
        // Fill in the code to return the x,y coordinate position of the
        // head (letter 'c') of the wrong way cow!
    	int up = 0;
    	int down = 0;
    	int right = 0;
    	int left = 0;
    	int [] u = new int[2];
    	int [] d = new int[2];
    	int [] r = new int[2];
    	int [] l = new int[2];
        for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if(field[i][j] == 'o') {
					if(i==0 || i==field.length-1) {
						if(field[i][j-1] == 'c') {
							r[0] = j-1;
							r[1] = i;
							 right++;
						}
						else {
							left++;
							l[0] = j+1;
							l[1] = i;
						}
					}
					else if(j==0 || j==field[i].length-1) {
						if(field[i-1][j] == 'c') {
							down++;
							d[0] = j;
							d[1] = i-1;
						}
						else {
							up++;
							u[0] = j;
							u[1] = i+1;
						}
					
					}
					else {
						if(field[i][j-1] == 'c') {
							r[0] = j-1;
							r[1] = i;
							 right++;
						}
						else if(field[i][j+1] == 'c'){
							left++;
							l[0] = j+1;
							l[1] = i;
						}
						else if(field[i-1][j] == 'c') {
							down++;
							d[0] = j;
							d[1] = i-1;
							}
						else {
							up++;
							u[0] = j;
							u[1] = i+1;
							}
						}
					}

				
			}
		}
        System.out.println(right);
        System.out.println(down);
        if(up == 1) {
        	return(u);
        }
        if(down == 1) {
        	return(d);
        }
        if(left == 1) {
        	return(l);
        }
        if(right == 1) {
        	return(r);
        }
    
        return null;
    }
}
