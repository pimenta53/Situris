package hirondelle.situris.users.admins;

import java.math.BigDecimal;
import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionTemplateShowAndApply;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;

public class NewPagamentoAction extends ActionTemplateShowAndApply {
  
  public static final SqlId GET_DATA_PAG = new SqlId("GET_DATA_PAG");
  public static final SqlId GET_VALOR_PAT = new SqlId("GET_VALOR_PAT");
  public static final SqlId ADD_PAG = new SqlId("ADD_PAG");
  public static final SqlId SET_ESTADO_PAT = new SqlId("SET_ESTADO_PAT");
  
  public static final RequestParameter ID_PAT = RequestParameter.withLengthCheck("Id");
  public static final RequestParameter ID_TAB = RequestParameter.withLengthCheck("idTabela");
  public static final RequestParameter VALUE = RequestParameter.withLengthCheck("value");
  
  public NewPagamentoAction(RequestParser aRequestParser) {
    super(FORWARD, REDIRECT, aRequestParser);
  }

  @Override
  protected void apply() throws AppException {
    System.out.println("ID DA TABELA: " + getIdParam(ID_TAB));
    System.out.println("VALOR A PAGAR: " + getIdParam(VALUE).getRawString());
    System.out.println("ID DO PATROCINIO: " + getIdParam(ID_PAT));
    
    BigDecimal aValor = new BigDecimal(getIdParam(VALUE).getRawString());
    Id idP = fDAO.addPag(aValor, getIdParam(ID_PAT), getIdParam(ID_TAB));
    if (idP == null)
      addError("The payment was not made! Please try again!");
    else{
      boolean b = fDAO.setEstado(getIdParam(ID_PAT));
      if(!b)
        addError("The sponsorship was not updated! Please do it manually!");
      else
        addMessage("The payment was made with success!");
    }
  }

  @Override
  protected void show() throws AppException {
    BigDecimal valor = fDAO.getValor(getIdParam(ID_PAT));
    System.out.println("VALOR DO PROPOSTA: " + valor);
    Id aIdTabela = null;
    //The value to pay is set accordingly to the value proposed
    if (valor.compareTo(new BigDecimal("250")) < 0)  aIdTabela = new Id("1");
    else if (valor.compareTo(new BigDecimal("750")) < 0) aIdTabela = new Id("2");
    else aIdTabela = new Id("3");
    fPagamento = fDAO.fetchDados(getIdParam(ID_PAT), aIdTabela);
    BigDecimal mult = new BigDecimal(fPagamento.getNCliques());
    value = fPagamento.getValor().multiply(mult).setScale(2, BigDecimal.ROUND_HALF_UP);
    addToRequest("value", value);
    addToRequest("dados", fPagamento);
    addToRequest("pat", getIdParam(ID_PAT));
  }

  @Override
  protected void validateUserInput() throws AppException {
  }
  
  // PRIVATE //
  private NewPagamentoDAO fDAO = new NewPagamentoDAO();
  private Pagamento fPagamento;
  private BigDecimal value;
  private static final ResponsePage FORWARD = TemplatedPage.get("Payment", "pagamento.jsp", NewPagamentoAction.class);
  private static final ResponsePage REDIRECT = new ResponsePage("checkSponsorshipsAction.list");

}
