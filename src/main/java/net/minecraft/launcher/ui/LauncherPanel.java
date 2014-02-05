package net.minecraft.launcher.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import net.minecraft.launcher.Launcher;
import net.minecraft.launcher.LauncherConstants;
import net.minecraft.launcher.Skin;
import net.minecraft.launcher.ZaredButton;
import net.minecraft.launcher.query.InformationServer;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

public class LauncherPanel extends JPanel {
    public static final String CARD_DIRT_BACKGROUND = "loading";
    public static final String CARD_LOGIN = "login";
    public static final String CARD_LAUNCHER = "launcher";
    private final CardLayout cardLayout;
    private final LauncherTabPanel tabPanel;
    private final BottomBarPanel bottomBar;
    private JProgressBar progressBar;
    private final Launcher launcher;
    private final JPanel loginPanel;
	public ZaredButton btnserv;
	public JButton btnfofo;
	public JButton btnsite;   
	public JButton btndmap;
	public JLabel pseudotxt=new JLabel("",0);
	public JButton logt=new JButton("Log Out");
	public JButton play=new JButton("Play");
	public JPanel skinviewer=new JPanel();
	public JPanel leskin=new JPanel();
	
    public LauncherPanel(final Launcher launcher) {
        this.launcher = launcher;
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        progressBar = new JProgressBar();
        bottomBar = new BottomBarPanel(launcher);
        tabPanel = new LauncherTabPanel(launcher);
        loginPanel = new TexturedPanel("/dirt.png");
        createInterface();
    }

    protected JPanel createDirtInterface() {
        return new TexturedPanel("/dirt.png");
    }
    public JPanel createZaredInterface(){
    	InformationServer infoser = new InformationServer();
    	JPanel headlist = infoser.Playerheadpanel(0, 0);
    	JPanel infos = infoser.Info();
    	skinviewer.setLayout(null);
    	JPanel panel = new JPanel();
    	panel.setLayout(null);
    	btnserv=new ZaredButton();
    	progressBar = new JProgressBar();
    	Skin skin=new Skin();
    	pseudotxt.setText("Steve");
    	logt.setText("Log out");
    	btnfofo=btnserv.Forum();
    	btnsite=btnserv.Site();
    	btndmap=btnserv.Dynmap();
    	logt=btnserv.logout();
    	play=btnserv.play();
    	play.setText("Play");
    	try{
    		leskin=skin.viewer("/char.png");
    		skinviewer.add(leskin);
    		leskin.setBounds(0, 0, 160, 320);
    	}catch(Exception e){
    		getLauncher().println(e);
    	}
    	play.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                getLauncher().getVersionManager().getExecutorService().submit(new Runnable() {
                    public void run() {
                    	 getLauncher().getGameLauncher().playGame();
                       play.setEnabled(false);
                       logt.setEnabled(false);
                    }
                });
            }
        });
    	logt.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            		 getLauncher().getProfileManager().getSelectedProfile().setPlayerUUID(null);
            		 getLauncher().getProfileManager().trimAuthDatabase();
            		 getLauncher(). showLoginPrompt();
       	       
            	}         
        });
    	
    	panel.add(headlist);
    	panel.add(infos);
    	panel.add(skinviewer);
    	panel.add(play);
    	panel.add(logt);
    	panel.add(pseudotxt);
    	panel.add(btnfofo);
    	panel.add(btnsite);
    	panel.add(btndmap);
    	panel.add(progressBar);

        progressBar.setVisible(true);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        
        headlist.setBounds(540+5, 20+50, 300, 485);
        infos.setBounds(540+ 55, 40,200, 30);
        play.setBounds(330, 460, 200, 60);
        logt.setBounds(360, 390, 150, 30);
        pseudotxt.setBounds(350,15,160,20);
        btnfofo.setBounds(70, 50, 210, 100);
        btnsite.setBounds(70, 220, 210, 100);
        btndmap.setBounds(70, 390, 210, 100);
        
        progressBar.setBounds(350, 375, 160, 10);
        skinviewer.setBounds(350, 50, 160, 320);
    	
    	
    	
    	return panel;
    }
    protected void createInterface() {
        add(createZaredInterface(), "launcher");
        add(createDirtInterface(), "loading");
        add(createLoginInterface(), "login");
    }

    protected JPanel createLauncherInterface() {
        final JPanel result = new JPanel(new BorderLayout());

        tabPanel.getBlog().setPage(LauncherConstants.URL_BLOG);

        final JPanel topWrapper = new JPanel();
        topWrapper.setLayout(new BorderLayout());
        //topWrapper.add(tabPanel, "Center");
        topWrapper.add(progressBar, "South");

        progressBar.setVisible(false);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);

        result.add(topWrapper, "Center");
        result.add(bottomBar, "South");

        return result;
    }

    protected JPanel createLoginInterface() {
        loginPanel.setLayout(new GridBagLayout());
        return loginPanel;
    }

    public BottomBarPanel getBottomBar() {
        return bottomBar;
    }

    public Launcher getLauncher() {
        return launcher;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public LauncherTabPanel getTabPanel() {
        return tabPanel;
    }

    public void setCard(final String card, final JPanel additional) {
        if(card.equals("login")) {
            loginPanel.removeAll();
            loginPanel.add(additional);
        }
        cardLayout.show(this, card);
    }
}