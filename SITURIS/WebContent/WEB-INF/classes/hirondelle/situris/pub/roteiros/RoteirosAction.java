package hirondelle.situris.pub.roteiros;

import java.util.List;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionTemplateListAndEdit;
import hirondelle.web4j.request.RequestParser;
import hirondelle.web4j.security.SafeText;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.SqlId;

public final class RoteirosAction extends ActionTemplateListAndEdit {

    public static final SqlId LIST_ROTEIROS = new SqlId("LIST_ROTEIROS");

	public RoteirosAction(RequestParser aRequestParser) {
		super(FORWARD, REDIRECT_TO_LISTING, aRequestParser);
	}
    List<SafeText> imagens=null;
	@Override
	protected void doList() throws DAOException {
	  List<Roteiro> roteiroList = fDAO.listRoteiros();
	  addToRequest(ITEMS_FOR_LISTING, roteiroList);
	  if( roteiroList.isEmpty() ){
	  addMessage("Please make a proposal first.");
	  }
	}

	// PRIVATE
	private RoteirosDAO fDAO = new RoteirosDAO();
	private static final ResponsePage FORWARD = TemplatedPage.get("Routes", "roteiros.jsp", RoteirosAction.class);
	private static final ResponsePage REDIRECT_TO_LISTING = new ResponsePage("PredictionListAction.do?Operation=List");

  @Override
  protected void attemptAdd() throws DAOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void attemptChange() throws DAOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void attemptDelete() throws DAOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void attemptFetchForChange() throws DAOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void validateUserInput() {
    // TODO Auto-generated method stub
    
  }


}
