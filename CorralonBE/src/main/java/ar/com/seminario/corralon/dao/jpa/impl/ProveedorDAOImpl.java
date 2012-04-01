package ar.com.seminario.corralon.dao.jpa.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.jpa.impl.SpringJpaDAOImpl;
import ar.com.seminario.corralon.dao.ProveedorDAO;
import ar.com.seminario.corralon.domain.Proveedor;

@Repository("proveedorDAO")
public class ProveedorDAOImpl extends SpringJpaDAOImpl<Proveedor>
		implements ProveedorDAO {

	@Override
	public Proveedor findByRazonSocial(String razonSocial) {
		String queryString = "SELECT p FROM Proveedor p" + 
							" WHERE p.razonSocial = '"+razonSocial+"'";
		Query query = entityManager.createQuery(queryString);
		Proveedor proveedor = (Proveedor) query.getSingleResult();
		return proveedor;
	}

}
