package com.Project2.ChattingAppliaction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.text.*;


import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Server implements ActionListener {

    
    private static final String Calender = null;
	JTextField text;
    JPanel a1;
    static Box vertical= Box.createVerticalBox();
    static JFrame f= new JFrame();
    static DataOutputStream dout ;
    
	public Server() {
        f.setLayout(null);

        // panel use for frame ke upar kucha krnar hai to
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        f.add(p1);
//arrow icon work
        ImageIcon i1 = createImageIcon("/com/Project2/icons/3.png", "Description");
        Image i2=i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);
        
        //arrow action
        back.addMouseListener(new MouseAdapter()
           {
              public void mouseClicked(MouseEvent ae)
        	   {
        		  System.exit(0);
        	   }
              });
       //profile icon 
        ImageIcon i4 = createImageIcon("/com/Project2/icons/1.png", "Description");
        Image i5=i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 10, 50, 50);
        p1.add(profile);
        
        //video image icon
        ImageIcon i7 = createImageIcon("/com/Project2/icons/video.png", "Description");
        Image i8=i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300, 20,30, 30);
        p1.add(video);
        
        //phone image icon
        ImageIcon i10 = createImageIcon("/com/Project2/icons/phone.png", "Description");
        Image i11=i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(360, 20, 35, 30);
        p1.add(phone);
        
        // 3icon image 
        ImageIcon i13 = createImageIcon("/com/Project2/icons/3icon.png", "Description");
        Image i14=i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel morevert = new JLabel(i15);
        morevert.setBounds(420, 20, 10, 25);
        p1.add(morevert);

        // if you want to write in frame using JLabel
        //name
        JLabel name= new JLabel("Gaitonde");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        p1.add(name);
        
        //status
        JLabel status= new JLabel("online");
        status.setBounds(110,35,100,18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,14));
        p1.add(status);
        
        //Text Area
        a1= new JPanel();
        a1.setBounds(5, 75, 440, 570);
        f.setUndecorated(true);
        f.add(a1);
        
        //Text field
        text =new JTextField(); 
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));//testsize
        f.add(text);
        
       //test button
        JButton send =new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);//textcolor
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));//testsize
        f.add(send);
        
        f.setSize(450, 700);// frame size
        f.setLocation(200, 50);// frame ka location
        f.getContentPane().setBackground(Color.WHITE);

        f.setVisible(true);// frame visible
    }
    public void actionPerformed(ActionEvent ae)
    {
    	String out = text.getText();
    	
  
    	JPanel p2 =formatLabel(out);
    	
    	
    	a1.setLayout(new BorderLayout());
    	
    	JPanel right = new JPanel(new BorderLayout());
    	right.add(p2,BorderLayout.LINE_END);
    	vertical.add(right);
    	vertical.add(Box.createVerticalStrut(15));
    	
    	
    	
    	a1.add(vertical, BorderLayout.PAGE_START);
    	
    	try {
			dout.writeUTF(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	text.setText("");//after send msg text box get empty
    	f.validate();
    }

    public static JPanel formatLabel(String out)
    {
		JPanel panel= new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
	    output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));
        
		panel.add(output);
		
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        
        panel.add(time);
    	return panel;
    }
    
    
    protected static ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = Server.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        new Server();
        
        try{
        	ServerSocket skt= new ServerSocket(6001);
        	while(true)
        	{
        		Socket s= skt.accept();
        		DataInputStream din =new DataInputStream(s.getInputStream());
        		 dout =new DataOutputStream(s.getOutputStream());
        		
        		while(true)
        		{
        			String msg=din.readUTF();
        			JPanel panel =formatLabel(msg);
        			
        			JPanel left= new JPanel(new BorderLayout());
        			left.add(panel,BorderLayout.LINE_START);
        			vertical.add(left);
        			f.validate();
        			
        		}
        	}
        }catch(Exception e)
        {
        	e.printStackTrace();
        	
        }
        
    }
}
