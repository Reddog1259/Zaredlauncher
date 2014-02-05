package net.minecraft.launcher;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.JButton;

public class ZaredButton {

	public JButton Forum(){
		JButton forum=new JButton();
		forum.setText("Forum");
		forum.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            	openURL("http://maohicraft.crystal-serv.com/phpBB3/index.php");
            }
        });
		return forum;
	}
	public JButton Site(){
		JButton site=new JButton();
		site.setText("Site");
		site.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            	openURL("http://maohicraft.crystal-serv.com/");
            }
        });
		return site;
	}
	public JButton Dynmap(){
		JButton Dynmap=new JButton();
		Dynmap.setText("Dynmap");
		Dynmap.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            	openURL("http://maohisrv.crystal-serv.com:25607/");
            }
        });
		return Dynmap;
	}
	public JButton play(){
		JButton play=new JButton();
		play.setText("Play");
		play.setEnabled(false);
		return play;	
		
	}
	public JButton logout(){
		JButton logout=new JButton();
		logout.setText("Log out");
		logout.setEnabled(false);
		return logout;	
	}
	
	
	public void openURL(String url){
    	URI uri = URI.create(url);
    	try {
			Desktop.getDesktop().browse(uri);
		} catch (IOException e1) {
		}
	}
}
