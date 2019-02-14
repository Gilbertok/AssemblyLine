package br.com.gilberto.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import br.com.gilberto.model.Task;

public class FileUtils {
	
	private static String CAMINHO_ARQUIVO = System.getProperty("user.dir") +"\\resources\\input.txt";
	
	public static List<Task> getInputTxt() throws Exception {
		List<Task> tasks = new ArrayList<Task>();
    	BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO));
    	while(br.ready()){
    		String linha = br.readLine();
    		tasks.add(FileUtils.getTask(linha));
    	}
    	br.close();
    	return tasks;
	}
	
	private static Task getTask(String linha) {
		Integer tempoExecucao = 0;
		String[] parts = linha.split(" ");
		String part = parts[parts.length-1];
		if (part.contains("maintenance")) {
			tempoExecucao = 5;
		} else {
			part = part.replaceAll("min", "");
			tempoExecucao = Integer.parseInt(part);
		}
		return new Task(tempoExecucao, linha);
	}
	
}
