package br.com.gilberto.model;

public class Task {
	
	private int executionTime;
	private String descriptionOperation;
	
	public Task(Integer executionTime, String descriptionOperation) {
		this.executionTime = executionTime;
		this.descriptionOperation = descriptionOperation;
	}
	
	public int getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
	}

	public String getDescriptionOperation() {
		return descriptionOperation;
	}

	public void setDescriptionOperation(String descriptionOperation) {
		this.descriptionOperation = descriptionOperation;
	}

	@Override
	public String toString() {
		return "Tarefa [tempoExecucao=" + executionTime + ", descricaoOperacao=" + descriptionOperation + "]";
	}

}
