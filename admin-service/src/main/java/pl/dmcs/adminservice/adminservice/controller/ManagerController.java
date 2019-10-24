package pl.dmcs.adminservice.adminservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.adminservice.adminservice.model.dto.ManagerDto;
import pl.dmcs.adminservice.adminservice.exception.ManagerNotFoundException;
import pl.dmcs.adminservice.adminservice.model.Manager;
import pl.dmcs.adminservice.adminservice.model.dto.UpdateManagerDto;
import pl.dmcs.adminservice.adminservice.service.inf.ManagerService;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getManager(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(managerService.get(id));
        } catch (ManagerNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createManager(@RequestBody ManagerDto manager) {
        int id = managerService.save(manager);
        return ResponseEntity.ok(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateManager(@RequestBody UpdateManagerDto managerDto){
        try {
            int id = managerService.update(managerDto);
            return ResponseEntity.ok().build();
        } catch (ManagerNotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteManager(@PathVariable("id") int id)  {
        managerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllManagers() {
        return ResponseEntity.ok(managerService.getAll());
    }

    @RequestMapping(value = "/{buildingId}/{managerId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addBuildingToManager(@PathVariable("buildingId") int buildingId, @PathVariable("managerId") int managerId) {
        managerService.addBuildingToManager(buildingId, managerId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{buildingId}/{managerId}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteBuildingFromManager(@PathVariable("buildingId") int buildingId, @PathVariable("managerId") int managerId) {
        managerService.deleteBuildingFromManager(buildingId,managerId);
        return ResponseEntity.ok().build();
    }
}
