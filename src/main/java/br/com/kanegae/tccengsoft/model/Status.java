package br.com.kanegae.tccengsoft.model;

public enum Status {
	NAO_INICIADO("Não Iniciado"),
	EM_ANDAMENTO("Em Andamento"),
	IMPEDIDO("Impedido"),
	CONCLUIDO("Concluído");
	
	private final String displayValue;
    
    private Status(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
