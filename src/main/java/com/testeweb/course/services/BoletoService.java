package com.testeweb.course.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.testeweb.course.domain.PagamentoComBoleto;
@Service
public class BoletoService {
	//possui a logica que vai pagar 7 dias depois do boleto recebimento
	public  void preencherPagamanetoComBoleto(PagamentoComBoleto pagto, Date instante) {
	
		Calendar cal = Calendar.getInstance();
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
		
	}

}
