package tsti.servicios;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tsti.dto.ClienteResponseDTO;
import tsti.dao.ClienteDAO;
import tsti.entidades.Clientes;
import tsti.excepcion.Excepcion;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

/**
 * 
 * @author cecilia
 *@apiNote Implementa los metodos para encontrar todos los clientes - encontrar clientes por id - Actualizar cliente
 * insertar nuevo cliente- eliminar por dni 
 */

@Service
public class ClienteServiceImpl implements IClienteService {
	
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
	public void deleteByDni(Long dni) {
		dao.deleteByDni(dni);
	}
	
	@Override
	public List<Clientes> filtrar(String apellido, String nombre) {
		if(apellido==null && nombre==null)
			return dao.findAll();
		else
			return dao.findByApellidoOrNombre(apellido, nombre);
	}

	
	@Override
	public Optional<Clientes> filtrarPorDni(Long dni){
		
			return dao.findByDni(dni);
		
	};
	

	
	


	
}
