package com.akki.buyer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.akki.buyer.model.Address;
import com.akki.buyer.model.Buyer;

@RestController
@RequestMapping("/api")
public class BuyerController {

	public static List<Buyer> buyers;

	static {
		buyers = new ArrayList<Buyer>();
		Buyer buyer1 = new Buyer((long)1, "Peddi Reddy", "Akki");
		Buyer buyer2 = new Buyer((long)2, "Sowmya", "Akki");
		Buyer buyer3 = new Buyer((long)3, "Geetika", "Akki");

		Address address = new Address((long)1, "A202, BM Reveira", "Kundalahalli", "Bangalore", "KA",
				"India", "560037");

		buyer1.addAddress(address);
		buyer2.addAddress(address);
		buyer3.addAddress(address);

		buyers.add(buyer1);
		buyers.add(buyer2);
		buyers.add(buyer3);
	}

	@CrossOrigin(origins = "http://localhost:8100")
	@RequestMapping(value = "/buyer", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Buyer>> getBuyers() {
		if (buyers.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Buyer>>(buyers, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:8100")
	@RequestMapping(value = "/buyer/{buyerId}", method = RequestMethod.GET)
	public ResponseEntity<Buyer> getBuyerDetailsById(@PathVariable long buyerId) {
		System.out.println("Get Buyer Details By ID is called");
		
		for (Buyer buyer : buyers) {
			if (buyer.getBuyerID()==buyerId) {
				return new ResponseEntity<Buyer>(buyer, HttpStatus.OK);
			}
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
