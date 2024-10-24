package com.cjc.cibilrandom.app.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/random")
public class RandomController 
{
	private final static Random r = new Random();

   @GetMapping("/checkCibilScore")
   public int create()
   {
	   int i = r.nextInt(300,900);
	   System.out.println(i);  
	return i;
	   
   }
}
