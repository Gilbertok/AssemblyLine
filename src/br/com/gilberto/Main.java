package br.com.gilberto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.com.gilberto.model.Task;
import br.com.gilberto.utils.DateUtils;
import br.com.gilberto.utils.FileUtils;

public class Main {
	
	private static SimpleDateFormat formatHour = new SimpleDateFormat("HH:mm");
	
	public static void main(String[] args) {
		try {
			List<Task> tasks = FileUtils.getInputTxt();
			int interator = tasks.size();
			int inicio = tasks.size();
			DateUtils date = new DateUtils();
			while (tasks.size() > 0) {
				if(interator == inicio) {
					System.out.println("Linha de montagem 1:");
				}
				interator--;
				if (interator < 0) {
					interator = tasks.size();
					interator--;
				}
				Task task = tasks.get(interator);
				if(date.verifyTimeWork(task.getExecutionTime(), 12, 0) 
						|| date.verifyTimeWork(task.getExecutionTime(), 16, 50)) {
					if(date.isLunch()) System.out.println("12:00 Almoço");
					printTime(date.getHoraExecucao(), task.getDescriptionOperation());
				} else {
					continue;
				}
				date.setHoraExecucao(task.getExecutionTime());
				if(date.zerarHoraTurno()) {
					printTime(date.getHoraExecucao(), "Ginástica laboral");
					date.setHoraExecucao(0);
					System.out.println("");
					System.out.println("");
					System.out.println("Linha de montagem 2:");
				}
				if(tasks.size() == 1) {
					printTime(date.getHoraExecucao(), "Ginástica laboral");
				}
				tasks.remove(interator);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void printTime(Calendar hora, String descricao) {
		System.out.println(formatHour.format(hora.getTime())+" "+descricao);
	}

}
