//package app.views.admin.viewListeners;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//import app.entities.User;
//import app.views.admin.AdminCreateUserView;
//import app.views.admin.AdminHome;
//import app.views.admin.AdminListUsersView;
//import app.views.admin.FindUserParamsView;
//
//public class AdminHomeListeners extends AdminListeners{
//	
//	AdminHome view;
//
//	public AdminHomeListeners(AdminHome view) {
//		this.view = view;
//		addListenerToCreateUserButton();
//		addListenerToFindUserButton();
//		addListenerToListUsersButton();
//	}
//	
//	private void addListenerToCreateUserButton() {
//		((AdminHome) view).addListenerToCreateUserButton(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				updateView(new AdminCreateUserView());
//			}
//		});
//	}
//	
//	private void addListenerToFindUserButton() {
//		((AdminHome) view).addListenerToFindUserButton(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				updateView(new FindUserParamsView());
//			}
//		});
//	}
//	
//	private void addListenerToListUsersButton() {
//		((AdminHome) view).addListenerToListUsersButton(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				List<User> users = getAllUsers();
//				System.out.println("Size of users = " + users.size());
//				updateView(new AdminListUsersView(users));
//			}
//		});
//	}
//}
