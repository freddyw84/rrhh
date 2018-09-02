package py.sgarrhh.repository;



import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Liquidacion;


public interface LiquidacionRepository extends CrudRepository<Liquidacion, String>{
	//Liquidacion findById(Liquidacion liquidacion);

	Liquidacion findById(long id);
	

	
//	@Query(value="SELECT distinct b.monto, b.id "
//				+ "FROM bonificacion as b "
//				+ "WHERE b.fecha between :inicio and :fin",nativeQuery=true)
//	Iterable<Bonificacion> findFechaBoniWithModifiedLiquiBetween (@Param("inicio") Date inicio, @Param("fin") Date fin);
//	
//
//	
	
	/*@Query(value="select distinct u.email "
					+ "from usuarios as u inner join presupuestos as p "
					+ "on u.id=p.id_usuario "
					+ "where p.fecha_modificacion between :inicio and :fin",nativeQuery=true)
	Iterable<Object> findUsuariosWithModifiedPresupuestosBetween(@Param("inicio") Date inicio, @Param("fin") Date fin);*/
}
