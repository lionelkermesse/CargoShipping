/** 
 *   CargoShipping Application.
 *
 *   Copyright 2011 Harmonic-Pharma
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   See http://www.harmonic-pharma.com/ for more information 
 *   about this app.
 */
package resources;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Cargo;
import domain.Port;
import domain.Ship;
import dto.ShipReport;

/**
 * @author Nelson Lionel KEMINSE
 * 10 aout 2011 08:03:08
 */
public class ShipMappingtoDTOTest {
	private static DozerBeanMapper mapper;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Definition des fichiers de mapping
		List<String> mappingFiles = new ArrayList<String>();
		mappingFiles.add("resources/mappings.xml");
		
		// Declaration des fichiers de mapping
		mapper = (DozerBeanMapper) DozerBeanMapperSingletonWrapper.getInstance();
		mapper.setMappingFiles(mappingFiles);
	}

	@Test
	public final void testShipMappingToShipDTO() {
		Port port = new Port("Douala", "Cameroon");
		Cargo cargo = new Cargo("Bindi");
		Ship shipSource = new Ship("ID2011", "Baba", cargo, port);
		
		// Recopie partielle de l'objet shipSource vers un objet shipDestination
		ShipReport shipDestination = (ShipReport) mapper.map(shipSource, ShipReport.class);
		
		// On verifie que la recopie a ete faite correctement en comparant les attributs un a un
		assertEquals("ID2011", shipDestination.getId());
		assertEquals("Baba", shipDestination.getName());
		assertEquals("Bindi", shipDestination.getCargoName());
		assertEquals("Douala", shipDestination.getPortCity());
		assertEquals("Cameroon", shipDestination.getPortCountry());
	}

}
