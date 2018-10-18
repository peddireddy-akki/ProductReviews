package com.akki.buyer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.akki.buyer.model.Buyer;

@RestController
@RequestMapping("/api")
public class BuyerController {
	
	public static List<Buyer>  buyers;
	
	static {
		buyers = new ArrayList<Buyer>();
		Buyer buyer1 = new Buyer(Long.parseLong("1"), "Peddi Reddy", "Akki");
		Buyer buyer2 = new Buyer(Long.parseLong("2"), "Sowmya", "Akki");
		Buyer buyer3 = new Buyer(Long.parseLong("3"), "Geetika", "Akki");
		
		
		//System.out.println("\n\n\n\n\n BuyerID: "+ Long.parseLong("1"));
		
		buyers.add(buyer1);
		buyers.add(buyer2);
		buyers.add(buyer3);
	}
	@CrossOrigin(origins = "http://localhost:8100")
	@RequestMapping(value= "/buyer", method= RequestMethod.GET)
	public ResponseEntity<List<Buyer>> getBuyers(){
		if(buyers.isEmpty())
		{
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}		
		return new ResponseEntity<List<Buyer>>(buyers, HttpStatus.OK);		
	}

}
