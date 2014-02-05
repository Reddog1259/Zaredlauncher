package net.minecraft.launcher.query;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Reddog
 */
public class InformationServer {

    public static boolean Outser = true;

    public static void main(String args[]) {
        InformationServer infoser = new InformationServer();
        JPanel infos = infoser.Info();

        JFrame frametest = new JFrame();

        frametest.setLayout(null);
        frametest.setPreferredSize(new Dimension(323, 540));
        frametest.add(infos);
        
        JPanel headlist = infoser.Playerheadpanel(frametest.getX(), frametest.getY());
        infos.setBounds(frametest.getX() + 55, frametest.getY() + 20, 200, 30);
        frametest.add(headlist);
        headlist.setBounds(frametest.getX() + 5, frametest.getY() + 50, 300, 485);
        frametest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frametest.pack();
        frametest.setVisible(true);
    }
    public static JLabel isonline = new JLabel();
    public static Color color = Color.RED;
    public JPanel Info() {
        JPanel infoframe = new JPanel();
        JLabel nbrperso = new JLabel();
        
        
        String s = new String();
        MCQuery query = new MCQuery("maohisrv.crystal-serv.com", 25605);

        int onlinep;
        int maxp;
        //basic status
        try {
            while (Outser) {

                QueryResponse rep = query.basicStat();

                onlinep = rep.getOnlinePlayers();
                maxp = rep.getMaxPlayers();
                if (onlinep > 1) {
                    s = "s";
                } else {
                    s = "";
                }
                isonline.setText("Serveur en Ligne");
                color = Color.GREEN;
                nbrperso.setText(onlinep + "/" + maxp + " Joueur" + s);

            }
        } catch (Exception e) {
        }
        try{
        	infoframe.add(isonline);
            infoframe.add(nbrperso);
            isonline.setForeground(color);
        } catch (Exception e) {
        }
        
        return infoframe;
    }

    public JPanel Playerheadpanel(int framex, int framey) {
        InformationServer infoser = new InformationServer();
        JPanel minihead = new JPanel();
        JPanel headlist = new JPanel();
        MCQuery query = new MCQuery("maohisrv.crystal-serv.com", 25605);
        ArrayList status;
        status = query.fullStat().getPlayerList();
        headlist.setLayout(null);
        if (status.size() > 11) {
            int x = framex;
            int y = framey;
            for (int i = 0; i < 11; i++) {
                if (x >= 310 - 55) {
                    y = y + 101;
                    x = framex;
                }
                JPanel[] head = new JPanel[11];

                head[i] = infoser.Playerhead(status.get(i).toString(), 11, true);
                head[i].setBounds(x, y, 96, 96);
                head[i].setToolTipText(status.get(i).toString());
                headlist.add(head[i]);
                head = null;
                x = x + 101;
                System.out.println("x : " + x);
                System.out.println("y : " + y);
            }
            if (status.size() < 47) {
                minihead.setBounds(x, y, 96, 96);
                minihead.setLayout(null);
                x = 0;
                y = 0;
                minihead.setToolTipText("<html>");
                for (int i = 12; i < status.size(); i++) {
                    if (x >= 96) {
                        y = y + 16;
                        x = framex;
                    }
                    JPanel[] head = new JPanel[status.size()];

                    head[i] = infoser.Playerhead(status.get(i).toString(), 2, false);
                    head[i].setBounds(x, y, 16, 20);
                    minihead.setToolTipText(minihead.getToolTipText() + status.get(i).toString() + "<br>");
                    minihead.add(head[i]);
                    head = null;
                    x = x + 16;
                    System.out.println("x : " + x);
                    System.out.println("y : " + y);
                }
                minihead.setToolTipText(minihead.getToolTipText() + "</html>");
                headlist.add(minihead);

            } else {
                minihead.setBounds(x, y, 96, 96);
                minihead.setLayout(null);
                x = 0;
                y = 0;
                minihead.setToolTipText("<html>");
                for (int i = 12; i < 47; i++) {
                    if (x >= 96) {
                        y = y + 16;
                        x = framex;
                    }
                    JPanel[] head = new JPanel[47];

                    head[i] = infoser.Playerhead(status.get(i).toString(), 2, false);
                    head[i].setBounds(x, y, 16, 20);
                    minihead.setToolTipText(minihead.getToolTipText() + status.get(i).toString() + "<br>");
                    minihead.add(head[i]);
                    head = null;
                    x = x + 16;
                    System.out.println("x : " + x);
                    System.out.println("y : " + y);
                }
                minihead.setToolTipText(minihead.getToolTipText() + "</html>");
                headlist.add(minihead);
            }
        } else {
            int x = framex;
            int y = framey;
            for (int i = 0; i < status.size(); i++) {
                if (x >= 310 - 55) {
                    y = y + 101;
                    x = framex;
                }
                JPanel[] head = new JPanel[status.size()];

                head[i] = infoser.Playerhead(status.get(i).toString(), 11, true);
                head[i].setBounds(x, y, 96, 96);
                head[i].setToolTipText(status.get(i).toString());
                headlist.add(head[i]);
                head = null;
                x = x + 101;
                System.out.println("x : " + x);
                System.out.println("y : " + y);
            }
        }

        return headlist;
    }

    public JPanel Playerhead(String pseudo, int multi, boolean pseudotool) {
        BufferedImage tete = null;
        JPanel ph = new JPanel();
        try {
            URL url = new URL(
            		"https://minotar.net/helm/"+pseudo+"/8");
            tete = ImageIO.read(url);
            System.out.println("Télechage le skin de : " + pseudo);
        } catch (IOException e) {

        }
        JLabel lbltete = new JLabel(new ImageIcon(scale(tete, multi)));
        ph.add(lbltete);
        if (pseudotool) {
            lbltete.setToolTipText(pseudo);
        }
        return ph;
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
}
