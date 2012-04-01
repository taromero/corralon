package ar.com.seminario.corralon.inserciones;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.seminario.corralon.domain.Madera;
import ar.com.seminario.corralon.services.MaterialService;

public class PruebasInsignificante {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BasicConfigurator.configure();

		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"applicationContextHibernate.xml");

//		MaterialService mServcice = (MaterialService) appContext.getBean("materialService");
//		Material m = mServcice.findByDescripcion("Algarrobo 3*2");
//		System.out.println(m.getDisc());

		float largo = 10;
		
		//prueba obtener maderas adecuadas
		MaterialService mServcice = (MaterialService) appContext.getBean("materialService");
		List<Madera> maderas = mServcice.obtenerMaderasAdecuadas("Algarrobo 3*2", 52);
		for (Madera madera : maderas) {
			System.out.println(madera.getDescripcion() + '\t' +madera.getLargo() + '\t' + mServcice.obtenerStockMadera(madera.getDescripcion(), madera.getLargo()));	
		}
		
//		Madera maderaMadre;
//		Madera maderaHija;
				
		//prueba cortar madera
		System.out.println("Prueba para Cortar Maderas");
		Madera maderaMadre1 = maderas.get(0); 
		Madera maderaHija1 = null;
		if (maderaMadre1.getLargo() > largo) {
			maderaHija1 = mServcice.cortarMadera(maderaMadre1, 15);
			maderaHija1 = mServcice.cortarMadera(maderaMadre1, 10);
			maderaHija1 = mServcice.cortarMadera(maderaMadre1, 12);
			System.out.println(maderaHija1.getDescripcion()+"\t"+maderaHija1.getLargo());			
		}
		
		//prueba rearmar madera
//		System.out.println("Prueba para Rearmar Maderas");
//		maderaHija = mServcice.findByDescripcionYLargo("Algarrobo 3*2", 12);
//		maderaMadre = mServcice.rearmarMadera(maderaHija);
//		System.out.println(maderaMadre.getDescripcion()+"\t"+maderaMadre.getLargo());
		
		//prueba rearmar madera con madre en presupuesto
//		System.out.println("Prueba para Rearmar Maderas con Madres Presupuestadas");
//		maderaHija1 = (Madera)mServcice.findByID((long) 7);
//		maderaMadre1 = mServcice.rearmarMadera(maderaHija1);
//		System.out.println(maderaMadre1.getDescripcion()+"\t"+maderaMadre1.getLargo());
		
//		Madera maderaBuscar = (Madera) mServcice.findByDescripcionYLargo("Algarrobo 3*2", Float.parseFloat("5"));
//		System.out.println("MaderaMadre: "+maderaBuscar.getDescripcion());
//		if(!maderaBuscar.getMaderasHijas().isEmpty()){
//			for (Madera madera : maderaBuscar.getMaderasHijas()) {
//				System.out.println("MaderaHija: " + madera.getDescripcion()+"\t"+ madera.getLargo());	
//			}			
//		}
	}

}
