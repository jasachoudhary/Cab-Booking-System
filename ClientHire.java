import java.io.*;
import java.net.*;
import java.util.Scanner;

class ClientHire
{
	Socket skt;
	ClientHire(String ip, int port) throws Exception
	{
		//requesting a connection
		skt = new Socket(ip, port);
	}
	
	void signup_detail(DataInputStream din,DataOutputStream dout)
	{
		try
		{
			Scanner input = new Scanner(System.in);

			System.out.println("ENTER THE FIRST NAME");
			String firstname = input.next();
			dout.writeUTF(firstname);
			System.out.println("ENTER THE LAST NAME");
			String lastname = input.next();
			dout.writeUTF(lastname);
			System.out.println("ENTER THE AGE");
			int age = input.nextInt();
			dout.writeInt(age);
			System.out.println("ENTER GMAIL ID");
			String gmail = input.next();
			dout.writeUTF(gmail);
			System.out.println("ENTER THE PHONE NUMBER");
			String phonenum = input.next();
			dout.writeUTF(phonenum);
			System.out.println("ENTER THE PASSWORD");
			String password = input.next();
			dout.writeUTF(password);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	
	void driver_detail(DataInputStream din,DataOutputStream dout)
	{
		try
		{

			Scanner input = new Scanner(System.in);

			System.out.println("ENTER THE FIRST NAME");
			String firstname = input.next();
			dout.writeUTF(firstname);
			System.out.println("ENTER THE LAST NAME");
			String lastname = input.next();
			dout.writeUTF(lastname);
			System.out.println("ENTER THE AGE");
			int age = input.nextInt();
			dout.writeInt(age);
			System.out.println("ENTER THE PHONE NUMBER");
			String phone = input.next();
			dout.writeUTF(phone);
			System.out.println("ENTER THE LICENSE NUMBER");
			String license_no = input.next();
			dout.writeUTF(license_no);
			System.out.println("ENTER THE ADDRESS");
			String address = input.next();
			dout.writeUTF(address);
			System.out.println("ENTER THE PINCODE");
			String pincode = input.next();
			dout.writeUTF(pincode);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	
	void car_detail(DataInputStream din,DataOutputStream dout)
	{
		try 
		{
	
			Scanner input = new Scanner(System.in);

			System.out.println("ENTER THE CAR NAME");
			String name = input.next();
			dout.writeUTF(name);
			System.out.println("ENTER THE MILEGE");
			int milege = input.nextInt();
			dout.writeInt(milege);
			System.out.println("ENTER THE TOP SPEED");
			int speed  = input.nextInt();
			dout.writeInt(speed);
			System.out.println("ENTER THE PASSENGER CAPACITY");
			int capacity = input.nextInt();
			dout.writeInt(capacity);
			System.out.println("ENTER THE CHARGE PER KILOMETER");
			int charge = input.nextInt();
			dout.writeInt(charge);
			System.out.println("ENTER THE COLOUR");
			String colour = input.next();
			dout.writeUTF(colour);
			System.out.println("ENTER THE AVAILABILITY");
			String available = input.next();
			dout.writeUTF(available);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}

	void interact() throws Exception
	{
		InputStream in = skt.getInputStream();
		OutputStream out = skt.getOutputStream();

		DataInputStream din = new DataInputStream(in);
		DataOutputStream dout = new DataOutputStream(out);

		Scanner input = new Scanner(System.in);
		int choice= 0;
		while(true)
		{
			//System.out.println("1. LOGIN AS ADMINISTRATOR");
			System.out.println("1. SIGNUP USER");
			System.out.println("2. LOGIN USER");
			System.out.println("3. INSERT NEW CAR");
			System.out.println("4. EXIT");
			System.out.println("ENTER YOUR CHOICE");
	
			choice = input.nextInt();

			dout.writeInt(choice);
	
			switch(choice)
			{
			case 1:
				signup_detail(din,dout);
				break;
			case 2:
				System.out.println("ENTER THE GMAIL");
				String gmail = input.next();
				dout.writeUTF(gmail);
				System.out.println("ENTER THE PASSWORD");
				String password = input.next();
				dout.writeUTF(password);
				if(din.readInt()==1)
				{
					System.out.println("YOU ARE LOGGED IN");
				
					System.out.println("*** THERE ARE MANY CAR PROPERTIES ***");
					String xy =din.readUTF();
					while("n".equals(xy)||"N".equals(xy))
					{
						boolean b = din.readBoolean();
						boolean b1 = true;
						//System.out.println("boolean value : "+b);
						while(b==b1)
						{
							System.out.println("\t"+din.readUTF());
							//System.out.println("boolean : "+b);
							b = din.readBoolean();
						}
						//System.out.println("out from while loop");
						System.out.println("ENTER THE CAR NAME WHICH DO U WANT  SEE THE PROPERTY (case sensitive) ");
						String name = input.next();
						dout.writeUTF(name);
					
						while(din.readBoolean())
						{
							
							String car_prop;
							int value;
							car_prop = din.readUTF();
							System.out.println("\t NAME                "+"\t : "+car_prop);
							value = din.readInt();
							System.out.println("\t MILEGE              "+"\t : "+value);
							value = din.readInt();
							System.out.println("\t TOPSPEED            "+"\t : "+value);
							System.out.println("\t PASSENGER CAPACITY  "+"\t : "+din.readInt());
							System.out.println("\t CHARGE              "+"\t : "+din.readInt());
							System.out.println("\t COLOUR              "+"\t : "+din.readUTF());
							System.out.println("\t AVAILABLE	   "+"\t : "+din.readUTF());
						}


						System.out.println("DO U WANT TO VISIT THIS CAR (Y/N)?");

						String cho = input.next();
	
						dout.writeUTF(cho);
						xy =din.readUTF();
					}
						String str = din.readUTF();
						if("y".equals(str)||"Y".equals(str))
						{
							System.out.println("ENTER THE DATE (DD-MM-YYYY)?");
							while(true)
							{
								String date = input.next();
								dout.writeUTF(date);
								int x = din.readInt();
								if(x>0)
								{
									System.out.println("IN THIS TIME CAR IS AVAILABLE");
									break;
								}
								else
									System.out.println("ENTER THE CORRECT DATE(DD-MM-YYYY)?");

							}
							
							System.out.println("enter the source station");						
							
							int source = input.nextInt();
							dout.writeInt(source);
							
							System.out.println("enter the destination");
								
							int destination = input.nextInt();
							dout.writeInt(destination);

							int distance = din.readInt();
							int amount = din.readInt();
							System.out.println("TOTAL DISTANCE OF YOUR JOURNEY IS :  "+distance);
					
							System.out.println("TOTAL AMOUNT IS :  " +amount*distance);

							System.out.println("SELECT THE BRANCH");
							System.out.println("1. SBI");
							System.out.println("2. PNB");
							System.out.println("3. HDFC");
							System.out.println("4. ICICI");
							System.out.println("5. AXIS BANK");
							System.out.println("ENTER CHOICE");
	
							int select = input.nextInt();
							
							dout.writeInt(select);
							
							System.out.println("SELECT THE PAYMENT MODE");
							System.out.println("1. DEBIT/CREDIT CARD");
							System.out.println("2. NETBANKING");
							System.out.println("ENTER CHOICE");
							int ch = input.nextInt();
							dout.writeInt(ch);

							switch(ch)
							{
							case 1:
								System.out.println("ENTER CREDIT/DEBIT CARD NUMBER");
								while(true)
								{
									
									String card_num = input.next();
								
									int len = card_num.length();
									boolean flag=false;
									try 
									{
										if(len==16)
										{
											if(card_num.matches("[0-9]+"))
												break;
											else
												flag=true;
										}
										else
											flag =true;
										if(flag)
										{
											System.out.println("INVALID CARD NUMBER");
											System.out.println("ENTER THE VALID CARD NUMBER");
										}
									}
									catch(NumberFormatException ex)
									{
										System.out.println(ex);
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
								break;
							case 2:
								System.out.println("ENTER USER NAME ");
								String name = input.next();
								System.out.println("ENTER PASSWORD");
								password =input.next();
								System.out.println("SUCCESSFULL TRANSACTION");
								break;
							}
			
						}
						else
						{
		
						}
					
				}
				else
				{
					System.out.println("INCORRECT PASSWORD AND GMAIL");
				}


				break;
			case 3:
				System.out.println("PLEASE ENTER THE DRIVER DETAILS");
				driver_detail(din,dout);
				System.out.println("ENTER THE CAR DETAILS");
				car_detail(din,dout);
				break;
			case 4:
				System.exit(0);
				break;
			}
		}

	}

	void close() throws Exception
	{
	skt.close();
	}

	public static void main(String args[])
	{
		try
		{
			ClientHire c  = new ClientHire("127.0.0.1", 5555);
			c.interact(); 
			c.close();
		} 
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}

