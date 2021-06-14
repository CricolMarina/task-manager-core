import com.stefanini.taskmanager.dao.factory.AbstractFactoryUser;
import com.stefanini.taskmanager.dao.factory.impl.UserDAOFactoryHibernate;
import com.stefanini.taskmanager.service.UserService;
import com.stefanini.taskmanager.service.impl.UserServiceImpl;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractFactoryUser daoFactory = new UserDAOFactoryHibernate();
    	UserService userService = new UserServiceImpl(daoFactory);
    	userService.showAllUsers();
	}

}
