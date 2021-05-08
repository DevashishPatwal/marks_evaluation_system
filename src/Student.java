import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable{
	private int roll;
	private String name;
	private int ext=0;
	private int synop=0;
	private int letter=0;
	private int prog1=0;
	private int prog2=0;
	private int prog3=0;
	public static LocalDate extdeadline;	//Deadline for report from external; 1 represent month and 23 represents day of month
	public static LocalDate synopdeadline;	//Deadline for Synopsis;
	public static LocalDate letterdeadline;//Deadline for Letter;
	public static LocalDate prog1deadline;	//Deadline for progress 1;
	public static LocalDate prog2deadline;	//Deadline for progress 2;
	public static LocalDate prog3deadline;	//Deadline for progress 3;
	public final static int MAX_MARKS=20;


	Student()
	{
		this(0,"default");
	}


	Student(int r,String n)
	{
		name=n;
		roll=r;

	}

	public int getExt() {
		return ext;
	}

	public int getTotal() {
		return (ext+synop+letter+prog1+prog2+prog3);
	}
	public void setExt(int ext) {
		this.ext = ext;
	}


	public int getSynop() {
		return synop;
	}


	public void setSnop(int snop) {
		this.synop = snop;
	}


	public int getLetter() {
		return letter;
	}


	public void setLetter(int letter) {
		this.letter = letter;
	}


	public int getProg1() {
		return prog1;
	}


	public void setProg1(int prog1) {
		this.prog1 = prog1;
	}


	public int getProg2() {
		return prog2;
	}


	public void setProg2(int prog2) {
		this.prog2 = prog2;
	}


	public int getProg3() {
		return prog3;
	}


	public void setProg3(int prog3) {
		this.prog3 = prog3;
	}


	public int getRoll()
	{
		return roll;
	}


	public void setRoll(int roll) {
		this.roll = roll;
	}


	public String getName()
	{
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public int getMarks(int choice)
	{
		switch(choice)
		{
		case 0:
			return ext;
		case 1:
			return synop;
		case 2:
			return letter;
		case 3:
			return prog1;
		case 4:
			return prog2;
		case 5:
			return prog3;
		default:
			return -1;
		}
	}
	public String field(int i)
	{
		switch(i)
		{
		case 0:
			return "External";
		case 1:
			return "Synopsis";
		case 2:
			return "Letter";
		case 3:
			return "Progress 1";
		case 4:
			return "Progress 2";
		case 5:
			return "Progress 3";
		default:
			return "";
		}
	}

}