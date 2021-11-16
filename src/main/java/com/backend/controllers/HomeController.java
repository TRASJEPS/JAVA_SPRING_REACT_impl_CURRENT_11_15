package com.backend.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.backend.models.Image;
import com.backend.models.Medicine;
import com.backend.models.Patient;
import com.backend.services.ImageService;
import com.backend.services.MedicineService;
import com.backend.services.PatientService;
import com.backend.services.UserService;


@Controller
public class HomeController {

	// TAKEN FROM THE CRUD.pharmatrack
	
	@Autowired
	private UserService uService;
	@Autowired
	private PatientService pService;
	@Autowired
	private ImageService iService;
	@Autowired
	private MedicineService mService;

	
	private static String UPLOADED_FOLDER ="src/main/resources/static/images/";
	
	//Dash board 
	@GetMapping("/dashboard")
	// REMOVE Model viewModel
	public String home(HttpSession session) {
		if(session.getAttribute("user__id") == null) {
			return "redirect:/";
		}
		// viewModel.addAttribute("user", this.uService.findUser((Long)session.getAttribute("user__id")));
		// viewModel.addAttribute("allPatients", this.pService.getAllPatients());
		
		return uRepo.findAll(), uRepo.findUser(), pRepo.findAll();
		//return "/dashboard.jsp";
	}
	
	//Create Patient Page
	@GetMapping("/createPatient")
	public String newPatient(@ModelAttribute ("patient") Patient patient) {
		
		return pRepo.save(patient);
		//return "addPatient.jsp";
	}
	
	//Add Patient
	@PostMapping("/addPatient")
	public String addPatient(@Valid @ModelAttribute("patient")Patient patient, BindingResult result) {
		if(result.hasErrors()) {
			return"/addPatient.jsp";
		}
		this.pService.createPatient(patient);
		return "redirect:/dashboard";
	}
	
	//Edit Patient page
	@GetMapping("/EditPatient/{id}")
	public String editPatient(@PathVariable("id")Long id,@ModelAttribute("patient")Patient patient, Model viewModel) {
		viewModel.addAttribute("patient",this.pService.getOnePatient(id));
		return "/editPatient.jsp";
	}
	//Edit Patient
	@PostMapping("/editPatient/{id}")
	public String edit(@Valid @ModelAttribute("patient")Patient patient,@PathVariable("id")Long id, BindingResult result) {
		if(result.hasErrors()) {
			return"/addPatient.jsp";
		}
		this.pService.updatePatient(patient);
		return "redirect:/dashboard";
	}
	
	//Patient Details
	@GetMapping("/patientDetails/{id}")
	public String patientDetails(@PathVariable("id")Long id, @ModelAttribute("patient")Patient patient, Model viewModel) {
		viewModel.addAttribute("patient", this.pService.getOnePatient(id));
		return "patientDetails.jsp";
	}
	
	//Delete a Patient
	@GetMapping("/deletePatient/{id}")
	public String DestroyPatient(@PathVariable("id")Long id) {
		this.pService.deletePatient(id);
		return "redirect:/dashboard";
	}
	
// Now to the Medications
	//Create Medicine
	@GetMapping("/newMedicine")
	public String createMedicine(@ModelAttribute ("medicines") Medicine medicine, Model viewModel){
		viewModel.addAttribute("meds", this.mService.getAllMeds());
		return "createMeds.jsp";
	}

	//Post For Create Med
	@PostMapping("/addMedicaiton")
	public String newMed(@Valid @ModelAttribute("medicines")Medicine medicine, BindingResult result) {
		if(result.hasErrors()) {
			return "createMeds.jsp";
		}
		this.mService.createMed(medicine);
		return "redirect:/newMedicine";
	}
	//Edit Medicine
	@GetMapping("/editMed/{id}")
	public String editMed(@PathVariable("id")Long id, @ModelAttribute("meds")Medicine medicine,Model viewModel) {
		viewModel.addAttribute("meds", this.mService.getOneMed(id));
		return "editMed.jsp";
	}
	
	//Post Of Edit Med
	@PostMapping("/updateMed/{id}")
	public String update(@Valid @ModelAttribute("meds")Medicine medicine,@PathVariable("id")Long id, BindingResult result,Model viewModel) {
		if (result.hasErrors()) {
			viewModel.addAttribute("med", this.mService.getOneMed(id));
			return "/editMed.jsp";
		}
		this.mService.updateMed(medicine);
		return "redirect:updateMed/{id}";
	}
	
	//Image Uplload
	@PostMapping("/upload/{id}")
	public String upload(@RequestParam("pic") MultipartFile file, @PathVariable("id") Long id, RedirectAttributes redirectAttr) {
		Medicine medImage = this.mService.getOneMed(id);
		//Save File to static Folder
		if(file.isEmpty()) {
			redirectAttr.addFlashAttribute("message","Field cannot be empty!!!!");
			return "editMed.jsp";
		}
		try {
			
			//get the file and throw it into the db
			byte[] bytes = file.getBytes();
			
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			// Get URL of the uploaded file
			String url = "/images/" + file.getOriginalFilename();
			
			this.iService.uploadImage(url, medImage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/dashboard";
	}
	
	//Medication Details
	@GetMapping("/medDetails/{id}")
	public String medDetails(@PathVariable("id")Long id,@ModelAttribute("meds")Medicine medicine,@ModelAttribute("patient")Patient patient,@ModelAttribute("pic")Image image,Model viewModel) {
		viewModel.addAttribute("meds",this.mService.getOneMed(id));
		viewModel.addAttribute("patient", this.pService.getAllPatients());
		viewModel.addAttribute("allPics", this.iService.medImage(medicine));
		
		return "medDetails.jsp";
	}
	
	
	//Add Med to Patient
	@GetMapping("/addPatToMed/{id}")
	public String addPatToMed(@PathVariable("id")Long medid,@ModelAttribute("patient")Patient patient, @ModelAttribute("id")Long id) {
		Medicine MedId = this.mService.getOneMed(id);
		Medicine medToAdd = this.mService.getOneMed(medid);
		Patient patToAdd = this.pService.getOnePatient(id);
		
		this.mService.addMedToPatient(patToAdd, medToAdd);
		return "redirect:/medDetails/"+ MedId;
	}
	
	
	//Remove Med From Patient
//	@GetMapping("/remMedFromPat")
//	public String remMedFromPat(@PathVariable("id")Long medid,@ModelAttribute("patient")Patient patient, @ModelAttribute("id")Long id) 
	public ResponseEntity<HttpStatus> remMedFromPat(long id)
	{
	//	mService 
	// WORK IN PROGRESS !	
		
//		Medicine medToAdd = this.mService.getOneMed(medid);
//		Patient patToAdd = this.pService.getOnePatient(id);
//		
//		this.mService.removeMedFromPatient(patToAdd, medToAdd);
//		return "redirect:dashboard";
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
