import com.mongodb.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

import java.text.SimpleDateFormat;
import java.util.Date;

class Main
{
	private DB db1;
	private DBCollection user_collection,car_collection,driver_collection;
	private DBCursor  allvalue;
	Scanner input = new Scanner(System.in);
	BasicDBObject query;
	
	//Socket skt;	

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
	void secure_signup(Socket skt)
	{
		try
		{
			InputStream in = skt.getInputStream();
			OutputStream out = skt.getOutputStream();

			//DataInputStream and DataOutputStream
			//extend data transfer to Java datatypes and Strings. 
			DataInputStream din = new DataInputStream(in);
			DataOutputStream dout = new DataOutputStream(out);

			String firstname = din.readUTF();
			String lastname = din.readUTF();
			int age = din.readInt();
			String gmail = din.readUTF();
			String phonenum = din.readUTF();
			String password = din.readUTF();
			query= new BasicDBObject().append("FIRSTNAME",firstname).append("LASTNAME",lastname).append("AGE",age).append  ("GMAIL_ID",gmail).append("PHONE_NUMBER",phonenum).append("PASSWORD",password);
	
			System.out.println("hiii");
			user_collection.insert(query);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}


	int secure_login(Socket skt)
	{
		
		try
		{
			InputStream in = skt.getInputStream();
			OutputStream out = skt.getOutputStream();

			//DataInputStream and DataOutputStream
			//extend data transfer to Java datatypes and Strings. 
			DataInputStream din = new DataInputStream(in);
			DataOutputStream dout = new DataOutputStream(out);

			String gmail = din.readUTF();
			String password = din.readUTF();
			allvalue = user_collection.find(new BasicDBObject("GMAIL_ID",gmail).append("PASSWORD",password));
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		if(allvalue.hasNext())
		{
				
			return 1;
		}
		else
		{
			return 0;
		}
	}


	void secure_driverdetail(Socket skt)
	{
		
		try
		{
			InputStream in = skt.getInputStream();
			OutputStream out = skt.getOutputStream();

			//DataInputStream and DataOutputStream
			//extend data transfer to Java datatypes and Strings. 
			DataInputStream din = new DataInputStream(in);
			DataOutputStream dout = new DataOutputStream(out);

			String firstname = din.readUTF();
			String lastname = din.readUTF();
			int age = din.readInt();
			String phone = din.readUTF();
			String license_no = din.readUTF();
			String address = din.readUTF();
			String pincode = din.readUTF();
		
			query = new BasicDBObject("FIRSTNAME",firstname).append("LASTNAME",lastname).append("AGE",age).append("PHONE_NO",phone).append("LICENSE_NO",license_no).append("ADDRESS",address).append("PINCODE",pincode);
		
			driver_collection.insert(query);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}	
	}

	void secure_cardetail(Socket skt)
	{

		try
		{
	
			InputStream in = skt.getInputStream();
			OutputStream out = skt.getOutputStream();

			//DataInputStream and DataOutputStream
			//extend data transfer to Java datatypes and Strings. 
			DataInputStream din = new DataInputStream(in);
			DataOutputStream dout = new DataOutputStream(out);

			String name =din.readUTF();
			int milege = din.readInt();
			int speed = din.readInt();
			int capacity = din.readInt();
			int charge = din.readInt();
			String colour = din.readUTF();
			String available = din.readUTF();
		
		
			query = new BasicDBObject("NAME",name).append("MILEGE",milege).append("TOPSPEED",speed).append("PASSENGER_CAPACITY",capacity).append("CHARGE",charge).append("COLOUR",colour).append("AVAILABLE",available);
		
			car_collection.insert(query);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
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
		int diskstra(Socket skt)
		{
			try
			{
				InputStream in = skt.getInputStream();
				OutputStream out = skt.getOutputStream();

				//DataInputStream and DataOutputStream
				//extend data transfer to Java datatypes and Strings. 
				DataInputStream din = new DataInputStream(in);
				DataOutputStream dout = new DataOutputStream(out);
			
				int source,current = 0,currentdist=0,tot = 0,destination;
				for(int i =0;i<vertex;i++)
					state[i] = 0;
				//System.out.println("enter the source ");
			
				source = din.readInt();
				//System.out.println("enter the destination");
				destination = din.readInt();
			
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
			catch(Exception ex)
			{
				System.out.println(ex);
			}
			return 0;// ese hi likha hua h
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
	public void signup(Socket s)
	{
		secure_signup(s);
	}
}

class Login extends Main
{
	public Login(DB db)
	{
		super(db);
	}
	int login(Socket s)
	{
		return secure_login(s);		
	}
}


class Driver extends Main
{
	
	public Driver(DB db)
	{
		super(db);
	}
	void driver_detail(Socket s)
	{
		secure_driverdetail(s);
		
	}
}


class Car extends Main
{
	public Car(DB db)
	{
		     super(db);
	}
	void car_detail(Socket s)
	{
		secure_cardetail(s);	
	}
}


class Date_time extends Main
{
	
	Date_time(DB db)
	{
		super(db);
	}
	void datetime(Socket skt)
	{
		try
		{
			
			InputStream in = skt.getInputStream();
			OutputStream out = skt.getOutputStream();

			//DataInputStream and DataOutputStream
			//extend data transfer to Java datatypes and Strings. 
			DataInputStream din = new DataInputStream(in);
			DataOutputStream dout = new DataOutputStream(out);

		
			String user_date;
			//System.out.println("ENTER THE DATE (DD-MM-YYYY)?");
			while(true)
			{	
				user_date = din.readUTF();
				
				Date myDate = new Date();
	    	    		SimpleDateFormat dmyFormat = new SimpleDateFormat("dd-MM-yyyy");
	    	    		String dmy = dmyFormat.format(myDate);
				
				int x = user_date.compareTo(dmy);				
				dout.writeInt(x);
				if(x>0)
				{
					//System.out.println("IN THIS TIME CAR IS AVAILABLE");
					break;
				}
				else
				{
					//System.out.println("ENTER THE CORRECT DATE(DD-MM-YYYY)?");
				}
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
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
		/*String str;
		//System.out.println("ENTER CREDIT/DEBIT CARD NUMBER");
		try
		{
			InputStream in = skt.getInputStream();
			OutputStream out = skt.getOutputStream();

			//DataInputStream and DataOutputStream
			//extend data transfer to Java datatypes and Strings. 
			DataInputStream din = new DataInputStream(in);
			DataOutputStream dout = new DataOutputStream(out);

			
			while(true)
			{
				//str = din.readUFT();
				int len;
				len=str.length();
				//dout.writeInt(len)
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
		catch(Exception ex)
		{
			System.out.println(ex);
		}*/
		
	}
	
	void netbanking()
	{
		/*try
		{
			InputStream in = skt.getInputStream();
			OutputStream out = skt.getOutputStream();

			//DataInputStream and DataOutputStream
			//extend data transfer to Java datatypes and Strings. 
			DataInputStream din = new DataInputStream(in);
			DataOutputStream dout = new DataOutputStream(out);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		System.out.println("ENTER USER NAME ");
		String name = input.next();
		System.out.println("ENTER PASSWORD");
		String password =input.next();
		System.out.println("SUCCESSFULL TRANSACTION");*/
	}
	
	void amount(Socket skt)
	{
		/*System.out.println("SELECT THE PAYMENT MODE");
		System.out.println("1. DEBIT/CREDIT CARD");
		System.out.println("2. NETBANKING");
		System.out.println("ENTER CHOICE");*/
		
		try
		{

			InputStream in = skt.getInputStream();
			OutputStream out = skt.getOutputStream();

			//DataInputStream and DataOutputStream
			//extend data transfer to Java datatypes and Strings. 
			DataInputStream din = new DataInputStream(in);
			DataOutputStream dout = new DataOutputStream(out);
			int ch = din.readInt();
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
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}

}

public class ServerHire implements Runnable
{
 ServerSocket port;
 Thread t;
 boolean flag;
 Diskstra dis;
 ServerHire(int p) throws Exception
 { 
    port = new ServerSocket(p);
	dis = new Diskstra();
	dis.createnetwork();
    flag = true;
    t = new Thread(this);
    t.start();
 }

 public void run()
 {

  while(flag)
  {
    acceptConnection(dis);
  }
  try
  {
    port.close();
  }
  catch(Exception ex)
  {
   System.out.println(ex);
  }
 }//run

 void acceptConnection(Diskstra dis)
 {
  try
  { 
   
   System.out.println("accept connection ");
   Socket s = port.accept();
   new ClientProcessor(s,dis); //concurrent processing per client
  }
  catch(Exception ex) 
  {
   System.out.println(ex);
  }
 }

 public static void main(String args[])
 {
  try
  {
   ServerHire s = new ServerHire(5555);
  }
  catch(Exception ex)
  {
   System.out.println(ex);
  }
 }
}//Server

class ClientProcessor extends Thread
{
	 Socket skt;
	 DB db;
	 Diskstra dis;
	 DBCollection car_collection;
	 ClientProcessor(Socket s,Diskstra d)
	 {
		skt = s;
		dis = d;
		try
		{
			MongoClient mongo = new MongoClient("localhost",27017);
			
			db = mongo.getDB("car");
			
			car_collection= db.getCollection("car");
			
			
			
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
	
		skt = s;
		start(); 
	 }

	 public void run()
	 {
	  try
	  {
	   
	   //InputStream and OutputStream
	   //limit data transfer to bytes only.
	   InputStream in = skt.getInputStream();
	   OutputStream out = skt.getOutputStream();

	   //DataInputStream and DataOutputStream
	   //extend data transfer to Java datatypes and Strings. 
	   DataInputStream din = new DataInputStream(in);
	   DataOutputStream dout = new DataOutputStream(out);

	   int choice;
	   while(true)
	   {
	    choice = din.readInt();
	   
	    switch(choice)
	    {
		case 1:
			Signup sign = new Signup(db);
			//System.out.println("hiiii after object");
			sign.signup(skt);
			break;
		case 2:
			Login log = new Login(db);
			int flag = log.login(skt);
			dout.writeInt(flag);				
			if(flag==1)
			{
				//System.out.println("YOU ARE LOGGED IN");
				
				//System.out.println("*** THERE ARE MANY CAR PROPERTIES ***");
				
				DBCursor allvalue = car_collection.find();
				
				String cho = "n";
				dout.writeUTF(cho);
				int amount = 0;
				String car_name;
				while("n".equals(cho)||"N".equals(cho))
				{
					
					BasicDBObject obj;
					while(allvalue.hasNext())
					{
						dout.writeBoolean(allvalue.hasNext());
						//System.out.println("\t"+allvalue.next().removeField("NAME"));
						System.out.println("hiii" + allvalue.hasNext());
						obj = (BasicDBObject)allvalue.next();

						car_name = obj.getString("NAME");
						dout.writeUTF(car_name);
					}
					dout.writeBoolean(allvalue.hasNext());
					System.out.println("out from while loop"+allvalue.hasNext());
					
					String select_carname = din.readUTF();
					allvalue = car_collection.find(new BasicDBObject("NAME",select_carname));	
									
					while(allvalue.hasNext())
					{
						dout.writeBoolean(allvalue.hasNext());
						
						obj = (BasicDBObject)allvalue.next();
						
						String name = obj.getString("NAME");
						dout.writeUTF(name);
						int milege = obj.getInt("MILEGE");
						dout.writeInt(milege);
						int speed = obj.getInt("TOPSPEED");
						dout.writeInt(speed);
						int capacity = obj.getInt("PASSENGER_CAPACITY");
						dout.writeInt(capacity);
						int charge = obj.getInt("CHARGE");
						dout.writeInt(charge);
						String colour = obj.getString("COLOUR");
						dout.writeUTF(colour);
						String available = obj.getString("AVAILABLE");
						dout.writeUTF(available);
					}
					dout.writeBoolean(allvalue.hasNext());

					allvalue = car_collection.find(new BasicDBObject("NAME",select_carname));
					
					while(allvalue.hasNext())
					{
					
						BasicDBObject singlefield = (BasicDBObject) allvalue.next();
						
						amount = singlefield.getInt("CHARGE");
					}
					allvalue = car_collection.find();
				
					//System.out.println("DO U WANT TO VISIT THIS CAR (Y/N)?");
					cho = din.readUTF();
					dout.writeUTF(cho);
				}
				
				dout.writeUTF(cho);
				if("y".equals(cho)||"Y".equals(cho))
				{
					Date_time date = new Date_time(db);
					
					date.datetime(skt);
					
					
					
					
					int distance = dis.diskstra(skt);
					
					//System.out.println("TOTAL DISTANCE OF YOUR JOURNEY IS :  "+distance);
					
					//System.out.println("TOTAL AMOUNT IS :  " +amount*distance);
					
					dout.writeInt(distance);
					
					dout.writeInt(amount);
					
					Transaction trans = new Transaction(amount*distance);
					
					
					
					int select = din.readInt();
					switch(select)
					{
					case 1:
						trans.amount(skt);
						break;
					case 2:
						trans.amount(skt);
						break;
					case 3:
						trans.amount(skt);
						break;
					case 4:
						trans.amount(skt);
						break;
					case 5:
						trans.amount(skt);
						break;
					}
					
					
				}
					
				else
				{
					//System.out.println(cho);
				}
			}		
			else
			{
				System.out.println("INCORRECT PASSWORD AND GMAIL");
			}
			break;
		case 3:
			Driver drive = new Driver(db);
			drive.driver_detail(skt);
			Car car = new Car(db);
			car.car_detail(skt);
			break;
		case 4:
			System.exit(0);
			break;
			
	    }
	    
	   }//while
	   //skt.close();
	  }
	  catch(Exception ex)
	  {
	   System.out.println(ex);
	  }
	 }//run
}//ClientProcessor

