package ma.isi.gestionProjet.model.ws;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.isi.gestionProjet.bean.Collaborateur;
import ma.isi.gestionProjet.model.service.CollaborateurService;


@RestController
@CrossOrigin( origins = {"http://localhost:4200"})
@RequestMapping("/manageproject/collaborateurs")
@Api(description="gérer les collaborateurs.")
public class CollaborateurRest {

 @Autowired
private CollaborateurService  collaborateurService  ;

@ApiOperation("Cette methode permet de sauvegarder un collaborateur")
 @PostMapping("/")
 public int save(@RequestBody Collaborateur collaborateur) {
	return collaborateurService.save(collaborateur);
    }
 
@ApiOperation("Cette methode permet de mettre à jour un collaborateur")
 @PostMapping("/up")
 public int update(@RequestBody Collaborateur collaborateur) {
     return collaborateurService.update(collaborateur);
 }

@ApiOperation("Cette methode permet de supprimer un collaborateur ")
 @DeleteMapping("/del/{matricule}")
public int delete(@PathVariable  String matricule) {
	return collaborateurService.delete(matricule);
}

@ApiOperation("Cette methode permet de trouver un collaborateur à partir du matricule")
 @GetMapping("/matricule/{matricule}")
public Collaborateur findByMatricule(@PathVariable  String matricule) {
	return collaborateurService.findByMatricule(matricule);
    }

@ApiOperation("Cette methode permet de trouver un collaborateur ")
@GetMapping("/nom/{infix}")
public Collection<Collaborateur> findByNomContaining(String infix) {
	return collaborateurService.findByNomContaining(infix);
}

@ApiOperation("Cette methode permet de trouver un collaborateur à partir du nom & prenom")
 @GetMapping("/nom/{nom}/prenom/{prenom}")
 public Collaborateur findByNomAndPrenom(@PathVariable String nom,@PathVariable String prenom) {
		return collaborateurService.findByNomAndPrenom(nom, prenom);
	}


@ApiOperation("Cette methode permet de trouver ts les collaborateurs ")
 @GetMapping("/")
 public Collection<Collaborateur> findAll() {
	return collaborateurService.findAll();
   }

@ApiOperation("Cette methode permet de trouver ts les collaborateurs disponible")
@GetMapping("/CollabsDisp")
public Collection<Collaborateur> findByEstdisponible() 
{
	return collaborateurService.findByEstdisponible();
}
public CollaborateurService getCollaborateurService() {
	return collaborateurService;
}

public void setCollaborateurService(CollaborateurService collaborateurService) {
	this.collaborateurService = collaborateurService;
}

 
 
 
}
