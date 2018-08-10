package py.sgarrhh.controller;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.DocumentException;

import py.sgarrhh.dto.Raffle;
import py.sgarrhh.models.Contrato;
import py.sgarrhh.models.Persona;
import py.sgarrhh.repository.ContratoRepository;
import py.sgarrhh.repository.PersonaRepository;
import py.sgarrhh.service.PdfGenarator;


@Controller
public class RaffleController {

	@Autowired
	private PdfGenarator pdfGenarator;
	
	@Autowired
	private PersonaRepository pr;
	@Autowired
	private ContratoRepository ctr;
	private String templateName = "templatePDF.html";

	private String fileName = "raffle.pdf";

	@RequestMapping("/printPersonas")
	public ModelAndView listaPersonas() {

		ModelAndView mv= new ModelAndView("persona/printPersonas");
		Iterable <Contrato> contratos= ctr.findAll();
		mv.addObject("contratos",contratos);
		return mv;
	}
	
	
	/*@GetMapping("/raffle")
	public String raffleForm(final Model model) {
		model.addAttribute("raffle", new Raffle());
		return "raffle";
	}

	@PostMapping("/raffle")
	public String raffleSubmit(@ModelAttribute final Raffle raffle) {

		String candidates = raffle.getCandidates();
		List<String> winners = doRaffle(asList(candidates));
		raffle.setWinners(winners);
		return "result";
	}
*/
	@RequestMapping(value="/personaPDF", method=RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> personaPDF(@ModelAttribute final Persona persona, final HttpServletRequest request,
			final HttpServletResponse response) throws DocumentException {
		
		Iterable <Persona> personas= pr.findAll();

		Map<String, Object> mapParameter = new HashMap<String, Object>();
	//	mapParameter.put("name", "Softtekiano");
		mapParameter.put("personas", personas);

		ByteArrayOutputStream byteArrayOutputStreamPDF = pdfGenarator.createPdf(templateName, mapParameter, request, response);
		ByteArrayResource inputStreamResourcePDF = new ByteArrayResource(byteArrayOutputStreamPDF.toByteArray());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName).contentType(MediaType.APPLICATION_PDF)
				.contentLength(inputStreamResourcePDF.contentLength()).body(inputStreamResourcePDF);

	}

	/*private List<String> doRaffle(final List<String> candidates) {

		Random random = new Random();
		List<String> winners = new ArrayList<String>();

		int winnerSize = new HashSet<String>(candidates).size(); // HashSet elimina duplicados
		while (winnerSize > 0) {
			String nextWinner = candidates.get(random.nextInt(candidates.size()));
			while (winners.contains(nextWinner)) {
				nextWinner = candidates.get(random.nextInt(candidates.size()));
			}
			winners.add(nextWinner);
			winnerSize--;
		}
		return winners;
	}

	private List<String> asList(final String candidates) {

		List<String> candidatesAsList = new ArrayList<String>();
		for (String line : candidates.split("\\n")) {

			String[] split = line.split(",");
			String name = split[0].trim();
			int tickets = Integer.valueOf(split[1].trim());

			while (tickets > 0) {
				candidatesAsList.add(name);
				tickets--;
			}
		}
		return candidatesAsList;
	}*/

}
