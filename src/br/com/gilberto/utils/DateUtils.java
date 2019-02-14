package br.com.gilberto.utils;

import java.util.Calendar;

public class DateUtils {
	
	private Calendar horaExecucao;
	
	public DateUtils() {
		this.horaExecucao = getHorarioInicio(0);
	}
	
	public void setHoraExecucao(int tempo) {
		horaExecucao = (tempo == 0 ? this.getHorarioInicio(tempo) : this.addTempo(tempo));  
	}
	
	private Calendar getHorarioInicio(int manhaTarde) {
		Calendar cal = Calendar.getInstance();
		if (manhaTarde == 0) {
			cal.set(Calendar.HOUR_OF_DAY, 9);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
		} else {
			cal.set(Calendar.HOUR_OF_DAY, 13);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
		}
		return cal;
	}

	public Calendar getHoraExecucao() {
		if (horaExecucao == null) {
			this.setHoraExecucao(0);
		}
		return (Calendar) horaExecucao.clone();
	}

	public void setHoraExecucao(Calendar horaExecucao) {
		this.horaExecucao = horaExecucao;
	}
	
	public boolean isEndOfWorkShift() {
		if (horaExecucao.get(Calendar.HOUR_OF_DAY) > 16 && horaExecucao.get(Calendar.HOUR_OF_DAY) <= 17) {
			this.setHoraExecucao(this.getHorarioInicio(0));
			return true;
		}
		return false;
	}
	
	private Calendar addTempo(int executionTime) {
		Calendar tempo = (Calendar) this.getHoraExecucao().clone();
		if (tempo.get(Calendar.HOUR_OF_DAY) < 12) {
			tempo.add(Calendar.MINUTE, executionTime);
			if (tempo.get(Calendar.HOUR_OF_DAY) == 12) {
				tempo = getHorarioInicio(executionTime);
			}
		} else {
			tempo.add(Calendar.MINUTE, executionTime);
		}
		return tempo;
	}
	
	public boolean isLunch() {
		if ((horaExecucao.get(Calendar.HOUR_OF_DAY) == 12) || (horaExecucao.get(Calendar.HOUR_OF_DAY) == 13 && horaExecucao.get(Calendar.MINUTE) == 0)) {
			return true;
		}
		return false;
	}

	public boolean verifyTimeWork(int executionTime, int compareHour, int compareMin) {
		Calendar tempo = (Calendar) this.getHoraExecucao().clone();
		tempo = this.addTempo(executionTime);
		if(tempo.get(Calendar.HOUR_OF_DAY) > compareHour) {
			return false;
		} else if (tempo.get(Calendar.HOUR_OF_DAY) == compareHour && tempo.get(Calendar.MINUTE) > compareMin) {
			return false;
		} else if (executionTime == 5 && ((tempo.get(Calendar.HOUR_OF_DAY) < 12)
				|| (tempo.get(Calendar.HOUR_OF_DAY) < 12))) {
			return false;
		}
		return true;
	}

	public boolean zerarHoraTurno() {
		if ((horaExecucao.get(Calendar.HOUR_OF_DAY) == 17) 
				|| (horaExecucao.get(Calendar.HOUR_OF_DAY) == 16 
					&& ((horaExecucao.get(Calendar.MINUTE) > 30) && horaExecucao.get(Calendar.MINUTE) <= 59))) {
			return true;
		}
		return false;
	}

	
}
