package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import services.impl.UserServiceImpl;
import views.Login;
import views.MenuUsuario;

public class UsersController implements ActionListener{
	private UserServiceImpl service = new UserServiceImpl();
	private Login loginView;
	
	public UsersController(Login view) {
		this.loginView = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String username = loginView.usernameInput();
		String password = loginView.passwordInput();
		
		if(service.credentialsAreValid(username, password)) {
			MenuUsuario menu = new MenuUsuario();
			
			menu.setVisible(true);
			loginView.dispose();		
		}
		else {
			JOptionPane.showMessageDialog(loginView, "Invalid credentials");
		}
	}
}
