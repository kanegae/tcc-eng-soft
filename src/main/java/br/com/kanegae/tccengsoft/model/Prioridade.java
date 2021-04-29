package br.com.kanegae.tccengsoft.model;

public enum Prioridade {
	INDEFINIDA("Indefinida"),
	BAIXA("Baixa"),
	MEDIA("Média"),
	ALTA("Alta");
	
	private final String displayValue;
    
    private Prioridade(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
