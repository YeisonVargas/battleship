/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.yeisonvargas.battleship.gui;

import co.yeisonvargas.battleship.business.Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author yeison_vargas
 */
public class Battleship extends javax.swing.JFrame {

    private Game newGame;
    final String alphabet [] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private JLabel [] [] ownOcean;
    private JLabel [] [] externalOcean;

    /**
     * Creates new form Battleship
     */
    public Battleship(int level, String name, String photo) {
        initComponents();

        this.newGame = new Game(this, level, name, photo);

        this.jLabelFirstPlayer.setText(name);
        this.jLabelFirstPlayerScore.setText("0");
        this.setUp(level);
    }

    private void setUp(int level){
        int easy = 9;
        int medium = 13;
        int hard = 19;

        switch (level){
            case 1:
                level = easy;
                break;
            case 2:
                level = medium;
                break;
            case 3:
                level = hard;
                break;
            default:
                break;
        }

        final int height = this.jPanelOwnOcean.getPreferredSize().height / level + 1;
        final int width = this.jPanelOwnOcean.getPreferredSize().width / level + 1;

        this.jPanelTwoSquares.setPreferredSize(new Dimension(width*2, height));
        this.jPanelThreeSquares.setPreferredSize(new Dimension(width*3, height));
        this.jPanelFourSquares.setPreferredSize(new Dimension(width*4, height));
        this.jPanelFiveSquares.setPreferredSize(new Dimension(width*5, height));

        this.jPanelTwoSquares.setLayout(new GridLayout(1, 2));
        this.jPanelThreeSquares.setLayout(new GridLayout(1, 3));
        this.jPanelFourSquares.setLayout(new GridLayout(1, 4));
        this.jPanelFiveSquares.setLayout(new GridLayout(1, 5));

        this.loadExamplesShips(2, width, height, this.jPanelTwoSquares);
        this.loadExamplesShips(3, width, height, this.jPanelThreeSquares);
        this.loadExamplesShips(4, width, height, this.jPanelFourSquares);
        this.loadExamplesShips(5, width, height, this.jPanelFiveSquares);


        load(level, height, width, jPanelOwnOcean, true);
        load(level, height, width, jPanelEnemyOcean, false);
    }

    public void setData(String data) {
        this.jLabelFirstPlayer.setText(data.split("-")[1]);
        this.jLabelFirstPlayerScore.setText(data.split("-")[0]);
    }

    private void loadExamplesShips(int size, int width, int height, JPanel container) {
        for(int i=0; i<size; i++) {
            JLabel item = new JLabel();
            item.setBackground(Color.BLACK);
            item.setOpaque(true);
            item.setBounds(new Rectangle(width, height));
            item.setVisible(true);
            container.add(item);
        }
    }

    public Battleship(String name, String photo) {
        initComponents();
        this.newGame = new Game(this, name, photo);
        this.jLabelSecondPlayer.setText(name);
        this.jLabelSecondScore.setText("0");

        this.setUp(this.newGame.getLevel());
    }

    public void setChat() {
        String content = "";
        for(String[] item: this.newGame.getConversation()) {
            content +=  item[1] + "\n\t" + item[0] + "\n";
        }
        System.out.println("OK UI REFRESH CLIENT ONE");
        this.jTextAreaChat.setText(content);
        this.jTextAreaNewMessage.setText("");
    }

    public void setDataSecondPlayer() {
        this.jLabelSecondPlayer.setText(this.newGame.getData().split("-")[1]);
        this.jLabelSecondScore.setText(this.newGame.getData().split("-")[0]);
    }
    
    private void load(int level, int height, int width, JPanel oceanContainer, boolean ownBoard) {
        oceanContainer.setLayout(new GridLayout(level + 1, level + 1));
        
        JLabel [] [] ocean = new JLabel[level + 1][level + 1];

        if(ownBoard) {
            this.ownOcean = ocean;
        } else {
            this.externalOcean = ocean;
        }

        for(int x=0; x<ocean.length; x++) {
            for(int y=0; y<ocean[x].length; y++) {
                ocean[x][y] = new JLabel(new ImageIcon(getClass().getResource("sea.jpg")));
                ocean[x][y].setOpaque(true);
                ocean[x][y].setBounds(new Rectangle(width, height));
                oceanContainer.add(ocean[x][y]);
                ocean[x][y].setVisible(true);
                ocean[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("Yay you clicked me");
                    }
                });
                final int a = x;
                final int b = y;
                ocean[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        jLabelPosition.setText(a + 1 + "" + alphabet[b]);
                    }
                });
            }
        }
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpanelBackground = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelOwnOcean = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaChat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaNewMessage = new javax.swing.JTextArea();
        jButtonSend = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCoordinateRegisterShip = new javax.swing.JTextField();
        jButtonAddShip = new javax.swing.JButton();
        jPanelEnemyOcean = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabelPosition = new javax.swing.JLabel();
        jTextFieldAttack = new javax.swing.JTextField();
        jButtonAttack = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanelTwoSquares = new javax.swing.JPanel();
        jLabelTwoLeftShips = new javax.swing.JLabel();
        jPanelFiveSquares = new javax.swing.JPanel();
        jLabelFiveLeftShips = new javax.swing.JLabel();
        jPanelFourSquares = new javax.swing.JPanel();
        jLabelFourLeftShips = new javax.swing.JLabel();
        jPanelThreeSquares = new javax.swing.JPanel();
        jLabelThreeLeftShips = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelFirstPlayerScore = new javax.swing.JLabel();
        jLabelFirstPlayer = new javax.swing.JLabel();
        jLabelSecondPlayer = new javax.swing.JLabel();
        jLabelSecondScore = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        JpanelBackground.setBackground(java.awt.Color.white);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanelOwnOcean.setMinimumSize(new java.awt.Dimension(200, 200));
        jPanelOwnOcean.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout jPanelOwnOceanLayout = new javax.swing.GroupLayout(jPanelOwnOcean);
        jPanelOwnOcean.setLayout(jPanelOwnOceanLayout);
        jPanelOwnOceanLayout.setHorizontalGroup(
            jPanelOwnOceanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanelOwnOceanLayout.setVerticalGroup(
            jPanelOwnOceanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jTextAreaChat.setEditable(false);
        jTextAreaChat.setColumns(20);
        jTextAreaChat.setRows(5);
        jScrollPane1.setViewportView(jTextAreaChat);

        jTextAreaNewMessage.setColumns(20);
        jTextAreaNewMessage.setRows(5);
        jScrollPane2.setViewportView(jTextAreaNewMessage);

        jButtonSend.setText("Send");
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chat");

        jLabel2.setText("Mar Propio");

        jLabel3.setText("Mar Enemigo");

        jTextFieldCoordinateRegisterShip.setText("1,A-1,B");
        jTextFieldCoordinateRegisterShip.setToolTipText("");

        jButtonAddShip.setText("Agregar Barco");
        jButtonAddShip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddShipActionPerformed(evt);
            }
        });

        jPanelEnemyOcean.setMinimumSize(new java.awt.Dimension(200, 200));
        jPanelEnemyOcean.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout jPanelEnemyOceanLayout = new javax.swing.GroupLayout(jPanelEnemyOcean);
        jPanelEnemyOcean.setLayout(jPanelEnemyOceanLayout);
        jPanelEnemyOceanLayout.setHorizontalGroup(
            jPanelEnemyOceanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanelEnemyOceanLayout.setVerticalGroup(
            jPanelEnemyOceanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jLabel4.setText("Posición actual:");

        jLabelPosition.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        jLabelPosition.setForeground(java.awt.Color.red);

        jTextFieldAttack.setText("1,A");
        jTextFieldAttack.setToolTipText("");

        jButtonAttack.setText("Atacar");
        jButtonAttack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAttackActionPerformed(evt);
            }
        });

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Barcos pendientes por posicionar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 1, 15), java.awt.Color.black)); // NOI18N

        jPanelTwoSquares.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout jPanelTwoSquaresLayout = new javax.swing.GroupLayout(jPanelTwoSquares);
        jPanelTwoSquares.setLayout(jPanelTwoSquaresLayout);
        jPanelTwoSquaresLayout.setHorizontalGroup(
            jPanelTwoSquaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );
        jPanelTwoSquaresLayout.setVerticalGroup(
            jPanelTwoSquaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabelTwoLeftShips.setBackground(java.awt.Color.white);
        jLabelTwoLeftShips.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTwoLeftShips.setText("3");

        jPanelFiveSquares.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout jPanelFiveSquaresLayout = new javax.swing.GroupLayout(jPanelFiveSquares);
        jPanelFiveSquares.setLayout(jPanelFiveSquaresLayout);
        jPanelFiveSquaresLayout.setHorizontalGroup(
            jPanelFiveSquaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );
        jPanelFiveSquaresLayout.setVerticalGroup(
            jPanelFiveSquaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabelFiveLeftShips.setBackground(java.awt.Color.white);
        jLabelFiveLeftShips.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFiveLeftShips.setText("1");

        jPanelFourSquares.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout jPanelFourSquaresLayout = new javax.swing.GroupLayout(jPanelFourSquares);
        jPanelFourSquares.setLayout(jPanelFourSquaresLayout);
        jPanelFourSquaresLayout.setHorizontalGroup(
            jPanelFourSquaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );
        jPanelFourSquaresLayout.setVerticalGroup(
            jPanelFourSquaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabelFourLeftShips.setBackground(java.awt.Color.white);
        jLabelFourLeftShips.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFourLeftShips.setText("2");

        jPanelThreeSquares.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout jPanelThreeSquaresLayout = new javax.swing.GroupLayout(jPanelThreeSquares);
        jPanelThreeSquares.setLayout(jPanelThreeSquaresLayout);
        jPanelThreeSquaresLayout.setHorizontalGroup(
            jPanelThreeSquaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );
        jPanelThreeSquaresLayout.setVerticalGroup(
            jPanelThreeSquaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabelThreeLeftShips.setBackground(java.awt.Color.white);
        jLabelThreeLeftShips.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelThreeLeftShips.setText("2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelTwoLeftShips, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanelTwoSquares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelFiveLeftShips, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelThreeLeftShips, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelThreeSquares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelFourLeftShips, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelFourSquares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelFiveSquares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelFiveSquares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelTwoSquares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTwoLeftShips, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFiveLeftShips, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelFourSquares, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelThreeSquares, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelThreeLeftShips, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFourLeftShips, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información del Juego", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 1, 15))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelSecondPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(jLabelFirstPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSecondScore, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFirstPlayerScore, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFirstPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFirstPlayerScore, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSecondPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSecondScore, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JpanelBackgroundLayout = new javax.swing.GroupLayout(JpanelBackground);
        JpanelBackground.setLayout(JpanelBackgroundLayout);
        JpanelBackgroundLayout.setHorizontalGroup(
            JpanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JpanelBackgroundLayout.createSequentialGroup()
                        .addGroup(JpanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelOwnOcean, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JpanelBackgroundLayout.createSequentialGroup()
                                .addComponent(jTextFieldCoordinateRegisterShip, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonAddShip))
                            .addGroup(JpanelBackgroundLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(JpanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(JpanelBackgroundLayout.createSequentialGroup()
                                .addComponent(jTextFieldAttack, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonAttack))
                            .addGroup(JpanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanelEnemyOcean, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(JpanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpanelBackgroundLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(JpanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(JpanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jButtonSend))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpanelBackgroundLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        JpanelBackgroundLayout.setVerticalGroup(
            JpanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelBackgroundLayout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(JpanelBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpanelBackgroundLayout.createSequentialGroup()
                        .addGroup(JpanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JpanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelOwnOcean, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelEnemyOcean, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(JpanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpanelBackgroundLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JpanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JpanelBackgroundLayout.createSequentialGroup()
                                        .addComponent(jLabelPosition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(12, 12, 12))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(JpanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldCoordinateRegisterShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonAddShip)))
                            .addGroup(JpanelBackgroundLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(JpanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldAttack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonAttack))))
                        .addGap(0, 0, 0)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(391, 391, 391))
                    .addGroup(JpanelBackgroundLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSend)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JpanelBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpanelBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 714, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
        // TODO add your handling code here:
        if(jTextAreaNewMessage.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El contenido del mensaje está vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println("Ok client Two");
        // TODO: Check...
        this.newGame.sendMessage(jTextAreaNewMessage.getText());
        this.setChat();
        //System.out.println(this.serviceChat.sendMessageClientOne(jTextAreaNewMessage.getText(), ""));
    }//GEN-LAST:event_jButtonSendActionPerformed

    private void jButtonAddShipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddShipActionPerformed
        // TODO add your handling code here:
        String start = this.jTextFieldCoordinateRegisterShip.getText().split("-")[0];
        String end = this.jTextFieldCoordinateRegisterShip.getText().split("-")[1];
        String result =  (Integer.parseInt(start.split(",")[0]) - 1) + "," + getIndexAlphabet(start.split(",")[1]) + "-" +
                (Integer.parseInt(end.split(",")[0]) - 1) + "," + getIndexAlphabet(end.split(",")[1]);

        String [] coordenates = this.newGame.registerShip(result);
        if( coordenates != null) {
            JLabel temp = null;
            System.out.println("Tamaño de lo que se pinta: " + coordenates.length);
            for(int i=0; i<coordenates.length; i++) {
                System.out.println("Coordenadas que se pintan: " + coordenates[i]);
                temp = this.ownOcean[Integer.parseInt(coordenates[i].split("-")[0])]
                        [Integer.parseInt(coordenates[i].split("-")[1])];
                temp.setIcon(null);
                temp.setOpaque(true);
                temp.setBackground(Color.black);
            }

            //JOptionPane.showMessageDialog(null, "Barco registrado.");


        } else {
            JOptionPane.showMessageDialog(null, "Hay algo mal en la posición que elegiste. Barco no registrado.");
        }
        this.jLabelTwoLeftShips.setText(String.valueOf(Game.shipsLeftTwoSquares));
        this.jLabelThreeLeftShips.setText(String.valueOf(Game.shipsLeftThreeSquares));
        this.jLabelFourLeftShips.setText(String.valueOf(Game.shipsLeftFourSquares));
        this.jLabelFiveLeftShips.setText(String.valueOf(Game.shipsLeftFiveSquares));
        
        
    }//GEN-LAST:event_jButtonAddShipActionPerformed

    private void jButtonAttackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAttackActionPerformed
        // TODO add your handling code here:

        String [] results;

        String start = this.jTextFieldAttack.getText().split("-")[0];

        String result =  (Integer.parseInt(start.split(",")[0]) - 1) + "," + getIndexAlphabet(start.split(",")[1]);


        results = this.newGame.registerAttack(result);

        if(results != null) {

            for(String a: results) {
                System.out.println(a);
            }
            if(results.length == 1) {
                JLabel a = this.externalOcean[Integer.parseInt(results[0].split(",")[0])]
                        [Integer.parseInt(results[0].split(",")[1])];

                a.setOpaque(true);
                a.setBackground(Color.RED);

                        //.setIcon(new ImageIcon(getClass().getResource("fall.jpg")));
            }

            for(String item: results){
                JLabel a  = this.externalOcean[Integer.parseInt(item.split("-")[0])]
                        [Integer.parseInt(item.split("-")[1])];

                a.setOpaque(true);
                a.setBackground(Color.GREEN);

                //.setIcon(new ImageIcon(getClass().getResource("sink.png")));
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error con la conexión.");
        }
    }//GEN-LAST:event_jButtonAttackActionPerformed

    private String getIndexAlphabet(String letter) {
        for(int i=0; i<this.alphabet.length; i++) {
            if(this.alphabet[i].equalsIgnoreCase(letter)) {
                return i + "";
            }
        }
        return "";
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpanelBackground;
    private javax.swing.JButton jButtonAddShip;
    private javax.swing.JButton jButtonAttack;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelFirstPlayer;
    private javax.swing.JLabel jLabelFirstPlayerScore;
    private javax.swing.JLabel jLabelFiveLeftShips;
    private javax.swing.JLabel jLabelFourLeftShips;
    private javax.swing.JLabel jLabelPosition;
    private javax.swing.JLabel jLabelSecondPlayer;
    private javax.swing.JLabel jLabelSecondScore;
    private javax.swing.JLabel jLabelThreeLeftShips;
    private javax.swing.JLabel jLabelTwoLeftShips;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelEnemyOcean;
    private javax.swing.JPanel jPanelFiveSquares;
    private javax.swing.JPanel jPanelFourSquares;
    private javax.swing.JPanel jPanelOwnOcean;
    private javax.swing.JPanel jPanelThreeSquares;
    private javax.swing.JPanel jPanelTwoSquares;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextAreaChat;
    private javax.swing.JTextArea jTextAreaNewMessage;
    private javax.swing.JTextField jTextFieldAttack;
    private javax.swing.JTextField jTextFieldCoordinateRegisterShip;
    // End of variables declaration//GEN-END:variables
}
