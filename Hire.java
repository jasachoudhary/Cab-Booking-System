import com.mongodb.*;

import java.util.Scanner;

import java.text.SimpleDateFormat;
import java.util.Date;


class Driver extends Main
{
	
	public Driver(DB db)
	{
		super(db);
	}
	void driver_detail()
	{
		secure_driverdetail();
		
	}
}

class Diskstra {

		int arr[][] = new int[100][100];
		int vertex;
		int edge;
		int state[] = new int[100];
		int distance[] = new int[100];
		
		Scanner input = new Scanner(System.in);
		
		public Diskstra()
		{
			vertex = 0;
			edge = 0;
		}
		void createnetwork()
		{
			System.out.println("how many station enter  ?");
			vertex = input.nextInt();
			
			System.out.println("how many way enter  ?");
			edge = input.nextInt();
			
			for(int i=0;i<vertex;i++)
			{
				for(int j = 0;j<vertex;j++)
				{
					arr[i][j] = 999;
				}
			}
			
			System.out.println("enter vertex 1,vertex 2 and distance ?");
			int v1,v2,d;
			for(int i=0;i<edge;i++)
			{
				v1 = input.nextInt();
				v2 = input.nextInt();
				d  = input.nextInt();
				
				arr[v1][v2] = d;
				arr[v2][v1] = d;
			}
		}
		int diskstra()
		{
			int source,current = 0,currentdist=0,tot = 0,destination;
			for(int i =0;i<vertex;i++)
				state[i] = 0;
			System.out.println("enter the source ");
			source = input.nextInt();
			System.out.println("enter the destination");
			destination = input.nextInt();
			state[source] = 1;
			for(int i =0;i<vertex;i++)
				distance[i] = arr[source][i];
			tot++;
			while(tot!=vertex)
			{
				int minidist = 999;
				for(int i=0;i<vertex;i++)
				{
					if(minidist>distance[i] && state[i] == 0)
					{
						minidist = distance[i];
						current = i;
					}
				}
				state[current]=1;
				for(int i =0;i<vertex;i++)
				{
					currentdist = arr[current][i]+minidist;
					if(state[i]==0 && currentdist<distance[i])
						distance[i] = currentdist;
				}
				tot++;
			}
			
			for(int i = 0;i<vertex;i++)
			{
				if(i == destination)
				{
					return distance[i];
				}
			}
			return distance[destination];
		}
}

class Date_time extends Main
{
	
	Date_time(DB db)
	{
		super(db);
	}
	void datetime()
	{
		try
		{
			String user_date;
			System.out.println("ENTER THE DATE (DD-MM-YYYY)?");
			while(true)
			{	
				user_date =input.next();
				
				Date myDate = new Date();
	    	    SimpleDateFormat dmyFormat = new SimpleDateFormat("dd-MM-yyyy");
	    	    String dmy = dmyFormat.format(myDate);
				
	    	    if(user_date.compareTo(dmy)>0)
	    	    {
	    	    	System.out.println("IN THIS TIME CAR IS AVAILABLE");
	    	    	break;
	    	    }
	    	    else
	    	    {
	    	    	System.out.println("ENTER THE CORRECT DATE(DD-MM-YYYY)?");
	    	    }
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}

class Car extends Main
{
	public Car(DB db)
	{
		     super(db);
	}
	void car_detail()
	{
		secure_cardetail();	
	}
}

class Login extends Main
{
	public Login(DB db)
	{
		super(db);
	}
	boolean login()
	{
		return secure_login();		
	}
}

class Main
{
	private DB db1;
	private DBCollection user_collection,car_collection,driver_collection;
	private DBCursor  allvalue;
	Scanner input = new Scanner(System.in);
	BasicDBObject query;
	
	public Main()
	{
		//System.out.println("default");
		db1 =null;
	}
	public Main(DB db)
	{
		//System.out.println("jii somu");
		db1 = db;
		user_collection = db1.getCollection("customer");
		driver_collection = db.getCollection("driver");
		car_collection = db.getCollection("car");
	}
	void secure_signup()
	{
		System.out.println("ENTER THE FIRST NAME");
		String firstname = input.next();
		System.out.println("ENTER THE LAST NAME");
		String lastname = input.next();
		System.out.println("ENTER THE AGE");
		int age = input.nextInt();
		System.out.println("ENTER GMAIL ID");
		String gmail = input.next();
		System.out.println("ENTER THE PHONE NUMBER");
		String phonenum = input.next();
		System.out.println("ENTER THE PASSWORD");
		String password = input.next();
		query= new BasicDBObject().append("FIRSTNAME",firstname).append("LASTNAME",lastname).append("AGE",age).append("GMAIL_ID",gmail).append("PHONE_NUMBER",phonenum).append("PASSWORD",password);
		
		System.out.println("hiii");
		user_collection.insert(query);
	}
	
	boolean secure_login()
	{
		System.out.println("ENTER THE GMAIL");
		String gmail = input.next();
		System.out.println("ENTER THE PASSWORD");
		String password = input.next();

		allvalue = user_collection.find(new BasicDBObject("GMAIL_ID",gmail).append("PASSWORD",password));
		
		if(allvalue.hasNext())
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	void secure_cardetail()
	{
		System.out.println("ENTER THE CAR NAME");
		String name = input.next();
		System.out.println("ENTER THE MILEGE");
		int milege = input.nextInt();
		System.out.println("ENTER THE TOP SPEED");
		int speed  = input.nextInt();
		System.out.println("ENTER THE PASSENGER CAPACITY");
		int capacity = input.nextInt();
		System.out.println("ENTER THE CHARGE PER KILOMETER");
		int charge = input.nextInt();
		System.out.println("ENTER THE COLOUR");
		String colour = input.next();
		System.out.println("ENTER THE AVAILABILITY");
		String available = input.next();
		
		
		query = new BasicDBObject("NAME",name).append("MILEGE",milege).append("TOPSPEED",speed).append("PASSENGER_CAPACITY",capacity).append("CHARGE",charge).append("COLOUR",colour).append("AVAILABLE",available);
		
		car_collection.insert(query);
	}
	
	void secure_driverdetail()
	{
		System.out.println("PLEASE ENTER THE DRIVER DETAILS");
		
		System.out.println("ENTER THE FIRST NAME");
		String firstname = input.next();
		System.out.println("ENTER THE LAST NAME");
		String lastname = input.next();
		System.out.println("ENTER THE AGE");
		int age = input.nextInt();
		System.out.println("ENTER THE PHONE NUMBER");
		String phone = input.next();
		System.out.println("ENTER THE LICENSE NUMBER");
		String license_no = input.next();
		System.out.println("ENTER THE ADDRESS");
		String address = input.next();
		System.out.println("ENTER THE PINCODE");
		String pincode = input.next();
		
		query = new BasicDBObject("FIRSTNAME",firstname).append("LASTNAME",lastname).append("AGE",age).append("PHONE_NO",phone).append("LICENSE_NO",license_no).append("ADDRESS",address).append("PINCODE",pincode);
		
		driver_collection.insert(query);
	}
	
}

class Signup extends Main
{
	public Signup(DB db) 
	{
		super(db);
		//this.user_collection=db.getCollection("customer");
		//System.out.println("hiiii Signup()");
		
	}
	public void signup()
	{
		secure_signup();
	}
}

class Transaction extends Main
{
	Scanner input = new Scanner(System.in);
	int tot_amount;
	public Transaction(int amount)
	{
		tot_amount = amount;
	}
	
	void card()
	{
		String str;
		System.out.println("ENTER CREDIT/DEBIT CARD NUMBER");
		while(true)
		{
			str = input.next();
			int len;
			len=str.length();
			try 
			{
				if(len==16)
				{
					Integer.parseInt(str);
					break;
				}
				else
				{
					System.out.println("INVALID CARD NUMBER");
					System.out.println("ENTER THE VALID CARD NUMBER");
				}
			}
			catch(NumberFormatException e)
			{
				System.out.println("INVALID CARD NUMBER");
				System.out.println("ENTER THE VALID CARD NUMBER");
			}
			
				
		}
		
		System.out.println("ENTER THE CVV NUMBER");
		while(true)
		{
			str = input.next();
			int len;
			len=str.length();
			try 
			{
				if(len==3)
				{
					Integer.parseInt(str);
					break;
				}
				else
				{
					System.out.println("INVALID CVV NUMBER");
					System.out.println("ENTER THE VALID CVV NUMBER");
				}
			}
			catch(NumberFormatException e)
			{
				System.out.println("INVALID CVV NUMBER");
				System.out.println("ENTER THE VALID CVV NUMBER");
			}
			
		}
		System.out.println("SUCCESSFULL TRANSACTION");
	}
	
	void netbanking()
	{
		System.out.println("ENTER USER NAME ");
		String name = input.next();
		System.out.println("ENTER PASSWORD");
		String password =input.next();
		System.out.println("SUCCESSFULL TRANSACTION");
	}
	
	void amount()
	{
		System.out.println("SELECT THE PAYMENT MODE");
		System.out.println("1. DEBIT/CREDIT CARD");
		System.out.println("2. NETBANKING");
		System.out.println("ENTER CHOICE");
		int ch = input.nextInt();
		switch(ch)
		{
		case 1:
			card();
			break;
		case 2:
			netbanking();
			break;
		}
	}

}
class Hire {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		
		try
		{
			MongoClient mongo = new MongoClient("localhost",27017);
			
			DB db = mongo.getDB("car");
			
			Scanner input = new Scanner(System.in);

			DBCollection car_collection = db.getCollection("car");
			
			Diskstra dis = new Diskstra();
			
			while(true)
			{
				System.out.println("1. LOGIN AS ADMINISTRATOR");
				System.out.println("2. SIGNUP USER");
				System.out.println("3. LOGIN USER");
				System.out.println("4. INSERT NEW CAR");
				System.out.println("5. EXIT");
				System.out.println("ENTER YOUR CHOICE");
				
				
				int choice = input.nextInt();
				switch(choice)
				{
				case 1:
					dis.createnetwork();
					break;
				case 2:
					//System.out.println("hiiii");
					Signup sign = new Signup(db);
					//System.out.println("hiiii after object");
					sign.signup();
					break;
				case 3:
					Login log = new Login(db);					
					if(log.login())
					{
						System.out.println("YOU ARE LOGGED IN");
						
						System.out.println("*** THERE ARE MANY CAR PROPERTIES ***");
						
							DBCursor allvalue = car_collection.find();
							
							String cho = "n";
							int amount = 0;
							while("n".equals(cho)||"N".equals(cho))
							{
								while(allvalue.hasNext())
								{
									System.out.println("\t"+allvalue.next().removeField("NAME"));
								}
								System.out.println("ENTER THE CAR NAME WHICH DO U WANT  SEE THE PROPERTY (case sensitive) ");
								String name = input.next();
								
								allvalue = car_collection.find(new BasicDBObject("NAME",name));	
												
								while(allvalue.hasNext())
								{
									
									System.out.println("\t"+allvalue.next());
								}
								
								allvalue = car_collection.find(new BasicDBObject("NAME",name));
								
								while(allvalue.hasNext())
								{
								
									BasicDBObject singlefield = (BasicDBObject) allvalue.next();
									
									amount = singlefield.getInt("CHARGE");
								}
							
								System.out.println("DO U WANT TO VISIT THIS CAR (Y/N)?");
								cho = input.next();
							}
							
							System.out.println(cho);
							
							if("y".equals(cho)||"Y".equals(cho))
							{
								Date_time date = new Date_time(db);
								
								date.datetime();
								
								
								
								
								int distance = dis.diskstra();
								
								System.out.println("TOTAL DISTANCE OF YOUR JOURNEY IS :  "+distance);
								
								System.out.println("TOTAL AMOUNT IS :  " +amount*distance);
								
								
								Transaction trans = new Transaction(amount*distance);
								
								
								
								
								System.out.println("SELECT THE BRANCH");
								System.out.println("1. SBI");
								System.out.println("2. PNB");
								System.out.println("3. HDFC");
								System.out.println("4. ICICI");
								System.out.println("5. AXIS BANK");
								System.out.println("ENTER CHOICE");
								
								int select = input.nextInt();
								switch(select)
								{
								case 1:
									trans.amount();
									break;
								case 2:
									trans.amount();
									break;
								case 3:
									trans.amount();
									break;
								case 4:
									trans.amount();
									break;
								case 5:
									trans.amount();
									break;
								}
								
								
							}	
							else
							{
								System.out.println(cho);
							}
							
					}
					else
					{
						System.out.println("INCORRECT PASSWORD AND GMAIL");
					}
					break;
				case 4:
					Driver drive = new Driver(db);
					drive.driver_detail();
					Car car = new Car(db);
					car.car_detail();
					
					break;
				case 5:
					System.exit(0);
					break;
				}
				
			}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
	}
}
