package sn.thecells.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;

import sn.thecells.control.GameController;

public class MessagePanel extends Panel {


	public MessagePanel(String message){
		super(new BorderLayout());
//	      Panel popupPanel = new Panel(new BorderLayout());
//	      popupPanel.setOpaque(true);
	      setMaximumSize(new Dimension(150, 170));
//	      popupPanel.setBorder(new LineBorder(Color.gray));
	      setVisible(true);
	      setFont(new Font("SansSerif", Font.PLAIN, 20));
	      
	      Label label = new Label(message);
	      add(wrapInPanel(label), BorderLayout.CENTER);

	      Button popupCloseButton = new Button("Ok!");
	      add(wrapInPanel(popupCloseButton), BorderLayout.SOUTH);

	      popupCloseButton.addActionListener(e -> {
			getParent().setVisible(false);
			getParent().getParent().validate();
			GameController.resumeGame();
	      });
	      
	      
	}

	private static Panel wrapInPanel(Component component) {
	      Panel jPanel = new Panel();
	      jPanel.setBackground(new Color(50, 210, 250, 150));
	      jPanel.add(component);
	      return jPanel;
	  }
//	private static Panel createPopupPanel(Component overlapComponent) {
//	      Panel popupPanel = new Panel(new BorderLayout());
////	      popupPanel.setOpaque(true);
//	      popupPanel.setMaximumSize(new Dimension(150, 70));
////	      popupPanel.setBorder(new LineBorder(Color.gray));
//	      popupPanel.setVisible(true);
//
//	      Label label = new Label("HI there!");
//	      popupPanel.add(wrapInPanel(label), BorderLayout.CENTER);
//
//	      Button popupCloseButton = new Button("Close");
//	      popupPanel.add(wrapInPanel(popupCloseButton), BorderLayout.SOUTH);
//
//	      popupCloseButton.addActionListener(e -> {
////	          overlapComponent.setEnabled(true);
//	    	  popupPanel.setVisible(false);
//	    	  popupPanel.getParent().validate();
//	      });
//
//	      return popupPanel;
//	}
}
