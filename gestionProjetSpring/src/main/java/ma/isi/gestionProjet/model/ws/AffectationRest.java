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
import ma.isi.gestionProjet.bean.Affectation;
import ma.isi.gestionProjet.bean.Collaborateur;
import ma.isi.gestionProjet.bean.Competence;
import ma.isi.gestionProjet.bean.CompetenceCollaborateur;
import ma.isi.gestionProjet.bean.Tache;
import ma.isi.gestionProjet.bean.Affectation;
import ma.isi.gestionProjet.model.service.AffectationService;
import ma.isi.gestionProjet.model.service.AffectationService;
 
import ma.isi.gestionProjet.model.ws.converter.AbstractConverter;
import ma.isi.gestionProjet.model.ws.vo.CollaborateurVo;
import ma.isi.gestionProjet.model.ws.vo.CompetenceCollaborateurVo;
import ma.isi.gestionProjet.model.ws.vo.AffectationVo;
import ma.isi.gestionProjet.model.ws.vo.TacheVo;

@RestController
@CrossOrigin( origins = {"http://localhost:4200"})
@RequestMapping("/manageproject/affectations")
@Api(description="gérer les affectation.")
public class AffectationRest {

 @Autowired
private AffectationService  affectationService  ;
 @Autowired
 @Qualifier("affectationConverter")
 private AbstractConverter<Affectation, AffectationVo> affectationConverter;
 @Autowired
 @Qualifier("tacheConverter")
 private AbstractConverter<Tache, TacheVo> tacheConverter;
 @Autowired
 @Qualifier("collaborateurConverter")
 private AbstractConverter<Collaborateur, CollaborateurVo> collaborateurConverter;
 
 @ApiOperation("Cette methode permet de sauvegarder une competenceCollaborateur")
 @PostMapping("/")
public int save(@RequestBody AffectationVo affectationVo) {
	  Affectation a= affectationConverter.toItem(affectationVo);
	return affectationService.save(a);
}
 @ApiOperation("Cette methode permet de supprimer une affectation avec le collaborateur et la tache assosiés")
 @DeleteMapping("/del/{id}")
public void delete(@PathVariable Long id) {
	affectationService.delete(id);
}
 
 @ApiOperation("Cette methode permet de supprimer une affectation avec le collaborateur et la tache assosiés")
 @GetMapping("/matricule/{matricule}")
public Affectation findAffectationByCollaborateurMatricule(@PathVariable String matricule) {
	return affectationService.findAffectationByCollaborateurMatricule(matricule);
}

 @ApiOperation("Cette methode permet de supprimer une affectation avec le collaborateur et la tache assosiés")
 @GetMapping("/intitule/{intitule}")
public Affectation findAffectationByTacheIntitule(@PathVariable String intitule) {
	return affectationService.findAffectationByTacheIntitule(intitule);
}


@ApiOperation("Cette methode permet de trouver une affectation à partir du titre de la tache et le matricule de son collaborateu ")
@GetMapping("/intitule/{intitule}/matricule/{matricule}")
public Affectation findByTacheIntituleAndCollaborateurMatricule(@PathVariable String intitule,@PathVariable String matricule) {
	return  affectationService.findByTacheIntituleAndCollaborateurMatricule(intitule, matricule);
}

@ApiOperation("Cette methode permet  une collection de tache a partir du matricule de son collaborateur affecté")
@GetMapping("/matriculetotache/{matricule}")
public Collection<Tache> findBycollaborateurMatricule(@PathVariable String matricule) {
	return affectationService.findByCollaborateurMatricule(matricule);
}

@ApiOperation("Cette methode permet  une collection de tache a partir du matricule de son collaborateur affecté")
@GetMapping("/intituletocollab/{intitule}")
public Collection<Collaborateur> findByTacheIntitule(@PathVariable String intitule) {
	return affectationService.findByTacheIntitule(intitule);
}
public AffectationService getAffectationService() {
	return affectationService;
}


public void setAffectationService(AffectationService affectationService) {
	this.affectationService = affectationService;
}


public AbstractConverter<Affectation, AffectationVo> getAffectationConverter() {
	return affectationConverter;
}


public void setAffectationConverter(
		AbstractConverter<Affectation, AffectationVo> affectationConverter) {
	this.affectationConverter = affectationConverter;
}


public AbstractConverter<Tache, TacheVo> getTacheConverter() {
	return tacheConverter;
}


public void setTacheConverter(AbstractConverter<Tache, TacheVo> tacheConverter) {
	this.tacheConverter = tacheConverter;
}


public AbstractConverter<Collaborateur, CollaborateurVo> getCollaborateurConverter() {
	return collaborateurConverter;
}


public void setCollaborateurConverter(AbstractConverter<Collaborateur, CollaborateurVo> collaborateurConverter) {
	this.collaborateurConverter = collaborateurConverter;
}
 
 
  


 

 
 
 
}
