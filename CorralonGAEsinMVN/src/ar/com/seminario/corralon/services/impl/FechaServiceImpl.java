package ar.com.seminario.corralon.services.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import ar.com.seminario.corralon.services.FechaService;

@Service("fechaService")
public class FechaServiceImpl implements FechaService{

	@SuppressWarnings("deprecation")
	@Override
	public Date generarFecha(String fecha) {
		Date f = new Date();
		String[] fechaPartida = fecha.split("-");
		f.setYear(Integer.parseInt(fechaPartida[2]) - 1900);
		f.setDate(Integer.parseInt(fechaPartida[0]));
		f.setMonth(Integer.parseInt(fechaPartida[1]) - 1);
		return f;
	}
	
}
