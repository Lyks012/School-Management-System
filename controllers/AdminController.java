package app.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import app.entities.Roles;
import app.entities.User;
import app.exception.db.AdminDAOException;
import app.exception.db.InputException;
import app.models.AdminDAOImpl;
import app.models.DAOImpl;

import app.utils.Utils;
import app.views.View;
import app.views.admin.AdminCreateUserView;
import app.views.admin.AdminEditUserView;
import app.views.admin.AdminHome;
import app.views.admin.AdminListUsersView;
import app.views.admin.AdminView;
import app.views.admin.FindUserParamsView;
import app.views.admin.UserFoundView;

public class AdminController extends Controller {

	public AdminController(View view, DAOImpl model) {
		super();
		this.view = view;
		this.model = model;
		this.view.setVisible(true);

		addListeners();
	}

	@Override
	public void addListeners() {
		switch (this.view.getClass().getSimpleName()) {
			case "AdminHome":
				addAdminHomeListeners();
				break;
			case "AdminCreateUserView":
				addAdminCreateUserViewListeners();
				break;
			case "FindUserParamsView":
				addFindUserListeners();
				break;
			case "UserFoundView":
				addUserFoundListeners();
				break;
			case "AdminListUsersView":
				addListUsersListeners();
				break;
			case "AdminEditUserView":
				addEditUserListeners();
				break;
		}

	}
	
	private void addEditUserListeners() {
		addListenerToModifierEditUserView();
		addListenerToAnnulerButton();
	}

	private void addListenerToModifierEditUserView() {
		((AdminEditUserView) this.view).addListenerToEditUserButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				editUser();
			}
		});
		
	}
	
	private void editUser() {
		User user = Utils.getUserInstance(((AdminEditUserView) this.view).getRole());
		
		user.setId(((AdminEditUserView) this.view).getUserId());
		user.setLogin(((AdminEditUserView) this.view).getLogin());
		user.setPassword(((AdminEditUserView) this.view).getPassword());
		
		try {
			((AdminDAOImpl) this.model).update(user, user.getRole());
			this.view.displaySuccessMessage("Utilisateur Modifie avec succes.");
			updateView(new AdminHome());
		} catch (AdminDAOException e) {
			this.view.displayErrorMessage(e.getMessage());
		}
		
		
	}

	private void addListUsersListeners() {
		addListenerToSupprimerButtonUserList();
		addListenerToModifierButton();
		addListenerToAnnulerButton();
	}

	private void addUserFoundListeners() {
		addListenerToAnnulerButton();
		addListenerToModifierButton();
		addListenerToSupprimerButtonUserFoundView();
	}

	// user found button listeners
	private void addListenerToModifierButton() {
		((AdminView) this.view).addListenerToModifierButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = getIdSelectedItem();
				String login = getLoginSelectedItem();
				String password = getPasswordSelectedItem();
				Roles role = getRoleSelectedItem();

				updateView(new AdminEditUserView(id, login, password, role));
			}
		});
	}

	private int getIdSelectedItem() {
		if(this.view.getClass().getSimpleName().equals("AdminListUsersView"))
			return ((AdminListUsersView) this.view).getIdSelectedItem();
		
		return ((UserFoundView) this.view).user.getId();
	}
	private String getLoginSelectedItem() {
		if(this.view.getClass().getSimpleName().equals("AdminListUsersView"))
			return ((AdminListUsersView) this.view).getLoginSelectedItem();
		
		return ((UserFoundView) this.view).user.getLogin();
	}

	private Roles getRoleSelectedItem() {
		if(this.view.getClass().getSimpleName().equals("AdminListUsersView"))
			return Roles.valueOf(((AdminListUsersView) this.view).getTypeSelectedItem());
		
		return ((UserFoundView) this.view).user.getRole();
	}

	private String getPasswordSelectedItem() {
		if(this.view.getClass().getSimpleName().equals("AdminListUsersView"))
			return ((AdminListUsersView) this.view).getPasswordSelectedItem();
		return ((UserFoundView) this.view).user.getPassword();
	}

	private void addListenerToSupprimerButtonUserFoundView() {
		((UserFoundView) this.view).addListenerToSupprimerButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				supprimerUser();
			}
		});
	}
	
	private void addListenerToSupprimerButtonUserList() {
		((AdminListUsersView) this.view).addListenerToSupprimerButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				supprimerUser();
			}
		});
	}

	// end user found button listeners
	private boolean checkPasswordMatch(String password1, String password2) {
		return password1.contentEquals(password2) ? true : false;
	}

	private void createUser(String login, String password, String repeatPassword, Roles role) {
		try {
			if (!checkPasswordMatch(password, repeatPassword)) {
				throw new InputException("Les mots de passe ne se resemblent pas !!!");
			}
			if (login == "" || password == "") {
				throw new InputException("Veuillez founir des informatins correctes");
			}
			
			User newUser = Utils.getUserInstance(role);
			
			newUser.setLogin(login);
			newUser.setPassword(password);

			((AdminDAOImpl) this.model).create(newUser);

			this.view.displaySuccessMessage("Utilisateur Cree avec succes");

			updateView(new AdminHome());
		} catch (InputException | AdminDAOException e) {
			e.printStackTrace();
			this.view.displayErrorMessage(e.getClass() + " : " + e.getMessage());
		}
	}

	private List<User> getAllUsers() {
		List<User> users = null;
		try {
			users = ((AdminDAOImpl) model).listAll();
		} catch (AdminDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	private void addAdminHomeListeners() {
		addListenerToCreateUserButton();
		addListenerToFindUserButton();
		addListenerToListUsersButton();
	}

	private void addAdminCreateUserViewListeners() {
		addListenerToCreateUser();
		addListenerToAnnulerButton();
	}

	private void addFindUserListeners() {
		addListenerToAnnulerButton();
		addListenerToFindByIdButton();
		addListenerToFindByLoginButton();
	}

	// UserFoundViewListeners

	// AdminHomeButtonListeners
	private void addListenerToCreateUserButton() {
		((AdminHome) view).addListenerToCreateUserButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateView(new AdminCreateUserView());
			}
		});
	}

	private void addListenerToFindUserButton() {
		((AdminHome) view).addListenerToFindUserButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateView(new FindUserParamsView());
			}
		});
	}

	private void addListenerToListUsersButton() {
		((AdminHome) view).addListenerToListUsersButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<User> users = getAllUsers();
				System.out.println("Size of users = " + users.size());
				updateView(new AdminListUsersView(users));
			}
		});
	}
	//

	// AdminFindUserListeners
	private void addListenerToFindByIdButton() {
		((FindUserParamsView) view).addListenerToSearchByIdButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				findUserById(
						Integer.parseInt(
								((FindUserParamsView) view).getId()));
			}
		});
	}

	private void addListenerToFindByLoginButton() {
		((FindUserParamsView) view).addListenerToSearchByLoginButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				findUserByLogin(
						((FindUserParamsView) view).getLogin());
			}
		});
	}

	private void addListenerToCreateUser() {
		((AdminCreateUserView) view).addListenerToCreateUserButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				createUser(
						((AdminCreateUserView) view).getLogin(),
						((AdminCreateUserView) view).getPassword(),
						((AdminCreateUserView) view).getRepeatPassword(),
						((AdminCreateUserView) view).getRole());
			}
		});
	}

	private void addListenerToAnnulerButton() {
		((AdminView) view).addListenerToAnnulerButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateView(new AdminHome());
			}
		});
	}

	private void supprimerUser() {
		boolean confirmedAction = this.view.displayConfirmationMessage(
				"Voulez vous supprimer cet utilisateur ? Cette action est irreversible", "Attention");

		if (confirmedAction) {
			int id = 0;
			
			if(this.view.getClass().getSimpleName().equals("AdminListUsersView"))
				id = ((AdminListUsersView) this.view).getIdSelectedItem();
			
			else if(this.view.getClass().getSimpleName().equals("UserFoundView"))
				id = ((UserFoundView) this.view).user.getId();
			
			try {
				((AdminDAOImpl) this.model).delete(id);
				this.view.displaySuccessMessage("Utilisateur supprime avec succes.");
				updateView(new AdminListUsersView(getAllUsers()));
			} catch (AdminDAOException e) {
				this.view.displayErrorMessage(e.getMessage());
			}
		}
	}

	private void findUserById(int id) {
		try {
			User user = ((AdminDAOImpl) model).findById(id);
			if(user == null)
				this.view.displayWarningMessage("No user found");
			else
				updateView(
						new UserFoundView(
								((AdminDAOImpl) model).findById(id)));
		} catch (AdminDAOException e) {
			this.view.displayErrorMessage(e.getClass() + " : " + e.getMessage());
		}
	}

	private void findUserByLogin(String login) {
		try {
			User user = ((AdminDAOImpl) model).findByLogin(login);
			
			if(user == null)
				this.view.displayWarningMessage("No user found");
			else
				updateView(new UserFoundView(((AdminDAOImpl) model).findByLogin(login)));
		} catch (AdminDAOException e) {
			this.view.displayErrorMessage(e.getClass() + " : " + e.getMessage());
		}
	}
}
