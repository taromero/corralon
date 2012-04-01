import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("materiales.txt"));
			BufferedWriter writer = new BufferedWriter(new FileWriter("materialesSQL.sql"));
			BufferedWriter writerMatProv = new BufferedWriter(new FileWriter("material_proveedorSQL.sql"));
			String line = reader.readLine();
			String[] linea;
			Long limiteInfCodMad = new Long("620000000000");
			long limiteSupCodMad = new Long("630000000000");
			int cantidadMaterialesProveedores = 0;
			int cantidadMaterialesProveedoresMaderas = 0;
			int materialId = 1;
			while(line!=null){
				linea = line.split(",");
				line = new String();
				String lineMatProv = new String();
				String descripcion = linea[1] + " - " + linea[0];
				//linea[1].replace("\u00bd", "1/2");
				if(Float.parseFloat(linea[2]) > 0 && Long.parseLong(linea[0]) < new Long("999999999999")){
					if(Long.parseLong(linea[0]) >= limiteInfCodMad && Long.parseLong(linea[0]) < limiteSupCodMad){
						for(int i = (int) Math.round(Math.random()*10 + 1);i<=10;i++){
							line+= "insert into material (disc, precioventa, descripcion, enpresupuesto, unidad, largo, conservarmadre) values('MAD', "+linea[2]+",'"+linea[1]+" - "+linea[0]+"', false, 'Metros lineales', "+Math.round(Math.random()*20 + 1)+", true);\n";
							if(cantidadMaterialesProveedoresMaderas < 100){
								lineMatProv = agregarMaterialProveedor(linea,materialId);
								cantidadMaterialesProveedoresMaderas++;
							}
							materialId++;							
						}
					}else if(descripcion.contains("BOLSON")){
						line= "insert into material (disc, precioventa, descripcion, enpresupuesto, stock, unidad) values('UNI', "+linea[2]+",'"+linea[1]+" - "+linea[0]+"', false, "+Math.round(Math.random()*100)+", '40 KG');";
						lineMatProv = agregarMaterialProveedor(linea,materialId);
						materialId++;
					}else if(descripcion.contains(" M3 ")){
						line= "insert into material (disc, precioventa, descripcion, enpresupuesto, stock, unidad) values('UNI', "+linea[2]+",'"+linea[1]+" - "+linea[0]+"', false, "+Math.round(Math.random()*100)+", 'Metros cubicos');";
						lineMatProv = agregarMaterialProveedor(linea,materialId);
						materialId++;
					}else if(descripcion.contains("BOLSA")){
						line= "insert into material (disc, precioventa, descripcion, enpresupuesto, stock, unidad) values('UNI', "+linea[2]+",'"+linea[1]+" - "+linea[0]+"', false, "+Math.round(Math.random()*100)+", 'KG');";
						lineMatProv = agregarMaterialProveedor(linea,materialId);
						cantidadMaterialesProveedores++;
						materialId++;
					}else{
						line= "insert into material (disc, precioventa, descripcion, enpresupuesto, stock) values('NORM', "+linea[2]+",'"+linea[1]+" - "+linea[0]+"', false, "+Math.round(Math.random()*100)+");";
						if(cantidadMaterialesProveedores < 200){
							lineMatProv = agregarMaterialProveedor(linea,materialId);
							cantidadMaterialesProveedores++;
						}
						materialId++;
					}
					
					System.out.println(line);
					System.out.println(lineMatProv);
					System.out.println(materialId);
					writer.write(line);
					writerMatProv.write(lineMatProv);
					writer.newLine();
					writerMatProv.newLine();
				}
				line = reader.readLine();
			}	
            writer.close();
            writerMatProv.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		try {
//			BufferedWriter writer = new BufferedWriter(new FileWriter("material_proveedorSQL.txt"));
//			String line = new String();		
//			for (int i=1;i<10000;i++){
//			}
//			System.out.println(line);
//			writer.write(line);
//			writer.newLine();				
//			writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
	}

	private static String agregarMaterialProveedor(String[] linea, int materialId) {
		String lineMatProv = new String();
		
		if (Math.round(Math.random()*2 + 1)%2 == 0){
			lineMatProv+= "insert into material_proveedor (precio, id_proveedor, material_id_material) values("+Float.parseFloat(linea[2])*Float.parseFloat("1.25")+",1,"+materialId+");\n";
		}
		if (Math.round(Math.random()*2 + 1)%2 == 0){
			lineMatProv+= "insert into material_proveedor (precio, id_proveedor, material_id_material) values("+Float.parseFloat(linea[2])*Float.parseFloat("1.20")+",2,"+materialId+");\n";
		}
		if (Math.round(Math.random()*2 + 1)%2 == 0){
			lineMatProv+= "insert into material_proveedor (precio, id_proveedor, material_id_material) values("+Float.parseFloat(linea[2])*Float.parseFloat("1.30")+",3,"+materialId+");\n";
		}
		if(lineMatProv.isEmpty()){
			lineMatProv+= "insert into material_proveedor (precio, id_proveedor, material_id_material) values("+Float.parseFloat(linea[2])*Float.parseFloat("1.50")+",3,"+materialId+");\n";
		}
		
		return lineMatProv;
		
	}

}
