package com.apap.tutorial4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.service.CarService;
import com.apap.tutorial4.service.DealerService;

/**
 * CarController
 * @author Raihan Mahendra
 *
 */
@Controller
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "dealerId") Long dealerId, Model model) {
		CarModel car = new CarModel();
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		car.setDealer(dealer);
		
		model.addAttribute("car", car);
		return "addCar";
	}
	
	@RequestMapping(value = "/car/add", method = RequestMethod.POST)
	private String addCarSubmit(@ModelAttribute CarModel car) {
		carService.addCar(car);
		return "add";
	}
	
	@RequestMapping(value = "/car/delete/{carId}", method = RequestMethod.GET)
	private String deleteCar(@PathVariable(value = "carId") Long carId) {
		carService.deleteCar(carService.getCarDetailById(carId).get());
		return "delete";
	}
	
	@RequestMapping(value = "/car/update/{carId}", method = RequestMethod.GET)
	private String update(@PathVariable(value = "carId") Long carId, Model model) {
		model.addAttribute("oldCar", carService.getCarDetailById(carId).get());
		model.addAttribute("newCar", new CarModel());
		return "updateCar";
	}
	
	@RequestMapping(value = "/car/update/{carId}", method = RequestMethod.POST)
	private String updateCarSubmit(@PathVariable(value = "carId") Long carId, @ModelAttribute CarModel newCar) {
		carService.updateCar(carId, newCar);
		return "update";
	}
	
}
