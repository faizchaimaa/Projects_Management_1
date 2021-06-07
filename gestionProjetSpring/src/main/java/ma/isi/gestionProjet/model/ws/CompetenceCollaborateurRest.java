package ma.isi.gestionProjet.model.ws;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import ma.isi.gestionProjet.bean.Competence;
import ma.isi.gestionProjet.bean.CompetenceCollaborateur;
import ma.isi.gestionProjet.model.service.CompetenceCollaborateurService;
 
import ma.isi.gestionProjet.model.ws.converter.AbstractConverter;
import ma.isi.gestionProjet.model.ws.vo.CollaborateurVo;
import ma.isi.gestionProjet.model.ws.vo.CompetenceCollaborateurVo;
import ma.isi.gestionProjet.model.ws.vo.CompetenceVo;

@RestController
@CrossOrigin( origins = {"http://localhost:4200"})
@RequestMapping("/manageproject/competencecollaborateurs")
@Api(description="gérer les competenceCollaborateur.")
public class CompetenceCollaborateurRest {

 @Autowired
private CompetenceCollaborateurService  competenceCollaborateurService  ;
 @Autowired
 @Qualifier("competenceCollaborateurConverter")
 private AbstractConverter<CompetenceCollaborateur, CompetenceCollaborateurVo> competenceCollaborateurConverter;
 @Autowired
 @Qualifier("competenceConverter")
 private AbstractConverter<Competence, CompetenceVo> competenceConverter;
 @Autowired
 @Qualifier("collaborateurConverter")
 private AbstractConverter<Collaborateur, CollaborateurVo> collaborateurConverter;
 
 

 @ApiOperation("Cette methode permet de sauvegarder une competenceCollaborateur")
 @PostMapping("/")
public int save(@RequestBody CompetenceCollaborateurVo competenceCollaborateurVo) {
	 CompetenceCollaborateur cc=competenceCollaborateurConverter.toItem(competenceCollaborateurVo); 
	return competenceCollaborateurService.save(cc);
}
 
 @ApiOperation("Cette methode permet de supprimer une competenceCollaborateur avec le collaborateur et la competence assosiés")
 @DeleteMapping("/del/{id}")
public void delete(@PathVariable Long id) {
	competenceCollaborateurService.delete(id);
}
 
 @ApiOperation("Cette methode permet de trouver une competenceCollaborateur a partir de son identifiant")
 @GetMapping("/id/{id}")
 public CompetenceCollaborateurVo  findById(@PathVariable Long id) {
     return  competenceCollaborateurConverter.toVo(competenceCollaborateurService.findById(id));
 }
 
 @ApiOperation("Cette methode permet  une collection competenceCollaborateur a partir du matricule de son collaborateur")
 @GetMapping("/matricule/{matricule}")
public Collection<Competence> findBycollaborateurMatricule(@PathVariable String matricule) {
	return competenceCollaborateurService.findByCollaborateurMatricule(matricule);
}

 @ApiOperation("Cette methode permet de trouver collection une competenceCollaborateur à partir du titre de la competence")
 @GetMapping("/titre/{titre}")
public Collection<Collaborateur> findByCompetenceTitre(@PathVariable String titre) {
	return competenceCollaborateurService.findByCompetenceTitre(titre);
}
 @ApiOperation("Cette methode permet de trouver une competenceCollaborateur à partir du titre de la competence ")
@GetMapping("/titre0/{titre}")
public CompetenceCollaborateur findCompetenceCollaborateurByCompetenceTitre(@PathVariable String titre) {
	return competenceCollaborateurService.findCompetenceCollaborateurByCompetenceTitre(titre);
}
 
 @ApiOperation("Cette methode permet de trouver une competenceCollaborateur a partir du matricule de son collaborateur")
 @GetMapping("/matricule0/{matricule}")
 public CompetenceCollaborateur findCompetenceCollaborateurByCollaborateurMatricule(String matricule) {
	return competenceCollaborateurService.findCompetenceCollaborateurByCollaborateurMatricule(matricule);
}
 
@ApiOperation("Cette methode permet de trouver une competenceCollaborateur à partir du titre de la competence et le matricule de son collaborateu ")
@GetMapping("/titre/{titre}/matricule/{matricule}")
public CompetenceCollaborateur findByCompetenceTitreAndCollaborateurMatricule(@PathVariable String titre,@PathVariable String matricule) {
	return competenceCollaborateurService.findByCompetenceTitreAndCollaborateurMatricule(titre, matricule);
}

public CompetenceCollaborateurService getCompetenceCollaborateurService() {
	return competenceCollaborateurService;
}


public void setCompetenceCollaborateurService(CompetenceCollaborateurService competenceCollaborateurService) {
	this.competenceCollaborateurService = competenceCollaborateurService;
}


public AbstractConverter<CompetenceCollaborateur, CompetenceCollaborateurVo> getCompetenceCollaborateurConverter() {
	return competenceCollaborateurConverter;
}


public void setCompetenceCollaborateurConverter(
		AbstractConverter<CompetenceCollaborateur, CompetenceCollaborateurVo> competenceCollaborateurConverter) {
	this.competenceCollaborateurConverter = competenceCollaborateurConverter;
}


public AbstractConverter<Competence, CompetenceVo> getCompetenceConverter() {
	return competenceConverter;
}


public void setCompetenceConverter(AbstractConverter<Competence, CompetenceVo> competenceConverter) {
	this.competenceConverter = competenceConverter;
}


public AbstractConverter<Collaborateur, CollaborateurVo> getCollaborateurConverter() {
	return collaborateurConverter;
}


public void setCollaborateurConverter(AbstractConverter<Collaborateur, CollaborateurVo> collaborateurConverter) {
	this.collaborateurConverter = collaborateurConverter;
}
 
 
  


 

 
 
 
}
