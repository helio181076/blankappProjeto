package br.eti.amazu.infra.view.showcase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.eti.amazu.component.dialog.DialogBean;
import br.eti.amazu.component.dialog.DialogType;
import br.eti.amazu.component.dialog.DialogUtil;
import br.eti.amazu.infra.util.FacesUtil;
import br.eti.amazu.infra.view.showcase.vo.Veiculo;

@Named
@ViewScoped
public class MessageCaseBean implements Serializable{

	private static final long serialVersionUID = 1L;	
	private Veiculo veiculo = new Veiculo();	
	private String txtHello = "Hello Messages";
	private int contador = 0;
	
	@Inject
	DialogBean dialogBean;
	
	public void testarInfoClosable(){ //INFO_CLOSABLE	
		dialogBean.addMessage(DialogUtil.getMessage("MPF003"), DialogType.INFO_CLOSABLE);
	}	
	
	public void testarInfoNotClosable(){ //INFO_NOT_CLOSABLE	
		dialogBean.addMessage(DialogUtil.getMessage("MPF003"),	DialogType.INFO_NOT_CLOSABLE);
	}
	
	public void testarWarn(){ //WARN		
		dialogBean.addMessage(DialogUtil.getMessage("MPF006"), DialogType.WARN);
	}	
	
	public void testarError(){ //ERROR
		dialogBean.addMessage(DialogUtil.getMessage("MPF001"), DialogType.ERROR );
	}
	
	public void testarFatalError(){ //FATAL_ERROR
		dialogBean.addMessage(DialogUtil.getMessage("MPF002"), DialogType.FATAL_ERROR );
	}
	
	public void testarGrowlInfo(){ //GROWL_INFO
		dialogBean.addMessage("Apenas uma informação: A janela não é modal!!!",	
				DialogType.GROWL_INFO);
	}

	public void testarGrowlError(){ //GROWL_ERROR
		dialogBean.addMessage("Há um erro desconhecido - Mostrando em uma janela não modal!!!",
				DialogType.GROWL_ERROR );
	}
	
	public void testarActionMessage(){ //ACTION MESSAGE	
		dialogBean.addActionMessage(DialogUtil.getMessage("MPF004"), 
				"messageCaseBean.printarRetornoBotaoOk",  null);		
	}
		
	public void testarConfirmMessage(){ //CONFIRM MESSAGE
		dialogBean.addConfirmMessage(DialogUtil.getMessage("MPF007"), 
			"messageCaseBean.printarRetornoBotaoYes", 	
				"messageCaseBean.printarRetornoBotaoNo", null);
	}
	
	public void testarWarnConfirmMessage(){ //WARN CONFIRM MESSAGE
		dialogBean.addConfirmWarnMessage("Perigo imediato. Prosseguir assim mesmo?", 
				"messageCaseBean.printarRetornoBotaoYes", 
						"messageCaseBean.printarRetornoBotaoNo", null);
	}
	
	public void printarErro(){ //Printar Erro - ERROR - no bloco try-catch
		try{			
			@SuppressWarnings("unused")
			int a = 1/0;		
			
		}catch (Exception e){
			dialogBean.addMessage(e.getMessage(), DialogType.ERROR);
		}
	}
	
	public void testarMsgParametrizadas(){ //INFO_CLOSABLE - com parametros
		String params[] = new String[]{veiculo.getMarca(),veiculo.getModelo(), 
				veiculo.getCor(),veiculo.getAno()};	
		
		dialogBean.addMessage(DialogUtil.getMessage("MPF005", params), 
				DialogType.INFO_CLOSABLE,null);
	}

	
	public void testarMsgSemBundle(){ //INFO_CLOSABLE - sem bundle
		dialogBean.addMessage("Mensagem transmitida diretamente sem o uso de resource bundle. "+
			"Não pode ser internacionalizada.", DialogType.INFO_CLOSABLE);
	}
	
	public void testarMsgComLista(){ //INFO_CLOSABLE com lista
		List<String> lista = new ArrayList<String>();		
		lista.add(DialogUtil.getMessage("MPF011"));
		lista.add(DialogUtil.getMessage("MPF012"));
		lista.add(DialogUtil.getMessage("MPF013"));	
		
		dialogBean.addMessage(DialogUtil.getMessage("CGL100"), DialogType.INFO_CLOSABLE, lista);
	}
	
	public void testarActionMessageWarnVarianteComLista(){ //ACTION MESSAGE + WARN + lista
		List<String> lista = new ArrayList<String>();		
		lista.add(DialogUtil.getMessage("MPF011"));
		lista.add(DialogUtil.getMessage("MPF012"));
		lista.add(DialogUtil.getMessage("MPF013"));		
		
		dialogBean.addActionMessage(DialogUtil.getMessage("MPF004"), 
				"messageCaseBean.printarRetornoBotaoOk", DialogType.WARN,  null, lista);		
	}
	
	public void testarUpdate(){ // update - tambem mostrando uma ACTION MESSAGE
		contador++;
		txtHello = "Hello Messages - SOFREU UPDATE-" + contador;
		
		dialogBean.addActionMessage("Componente txtHello sofrerá update", null, 
				":formMessages:txtHello,:formMessages:panelContador");
	}
		
	public void testarRedirect(){ // redirecionando para outra pagina...
		dialogBean.addActionMessage("Você será direcionado para o Show Case", 
				"messageCaseBean.retornarHomeShowCase",null);
	}
	
	//Metodos usados para testar os botoes...
	public void printarRetornoBotaoOk(){
		System.out.println(DialogUtil.getMessage("MPF008"));
	}	
	
	public void printarRetornoBotaoYes(){
		System.out.println(DialogUtil.getMessage("MPF009"));
	}	
	
	public void printarRetornoBotaoNo(){
		System.out.println(DialogUtil.getMessage("MPF010"));
	}
	
	//metodo usado para testar o redirect.
	public void retornarHomeShowCase(){
		System.out.println("...faz alguma coisa e redireciona.");
		FacesUtil.redirect("/pages/showcase/homeShowCase");
	}

	
	/*--------
	 * get/set
	 ---------*/
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public String getTxtHello() {
		return txtHello;
	}
	public void setTxtHello(String txtHello) {
		this.txtHello = txtHello;
	}
	public int getContador() {
		return contador;
	}
	public void setContador(int contador) {
		this.contador = contador;
	}
	public DialogBean getDialogBean() {
		return dialogBean;
	}
	public void setDialogBean(DialogBean dialogBean) {
		this.dialogBean = dialogBean;
	}

}
