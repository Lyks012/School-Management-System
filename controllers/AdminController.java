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
		}

	}

	private void addListUsersListeners() {
		// addListenerToSupprimerButton();
		addListenerToModifierButton();
		addListenerToAnnulerButton();
	}

	private void addUserFoundListeners() {
		addListenerToAnnulerButton();
		addListenerToModifierButton();
		addListenerToSupprimerButton();
	}

	// user found button listeners
	private void addListenerToModifierButton() {
		((AdminView) this.view).addListenerToModifierButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String login = getLoginSelectedItem();
				
				String password = getPasswordSelectedItem();
				
				System.out.println(login+" "+ password);
				Roles role = getRoleSelectedItem();

				updateView(new AdminEditUserView(login, password, role));
			}
		});
	}

	private String getLoginSelectedItem() {
		return ((AdminListUsersView) this.view).getLoginSelectedItem();
	}

	private Roles getRoleSelectedItem() {
		return Roles.valueOf(((AdminListUsersView) this.view).getTypeSelectedItem());
	}

	private String getPasswordSelectedItem() {
		return ((AdminListUsersView) this.view).getPasswordSelectedItem();
	}

	private void addListenerToSupprimerButton() {
		((UserFoundView) this.view).addListenerToSupprimerButton(new ActionListener() {

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
			int idUser = ((UserFoundView) this.view).getId();
			try {
				((AdminDAOImpl) this.model).delete(idUser);
				this.view.displaySuccessMessage("Utilisateur supprime avec succes.");
				updateView(new AdminHome());
			} catch (AdminDAOException e) {
				this.view.displayErrorMessage(e.getMessage());
			}
		}
		// this.view.displayErrorMessage("ali");
		System.out.println(confirmedAction);
	}

	private void findUserById(int id) {
		try {
			updateView(
					new UserFoundView(
							((AdminDAOImpl) model).findById(id)));
		} catch (AdminDAOException e) {
			this.view.displayErrorMessage(e.getClass() + " : " + e.getMessage());
		}
	}

	private void findUserByLogin(String login) {
		try {
			updateView(new UserFoundView(((AdminDAOImpl) model).findByLogin(login)));
		} catch (AdminDAOException e) {
			this.view.displayErrorMessage(e.getClass() + " : " + e.getMessage());
		}
	}
}
