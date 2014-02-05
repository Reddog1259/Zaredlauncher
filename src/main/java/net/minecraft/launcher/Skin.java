package net.minecraft.launcher;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import net.minecraft.launcher.Launcher;
import net.minecraft.launcher.ui.LauncherPanel;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Skin {
	public Launcher launcher;
	public static void main(String args[]) {
		
	}
	
    public JPanel viewer(String pseudo) { //Genere le skin Minecraft en 2D(fonctionnelle en static)
        
        BufferedImage image = null;
        BufferedImage corps=null;
        BufferedImage tete=null;
        BufferedImage bras=null;
        BufferedImage jambe=null;
        if(pseudo=="/char.png"){
        	try {
                final InputStream in = Launcher.class.getResourceAsStream("/char.png");
                if(in != null)
                   image = ImageIO.read(in);
            }
            catch(final IOException localIOException) {
            	launcher.println(localIOException);
            }
        }else{
        try {
            URL url = new URL(
                    "https://s3.amazonaws.com/MinecraftSkins/"+pseudo+".png");
            image = ImageIO.read(url);
            System.out.println("Télechage le skin de : "+pseudo);
        } catch (IOException e) {
        	launcher.println(e);
        }
        }
        try {
            URL url = new URL(
                    "https://minotar.net/helm/"+pseudo+"/8");
            tete = ImageIO.read(url);
        } catch (IOException e) {
        	launcher.println(e);
        }
        
        corps = image.getSubimage(20, 20, 8, 12);
        bras = image.getSubimage(44, 20, 4, 12);
        jambe = image.getSubimage(4, 20, 4, 12);

        JPanel framed = new JPanel();
        framed.setLayout(null);
        JLabel lbltete = new JLabel(new ImageIcon(scale(tete, 10)));
        JLabel lblcorps = new JLabel(new ImageIcon(scale(corps, 10)));
        JLabel lblbras1 = new JLabel(new ImageIcon(scale(bras, 10)));
        JLabel lblbras2 = new JLabel(new ImageIcon(horizontalflip(scale(bras, 10))));
        JLabel lbljambe1 = new JLabel(new ImageIcon(scale(jambe, 10)));
        JLabel lbljambe2 = new JLabel(new ImageIcon(horizontalflip(scale(jambe, 10))));

        framed.add(lblcorps);
        framed.add(lbltete);
        framed.add(lblbras1);
        framed.add(lblbras2);
        framed.add(lbljambe1);
        framed.add(lbljambe2);

        lblcorps.setBounds(40, 80, 80, 120);
        lbltete.setBounds(40, 0, 80, 80);
        lblbras1.setBounds(0, 80, 40, 120);
        lblbras2.setBounds(120, 80, 40, 120);
        lbljambe1.setBounds(40, 200, 40, 120);
        lbljambe2.setBounds(80, 200, 40, 120);
        framed.setSize(160, 320);
        return framed;
    }
    public BufferedImage scale(BufferedImage bImage, double factor) {
        int destWidth = (int) (bImage.getWidth() * factor);
        int destHeight = (int) (bImage.getHeight() * factor);
//créer l'image de destination
        GraphicsConfiguration configuration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage bImageNew = configuration.createCompatibleImage(destWidth, destHeight);
        Graphics2D graphics = bImageNew.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        //dessiner l'image de destination
        graphics.drawImage(bImage, 0, 0, destWidth, destHeight, 0, 0, bImage.getWidth(), bImage.getHeight(), null);
        graphics.dispose();

        return bImageNew;
    }
    public  BufferedImage horizontalflip(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(w, h, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();
        return dimg;
    }
    public void testskin(String pseudo){
    	Skin skin=new Skin();
    	JFrame frame = new JFrame(pseudo+"'s skin");

    	//2. Optional: What happens when the frame closes?
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	//3. Create components and put them in the frame.
    	//...create emptyLabel...
    	frame.getContentPane().add(skin.viewer(pseudo));

    	//4. Size the frame.
    	frame.pack();

    	//5. Show it.
    	frame.setVisible(true);
    	
    }
    
}

