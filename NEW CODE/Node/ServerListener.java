import javax.swing.*;
import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.Vector;
class ServerListener extends Thread
{
	
	ServerSocket ss;
	int port_id=0;
	ServerListener(int port)
	{
		super();
		port_id=port;
		start();
	}
	
	public void run()
	{
		try
		{
			ss=new ServerSocket(port_id);
			while(true)
			{
				Socket s=ss.accept();
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				String req=(String)ois.readObject();
				System.out.println("REQ "+req);
				
				if(req.equals("UPDATE_NODES"))
				{
					Vector node_info=(Vector)ois.readObject();
					if(Index.jcmb!=null)
					{
						if(Index.jcmb.getItemCount()>0)
						{
							Index.jcmb.removeAllItems();	
						}
						
						
						for(int i=0;i<node_info.size();i++)
						{
							if(!Index.Usr_Name.equalsIgnoreCase(node_info.elementAt(i).toString()))
							{
								Index.jcmb.addItem(node_info.elementAt(i));	
							}
							
						}
					}
					
					
				}
				else if(req.equals("FRWD"))
				{
					boolean flg=true;
					String snd_dest = (String)ois.readObject();
					String prev_peer = (String)ois.readObject();
					String file_name = (String)ois.readObject();
					byte[] file_data= (byte[])ois.readObject();
					String Usr_Nme=Index.Usr_Name;
					
					
					String split_snd_dest[]=snd_dest.split("#");
					if(split_snd_dest[1].equals(Index.Usr_Name))
					{
						flg=false;
						Index.jta3.setText("RECEIVED FILE /n SENDER : "+split_snd_dest[0]);
						
						File f=new File(Index.Usr_Name);
						System.out.println(f.getAbsolutePath());
						if(!f.exists())
						{
							f.mkdir();
						}
						FileOutputStream fout=new FileOutputStream(f+"/"+file_name);
						fout.write(file_data);
						fout.close();
						//JOptionPane.showMessageDialog(null,"");
				
					}
					else
					{
						if(Index.jcb.isSelected())
						{
							snd_dest=split_snd_dest[1]+"#"+split_snd_dest[0];
							
						}
					}
					
					
					
									
					Index.jta2.setText("Received file from node "+prev_peer);
					
					if(flg)
					{
						int ch=0;
						String IP="";
						FileInputStream fin=new FileInputStream("ServerIP.txt");
						while((ch=fin.read())!=-1)
						IP+=(char)ch;
						IP.trim();
												
						Socket ss=new Socket(IP,6996);
						ObjectOutputStream oos1=new ObjectOutputStream(ss.getOutputStream());
						ObjectInputStream ois1=new ObjectInputStream(ss.getInputStream());
						oos1.writeObject("FRWD");
						oos1.writeObject(snd_dest);
						oos1.writeObject(Usr_Nme);
						
						String resp=(String)ois1.readObject();
						ss.close();
						
						if(resp.indexOf("DROP PACKET")!=-1)
						{
							Index.jta21.setText("PACKET DROPPED");
						}
						else if(resp.indexOf("NO ROUTE AVAILABLE!!!")!=-1)
						{
							Index.jta21.setText("NO ROUTE AVAILABLE");
						}
						else 
						{
							String split_resp[]=resp.split("#");
						
							String nxt_peer=split_resp[0].trim();
							String nxt_peer_ip=split_resp[1].trim();
							int port = Integer.parseInt(split_resp[2].trim());
							
							Index.jta21.setText("Sending file using node "+nxt_peer);
							
							sendDataToNxtPeer(snd_dest,Index.Usr_Name,file_data,nxt_peer_ip,port,file_name);
						
						}
					}
				}
			}
		}
		catch(NullPointerException npe)
		{
			npe.printStackTrace();
		}
		catch(BindException be)
		{
			System.exit(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public void sendDataToNxtPeer(String snd_dest,String cur_node,byte[] file,String nxt_pr_ip,int nxt_pr_prt,String fil_nme)
	{
		try
		{
			Socket s=new Socket(nxt_pr_ip,nxt_pr_prt);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			oos.writeObject("FRWD");
			oos.writeObject(snd_dest);
			oos.writeObject(cur_node);
			oos.writeObject(fil_nme);
			oos.writeObject(file);
			s.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
