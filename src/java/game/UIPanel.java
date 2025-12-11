package game;

import game.entities.PacGum;
import game.entities.SuperPacGum;
import game.entities.ghosts.Ghost;
import game.ghostStates.FrightenedMode;

import javax.swing.*;
import java.awt.*;
import java.util.prefs.Preferences;

//Panneau de l'interface utilisateur
public class UIPanel extends JPanel implements Observer {
    public static int width;
    public static int height;

    private int score = 0;
    private JLabel scoreLabel;

    private JLabel rankTitle;
    private JLabel rankLabel1;

    private JLabel rankLabel2;

    private JLabel rankLabel3;

    //저장
    private static final Preferences prefs =
            Preferences.userNodeForPackage(UIPanel.class);

    public UIPanel(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(scoreLabel.getFont().deriveFont(20.0F));
        scoreLabel.setForeground(Color.white);
        this.add(scoreLabel, BorderLayout.WEST);


        rankTitle = new JLabel("Ranking ");
        rankTitle.setFont(rankTitle.getFont().deriveFont(15.0F));
        rankTitle.setForeground(Color.white);
        this.add(rankTitle, BorderLayout.WEST);

        int[] lastScores = loadSavedScores();
        rankLabel1 = new JLabel("1st:  "+lastScores[0]);
        rankLabel1.setFont(rankLabel1.getFont().deriveFont(15.0F));
        rankLabel1.setForeground(Color.white);
        this.add(rankLabel1, BorderLayout.WEST);


        rankLabel2 = new JLabel("2nd:  "+lastScores[1]);
        rankLabel2.setFont(rankTitle.getFont().deriveFont(15.0F));
        rankLabel2.setForeground(Color.white);
        this.add(rankLabel2, BorderLayout.WEST);


        rankLabel3 = new JLabel("3rd:  "+lastScores[2]);
        rankLabel3.setFont(rankLabel3.getFont().deriveFont(15.0F));
        rankLabel3.setForeground(Color.white);
        this.add(rankLabel3, BorderLayout.WEST);
    }

    public void updateScore(int incrScore) {
        this.score += incrScore;
        this.scoreLabel.setText("Score: " + score);
    }

    public int getScore() {
        return score;
    }

    //L'interface est notifiée lorsque Pacman est en contact avec une PacGum, une SuperPacGum ou un fantôme, et on met à jour le score affiché en conséquence
    @Override
    public void updatePacGumEaten(PacGum pg) {
        updateScore(10);
    }

    @Override
    public void updateSuperPacGumEaten(SuperPacGum spg) {
        updateScore(100);
    }

    @Override
    public void updateGhostCollision(Ghost gh) {
        updateScore(gh.getScore());
    }

    public void updateSavedScores(){
        int s1 =prefs.getInt("s1", 0);
        int s2 =prefs.getInt("s2", 0);
        int s3 =prefs.getInt("s3", 0);
        if(score >=s1){
            prefs.putInt("s3", s2);
            prefs.putInt("s2", s1);
            prefs.putInt("s1", score);
        }
        else if(score >=s2){
            prefs.putInt("s3", s2);
            prefs.putInt("s2", score);
        }
        else if(score >=s3){
            prefs.putInt("s3", score);
        }
    }

    public int[] loadSavedScores(){
        int s1 =prefs.getInt("s1", 0);
        int s2 =prefs.getInt("s2", 0);
        int s3 =prefs.getInt("s3", 0);
        return  new int[]{s1,s2,s3};
    }
}
