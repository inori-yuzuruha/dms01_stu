package com.qst.dms.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.qst.dms.entity.User;
import com.qst.dms.service.UserService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	private JPasswordField textPwd;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("登录名：");
		lblNewLabel.setFont(new Font("幼圆", Font.PLAIN, 23));
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setBounds(10, 49, 122, 44);
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		contentPane.add(lblNewLabel);
		
		textName = new JTextField();
		textName.setBounds(159, 49, 167, 36);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("密码：");
		lblNewLabel_1.setFont(new Font("幼圆", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(38, 124, 106, 36);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//根据用户名查询用户
				 
				 User user =  new UserService().findUserByName(textName.getText().trim());
				//判断用户是否存在
				if(user != null)
				{
					 //判断输入的密码是否正确
					 if(user.getPassword().equals(new String(textPwd.getPassword()))) {
						//登陆成功，隐藏登陆窗口
						LoginFrame.this.setVisible(false);
						//显示主窗口
						new MainFrame_temp();
					}else {
						JOptionPane.showMessageDialog(null, "密码错误", "错误提示",
								JOptionPane.INFORMATION_MESSAGE);
						//清空密码框
						textPwd.setText("");						
					}
				}else {
					JOptionPane.showMessageDialog(null, "不存在该用户，请注册", "错误提示",
							JOptionPane.INFORMATION_MESSAGE);	
				}
			}
		});
		btnNewButton.setFont(new Font("华文楷体", Font.PLAIN, 31));
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBounds(304, 202, 122, 51);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.setFont(new Font("方正舒体", Font.PLAIN, 31));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textName.setText("");
				textPwd.setText("");
			}
		});
		btnNewButton_1.setBounds(159, 201, 122, 53);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("注册");
		btnNewButton_2.setFont(new Font("方正舒体", Font.PLAIN, 31));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginFrame.this.setVisible(false);
				new RegistFrame();
			}
		});
		btnNewButton_2.setBounds(28, 202, 104, 51);
		contentPane.add(btnNewButton_2);
		
		textPwd = new JPasswordField();
		textPwd .setBounds(159, 119, 167, 36);
		contentPane.add(textPwd );
	}
}
