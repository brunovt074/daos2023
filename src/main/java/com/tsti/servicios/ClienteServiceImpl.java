package com.tsti.servicios;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsti.dao.ClienteDAO;
import com.tsti.entidades.Clientes;
import com.tsti.excepcion.Excepcion;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

/**
 * 
 * @author cecilia
 *
 */

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private  Validator validator;
	
	@Autowired
	private ClienteDAO dao;
	
	@Override
	public List<Clientes> getAll(){
		return dao.findAll();
	}
	
	public Optional<Clientes> getById(Long id){
		return dao.findById(id);
	}
	
	@Override
	public void update(Clientes c) {
		dao.save(c);
	}
	
	@Override
	public void insert(Clientes c) throws Exception {
		Set<ConstraintViolation<Clientes>> cv = validator.validate(c);
		if(cv.size()>0)
		{
			String err="";
			for (ConstraintViolation<Clientes> constraintViolation : cv) {
				err+=constraintViolation.getPropertyPath()+": "+constraintViolation.getMessage()+"\n";
			}
			throw new Excepcion(err,400);
		}
		else
			dao.save(c);
	}
	
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}
	
	@Override
	public List<Clientes> filtrar(String apellido, String nombre) {
		if(apellido==null && nombre==null)
			return dao.findAll();
		else
			return dao.findByApellidoOrNombre(apellido, nombre);
	}


	
}
