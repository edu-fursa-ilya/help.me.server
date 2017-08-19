package com.furs.api.controllers;

import com.furs.api.entities.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.furs.api.repositories.PlaceRepository;
import com.furs.api.vars.ApiConstants;


@RestController
@RequestMapping("/help.me/api")
public class HelpApiController {

    @Autowired
    private PlaceRepository repository;

    @GetMapping(path = "/add")
    public @ResponseBody String add(@RequestParam String title,
                                    @RequestParam String type,
                                    @RequestParam double lat,
                                    @RequestParam double lng,
                                    @RequestParam String address,
                                    @RequestParam String phone) {

        repository.save(new Place(title, type, lat, lng, address, phone));
        return ApiConstants.STATUS_OK;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Place> getAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/del")
    public @ResponseBody String remove(@RequestParam long id) {
        Place place = repository.findOne(id);
        repository.delete(place);
        return ApiConstants.STATUS_REMOVED;
    }
}
