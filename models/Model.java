package app.models;

public class Model {
	public UserDAOImpl userDAO;
	public AuthService authService;
	public ClassesDAOImpl classesDAO;
	public CoursDAOImpl coursDAO;
	public EtatModuleDAOImpl etatModuleDAO;
	public PaiementDAOImpl paiementDAO;
	public MatiereDAO matiereDAO;
	
	public Model() {
		this.authService = new AuthService();
		this.userDAO = new UserDAOImpl();
		this.classesDAO = new ClassesDAOImpl();
		this.coursDAO = new CoursDAOImpl();
		this.etatModuleDAO = new EtatModuleDAOImpl();
		this.paiementDAO = new PaiementDAOImpl();
		this.matiereDAO = new MatiereDAO();
	}
}
